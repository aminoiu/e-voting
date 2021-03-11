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
                        sh 'mvn test'
                    }
                }
    stage('Build docker images') {
                     stages {
                         stage('Build backend docker image') {
                             steps {
                                 script {
                                     dir('Backend') {
                                         echo 'Building docker image for back-end project...'
                                         backendImage = docker.build backendImageName
                                     }
                                 }
                             }
                         }
         				stage('Build frontend docker image ') {
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
     stage('Push docker images') {
     			stages {
                     stage('Push backend image') {
                         steps {
                             script {
                                 dir('Backend') {
                                     echo 'Pushing back-end image...'
                                     docker.withRegistry('', dockerRegistryCredential) {
                                         backendImage.push();
                                     }
                                 }
                             }
                         }
                     }
                     stage('Push frontend image') {
                         steps {
                             script {
                                 dir('Frontend') {
                                     echo 'Pushing front-end image...'
                                     docker.withRegistry('', dockerRegistryCredential) {
                                         frontendImage.push();
                                     }
                                 }
                             }
                         }
                     }
                 }
     		}

     		stage('Deploy') {
                        steps {
                            script {
                                sshagent(credentials: ['jtom-app-instance']) {
                                    withCredentials([
                                            usernamePassword(credentialsId: dockerRegistryCredential,
                                                    usernameVariable: 'username',
                                                    passwordVariable: 'password')
                                    ]) {
                                        def backendContainerName = "backend"
                                        def frontendContainerName = "frontend"
                                        sh("""sshpass -p "jenkins" ssh -o StrictHostKeyChecking=no jenkins@evoting-vm << EOF
                                              docker login -u ${username} -p ${password}
                                              docker stop ${backendContainerName}
                                              docker stop ${frontendContainerName}
                                              docker rm ${backendContainerName}
                                              docker rm ${frontendContainerName}
                                              docker image rm ${backendImageName}
                                              docker image rm ${frontendImageName}
                                              docker pull ${backendImageName}
                                              docker pull ${frontendImageName}
                                              docker run --rm -d -p 8080:8080 --name ${backendContainerName} ${backendImageName}
                                              docker run --rm -d -p 80:80 --name ${frontendContainerName} ${frontendImageName}
                                              exit
                                        EOF
                                        """)
                                    }
                                }
                            }
                        }
                    }
    }
}
