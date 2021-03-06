#!/bin/bash
cd /tmp
java -jar -Dspring.profiles.active=prod Transactions-0.1-SNAPSHOT.jar 2>&1 >> logfile.log &
#echo "Application started!"