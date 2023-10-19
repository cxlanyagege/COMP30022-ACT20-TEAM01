# IT Project: Teaching Support Platform for Applying Special Consideration

## Team Information
- **Activity Code**: ACT20
- **Team Number**: TEAM01

## Live Demo
- [LTI Launch](https://act20team01.xyz:8081/lti/launch)

## Build & Run Instructions

### Prerequisites
1. Install [Node.js](https://nodejs.org/en/download). Recommended version: 20.x
2. Install [Adoptium Temurin OpenJDK](https://adoptium.net/zh-CN/temurin/releases/). Recommended version: 17 LTS
3. Install and setup [MySQL Server](https://dev.mysql.com/downloads/mysql/). Recommended version: 8.0.34+

### Clone
Clone the repository to your local machine:

```
git clone https://github.com/cxlanyagege/COMP30022-ACT20-TEAM01.git
```

### Setup
Install npm dependencies which are required for building vue:

```
cd COMP30022-ACT20-TEAM01/vue
npm install
```

Import template SQL schema into your database:
```
cd ../sql
mysql -u root -p
CREATE DATABASE sc;
QUIT;
mysql -u root -p sc < template.sql
```

Your database configuration and desired attachment saved location may be different to what was defined in project file. 
You may manually modify these line in `application.properties` in spring/src/main/resources:

```
spring.datasource.url=jdbc:mysql://localhost:3306/sc
spring.datasource.username=itproject
spring.datasource.password=123456
...
upload.localPath=C:\\Users\\cxlan\\AppData\\Local\\Temp\\
upload.accessPath=/upload/
```

Or you can place your own `application.properties` with above new defining, along with `application-v1.0-alpha.jar` in spring/build/libs after build

### Build
Navigate back to the root directory of the project:

```
cd ..
```

If you are using macOS or Linux, execute:

```
./build.sh
```

Or if you are using Windows 7 or higher, execute:

```
.\build.ps1
```

### Run
Execute the following command to run the application:

```
java -jar spring/build/libs/application-v1.0-alpha.jar
```

### Connect
For the connection using LTI1.1 standards, please refer to [How to connect to LMS](https://wxd.atlassian.net/wiki/spaces/~712020572c35dfb81c4ad48d0be3dc166f9960/pages/7438386/Deployment#Connect-to-LMS%3A)
