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

Camel-Cassandra component is based on camel-core 2.15.2 release. So we need to use an Apache Servicemix version based on this release.

The Apache Servicemix 5.5.0 is the correct release.

- Download the Apache Servicemix 5.5.0 package from: http://servicemix.apache.org/downloads/servicemix-5.5.0.html

- Unzip the package in a directory (we denote this folder with $SERVICEMIX_HOME)

- Execute $SERVICEMIX_HOME/bin/servicemix

- Inside Karaf execute the following instructions:

```shell

karaf@root> features:addurl mvn:com.github.oscerd/camel-cassandra/1.3.0/xml/features


```

- __Install camel-cassandra feature__

```shell

karaf@root> feature:install camel-cassandra


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

karaf@root> osgi:install -s file:///$PROJECT_HOME/target/camel-cassandra-servicemix-example-1.0.0-SNAPSHOT.jar

```

We can also verify everything is ok by looking at the log. Type the following command inside karaf:

```shell

karaf@root> log:tail

```

You should see something like this:

```shell

2015-10-11 09:37:22,918 | INFO  | l Console Thread | ultOsgiApplicationContextCreator | 84 - org.springframework.osgi.extender - 1.2.1 | Discovered configurations {osgibundle:/META-INF/spring/*.xml} in bundle [Camel Cassandra Servicemix Route Example (com.github.oscerd.camel-cassandra-servicemix-example)]
2015-10-11 09:37:22,919 | INFO  | ExtenderThread-3 | OsgiBundleXmlApplicationContext  | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Refreshing OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml): startup date [Sun Oct 11 09:37:22 CEST 2015]; root of context hierarchy
2015-10-11 09:37:22,921 | INFO  | ExtenderThread-3 | OsgiBundleXmlApplicationContext  | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Application Context service already unpublished
2015-10-11 09:37:22,923 | INFO  | ExtenderThread-3 | XmlBeanDefinitionReader          | 77 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Loading XML bean definitions from URL [bundle://283.0:0/META-INF/spring/camel-context.xml]
2015-10-11 09:37:22,992 | INFO  | ExtenderThread-3 | WaiterApplicationContextExecutor | 84 - org.springframework.osgi.extender - 1.2.1 | No outstanding OSGi service dependencies, completing initialization for OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml)
2015-10-11 09:37:22,994 | INFO  | ExtenderThread-4 | DefaultListableBeanFactory       | 77 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@449ecd21: defining beans [camel1:beanPostProcessor,camel1,camelCassandraBuilder]; root of factory hierarchy
2015-10-11 09:37:23,009 | INFO  | ExtenderThread-4 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | Apache Camel 2.15.2 (CamelContext: camel1) is starting
2015-10-11 09:37:23,009 | INFO  | ExtenderThread-4 | ManagedManagementStrategy        | 124 - org.apache.camel.camel-core - 2.15.2 | JMX is enabled
2015-10-11 09:37:23,095 | INFO  | ExtenderThread-4 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | AllowUseOriginalMessage is enabled. If access to the original message is not needed, then its recommended to turn this option off as it may improve performance.
2015-10-11 09:37:23,095 | INFO  | ExtenderThread-4 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2015-10-11 09:37:23,147 | INFO  | ExtenderThread-4 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | Route: route2 started and consuming from: Endpoint[timer://timer?fixedRate=true&period=10000&repeatCount=1]
2015-10-11 09:37:23,148 | INFO  | ExtenderThread-4 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | Total 1 routes, of which 1 is started.
2015-10-11 09:37:23,148 | INFO  | ExtenderThread-4 | OsgiSpringCamelContext           | 124 - org.apache.camel.camel-core - 2.15.2 | Apache Camel 2.15.2 (CamelContext: camel1) started in 0.139 seconds
2015-10-11 09:37:23,150 | INFO  | ExtenderThread-4 | OsgiBundleXmlApplicationContext  | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Publishing application context as OSGi service with properties {org.springframework.context.service.name=com.github.oscerd.camel-cassandra-servicemix-example, Bundle-SymbolicName=com.github.oscerd.camel-cassandra-servicemix-example, Bundle-Version=1.0.0.SNAPSHOT}
2015-10-11 09:37:23,153 | INFO  | ExtenderThread-4 | ContextLoaderListener            | 84 - org.springframework.osgi.extender - 1.2.1 | Application context successfully refreshed (OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml))
2015-10-11 09:37:24,148 | INFO  |  - timer://timer | Querying Cassandra               | 124 - org.apache.camel.camel-core - 2.15.2 | Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
2015-10-11 09:37:24,233 | INFO  |  - timer://timer | NettyUtil                        | 281 - com.datastax.driver.core - 2.1.7.1 | Did not find Netty's native epoll transport in the classpath, defaulting to NIO.
2015-10-11 09:37:24,469 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 281 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 09:37:24,470 | INFO  |  - timer://timer | Cluster                          | 281 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 09:37:24,513 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: 53f5db80-6fea-11e5-a0c6-d1683b4c262c - Album: Undertow - Title: Prison Sex
2015-10-11 09:37:24,513 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: df76d938-7860-425a-8c53-f9d999eb37d7 - Album: Undertow - Title: Bottom
2015-10-11 09:37:24,513 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: 53f650b0-6fea-11e5-a0c6-d1683b4c262c - Album: Undertow - Title: Sober
2015-10-11 09:37:24,514 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: 53f4a300-6fea-11e5-a0c6-d1683b4c262c - Album: Undertow - Title: Intolerance
2015-10-11 09:37:24,514 | INFO  |  - timer://timer | Inserting the object             | 124 - org.apache.camel.camel-core - 2.15.2 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: ]
2015-10-11 09:37:24,559 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 281 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 09:37:24,560 | INFO  |  - timer://timer | Cluster                          | 281 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 09:37:24,587 | INFO  |  - timer://timer | Querying Cassandra               | 124 - org.apache.camel.camel-core - 2.15.2 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: ]
2015-10-11 09:37:24,637 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 281 - com.datastax.driver.core - 2.1.7.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-10-11 09:37:24,637 | INFO  |  - timer://timer | Cluster                          | 281 - com.datastax.driver.core - 2.1.7.1 | New Cassandra host /127.0.0.1:9042 added
2015-10-11 09:37:24,653 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: 1c69b03e-150a-4eaf-814a-46a7f5f5505b - Album: Undertow - Title: Bottom
2015-10-11 09:37:24,653 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: 53f5db80-6fea-11e5-a0c6-d1683b4c262c - Album: Undertow - Title: Prison Sex
2015-10-11 09:37:24,653 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: df76d938-7860-425a-8c53-f9d999eb37d7 - Album: Undertow - Title: Bottom
2015-10-11 09:37:24,654 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: 53f650b0-6fea-11e5-a0c6-d1683b4c262c - Album: Undertow - Title: Sober
2015-10-11 09:37:24,654 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 124 - org.apache.camel.camel-core - 2.15.2 | Id: 53f4a300-6fea-11e5-a0c6-d1683b4c262c - Album: Undertow - Title: Intolerance

```

# The route

This route executes a select all on songs table in the simplex keyspace (only 3 songs are stored in the songs table at this point), then it executes an insert of one new song and then it executes another select all on the same table of same keyspace logging the result.
