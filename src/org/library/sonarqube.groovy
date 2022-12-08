package org.library

def SonarScan(projectName,projectDescription,projectPath,version){
    withSonarQubeEnv("sonarqube"){
        def scannerHome = "/jenkins/software/sonar-scanner/"
        sh """
        ${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} \
                                         -Dsonar.projectName=${projectName} \
                                         -Dsonar.projectVersion=${version} \
                                         -Dsonar.ws.timeout=30 \
                                         -Dsonar.projectDescription="${projectDescription}" \
                                         -Dsonar.sources="${projectPath}" \
                                         -Dsonar.sourceEncoding=UTF-8 \
                                         -Dsonar.java.binnaries=target/classes \
        """
    }
    def qg = waitForQualityGate()
    if (qg.status != 'OK') {
        error "Pipeline aborted due to quality gate failure: ${qg.status}"
    }
}
