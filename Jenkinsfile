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
                bat "exit 0"
            }
        }
        stage('Build') {
            steps {
                bat "mvn -Dmaven.test.failure.ignore=true clean compile"
            }
        }
        stage('Test') {
            steps {
                catchError(message: 'Execution of BDD tests failed', buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn -Dmaven.test.failure.ignore=false test"
                }
            }
        }
        stage('Package') {
            steps {
                catchError(message: 'Execution of BDD tests failed', buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn -Dmaven.test.failure.ignore=true package"
                }
            }
        }
        stage('Hygieia'){
            steps{
                hygieiaTestPublishStep buildStatus: 'Success', testApplicationName: 'HelloCucumberJenkins', testEnvironmentName: 'Dev', testFileNamePattern: 'cucumber.json', testResultsDirectory: '/target', testType: 'Functional'
                bat "exit 0"
            }
        }
    }
}