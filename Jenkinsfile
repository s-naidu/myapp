pipeline {
    agent any 
    
    options {
    // Stop the build early in case of compile or test failures
    skipStagesAfterUnstable()
  }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: ' https://github.com/s-naidu/myapp.git'
            }
        }

    stage('Clean & Prepare') {
        steps{
            sh  'chmod +x ./gradlew'
            sh "./gradlew clean"
        }
        }
    stage('Quality Tests') {
        steps{
            sh "./gradlew lintDebug"
            //sh './gradlew checkStyle'
            //androidLint pattern: 'app/build/reports/lint-results-debug.xml'
            //sh './gradlew -Pci --console=plain :app:lintDebug -PbuildDir=lint'
        }
        }    
        
    stage('Unit test') {
      steps {
        // Compile and run the unit tests for the app and its dependencies
        sh './gradlew testDebugUnitTest'
       // sh './gradlew test' 
        sh 'find . -name "TEST-*.xml" -exec touch {} \\;'
        // Analyse the test results and update the build result as appropriate
        junit '**/TEST-*.xml'
      }
      
      post {
        always {
    jiraSendBuildInfo branch: 'ANPIPELINE-3', site: 'sudarshandn.atlassian.net'
            
        }
 }
    }
    stage('Build APK') {
      steps {
        // Finish building and packaging the APK
        sh './gradlew assembleDebug'

      }
      post {
        always {
    jiraSendBuildInfo branch: 'ANPIPELINE-3', site: 'sudarshandn.atlassian.net'
            
        }
 }
    }

        stage('Upload app to app centre') {
            steps {
        // Build the app in release mode, and sign the APK using the environment variables
        sh './gradlew assembleRelease'

        // Archive the APKs so that they can be downloaded from Jenkins
        archiveArtifacts '**/*.apk'

        // Upload the APK to Google Play
   
        appCenter apiToken: '9e263501c7838401cf498b74a79fe851f56ebcca',
        ownerName: 'dsnaidu07-gmail.com',
        appName: 'androidTest',
        pathToApp: '**/*app-debug.apk',
        distributionGroups: 'ALphaTestingGroup'
     }
post {
        always {
    jiraSendBuildInfo branch: 'ANPIPELINE-3', site: 'sudarshandn.atlassian.net'
            
        }
 }
     
    
      }
  
       stage('Run Docker Compose File')
    {
        sh 'sudo docker-compose build'
        sh 'sudo docker-compose up -d'
    }
  stage('PUSH image to Docker Hub')
    {
            //docker.withRegistry( 'https://registry.hub.docker.com', 'DockerHubPassword' ) {
             
             sh 'sudo docker login -u "sudarshandn" -p "DockerHub@2016" docker.io'
             sh 'sudo docker tag ecommerce_demo_site_web sudarshandn/ecommerce_01'
             sh 'sudo docker push sudarshandn/ecommerce_01:latest'
           
          
    }   
    }
  }
  
