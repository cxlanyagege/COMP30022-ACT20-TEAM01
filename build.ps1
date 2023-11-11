# Ask the user for deployment address
$deployAddr = Read-Host "Please enter the deployment address"

# Decide npm build type based on user input
if ($deployAddr -eq "localhost") {
    npm run build:stage
} else {
    # Define the path to the .env.production file
    $envFilePath = ".\.env.production"

    # Read the contents of the .env.production file
    $envFileContent = Get-Content $envFilePath

    # Replace placeholder address with the new one from $deployAddr
    $updatedEnvFileContent = $envFileContent -replace "ADDRESS", $deployAddr

    # Write the updated content back to the .env.production file
    $updatedEnvFileContent | Set-Content $envFilePath

    npm run build:prod

    # Restore the .env.production file by replacing the deploy address with "ADDRESS"
    $restoredEnvFileContent = $updatedEnvFileContent -replace $deployAddr, "ADDRESS"
    $restoredEnvFileContent | Set-Content $envFilePath
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
