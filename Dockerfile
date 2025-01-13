FROM maven:3.8.3-openjdk-17 AS builder

RUN mkdir /build
WORKDIR /build

COPY ./pom.xml /build/pom.xml
RUN ["mvn", "dependency:go-offline"]

COPY . /build
RUN ["sh", "build.sh"]

FROM maven:3.8.3-openjdk-17

COPY --from=builder /build/target/rebate-service-1.0-SNAPSHOT.jar /application.jar

EXPOSE 8080
CMD java -jar /application.jar


