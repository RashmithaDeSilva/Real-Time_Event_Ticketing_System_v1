#!/bin/bash

# Define variables
containerName="mysql"
imageName="mysql"
portMapping="3306:3306"
rootPassword="12345"
logFile="start-db-output.txt"
dbName="event_ticketing_db"

# Delete start-db-output.txt if it exists
echo "[STEP 1] Delete previous log file if it exists"
if [ -f "$logFile" ]; then
    rm "$logFile"
fi
echo "[STEP 1] Delete previous log file if it exists --------------------------------------------" >> "$logFile" 2>&1

# Pull the MySQL image and log output
echo "[STEP 2] Pull the MySQL image and log output"
echo "[STEP 2] Pull the MySQL image and log output --------------------------------------------" >> "$logFile" 2>&1
docker pull "$imageName" >> "$logFile" 2>&1

# Check if the container is already running
echo "[STEP 3] Check if the container is already running"
echo "[STEP 3] Check if the container is already running ----------------------------------------" >> "$logFile" 2>&1
existingContainerId=$(docker ps -a --filter "name=$containerName" --format "{{.ID}}")

if [ -n "$existingContainerId" ]; then
    # Stop and remove the existing container
    echo "[STEP 4] Stop and remove the existing container"
    echo "[STEP 4] Stop and remove the existing container ---------------------------------------" >> "$logFile" 2>&1
    docker stop "$containerName" >> "$logFile" 2>&1
    docker rm "$containerName" >> "$logFile" 2>&1
fi

# Start the MySQL container and log output
echo "[STEP 5] Start the MySQL container and log output"
echo "[STEP 5] Start the MySQL container and log output ---------------------------------------" >> "$logFile" 2>&1
docker run -d --name "$containerName" -p "$portMapping" --restart always \
    -e MYSQL_ROOT_PASSWORD="$rootPassword" -e MYSQL_DATABASE="$dbName" "$imageName" >> "$logFile" 2>&1

# Log completion
echo "Backup import completed successfully on $(date)." >> "$logFile" 2>&1
