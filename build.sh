#!/bin/bash

# Ask the user for deployment address
echo -n "Please enter the deployment address: "
read deployAddr

# Define the path to the .env.production file
envFilePath="./.env.production"

# Decide npm build type based on user input
if [ "$deployAddr" == "localhost" ]; then
    npm run build:stage
else
    # Backup original .env.production file
    cp "$envFilePath" "$envFilePath.bak"

    # Replace ADDRESS placeholder with the new one from $deployAddr
    sed -i "s/ADDRESS/$deployAddr/g" "$envFilePath"

    npm run build:prod

    # Restore the original .env.production file
    mv "$envFilePath.bak" "$envFilePath"
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
