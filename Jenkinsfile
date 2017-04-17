pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
            wrap([$class: 'Xvnc', useXauthority: true]) {
                    sh 'mvn clean test'
                }
            }
        }
        stage('Deploy') {
            steps {
                wrap([$class: 'Xvnc', useXauthority: true]) {
                    sh 'mvn clean package'
                    archiveArtifacts artifacts: '*/target/.jar, */target/site//.', fingerprint: true
                }
            }
        }
    }
}