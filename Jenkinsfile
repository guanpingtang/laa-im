pipeline {
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('copy') {
            steps {
                sh 'cp /root/.jenkins/workspace/laa/main/target/main-0.0.1-SNAPSHOT.jar /root/laa && cd /root/laa/ && mv main-0.0.1-SNAPSHOT.jar laa.jar'
            }
        }
        stage('stop') {
            steps {
                sh 'cd /root/laa/ && sh plat.sh stop && sleep 5'
            }
        }
        stage('start') {
            steps {
                sh 'cd /root/laa/ && sh plat.sh start'
            }
        }
    }
}
