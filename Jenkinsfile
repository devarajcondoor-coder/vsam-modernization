pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/devarajcondoor-coder/vsam-modernization.git'
            }
        }

        stage('Build Verification') {
            steps {
                sh 'echo "Build triggered successfully from GitHub"'
                sh 'echo "Code checkout verified"'
            }
        }
    }
}
