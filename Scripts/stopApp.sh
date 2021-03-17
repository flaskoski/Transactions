#!/bin/bash
echo "Killing process on port 8080"
kill -9 $(lsof -t -i:8080)
echo "Done killing the process"