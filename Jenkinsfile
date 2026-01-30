pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/devarajcondoor-coder/vsam-modernization.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh '''
                docker run --rm \
                  -v "$PWD":/app \
                  -w /app \
                  maven:3.9.6-eclipse-temurin-17 \
                  mvn clean package
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t vsam-api .'
            }
        }
    }
}
