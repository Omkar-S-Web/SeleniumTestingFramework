pipeline {
    agent any
    
    triggers {
        cron('H 9 * * 1-5') 
    }

    tools {
        maven 'Maven_3.9.11' 
        jdk 'Java_21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test' 
            }
        }
    }

    post {
        always {
            script {
                // 1. Copy Report
                bat 'xcopy "D:\\Testing report\\*.html" "%WORKSPACE%\\" /Y'
                archiveArtifacts artifacts: '*.html', allowEmptyArchive: true
                
                // 2. Copy Screenshots
                bat 'if exist "D:\\Testing failures SS\\*.png" xcopy "D:\\Testing failures SS\\*.png" "%WORKSPACE%\\screenshots\\" /Y /I'
                archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
            }
        }
        success {
            mail to: 'your-email@gmail.com',
                 subject: "SUCCESS: Naukri Automation - Build #${env.BUILD_NUMBER}",
                 body: "All tests passed successfully.\n\nView Report: ${env.BUILD_URL}artifact/"
        }
        failure {
            mail to: 'your-email@gmail.com',
                 subject: "!!! URGENT FAILURE !!!: Naukri Automation - Build #${env.BUILD_NUMBER}",
                 body: "The Naukri Automation suite has FAILED.\n\nCheck the report here: ${env.BUILD_URL}artifact/"
        }
    }
}