FROM eclipse-temurin:17-jdk AS build

WORKDIR /src
COPY compile.sh ./
COPY src/ src/
COPY lib/runtime/ lib/runtime/
RUN ./compile.sh && java -jar enhsp25.jar -help >/dev/null

FROM eclipse-temurin:17-jre

WORKDIR /opt/enhsp
COPY --from=build /src/enhsp25.jar ./enhsp25.jar
COPY --from=build /src/lib/runtime/ ./lib/runtime/

ENTRYPOINT ["java", "-jar", "/opt/enhsp/enhsp25.jar"]
CMD ["-h"]
