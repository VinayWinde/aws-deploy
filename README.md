📦 Spring Boot App Deployment on AWS (Fargate + CodePipeline)
This project demonstrates a CI/CD pipeline deploying a Spring Boot application to Amazon ECS (Fargate) using:

1-AWS CodePipeline (CI/CD)

2-AWS CodeBuild (Build & push Docker image)

3-Amazon ECR (Docker image storage)

4-Amazon ECS (Fargate) (Deployment platform)

🧱 Architecture Overview
text
Copy
Edit
GitHub/CodeCommit ─▶ CodePipeline ─▶ CodeBuild ─▶ ECR
                                       │
                                    (image)
                                       │
                                    ECS (Fargate)
                                       │
                                Public IP/Load Balancer
🚀 How It Works
✅ 1. Code Commit → Pipeline Trigger
Push to main branch → triggers CodePipeline

✅ 2. CodeBuild (buildspec.yml)
Builds Docker image

Tags & pushes image to ECR

✅ 3. ECS Service (Fargate)
CodePipeline deploys latest image

Task runs on Fargate

Public IP or Load Balancer exposes the app

📁 Project Structure
bash
Copy
Edit
.
├── Dockerfile
├── buildspec.yml
├── src/                   # Spring Boot app code
├── application.properties # Should include server.port=7834 and address=0.0.0.0
└── README.md
🐳 Dockerfile (sample)
dockerfile
Copy
Edit
FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
📦 buildspec.yml
yaml
Copy
Edit
version: 0.2

phases:
  pre_build:
    commands:
      - echo Logging in to Amazon ECR...
      - aws ecr get-login-password --region $AWS_DEFAULT_REGION | docker login --username AWS --password-stdin <your-account-id>.dkr.ecr.<region>.amazonaws.com
  build:
    commands:
      - echo Building the Docker image...
      - docker build -t springboot-app .
      - docker tag springboot-app:latest <your-ecr-uri>:latest
  post_build:
    commands:
      - echo Pushing the Docker image...
      - docker push <your-ecr-uri>:latest
      - echo Writing image definitions...
      - printf '[{"name":"<container-name>","imageUri":"%s"}]' <your-ecr-uri>:latest > imagedefinitions.json

artifacts:
  files: imagedefinitions.json
🌍 Accessing the App
After successful deployment, access the app at:

perl
Copy
Edit
http://<public-ip>:<port>/book/addbook
Make sure:

Security Group allows inbound on that port (7834)

Task has a public IP assigned

App binds to 0.0.0.0 and correct port

🛠 Troubleshooting Tips
⛔ Timeout? → Check if container listens on 0.0.0.0:<port>

⛔ No Public IP? → ECS task must be in public subnet with "Assign public IP = ENABLED"

⛔ Image not updating? → Check imagedefinitions.json was generated correctly

✅ Prerequisites
AWS CLI configured

IAM roles with required permissions

Docker installed (locally, for testing)

AWS services setup:

ECR repo

ECS Cluster + Service

CodeBuild project

CodePipeline

📬 Support
If something breaks or you need help customizing, feel free to ask in an issue or reach out.
