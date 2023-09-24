# IT Project: Teaching Support Platform for Applying Special Consideration

## Team Information
- **Activity Code**: ACT20
- **Team Number**: TEAM01

## Live Demo
- [Developer Login](http://121.41.46.143:8081/login.html)
- [User Dashboard](http://121.41.46.143:8081/profile.html)

## Sample User Information
| Identifier | User Role | Password | Sample Request History |
|------------|-----------|----------|------------------------|
| Demo       | Student   | 123456   | Contains 1 built-in sample request |
| Test       | Student   | 123456   | No sample requests      |

## Build & Run Instructions

### Prerequisites
1. Install [Adoptium Temurin OpenJDK](https://adoptium.net/zh-CN/temurin/releases/). Recommended version: 17 or higher.
2. Optionally, install [Gradle Build Tool](https://gradle.org/install/).

### Cloning the Repository
Clone the repository to your local machine:

```
git clone https://github.com/cxlanyagege/COMP30022-ACT20-TEAM01.git
```

### Building the Project
Navigate to the root directory of the project:

```
cd COMP30022-ACT20-TEAM01
```

To build the project using the Gradle Wrapper included in the repository, execute:

```
.\gradlew clean build
```

If you have Gradle installed, you can also run:

```
gradle clean build
```

### Running the Application
Execute the following command to run the application:

```
java -jar build/libs/application-0.0.1-SNAPSHOT.jar
```

### Steps required on testing locally
1. Make sure MySQL is open before testing
2. Navigate to application.yml file in the spring folder, make changes to the below content: 

datasource:
    url: jdbc:mysql://(your-localhost address, for instance, 127.0.0.1:3306, you can look it up on your MySQL workbench starting page)/student_requests(this is a schema in MySQL workbench, no need to worry)
    username: root <-- change this to your MySQL workbench sever name, it will show on the starting page under each connection
    password: 54!45L^i <-- change to your password required to log in to MySQL
    driver-class-name: com.mysql.cj.jdbc.Driver

3. In the same application.yml, make changes to the below content as well:

upload:
  localPath: /Users/wangzeyu/Desktop/IT Project/IT-project/test uploaded attachments/ <-- where you would like uploaded files to be stored
  accessPath: /upload/

4. Navigate to the vue folder, change 2 addresses (localhost:8080) that are in src->config->config.js to your localhost address

5. The axios.defaults.baseURL in src->utils->communication.js should be changed to your localhost address as well

6. Run the springboot project first, and then run the vue project, you should now be seeing website opened
