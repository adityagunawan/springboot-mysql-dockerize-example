FROM openjdk:8
EXPOSE 8081
ADD target/springboot-mysql.jar springboot-mysql.jar
CMD ["nohup","java","-jar","/springboot-mysql.jar"]