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
