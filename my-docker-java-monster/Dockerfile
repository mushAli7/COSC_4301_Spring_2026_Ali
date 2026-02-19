# Use official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy project files into container
COPY . /app

# Compile Java source files
RUN javac *.java

# Run the application
CMD ["java", "Main"]
