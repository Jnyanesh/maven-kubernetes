pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }

    environment {
        DOCKER_IMAGE = 'demo-app-web:latest'
        PATH = "${env.PATH}:/home/jnyanesh/.local/bin"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url:'https://github.com/Jnyanesh/maven-kubernetes.git'
            }
        }

        stage('Build & Package') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh "minikube start --driver=docker --memory=2048"
                // Load the locally built image into the Minikube cluster
                sh "minikube image load ${DOCKER_IMAGE}"
                
                // Deploy the application
                sh "kubectl apply -f k8s/deployment.yaml"
                sh "kubectl apply -f k8s/service.yaml"
                
                // Verify rollout
                sh "kubectl rollout status deployment/demo-app-web"
            }
        }
    }

    post {
        success {
            emailext (
                subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Kubernetes Deployment succeeded!\nCheck: ${BUILD_URL}",
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
