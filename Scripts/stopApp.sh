#!/bin/bash
echo "Killing process on port 8080"
kill -9 $(lsof -t -i:80)
echo "Done killing the process"