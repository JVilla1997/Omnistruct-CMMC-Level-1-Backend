FROM eclipse-temurin:17-jdk-jammy
COPY ./target/OmniCMMC-0.0.1-SNAPSHOT.jar OmniCMMC-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "OmniCMMC-0.0.1-SNAPSHOT.jar"]