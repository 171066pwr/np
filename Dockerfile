FROM maven:3.9.11-amazoncorretto-24-alpine
WORKDIR '/my-app'
COPY . .
COPY ./src/main/resources/appsettings.docker.properties ./src/main/resources/appsettings.properties
RUN mvn clean package
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.mycompany.app.AppEntry"]
