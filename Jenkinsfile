pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/BhoomiBiradar/Devops_Pipeline_Practice.git'
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('Deploy Artifact') {
      steps {
        sh 'mvn deploy'
      }
    }
  }
}
