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
    }
}