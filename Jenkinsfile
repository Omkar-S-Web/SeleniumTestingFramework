pipeline {
    agent any

    tools {
        // Must match the names you configured in Jenkins 'Global Tool Configuration'
        maven 'Maven_3.9' 
        jdk 'Java_21'
    }

    stages {
        stage('Checkout') {
            steps {
                // This pulls your latest code from GitHub
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Runs Maven with your TestNG suite and Retry Logic
                bat 'mvn clean test' 
            }
        }
    }

    post {
        always {
            // Saves your Extent Reports even if tests fail
            archiveArtifacts artifacts: 'target/ExtentReport/*.html', fingerprint: true
            
            // Saves the failure screenshots from your D: drive
            archiveArtifacts artifacts: 'D:/Testing failures SS/*.png', allowEmptyArchive: true
        }
    }
}