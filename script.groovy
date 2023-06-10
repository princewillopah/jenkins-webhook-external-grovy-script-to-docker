def buildJar() {
    echo "this application will be comited github which will trigger a build in jenkins via webhook"
    sh 'mvn package'
} 

def buildImage() {
    echo "building image && push image to docker hub"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                            sh '''
                                docker build -t princewillopah/jenkins-webhook-external-grovy-script-to-docker:jv-mvn-app-1.0 .
                                echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                                docker push princewillopah/jenkins-webhook-external-grovy-script-to-docker:jv-mvn-app-1.0
                            '''
                        }
    // withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
    //     sh 'docker build -t nanajanashia/demo-app:jma-2.0 .'
    //     sh "echo $PASS | docker login -u $USER --password-stdin"
    //     sh 'docker push nanajanashia/demo-app:jma-2.0'
    // }


} 

def deployApp() {
    echo 'deploying the application to AWS ...'
} 

return this