package org.library

def SonarScan(projectName,projectDescription,projectPath,version,runOps='',projectId='',commit_sha='',branchName=''){
    withSonarQubeEnv("sonarqube"){
        def scannerHome = "/jenkins/software/sonar-scanner/"
        if (runOps == "GitlabPush") {
            sh """
                ${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} \
                                                 -Dsonar.projectName=${projectName} \
                                                 -Dsonar.projectVersion=${version} \
                                                 -Dsonar.ws.timeout=30 \
                                                 -Dsonar.projectDescription="${projectDescription}" \
                                                 -Dsonar.sources="${projectPath}" \
                                                 -Dsonar.sourceEncoding=UTF-8 \
                                                 -Dsonar.java.binnaries=target/classes \
                                                 -Dsonar.analysis.mode=preview \
                                                 -Dsonar.gitlab.project_id=${projectId} \
                                                 -Dsonar.gitlab.commit_sha=${commit_sha} \
                                                 -Dsonar.gitlab.ref_name=${branchName}
                """
        } else {
            sh """
                ${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} \
                                                 -Dsonar.projectName=${projectName} \
                                                 -Dsonar.projectVersion=${version} \
                                                 -Dsonar.ws.timeout=30 \
                                                 -Dsonar.projectDescription="${projectDescription}" \
                                                 -Dsonar.sources="${projectPath}" \
                                                 -Dsonar.sourceEncoding=UTF-8 \
                                                 -Dsonar.java.binnaries=target/classes
                """
        }
    }
}
