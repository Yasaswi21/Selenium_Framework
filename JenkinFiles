pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'    // Ensure this tool name exists in Jenkins
        jdk 'JDK 17'           // Or change based on your local JDK
    }

    environment {
        REPORT_DIR = "results/reports"
        SCREENSHOT_DIR = "results/screenshots"
        LOG_DIR = "results/logs"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/Yasaswi21/Selenium_Framework.git'
            }
        }

        stage('Build & Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML(target: [
                    reportDir: "${REPORT_DIR}",
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }

        stage('Archive Screenshots & Logs') {
            steps {
                archiveArtifacts artifacts: "${SCREENSHOT_DIR}/**/*.png", allowEmptyArchive: true
                archiveArtifacts artifacts: "${LOG_DIR}/**/*.log", allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Build finished.'
        }
        failure {
            echo 'Build failed. Check reports and logs.'
        }
    }
}
