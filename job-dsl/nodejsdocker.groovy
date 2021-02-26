job('nodejsdockerexample') {
    scm {
        git('https://github.com/nirajKudos/jenkins-course.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('nirajKudos')
            node / gitConfigEmail('niraj@kudosfinance.in')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('nirajKudos/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
