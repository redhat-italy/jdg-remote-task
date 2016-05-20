# jdg-remote-task

Simple example of JDG 7 ServerTask invoked by remote client

## How to run:

(1) Export the JBOSS_HOME environment variable with a JBoss Datagrid 7 Beta home directory

(2) run the test by executing "mvn clean install"

## Project structure

(1) model :: shared model with both testing and server module. It contains only simple Serializable object wrapping String

(2) testing :: a simple arquillian test with an hot rod client that invoke a remote server task

(3) server :: jar containing the ServerTask, activated by META-INF/service definition and the Pojo of the shared model



