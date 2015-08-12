[![Build Status](https://travis-ci.org/cloudronin/cyberattack-event-collector.svg?branch=master)](https://travis-ci.org/cloudronin/cyberattack-event-collector)


# Cyber Attack Event Collector 
Tool to collect and persist cyber attack events that Norse tracks (http://map.norsecorp.com/) into a locally running MongoDB instance.
The purpose of collecting these events is to use them for analysis by downstream tools like Spark and/or R/Python to train a model that classify IP traffic as malicious

## Development Instructions
The Cyber Attack Event Collector Tool was built and tested on a Mac OSX 10.10.4 and requires the following dependencies to run:

*	Java SE 7 (1.7.0_71) 
*	Mongo DB version 3.0.5
*	Apache Maven 3.3.3 


Once you have installed the above dependencies, you can build the tool by running the following:  

	mvn clean install


This should build the shaded Jar (binary) needed to run the tool under the target/ folder.
After which you can run the tool by issuing the following command in the command line:

	java -cp target/cyberattack-event-collector.jar events.EventCollector

## Build Docker Container
If you want the build and run the Docker container you can issue the following commands:

	./build-docker.sh
	./stop-docker.sh
	./run-docker.sh

Make sure that you have you have the following directory pre-created first on your host:

	/opt/mongodb

# Run Docker container directly
You can also directly pull and run the docker from the docker hub by issuing:
	
	sudo docker pull cloudronin/cyberattack-event-collector
	sudo docker run -d -v /opt/mongodb:/data/db -p 27017:27017 cloudronin/cyberattack-event-collector



