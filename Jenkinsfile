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

//     stage('Deploy Artifact') {
//       steps {
//         sh 'mvn deploy'
//       }
//     }

    stage('Docker Build') {
      steps {
        // Build Docker image with Jenkins build number as tag
        sh 'docker build -t bhoomirb/jenkinstest:$BUILD_NUMBER .'

      }
    }

    stage('Docker Login') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKERHUB_CREDS_USR', passwordVariable: 'DOCKERHUB_CREDS_PSW')]) {
        sh 'echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin'
        }
      }
    }


    stage('Docker Push') {
      steps {
        sh 'docker push bhoomirb/jenkinstest:$BUILD_NUMBER'
      }
    }

    stage('Kubernetes Deploy') {
      steps {
        // Apply Kubernetes manifests (make sure they reference the image with $BUILD_NUMBER)
        sh 'kubectl apply -f k8s/deployment.yaml'
        sh 'kubectl apply -f k8s/service.yaml'
      }
    }

    stage('Verify Kubernetes Deployment') {
      steps {
        // Check deployments, pods, and services
        sh 'kubectl get deployments'
        sh 'kubectl get pods'
        sh 'kubectl get services'
        // Optional: wait until deployment is available
        sh 'kubectl wait --for=condition=available --timeout=60s deployment/addressbook'
      }
    }
  }

  post {
    always {
      // Clean up Docker login session
      sh 'docker logout'
    }
  }
}
