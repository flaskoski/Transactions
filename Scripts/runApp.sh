#!/bin/bash
cd /tmp
java -jar -Dspring.profiles.active=prod Transactions-0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null &
#echo "Application started!"