
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17

WORKDIR /app
# Copy the compiled Java application JAR file into the container
COPY ./target/aws.jar /app
# Expose the port the Spring Boot application will run on
EXPOSE 7834
# Command to run the application
CMD ["java", "-jar", "aws.jar"]