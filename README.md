

<h1>Hello world app with Camel and serviceMix</h1>
=========================================

It consumes files from a folder and copies them to another folder

<h3>Servicemix deployment.</h3>
<b>Step 1:</b> compile the source. With mvn compile <br/>
<b>Step 2:</b> install the bundle with mvn install<br/>
<b>Step 3:</b> deploy bundle<br/>
There are two ways of deploying a bundle to servicemix.<br/>
1: Hot deployment: just drop your bundle in the deploy folder, service mix will install and start it (the bundle is the jar found in the target folder).<br/>
2:  use Karaf on the cmd. Once you run servicemix.bat in a command shell, it opens a root instance of Karaf. Use this command to install your bundle.<br/>
Osgi:install  -s mvn:url where url in this case = com.helloworld.filecopy.spring/spring-dsl/1.0.0
