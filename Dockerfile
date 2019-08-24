FROM openjdk:8
ADD target/FlairsTechWorkshop.jar FlairsTechWorkshop.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "FlairsTechWorkshop.jar"]