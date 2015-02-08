# Camel Cassandra Component Servicemix Example

This is a simple example of a Camel route based on the Camel-cassandra component (https://github.com/oscerd/camel-cassandra) I've developed.
This route will be deployed on Apache Servicemix.

# Setting up the environment and deployment

To run this example you need to follow these instructions.

# Setting up Apache-Cassandra

- Install Apache Cassandra from http://cassandra.apache.org/download/ . In my configuration I've used Apache Cassandra version 2.1.2.

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

Camel-Cassandra component is based on camel-core 2.14.1 release. So we need to use an Apache Servicemix version based on this release.

The Apache Servicemix 5.4.0 is the correct release.

- Download the Apache Servicemix 5.4.0 package from: http://servicemix.apache.org/downloads/servicemix-5.4.0.html

- Unzip the package in a directory (we denote this folder with $SERVICEMIX_HOME)

- Execute $SERVICEMIX_HOME/bin/servicemix

- Inside Karaf execute the following instructions:

- Install Google Guava

```shell

karaf@root> osgi:install -s mvn:com.google.guava/guava/16.0.1

```

- Install codahale metrics

```shell

karaf@root> osgi:install -s mvn:com.codahale.metrics/metrics-core/3.0.2

```

- Install Netty

```shell

karaf@root> osgi:install -s mvn:io.netty/netty/3.9.0.Final

```

- Install lz4

```shell

karaf@root> osgi:install -s mvn:net.jpountz.lz4/lz4/1.2.0

```

- Install Snappy Java

```shell

karaf@root> osgi:install -s mvn:org.xerial.snappy/snappy-java/1.0.4

```

- Install Datastax Java Driver

```shell

karaf@root> osgi:install -s mvn:com.datastax.cassandra/cassandra-driver-core/2.1.1

```

- Install Camel Cassandra 

```shell

karaf@root> osgi:install -s mvn:com.github.oscerd/camel-cassandra/1.0.0

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

karaf@root> osgi:install -s file:///$PROJECT_HOME/target/camel-cassandra-servicemix-example-1.0.0-SNAPSHOT.jar

```

- To verify that the bundle is correctly deployed type the following command: 

```shell

karaf@root> osgi:list

```

you should see something like this:

```shell

[ 211] [Active     ] [            ] [       ] [   80] Guava: Google Core Libraries for Java (16.0.1)
[ 212] [Active     ] [            ] [       ] [   80] Metrics Core (3.0.2)
[ 213] [Active     ] [            ] [       ] [   80] The Netty Project (3.9.0.Final)
[ 214] [Active     ] [            ] [       ] [   80] mvn:net.jpountz.lz4/lz4/1.2.0
[ 215] [Active     ] [            ] [       ] [   80] org.xerial.snappy.snappy-java (1.0.4)
[ 216] [Active     ] [            ] [       ] [   80] DataStax Java Driver for Apache Cassandra - Core (2.1.1)
[ 217] [Active     ] [            ] [       ] [   80] Camel :: Cassandra (1.0.0)
[ 218] [Active     ] [            ] [Started] [   80] Camel Cassandra Servicemix Route Example (1.0.0.SNAPSHOT)

```

We can also verify everything is ok by looking at the log. Type the following command inside karaf:

```shell

karaf@root> log:tail

```

You should see something like this:

```shell

2015-02-08 12:06:04,488 | INFO  | l Console Thread | ultOsgiApplicationContextCreator | ?                                   ? | 122 - org.springframework.osgi.extender - 1.2.1 | Discovered configurations {osgibundle:/META-INF/spring/*.xml} in bundle [Camel Cassandra Servicemix Route Example (com.github.oscerd.camel-cassandra-servicemix-example)]
2015-02-08 12:06:04,489 | INFO  | xtenderThread-13 | OsgiBundleXmlApplicationContext  | ?                                   ? | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Refreshing OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml): startup date [Sun Feb 08 12:06:04 CET 2015]; root of context hierarchy
2015-02-08 12:06:04,490 | INFO  | xtenderThread-13 | OsgiBundleXmlApplicationContext  | ?                                   ? | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Application Context service already unpublished
2015-02-08 12:06:04,492 | INFO  | xtenderThread-13 | XmlBeanDefinitionReader          | ?                                   ? | 77 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Loading XML bean definitions from URL [bundle://227.0:0/META-INF/spring/camel-context.xml]
2015-02-08 12:06:04,553 | INFO  | xtenderThread-13 | WaiterApplicationContextExecutor | ?                                   ? | 122 - org.springframework.osgi.extender - 1.2.1 | No outstanding OSGi service dependencies, completing initialization for OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml)
2015-02-08 12:06:04,555 | INFO  | xtenderThread-14 | DefaultListableBeanFactory       | ?                                   ? | 77 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@4a8b9b70: defining beans [camel1:beanPostProcessor,camel1,camelCassandraBuilder]; root of factory hierarchy
2015-02-08 12:06:04,569 | INFO  | xtenderThread-14 | OsgiSpringCamelContext           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Apache Camel 2.14.1 (CamelContext: camel1) is starting
2015-02-08 12:06:04,569 | INFO  | xtenderThread-14 | ManagedManagementStrategy        | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | JMX is enabled
2015-02-08 12:06:04,639 | INFO  | xtenderThread-14 | OsgiSpringCamelContext           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | AllowUseOriginalMessage is enabled. If access to the original message is not needed, then its recommended to turn this option off as it may improve performance.
2015-02-08 12:06:04,640 | INFO  | xtenderThread-14 | OsgiSpringCamelContext           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2015-02-08 12:06:04,677 | INFO  | xtenderThread-14 | OsgiSpringCamelContext           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Route: route7 started and consuming from: Endpoint[timer://timer?fixedRate=true&period=10000&repeatCount=1]
2015-02-08 12:06:04,677 | INFO  | xtenderThread-14 | OsgiSpringCamelContext           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Total 1 routes, of which 1 is started.
2015-02-08 12:06:04,677 | INFO  | xtenderThread-14 | OsgiSpringCamelContext           | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Apache Camel 2.14.1 (CamelContext: camel1) started in 0.108 seconds
2015-02-08 12:06:04,679 | INFO  | xtenderThread-14 | OsgiBundleXmlApplicationContext  | ?                                   ? | 79 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Publishing application context as OSGi service with properties {org.springframework.context.service.name=com.github.oscerd.camel-cassandra-servicemix-example, Bundle-SymbolicName=com.github.oscerd.camel-cassandra-servicemix-example, Bundle-Version=1.0.0.SNAPSHOT}
2015-02-08 12:06:04,681 | INFO  | xtenderThread-14 | ContextLoaderListener            | ?                                   ? | 122 - org.springframework.osgi.extender - 1.2.1 | Application context successfully refreshed (OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml))
2015-02-08 12:06:05,678 | INFO  |  - timer://timer | Querying Cassandra               | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
2015-02-08 12:06:05,713 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | ?                                   ? | 217 - com.datastax.driver.core - 2.1.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-02-08 12:06:05,714 | INFO  |  Driver worker-0 | Cluster                          | ?                                   ? | 217 - com.datastax.driver.core - 2.1.1 | New Cassandra host /127.0.0.1:9042 added
2015-02-08 12:06:05,741 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Id: 6065efa0-af82-11e4-9eae-e15e042f3dd3 - Album: Undertow - Title: Intolerance
2015-02-08 12:06:05,742 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Id: 606664d0-af82-11e4-9eae-e15e042f3dd3 - Album: Undertow - Title: Prison Sex
2015-02-08 12:06:05,742 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Id: 60670110-af82-11e4-9eae-e15e042f3dd3 - Album: Undertow - Title: Sober
2015-02-08 12:06:05,742 | INFO  |  - timer://timer | Inserting the object             | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: ]
2015-02-08 12:06:05,771 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | ?                                   ? | 217 - com.datastax.driver.core - 2.1.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-02-08 12:06:05,771 | INFO  |  Driver worker-0 | Cluster                          | ?                                   ? | 217 - com.datastax.driver.core - 2.1.1 | New Cassandra host /127.0.0.1:9042 added
2015-02-08 12:06:05,784 | INFO  |  - timer://timer | Querying Cassandra               | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: ]
2015-02-08 12:06:05,808 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | ?                                   ? | 217 - com.datastax.driver.core - 2.1.1 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-02-08 12:06:05,809 | INFO  |  Driver worker-0 | Cluster                          | ?                                   ? | 217 - com.datastax.driver.core - 2.1.1 | New Cassandra host /127.0.0.1:9042 added
2015-02-08 12:06:05,821 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Id: 532c650d-2596-478c-b770-603cd73abca1 - Album: Undertow - Title: Bottom
2015-02-08 12:06:05,822 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Id: 6065efa0-af82-11e4-9eae-e15e042f3dd3 - Album: Undertow - Title: Intolerance
2015-02-08 12:06:05,822 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Id: 606664d0-af82-11e4-9eae-e15e042f3dd3 - Album: Undertow - Title: Prison Sex
2015-02-08 12:06:05,822 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | ?                                   ? | 116 - org.apache.camel.camel-core - 2.14.1 | Id: 60670110-af82-11e4-9eae-e15e042f3dd3 - Album: Undertow - Title: Sober

```

# The route

This route executes a select all on songs table in the simplex keyspace (only 3 songs are stored in the Cassandra instance at this point), then it executes an insert of one new song and then it executes another select all on the same table of same keyspace logging the result.
