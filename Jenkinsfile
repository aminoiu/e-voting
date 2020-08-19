pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3'
        jdk 'jdk9'
    }
    stages {
        stage ('Build') {
                steps {
                    sh 'mvn clean install -DskipTests=true'
                }
        }
        stage('Test') {
                    steps {
                        echo 'Testing..'
                    }
                }
         stage('Deploy') {
                    steps {
                        echo 'Deploying....'
                    }
                }
    }
}
