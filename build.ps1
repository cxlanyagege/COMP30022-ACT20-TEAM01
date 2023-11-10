# Ask the user for build type
$buildType = Read-Host "Please enter build type (stage or prod)"

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

# Define the static folder path
$staticFolderPath = ".\src\main\resources\static\"

# Remove all files in the spring static folder if it exists
if (Test-Path -Path $staticFolderPath -PathType Container) {
    Remove-Item -Path "$staticFolderPath*" -Recurse -Force
} else {
    New-Item -Path $staticFolderPath -ItemType Directory
}

# Move dist to spring static folder
Copy-Item -Recurse .\dist\* .\src\main\resources\static\

# Build spring with gradle
.\gradlew.bat clean build

# Check gradle build status
if ($LASTEXITCODE -ne 0) {
    Write-Host "Spring Boot build failed"
    exit 1
}
