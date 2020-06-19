FROM openjdk:14-alpine
COPY build/libs/calculator-backend-*-all.jar calculator-backend.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "calculator-backend.jar"]
