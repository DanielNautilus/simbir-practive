pipeline {
    agent any

    stages {
        stage('Build and Test') {
            steps {
                bat '''
                    set PATH=%PATH%;C:\\Program Files\\maven\\apache-maven-3.9.4\\bin
                    mvn clean test
                '''
            }
        }
    }

    post {
        always {
            script {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}
