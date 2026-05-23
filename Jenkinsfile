pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }

    environment {
        DOCKER_IMAGE = 'demo-app-web:latest'
        CONTAINER_NAME = 'demo-app-container'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url:'https://github.com/Naveen04jan/ven.git', 
                credentialsId: 'github-token'
            }
        }

        stage('Build & Package') {
            steps {
                // This command compiles, runs tests, and packages the app as an executable JAR using Spring Boot plugin
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Run Docker Container') {
            steps {
                // Stop and remove any existing container running the app
                sh "docker stop ${CONTAINER_NAME} || true"
                sh "docker rm ${CONTAINER_NAME} || true"
                
                // Run the new container in detached mode, mapping port 5000
                sh "docker run -d --name ${CONTAINER_NAME} -p 5000:5000 ${DOCKER_IMAGE}"
            }
        }
    }

    post {
        success {
            emailext (
                subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Build, Docker Image Creation, and Deployment succeeded!\nCheck: ${BUILD_URL}",
                to: "itsmejnyanesh@gmail.com"
            )
        }

        failure {
            emailext (
                subject: "FAILED: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Pipeline failed!\nCheck: ${BUILD_URL}",
                to: "itsmejnyanesh@gmail.com"
            )
        }
    }
}
