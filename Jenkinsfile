pipeline {
    agent any
    
    properties([
        pipelineTriggers([
            cron('H 9 * * 1-5') // Runs every weekday morning
        ])
    ])

    tools {
        // Must match the names you configured in Jenkins 'Global Tool Configuration'
        maven 'Maven_3.9.11' 
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
           // 1. Copy Report
            bat 'xcopy "D:\\Testing report\\*.html" "%WORKSPACE%\\" /Y'
            archiveArtifacts artifacts: '*.html', allowEmptyArchive: true
            
            // 2. Copy Screenshots (if any exist)
            // We use 'if exist' to prevent errors if there are no failures/screenshots
            bat 'if exist "D:\\Testing failures SS\\*.png" xcopy "D:\\Testing failures SS\\*.png" "%WORKSPACE%\\screenshots\\" /Y /I'
            
            // 3. Archive the screenshots folder from the Workspace
            archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
        }// These blocks now handle the emailing based on the result
    success {
        mail to: 'your-email@gmail.com',
             subject: "SUCCESS: Naukri Automation - Build #${env.BUILD_NUMBER}",
             body: "All tests passed successfully.\n\nView Report: ${env.BUILD_URL}artifact/"
    }
    failure {
        mail to: 'your-email@gmail.com',
             subject: "!!! URGENT FAILURE !!!: Naukri Automation - Build #${env.BUILD_NUMBER}",
             body: """
ATTENTION: The Naukri Automation suite has FAILED.

Status: ${currentBuild.currentResult}
Build Link: ${env.BUILD_URL}

Check the report and screenshots here: ${env.BUILD_URL}artifact/
"""
    }
}
}