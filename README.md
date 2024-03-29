# StuRequestHub
IT Project: Teaching Support Platform for Applying Special Consideration

## Team Information
- **Activity Code**: ACT20
- **Team Number**: TEAM01

## Build & Run Instructions

### Prerequisites
1. Install [Node.js](https://nodejs.org/en/download). Recommended version: 20.x
2. Install [Adoptium Temurin OpenJDK](https://adoptium.net/zh-CN/temurin/releases/). Recommended version: 17 LTS
3. Install and setup [MySQL Server](https://dev.mysql.com/downloads/mysql/). Recommended version: 8.1 Innovation

### Clone
Clone the repository to your local machine:

```
git clone https://github.com/cxlanyagege/COMP30022-ACT20-TEAM01.git
```

### Setup
Change directory to our project:

```
cd COMP30022-ACT20-TEAM01
```

Install npm dependencies which are required for building vue:

```
npm install
```

Import template SQL schema into your database:

```
mysql -u root -p < database.sql
```

### Build
If you are using macOS or Linux, execute:

```
./build.sh
```

Or if you are using Windows 10 / 11, execute:

```
.\build.ps1
```

The script will ask you for deploy address. For example, if you want to deploy on your local environment, type `localhost`:

```
Please enter the deployment address: localhost
```

Or if you wish to deploy on your remote server, such as `121.41.46.143`, type:

```
Please enter the deployment address: 121.41.46.143
```

### Run
Execute the following command to run the application:

```
java -jar build/libs/application-v2.1.jar
```

### Connect
For the connection using LTI1.1 standards, please refer to [How to connect to LMS](https://wxd.atlassian.net/wiki/spaces/~712020572c35dfb81c4ad48d0be3dc166f9960/pages/7438386/Deployment#Connect-to-LMS%3A)

| Key           | Value                                         |
|---------------|-----------------------------------------------|
| Name          | StuRequestHub                                 |
| Consumer Key  | sturequesthub_itproject_act20_team01          |
| Shared Secret | f9Dh7sd1Gk8oP4nQz6jWc3aRb0xLm2Yv              |

- [LTI Registration XML](https://github.com/cxlanyagege/COMP30022-ACT20-TEAM01/blob/main/registration.xml)

## `application.properties` Template
Create your own `application.properties` following the template below, along with `application-vX.X.jar` in build/libs after build
```
# Application server port
server.port=8081

# Database source link
spring.datasource.url=jdbc:mysql://localhost:3306/student_requests

# Database user name
spring.datasource.username=root

# Database user password
spring.datasource.password=123456

# Database driver
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Attachment size restriction
spring.servlet.multipart.max-request-size=5MB
spring.servlet.multipart.max-file-size=5MB

# Attachment save location
upload.localPath=/
upload.accessPath=/upload/

# LTI config
lti.consumerKey=sturequesthub_itproject_act20_team01
lti.sharedSecret=f9Dh7sd1Gk8oP4nQz6jWc3aRb0xLm2Yv

# Mail service
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=student.request.hub.demo@gmail.com
spring.mail.password=xkvxvjiwtmegvhjm
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.protocol=smtp
spring.mail.properties.debug=false
```

## FAQ
### I cannot build the project by running `build.sh` or `build.ps1`
If you are facing permission issues while running `build.sh`, try to run `chmod -R 755 .` in COMP30022-ACT20-TEAM01 directory

If you are facing digital signing issues while running `build.ps1`, make sure you first go to `Settings` -> `System` -> `Developer Options`, turn on Developer Mode and the option which allows to run Powershell scripts without digital signature

### Why I cannot see any requests in tutor's dashboard?
Tutor's dashboard has filter constrains. You may notice that there are different course blocks you can choose on sidebar. Simply choose the course you want to see first, then the corresponding requests will appear on the right pannel

### Saving requests / Approving requests are very slow
This is a MySQL 8.x bug, it will impact sql performance somehow. Try newer versions if possible

### If I need to deploy on my remote server, do I need to setup MySQL database remotely?
Yes, you may follow [Prerequisites](#prerequisites) and [Setup](#setup) to install and config Java and MySQL Server. Then try to run the application

### What is the difference between `application-v2.1.jar` and `application-v2.1-plain.jar`?
Spring Boot will build two production application forms by default:

The one without `plain` includes all dependencies, class files, resources, etc. It allows direct execution using `java -jar` commands, and its internal structure already includes embedded tomcat servers, dependency libraries, etc

The one with `plain` is a structure closer to traditional jar files and does not include specific structures of Spring Boot, such as embedded servers. If you need to use it, you need to deploy it in an existing server environment, such as on a standalone Tomcat server. In other words, it usually only includes the application's classes and resources, and does not include the complete set of dependencies

## Limitation
Due to some misleading guidance and solutions in the early stages of development, we can only use the LTI 1.1 standard to achieve basic interaction with LMS. Deeper integration, such as automatic changes to assignment due dates, rescoring, etc., requires administrators to provide separate tokens and use the LMS Restful API to achieve interaction. This is not in line with the original intention for onekey deployment of our product design philosophy, and it also violates some safety concepts. A better implementation solution is to use the LTI1.3 standard, which is a more advanced, complete, and secure modern standard solution for interacting with LMS. However, it has some special requirements for LMS terminals, with higher costs on migration

## There are so many hidden functionalities waiting for you to find, enjoy:)
