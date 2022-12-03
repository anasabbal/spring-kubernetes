FROM gcr.io/distroless/java:17
COPY spring-kubernetes-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
USER 1002
CMD ["/app.jar"]