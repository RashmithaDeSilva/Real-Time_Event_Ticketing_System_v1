@echo off
rem Define variables
set containerName=mysql
set imageName=mysql
set portMapping=3306:3306
set rootPassword=12345
@REM set containerWdir=/home
@REM set backupFileName=Test_database.tar.gz
@REM set backupFileNameUnzip=Test_database
set logFile=start-db-output.txt
set dbName=event_ticketing_db

rem Delete start-db-output.txt if it exists
echo [STEP 1] Delete previous log file if it exists
if exist %logFile% (
    del %logFile%
)
echo [STEP 1] Delete previous log file if it exists -------------------------------------------- >> %logFile% 2>&1

rem Pull the MySQL image and log output
echo [STEP 2] Pull the MySQL image and log output
echo [STEP 2] Pull the MySQL image and log output -------------------------------------------- >> %logFile% 2>&1
docker pull %imageName% >> %logFile% 2>&1

rem Check if the container is already running
echo [STEP 3] Check if the container is already running
echo [STEP 3] Check if the container is already running ---------------------------------------- >> %logFile% 2>&1
docker ps -a --filter "name=%containerName%" --format "{{.ID}}" > temp.txt
set /p existingContainerId=<temp.txt
del temp.txt

if not "%existingContainerId%"=="" (
    rem Stop and remove the existing container
    echo [STEP 4] Stop and remove the existing container
    echo [STEP 4] Stop and remove the existing container --------------------------------------- >> %logFile% 2>&1
    docker stop %containerName% >> %logFile% 2>&1
    docker rm %containerName% >> %logFile% 2>&1
)

rem Start the MySQL container and log output
echo [STEP 5] Start the MySQL container and log output
echo [STEP 5] Start the MySQL container and log output --------------------------------------- >> %logFile% 2>&1
docker run -d --name %containerName% -p %portMapping% --restart always -e MYSQL_ROOT_PASSWORD=%rootPassword% -e MYSQL_DATABASE=%event_ticketing_db% %imageName% >> %logFile% 2>&1

rem Log completion
echo Backup import completed successfully on %date% at %time%. >> %logFile% 2>&1