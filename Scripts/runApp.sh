#!/bin/bash
cd /tmp
FILE=./app.pid
if [ -f "$FILE" ]; then
  kill $(<$FILE)
fi
nohup java -jar -Dspring.profiles.active=prod Transactions-0.1-SNAPSHOT.jar 2>&1 >> logfile.log &
echo $! > $FILE