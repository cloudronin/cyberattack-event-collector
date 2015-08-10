[![Build Status](https://travis-ci.org/cloudronin/cyberattack-collector.svg?branch=master)](https://travis-ci.org/cloudronin/cyberattack-collector/)


# Cyber Attack Event Collector 
Tool to collect and persist cyber attack events that Norse tracks (http://map.norsecorp.com/) into a locally running MongoDB instance.
The purpose of collecting these events is to use them for analysis by downstream tools like Spark and/or R/Python to train a model that classify IP traffic as malicious

The Cyber Attack Event Collector Tool was built and tested on a Mac OSX 10.10.4 and requires the following dependencies to run:

*	Java SE 7 (1.7.0_71) 
*	Mongo DB version 3.0.5
*	Apache Maven 3.3.3 


Once you have installed the above dependencies, you can build the tool by running the following:  

	mvn clean install


This should build the shaded Jar (binary) needed to run the tool under the target/ folder.
After which you can run the tool by issuing the following command in the command line:

	java -cp target/cyberattack-collector.jar events.EventCollector


