# Ask the user for build type
$buildType = Read-Host "Please enter build type (stage or prod)"

# Change directory to vue to build the project
cd .\vue\

# Decide npm build type based on user input
if ($buildType -eq "stage") {
    npm run build:stage
} elseif ($buildType -eq "prod") {
    npm run build:prod
} else {
    Write-Host "Invalid build type entered. Please enter either 'stage' or 'prod'."
    exit 1
}

# Check build status
if ($LASTEXITCODE -ne 0) {
    Write-Host "Vue.js build failed"
    exit 1
}

# Go back to previous directory
cd ..

# Define the static folder path
$staticFolderPath = ".\spring\src\main\resources\static\"

# Remove all files in the spring static folder if it exists
if (Test-Path -Path $staticFolderPath -PathType Container) {
    Remove-Item -Path "$staticFolderPath*" -Recurse -Force
} else {
    New-Item -Path $staticFolderPath -ItemType Directory
}

# Move dist to spring static folder
Copy-Item -Recurse .\vue\dist\* .\spring\src\main\resources\static\

# Switch to spring directory and build with gradle
cd .\spring\
.\gradlew.bat clean build

# Check gradle build status
if ($LASTEXITCODE -ne 0) {
    Write-Host "Spring Boot build failed"
    exit 1
}

# Generate other properties files if needed
if ($buildType -eq "prod") {
    Copy-Item .\src\main\resources\production.properties .\build\libs\application.properties
}

# Go back to project root directory
cd ..
