#!/bin/bash

# Ask the user for build type
echo -n "Please enter build type (stage or prod): "
read buildType

# Decide npm build type based on user input
if [ "$buildType" == "stage" ]; then
    npm run build:stage
elif [ "$buildType" == "prod" ]; then
    npm run build:prod
else
    echo "Invalid build type entered. Please enter either 'stage' or 'prod'."
    exit 1
fi

# Check build status
if [ $? -ne 0 ]; then
    echo "Vue.js build failed"
    exit 1
fi

# Define the static folder path
staticFolderPath="./src/main/resources/static/"

# Remove all files in the spring static folder if it exists
if [ -d "$staticFolderPath" ]; then
    rm -rf "${staticFolderPath:?}"/*
else
    mkdir -p "$staticFolderPath"
fi

# Move dist to spring static folder
cp -r ./dist/* ./src/main/resources/static/

# Build spring with gradle
./gradlew clean build

# Check gradle build status
if [ $? -ne 0 ]; then
    echo "Spring Boot build failed"
    exit 1
fi

# Generate other properties files if needed
if [ "$buildType" == "prod" ]; then
cp ./src/main/resources/production.properties ./build/libs/application.properties
