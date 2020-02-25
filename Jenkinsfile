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
         stage('SonarQube analysis') {
            steps {
                script {
                    if (env.GIT_BRANCH == 'develop') {
                        sonarTargetBranch = '-Dsonar.branch.target=master'
                    } else if (env.GIT_BRANCH == 'master') {
                        // the master branch must not have a target branch as it is the default one.
                        sonarTargetBranch = ''
                    } else {
                        sonarTargetBranch = '-Dsonar.branch.target=develop'
                    }
                    scannerHome = tool 'SonarQubeScanner'
                }
                 withSonarQubeEnv(credentialsId: 'SonarQube', installationName: 'SonarQube') {
                    bat 'echo "pulling from this git branch!...."+ ${GIT_BRANCH}'
                    bat "\"${scannerHome}/bin/sonar-scanner\" -Dproject.settings=build/sonar-project.properties -Dsonar.branch.name=${GIT_BRANCH} ${sonarTargetBranch} -Dsonar.projectVersion=${BUILD_NUMBER}-${GIT_BRANCH}"
                      //bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:4.2.0.1873:sonar'
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