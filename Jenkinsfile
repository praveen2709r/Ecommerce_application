pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                 checkout scm
            }
        }

        stage('Build API Gateway') {
            steps {
                dir('api-gateway/api-gateway') {
                    bat 'mvn clean package -DskipTests'
                }
            }
            }

        stage('Build Order Service') {
            steps {
                dir('order-service/order-service'){
                    bat 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Build Product Service'){
            steps {
                dir('product-service/product-service'){
                    bat 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Build Service discovery'){
            steps {
                dir('service-discovery/service-discovery'){
                    bat 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Docker Build - Product service'){
            steps {
                dir('product-service/product-service'){
                    bat 'docker build -t product-service:latest .'
                }
            }
        }
        stage('Docker Build - Order service'){
            steps {
                dir('order-service/order-service'){
                    bat 'docker build -t order-service:latest .'
                }
            }
        }
        stage('Docker Build - eureka server'){
            steps {
                dir('service-discovery/service-discovery'){
                    bat 'docker build -t eureka-server:latest .'
                }
            }
        }
        stage('Docker Build - api gateway'){
            steps {
                dir('api-gateway/api-gateway'){
                    bat 'docker build -t api-gateway:latest .'
                }
            }
        }
        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: '4139cef3-a5b9-4682-a238-d5f410c388c8',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_TOKEN'
                )]) {
                    bat 'docker login -u "%DOCKER_USERNAME%" -p "%DOCKER_TOKEN%"'
                }
            }
        }
        stage('push order service'){
            steps {
                bat 'docker tag order-service:latest p2709/order-service'
                bat 'docker push p2709/order-service'
            }
        }
        stage('push product service'){
             steps {
                 bat 'docker tag product-service:latest p2709/product-service'
                 bat 'docker push p2709/product-service'
             }
        }
        stage('push api gateway'){
             steps {
                 bat 'docker tag api-gateway:latest p2709/api-gateway'
                 bat 'docker push p2709/api-gateway'
             }
        }
        stage('push eureka server'){
             steps {
                 bat 'docker tag eureka-server:latest p2709/eureka-server'
                 bat 'docker push p2709/eureka-server'
             }
        }
    }
}