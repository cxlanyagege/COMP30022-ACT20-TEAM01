# IT Project

## Activity: 20 Team: 1
Teaching Support Platform for Applying Special Consideration

### Deployment Test
- [Dev Login](http://121.41.46.143:8081/login.html)
- [New Dashboard](http://121.41.46.143:8081/profile.html)

### Sample Usage
|     | User type | Password | Sample request history |
|-----|-----------|----------|------------------------|
|Demo | Student   | 123456   | Built-in 1 sample      |
|Test | Student   | 123456   | Empty sample           |

### Build & Run
#### Prerequisits
1. Install [Adoptium Temurin OpenJDK](https://adoptium.net/zh-CN/temurin/releases/), recommended 17+
2. (Optional) Install [Gradle](https://gradle.org/install/)

#### Clone
Clone the repository to your local environment

`https://github.com/cxlanyagege/COMP30022-ACT20-TEAM01.git`

#### Build
Go to project's root directory

`cd COMP30022-ACT20-TEAM01`

Build project with Gradle

`.\gradlew clean build`

Or if you have already installed Gradle, type

`gradle clean build`

#### Run
`java -jar build/libs/application-0.0.1-SNAPSHOT.jar`
