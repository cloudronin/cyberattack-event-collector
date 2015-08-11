#!/bin/bash
cd /data
mongod & 
while ! echo exit | nc localhost 27017; do sleep 10; done
java -cp /opt/cyber-event-collector/target/cyberattack-event-collector.jar events.EventCollector
