pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'ashiksp123/springlearningprojects:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ashiksp1994/SpringLearningProjects.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    dockerImage = docker.build("${env.DOCKER_IMAGE}")
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    dockerImage.inside {
                        sh './mvnw clean test'
                    }
                }
            }
        }
        stage('Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials-id') {
                        dockerImage.push()
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build, test, and push completed successfully.'
        }
        failure {
            echo 'Build, test, or push failed.'
        }
    }
}
