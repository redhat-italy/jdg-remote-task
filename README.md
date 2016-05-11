# jdg-remote-task

Simple example of JDG 7 ServerTask invoked by remote client

## How to run:

(1) build root project "mvn clean install"

(2) deploy ./client/target/task-client.war on JBoss EAP 6 server (powered with Red Hat JBoss Data Grid 7.0.0 Beta Library Module)

(3) deploy ./server/target/task-server-1.0-SNAPSHOT-jar-with-dependencies.jar on Jboss JDG 7 Beta server (offset +100)

(4) do http POST on
http://{eaphost}:{eaphttpport}/task-client/modelPojo

## Project structure

(1) model :: shared model with both client and server module. It contains only simple Serializable object wrapping String

(2) client :: standard JEE6 module using Hotrod client to invoke remote jdg server. It use the inifinispan depedencies provided by Red Hat JBoss Data Grid 7.0.0 Beta Library Module. It has a simple Rest service to trigger ServerTask invocation.

(3) server :: jar containing the ServerTask, activated by META-INF/service definition and the Pojo of the shared model



