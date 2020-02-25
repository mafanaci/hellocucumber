pipeline {
    agent any
    tools{
        maven 'Maven3'
    }
    stages {
        stage('Initializing') {
            steps {
                echo "Building my app :)"
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
            }
        }
        stage('Build') {
            steps {
                bat "mvn -Dmaven.test.failure.ignore=false clean package"
            }
        }
        stage('Hygieia'){
            steps{
                hygieiaTestPublishStep buildStatus: 'Success', testApplicationName: 'HelloCucumberJenkins', testEnvironmentName: 'Dev', testFileNamePattern: 'cucumber.json', testResultsDirectory: '/target', testType: 'Functional'
            }
        }
    }
}