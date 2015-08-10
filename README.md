# cyberattack-collector
Tool to collect cyber attacks that Norse tracks and persist it into locally running MongoDB instance

To run the tool, you would need to install MongoDB locally first by following these instructions
http://docs.mongodb.org/manual/installation/

Once that is done, you can build the tool by doing a 

mvn clean install

and then running the main class in the tool (no arguments needed)

java -cp target/cyberattack-collector.jar events.EventCollector
