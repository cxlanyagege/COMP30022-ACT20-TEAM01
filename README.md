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
