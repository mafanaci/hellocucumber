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
            bat "mvn -Dmaven.test.failure.ignore=true clean package"
         }
    }
   }
   post {
           always {
               archiveArtifacts artifacts: 'target/cucumber.json', fingerprint: true
           }
       }
}