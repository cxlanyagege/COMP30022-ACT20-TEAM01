# Change directory to vue to build the project
cd .\vue\
npm run build:stage

# Check build status
if ($LASTEXITCODE -ne 0) {
    Write-Host "Vue.js build failed"
    exit 1
}

# Go back to previous directory
cd ..

# Remove all files in the spring static folder
Remove-Item .\spring\src\main\resources\static\* -Recurse -Force

# Move dist to spring static folder
Copy-Item -Recurse .\vue\dist\* .\spring\src\main\resources\static\

# Switch to spring directory and build with gradle
cd .\spring\
.\gradlew clean build

# Check gradle build status
if ($LASTEXITCODE -ne 0) {
    Write-Host "Spring Boot build failed"
    exit 1
}

# Go back to project root directory
cd ..
