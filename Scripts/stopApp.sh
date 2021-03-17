#!/bin/bash
echo "Killing process on port 8080"
sudo kill -9 $(sudo lsof -t -i:8080)
echo "Done killing the process"