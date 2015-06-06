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

Camel-Cassandra component is based on camel-core 2.15.2 release. So we need to use an Apache Servicemix version based on this release.

The Apache Servicemix 6.0.0.M2 is the correct release.

- Download the Apache Servicemix 6.0.0.M2 package from: http://servicemix.apache.org/downloads/servicemix-6.0.0.M2.html

- Unzip the package in a directory (we denote this folder with $SERVICEMIX_HOME)

- Execute $SERVICEMIX_HOME/bin/servicemix

- Inside Karaf execute the following instructions:

- __Install Google Guava Bundle__

```shell

karaf@root> install -s mvn:com.google.guava/guava/14.0.1

```

- __Install codahale metrics Bundle__

```shell

karaf@root> install -s mvn:com.codahale.metrics/metrics-core/3.0.2

```

- __Install Netty Common Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-common/4.0.27.Final

```

- __Install Netty Buffer Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-buffer/4.0.27.Final

```

- __Install Netty Transport Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-transport/4.0.27.Final

```

- __Install Netty Codec Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-codec/4.0.27.Final

```

- __Install Netty Transport Native Epoll Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-handler/4.0.27.Final

```

- __Install Netty Handler Bundle__

```shell

karaf@root> install -s mvn:io.netty/netty-transport-native-epoll/4.0.27.Final

```

- __Install lz4 Bundle__

```shell

karaf@root> install -s mvn:net.jpountz.lz4/lz4/1.2.0

```

- __Install Snappy Java Bundle__

```shell

karaf@root> install -s mvn:org.xerial.snappy/snappy-java/1.0.4

```

- __Install Datastax Java Driver Bundle__

```shell

karaf@root> install -s mvn:com.datastax.cassandra/cassandra-driver-core/2.1.6

```

- __Install Camel Cassandra Bundle__ 

```shell

karaf@root> install -s mvn:com.github.oscerd/camel-cassandra/1.2.0

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

229 | Active |  80 | 14.0.1                             | Guava: Google Core Libraries for Java
230 | Active |  80 | 3.0.2                              | Metrics Core
233 | Active |  80 |                                    | mvn:net.jpountz.lz4/lz4/1.2.0
235 | Active |  80 | 1.0.4                              | org.xerial.snappy.snappy-java
236 | Active |  80 | 2.1.6                              | DataStax Java Driver for Apache Cassandra - Core
237 | Active |  80 | 4.0.27.Final                       | Netty/Handler
238 | Active |  80 | 4.0.27.Final                       | Netty/Buffer
239 | Active |  80 | 4.0.27.Final                       | Netty/Common
240 | Active |  80 | 4.0.27.Final                       | Netty/Transport
241 | Active |  80 | 4.0.27.Final                       | Netty/Codec
242 | Active |  80 | 4.0.27.Final                       | Netty/Transport/Native/Epoll
246 | Active |  80 | 1.2.0                              | Camel :: Cassandra 

```

We can also verify everything is ok by looking at the log. Type the following command inside karaf:

```shell

karaf@root> log:tail

```

You should see something like this:

```shell

2015-06-06 19:11:34,660 | INFO  | l for user karaf | ultOsgiApplicationContextCreator | 106 - org.springframework.osgi.extender - 1.2.1 | Discovered configurations {osgibundle:/META-INF/spring/*.xml} in bundle [Camel Cassandra Servicemix Route Example (com.github.oscerd.camel-cassandra-servicemix-example)]
2015-06-06 19:11:34,660 | INFO  | ExtenderThread-5 | OsgiBundleXmlApplicationContext  | 101 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Refreshing OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml): startup date [Sat Jun 06 19:11:34 CEST 2015]; root of context hierarchy
2015-06-06 19:11:34,662 | INFO  | ExtenderThread-5 | OsgiBundleXmlApplicationContext  | 101 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Application Context service already unpublished
2015-06-06 19:11:34,663 | INFO  | ExtenderThread-5 | XmlBeanDefinitionReader          | 99 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Loading XML bean definitions from URL [bundle://247.0:0/META-INF/spring/camel-context.xml]
2015-06-06 19:11:34,768 | INFO  | ExtenderThread-5 | WaiterApplicationContextExecutor | 106 - org.springframework.osgi.extender - 1.2.1 | No outstanding OSGi service dependencies, completing initialization for OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml)
2015-06-06 19:11:34,772 | INFO  | ExtenderThread-6 | DefaultListableBeanFactory       | 99 - org.apache.servicemix.bundles.spring-beans - 3.2.11.RELEASE_1 | Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@19e5763c: defining beans [camel1:beanPostProcessor,camel1,camelCassandraBuilder]; root of factory hierarchy
2015-06-06 19:11:34,816 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | Apache Camel 2.15.2 (CamelContext: camel1) is starting
2015-06-06 19:11:34,817 | INFO  | ExtenderThread-6 | ManagedManagementStrategy        | 113 - org.apache.camel.camel-core - 2.15.2 | JMX is enabled
2015-06-06 19:11:34,912 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | AllowUseOriginalMessage is enabled. If access to the original message is not needed, then its recommended to turn this option off as it may improve performance.
2015-06-06 19:11:34,912 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2015-06-06 19:11:34,963 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | Route: route3 started and consuming from: Endpoint[timer://timer?fixedRate=true&period=10000&repeatCount=1]
2015-06-06 19:11:34,963 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | Total 1 routes, of which 1 is started.
2015-06-06 19:11:34,963 | INFO  | ExtenderThread-6 | OsgiSpringCamelContext           | 113 - org.apache.camel.camel-core - 2.15.2 | Apache Camel 2.15.2 (CamelContext: camel1) started in 0.147 seconds
2015-06-06 19:11:34,965 | INFO  | ExtenderThread-6 | OsgiBundleXmlApplicationContext  | 101 - org.apache.servicemix.bundles.spring-context - 3.2.11.RELEASE_1 | Publishing application context as OSGi service with properties {org.springframework.context.service.name=com.github.oscerd.camel-cassandra-servicemix-example, Bundle-SymbolicName=com.github.oscerd.camel-cassandra-servicemix-example, Bundle-Version=1.0.0.SNAPSHOT}
2015-06-06 19:11:34,967 | INFO  | ExtenderThread-6 | ContextLoaderListener            | 106 - org.springframework.osgi.extender - 1.2.1 | Application context successfully refreshed (OsgiBundleXmlApplicationContext(bundle=com.github.oscerd.camel-cassandra-servicemix-example, config=osgibundle:/META-INF/spring/*.xml))
2015-06-06 19:11:35,964 | INFO  |  - timer://timer | Querying Cassandra               | 113 - org.apache.camel.camel-core - 2.15.2 | Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
2015-06-06 19:11:36,045 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 236 - com.datastax.driver.core - 2.1.6 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-06-06 19:11:36,045 | INFO  |  - timer://timer | Cluster                          | 236 - com.datastax.driver.core - 2.1.6 | New Cassandra host /127.0.0.1:9042 added
2015-06-06 19:11:36,087 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 113 - org.apache.camel.camel-core - 2.15.2 | Id: a7047050-0c6e-11e5-83a7-0d0908f21338 - Album: Undertow - Title: Intolerance
2015-06-06 19:11:36,088 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 113 - org.apache.camel.camel-core - 2.15.2 | Id: a7066c20-0c6e-11e5-83a7-0d0908f21338 - Album: Undertow - Title: Sober
2015-06-06 19:11:36,088 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 113 - org.apache.camel.camel-core - 2.15.2 | Id: a705a8d0-0c6e-11e5-83a7-0d0908f21338 - Album: Undertow - Title: Prison Sex
2015-06-06 19:11:36,088 | INFO  |  - timer://timer | Inserting the object             | 113 - org.apache.camel.camel-core - 2.15.2 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: ]
2015-06-06 19:11:36,144 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 236 - com.datastax.driver.core - 2.1.6 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-06-06 19:11:36,144 | INFO  |  - timer://timer | Cluster                          | 236 - com.datastax.driver.core - 2.1.6 | New Cassandra host /127.0.0.1:9042 added
2015-06-06 19:11:36,162 | INFO  |  - timer://timer | Querying Cassandra               | 113 - org.apache.camel.camel-core - 2.15.2 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: ]
2015-06-06 19:11:36,213 | INFO  |  - timer://timer | DCAwareRoundRobinPolicy          | 236 - com.datastax.driver.core - 2.1.6 | Using data-center name 'datacenter1' for DCAwareRoundRobinPolicy (if this is incorrect, please provide the correct datacenter name with DCAwareRoundRobinPolicy constructor)
2015-06-06 19:11:36,213 | INFO  |  - timer://timer | Cluster                          | 236 - com.datastax.driver.core - 2.1.6 | New Cassandra host /127.0.0.1:9042 added
2015-06-06 19:11:36,229 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 113 - org.apache.camel.camel-core - 2.15.2 | Id: a7047050-0c6e-11e5-83a7-0d0908f21338 - Album: Undertow - Title: Intolerance
2015-06-06 19:11:36,229 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 113 - org.apache.camel.camel-core - 2.15.2 | Id: 22388bbb-fd23-4231-a11e-36492fd2f9e7 - Album: Undertow - Title: Bottom
2015-06-06 19:11:36,229 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 113 - org.apache.camel.camel-core - 2.15.2 | Id: a7066c20-0c6e-11e5-83a7-0d0908f21338 - Album: Undertow - Title: Sober
2015-06-06 19:11:36,229 | INFO  |  - timer://timer | CamelCassandraRouteBuilder       | 113 - org.apache.camel.camel-core - 2.15.2 | Id: a705a8d0-0c6e-11e5-83a7-0d0908f21338 - Album: Undertow - Title: Prison Sex

```

# The route

This route executes a select all on songs table in the simplex keyspace (only 3 songs are stored in the Cassandra instance at this point), then it executes an insert of one new song and then it executes another select all on the same table of same keyspace logging the result.
