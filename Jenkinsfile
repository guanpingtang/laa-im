pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
    stages {
         stage('copy') {
             steps {
                 sh 'cp main/target/main-0.0.1-SNAPSHOT.jar /root/laa/'
             }
         }
    }
    stages {
         stage('start') {
              steps {
                 sh 'cd /root/laa && mv main-0.0.1-SNAPSHOT.jar laa.jar && sh plat.sh stop && sleep 5 && sh plat.sh start'
              }
         }
    }
}