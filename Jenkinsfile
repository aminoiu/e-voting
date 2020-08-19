pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3'
        jdk 'jdk9'
    }
    options {
            disableConcurrentBuilds()
        }
        environment {
            def repoName = "";
            def branchName = "";
            def versionName = "";
            def frontendImageName = "";
            def backendImageName = "";
            def dockerRegistryCredential = 'jtom'
            def dockerRegistry = "aminoiu/evoting"
            def backendImage = '';
            def frontendImage = '';
         }
    stages {
    stage('Initialization') {
                steps {
                    script {
                        echo 'Initialization'
                        repoName = "https://github.com/aminoiu/e-voting.git"
                        backendImageName = "${dockerRegistry}:backend"
                        frontendImageName = "${dockerRegistry}:frontend"
                    }

                }
            }
    stage('Clone') {
                steps {
                    script {
                        echo 'Clone repository'
                        checkout scm
                    }
                }
            }
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
    stage('Build docker images') {
                     stages {
                         stage('build docker image for back-end project') {
                             steps {
                                 script {
                                     dir('Backend') {
                                         echo 'Building docker image for back-end project...'
                                         backendImage = docker.build backendImageName
                                     }
                                 }
                             }
                         }
         				stage('build docker image for front-end project') {
         					steps {
         						script {
         							dir('Frontend') {
         								echo 'Building docker image for front-end project...'
         								frontendImage = docker.build frontendImageName
         							}
         						}
         					}
         				}
                     }
         		}
    }
}
