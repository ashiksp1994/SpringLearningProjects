pipeline {
    agent {
        docker { image 'maven:3.6.3-jdk-11' }
    }
    environment {
        DOCKER_CLI = '/usr/bin/docker'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/yourusername/your-repo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('your-docker-image:latest')
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -d -p 8080:8080 your-docker-image:latest'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
