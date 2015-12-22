# Camel Cassandra Component Servicemix Example

This is a simple example of a Camel route based on the Camel-cassandra component (https://github.com/oscerd/camel-cassandra) I've developed.
This route will be deployed on Apache Servicemix.

# Setting up the environment and deployment

To run this example you need to follow these instructions.

# Setting up Apache-Cassandra

- Install Apache Cassandra from http://cassandra.apache.org/download/ . In my configuration I've used Apache Cassandra version 2.2.4.

- Run Apache Cassandra by executing 

```shell

$CASSANDRA_HOME/bin/cassandra

```

- Run CQL console by executing 

```shell

$CASSANDRA_HOME/bin/cqlsh

```

- In CQL console run the following command (populate.cql is a cql file stored in `src/main/resources/cql/`)

```shell

SOURCE 'populate.cql'

```

# Setting up Servicemix

Camel-Cassandra component is based on camel-core 2.16.1 release. So we need to use an Apache Servicemix version based on this release.

The Apache Servicemix 6.1.0 is the correct release.

- Download the Apache Servicemix 6.1.0 package from: http://servicemix.apache.org/downloads/servicemix-6.1.0.html

- Unzip the package in a directory (we denote this folder with $SERVICEMIX_HOME)

- Execute $SERVICEMIX_HOME/bin/servicemix

- Inside Karaf execute the following instructions:

```shell

karaf@root> features:repo-add mvn:com.github.oscerd/camel-cassandra/1.4.0/xml/features


```

- __Install camel-cassandra feature__

```shell

karaf@root> feature:install camel-cassandra

```

# Deployment on Servicemix

- Clone the project this documentation refers to: https://github.com/oscerd/camel-cassandra-servicemix-example

```shell

git clone https://github.com/oscerd/camel-cassandra-servicemix-example.git

```

- Enter in the project directory and execute the following command:

```shell

mvn clean package

```

- Turn back to Servicemix console and executes this instruction:

```shell

karaf@root> bundle:install -s file:///$PROJECT_HOME/target/camel-cassandra-servicemix-example-1.0.0-SNAPSHOT.jar

```

We can also verify everything is ok by looking at the log. Type the following command inside karaf:

```shell

karaf@root> log:tail

```

You should see something like this:

```shell

2015-12-22 17:27:59,972 | INFO  | l for user karaf | ultOsgiApplicationContextCreator | 166 - org.springframework.osgi.extender - 1.2.1 | Discovered configurations {osgibundle:/META-INF/spring/*.xml} in bundle [Camel Cassandra Servicemix Route Example (com.github.oscerd.camel-cassandra-servicemix-example)]
2015-12-22 17:27:59,976 | INFO  | ExtenderThread-1 | OsgiBundleXmlApplicationContext  | 161 - org.apache.servicemix.bundles.spring-context - 3.2.14.RELEASE_1 | Refreshing OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml): startup date [Tue Dec 22 17:27:59 CET 2015]; root of context hierarchy
2015-12-22 17:27:59,977 | INFO  | ExtenderThread-1 | OsgiBundleXmlApplicationContext  | 161 - org.apache.servicemix.bundles.spring-context - 3.2.14.RELEASE_1 | Application Context service already unpublished
2015-12-22 17:27:59,979 | INFO  | ExtenderThread-1 | XmlBeanDefinitionReader          | 159 - org.apache.servicemix.bundles.spring-beans - 3.2.14.RELEASE_1 | Loading XML bean definitions from URL [bundle://249.0:0/META-INF/spring/camel-context.xml]
2015-12-22 17:28:00,047 | INFO  | ExtenderThread-1 | CamelNamespaceHandler            | 203 - org.apache.camel.camel-spring - 2.16.1 | OSGi environment detected.
2015-12-22 17:28:00,475 | INFO  | ExtenderThread-1 | WaiterApplicationContextExecutor | 166 - org.springframework.osgi.extender - 1.2.1 | No outstanding OSGi service dependencies, completing initialization for OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml)
2015-12-22 17:28:00,481 | INFO  | ExtenderThread-2 | DefaultListableBeanFactory       | 159 - org.apache.servicemix.bundles.spring-beans - 3.2.14.RELEASE_1 | Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@6207ecbb: defining beans [template,consumerTemplate,camel1:beanPostProcessor,camel1,camelCassandraBuilder]; root of factory hierarchy
2015-12-22 17:28:00,653 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 199 - org.apache.camel.camel-core - 2.16.1 | Apache Camel 2.16.1 (CamelContext: camel1) is starting
2015-12-22 17:28:00,654 | INFO  | ExtenderThread-2 | ManagedManagementStrategy        | 199 - org.apache.camel.camel-core - 2.16.1 | JMX is enabled
2015-12-22 17:28:00,727 | WARN  | ExtenderThread-2 | DefaultTypeConverter             | 199 - org.apache.camel.camel-core - 2.16.1 | Overriding type converter from: StaticMethodTypeConverter: public static org.apache.activemq.command.ActiveMQDestination org.apache.activemq.camel.converter.ActiveMQConverter.toDestination(java.lang.String) to: StaticMethodTypeConverter: public static org.apache.activemq.command.ActiveMQDestination org.apache.activemq.camel.converter.ActiveMQConverter.toDestination(java.lang.String)
2015-12-22 17:28:00,728 | WARN  | ExtenderThread-2 | DefaultTypeConverter             | 199 - org.apache.camel.camel-core - 2.16.1 | Overriding type converter from: InstanceMethodTypeConverter: public org.apache.camel.Processor org.apache.activemq.camel.converter.ActiveMQMessageConverter.toProcessor(javax.jms.MessageListener) to: InstanceMethodTypeConverter: public org.apache.camel.Processor org.apache.activemq.camel.converter.ActiveMQMessageConverter.toProcessor(javax.jms.MessageListener)
2015-12-22 17:28:00,728 | WARN  | ExtenderThread-2 | DefaultTypeConverter             | 199 - org.apache.camel.camel-core - 2.16.1 | Overriding type converter from: InstanceMethodTypeConverter: public org.apache.activemq.command.ActiveMQMessage org.apache.activemq.camel.converter.ActiveMQMessageConverter.toMessage(org.apache.camel.Exchange) throws javax.jms.JMSException to: InstanceMethodTypeConverter: public org.apache.activemq.command.ActiveMQMessage org.apache.activemq.camel.converter.ActiveMQMessageConverter.toMessage(org.apache.camel.Exchange) throws javax.jms.JMSException
2015-12-22 17:28:00,750 | INFO  | ExtenderThread-2 | DefaultRuntimeEndpointRegistry   | 199 - org.apache.camel.camel-core - 2.16.1 | Runtime endpoint registry is in extended mode gathering usage statistics of all incoming and outgoing endpoints (cache limit: 1000)
2015-12-22 17:28:00,824 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 199 - org.apache.camel.camel-core - 2.16.1 | AllowUseOriginalMessage is enabled. If access to the original message is not needed, then its recommended to turn this option off as it may improve performance.
2015-12-22 17:28:00,825 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 199 - org.apache.camel.camel-core - 2.16.1 | StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2015-12-22 17:28:00,880 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 199 - org.apache.camel.camel-core - 2.16.1 | Route: route1 started and consuming from: Endpoint[timer://timer?fixedRate=true&period=10000&repeatCount=1]
2015-12-22 17:28:00,881 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 199 - org.apache.camel.camel-core - 2.16.1 | Total 1 routes, of which 1 is started.
2015-12-22 17:28:00,882 | INFO  | ExtenderThread-2 | OsgiSpringCamelContext           | 199 - org.apache.camel.camel-core - 2.16.1 | Apache Camel 2.16.1 (CamelContext: camel1) started in 0.228 seconds
2015-12-22 17:28:00,884 | INFO  | ExtenderThread-2 | OsgiBundleXmlApplicationContext  | 161 - org.apache.servicemix.bundles.spring-context - 3.2.14.RELEASE_1 | Publishing application context as OSGi service with properties {org.springframework.context.service.name=com.github.oscerd.camel-cassandra-servicemix-example, Bundle-SymbolicName=com.github.oscerd.camel-cassandra-servicemix-example, Bundle-Version=1.0.0.SNAPSHOT}
2015-12-22 17:28:00,890 | INFO  | ExtenderThread-2 | ContextLoaderListener            | 166 - org.springframework.osgi.extender - 1.2.1 | Application context successfully refreshed (OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml))
2015-12-22 17:28:01,891 | INFO  |  - timer://timer | Querying Cassandra               | 199 - org.apache.camel.camel-core - 2.16.1 | Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
2015-12-22 17:28:01,934 | INFO  |  - timer://timer | NettyUtil                        | 247 - com.datastax.driver.core - 2.1.9 | Did not find Netty's native epoll transport in the classpath, defaulting to NIO.
2015-12-22 17:28:02,128 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:28:02,128 | INFO  |  - timer://timer | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:28:04,377 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 199 - org.apache.camel.camel-core - 2.16.1 | Id: f501ead0-a8c7-11e5-b479-c98a9e63c0e2 - Album: Undertow - Title: Sober
2015-12-22 17:28:04,377 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 199 - org.apache.camel.camel-core - 2.16.1 | Id: f5010070-a8c7-11e5-b479-c98a9e63c0e2 - Album: Undertow - Title: Intolerance
2015-12-22 17:28:04,377 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 199 - org.apache.camel.camel-core - 2.16.1 | Id: f50175a0-a8c7-11e5-b479-c98a9e63c0e2 - Album: Undertow - Title: Prison Sex
2015-12-22 17:28:04,378 | INFO  |  - timer://timer | Inserting the object             | 199 - org.apache.camel.camel-core - 2.16.1 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: ]
2015-12-22 17:28:04,428 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:28:04,428 | INFO  |  - timer://timer | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:28:06,647 | INFO  |  - timer://timer | Querying Cassandra               | 199 - org.apache.camel.camel-core - 2.16.1 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: ]
2015-12-22 17:28:06,693 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 247 - com.datastax.driver.core - 2.1.9 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-12-22 17:28:06,694 | INFO  |  - timer://timer | Cluster                          | 247 - com.datastax.driver.core - 2.1.9 | New Cassandra host /127.0.0.1:9042 added
2015-12-22 17:28:08,911 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 199 - org.apache.camel.camel-core - 2.16.1 | Id: f501ead0-a8c7-11e5-b479-c98a9e63c0e2 - Album: Undertow - Title: Sober
2015-12-22 17:28:08,911 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 199 - org.apache.camel.camel-core - 2.16.1 | Id: f5010070-a8c7-11e5-b479-c98a9e63c0e2 - Album: Undertow - Title: Intolerance
2015-12-22 17:28:08,911 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 199 - org.apache.camel.camel-core - 2.16.1 | Id: f50175a0-a8c7-11e5-b479-c98a9e63c0e2 - Album: Undertow - Title: Prison Sex
2015-12-22 17:28:08,912 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 199 - org.apache.camel.camel-core - 2.16.1 | Id: ce595417-18bc-4491-89c0-db6584bd356e - Album: Undertow - Title: Bottom

```

# The route

This route executes a select all on songs table in the simplex keyspace (only 3 songs are stored in the songs table at this point), then it executes an insert of one new song and then it executes another select all on the same table of same keyspace logging the result.
