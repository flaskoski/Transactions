#!/bin/bash
cd /tmp
FILE=./app.pid
if [ -f "$FILE" ]; then
  kill $(<$FILE)
  rm $FILE
fi