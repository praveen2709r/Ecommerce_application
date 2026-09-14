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
    }
}