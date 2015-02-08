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
import org.apache.camel.main.Main;

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
    	
        from ("timer://timer?fixedRate=true&period=10000&repeatCount=1")
        .setHeader(CassandraConstants.CASSANDRA_CONTACT_POINTS, constant(collAddr))
        .to("cassandra:cassandraConnection?keyspace=simplex&table=songs&operation=selectAll")
        .process(new Processor() {
			
			@Override
			public void process(Exchange ex) throws Exception {
				ResultSet resultSet = (ResultSet) ex.getIn().getBody();
				StringBuilder stringBuilder = new StringBuilder();
				for (Row row : (ResultSet) resultSet) {
					log.info("Id: " + row.getUUID("id") + " - Album: " + row.getString("album") + " - Title: " + row.getString("title")); 
		        }
			}
		
		})
		.stop();
    }

}
