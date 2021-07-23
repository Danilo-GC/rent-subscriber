FROM gcr.io/distroless/java:11

COPY /build/libs /app
WORKDIR /app

CMD ["rent-subscriber-0.1-all.jar"]