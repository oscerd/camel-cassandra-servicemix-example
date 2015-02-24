package com.github.oscerd.camel.cassandra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.github.oscerd.component.cassandra.CassandraConstants;

/**
 * Usage example of Camel-cassandra Component
 */
public class CamelCassandraRouteBuilder extends RouteBuilder {
    
	@Override
    public void configure() {

    	String addr = "127.0.0.1";
    	List<String> collAddr = new ArrayList<String>();
    	collAddr.add(addr);
        Set<String> tags = new HashSet<String>();
        tags.add("1993");
        tags.add("Alternative Metal");
        HashMap<String, Object> insert = new HashMap<String, Object>();
        insert.put("id", UUID.randomUUID());
        insert.put("album", "Undertow");
        insert.put("title", "Bottom");
        insert.put("artist", "Tool");
        insert.put("tags", tags);
    	
        from ("timer://timer?fixedRate=true&period=10000&repeatCount=1")
        .setHeader(CassandraConstants.CASSANDRA_CONTACT_POINTS, constant(collAddr))
        .to("log:Querying Cassandra")
        .to("cassandra:cassandraConnection?keyspace=simplex&table=songs&operation=selectAll")
        .process(new Processor() {
			
			@Override
			public void process(Exchange ex) throws Exception {
				ResultSet resultSet = (ResultSet) ex.getIn().getBody();
				for (Row row : (ResultSet) resultSet) {
					log.info("Id: " + row.getUUID("id") + " - Album: " + row.getString("album") + " - Title: " + row.getString("title")); 
		        }
			}
		
		})
		.setBody(constant(""))
		.setHeader(CassandraConstants.CASSANDRA_INSERT_OBJECT, constant(insert))
		.to("log:Inserting the object")
		.to("cassandra:cassandraConnection?keyspace=simplex&table=songs&operation=insert")
		.removeHeader(CassandraConstants.CASSANDRA_INSERT_OBJECT)
		.setBody(constant(""))
		.to("log:Querying Cassandra")
		.to("cassandra:cassandraConnection?keyspace=simplex&table=songs&operation=selectAll")
        .process(new Processor() {
			
			@Override
			public void process(Exchange ex) throws Exception {
				ResultSet resultSet = (ResultSet) ex.getIn().getBody();
				for (Row row : (ResultSet) resultSet) {
					log.info("Id: " + row.getUUID("id") + " - Album: " + row.getString("album") + " - Title: " + row.getString("title")); 
		        }
				
			}
		});
    }

}
