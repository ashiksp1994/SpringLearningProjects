pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'springboot-app'
        DOCKER_REGISTRY = 'your_docker_registry'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/your-repo/springboot-app.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    docker.image('maven:3.6.3-jdk-11').inside {
                        sh 'mvn clean package'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    docker.image('maven:3.6.3-jdk-11').inside {
                        sh 'mvn test'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    docker.build("${DOCKER_REGISTRY}/${DOCKER_IMAGE}")
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        docker.image("${DOCKER_REGISTRY}/${DOCKER_IMAGE}").push()
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker stop springboot-app || true'
                    sh 'docker rm springboot-app || true'
                    sh 'docker run -d -p 8080:8080 --name springboot-app ${DOCKER_REGISTRY}/${DOCKER_IMAGE}'
                }
            }
        }
    }
}
