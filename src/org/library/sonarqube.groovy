package org.library

// 代码扫描
def SonarScan(projectName,projectDescription,projectPath,version){
    def scannerHome = "/jenkins/software/sonar-scanner/"
    def sonarServer = "http://10.0.0.11:9000"
    sh """
    ${scannerHome}/bin/sonar-scanner -Dsonar.host.url=${sonarServer} \
                                     -Dsonar.projectKey=${projectName} \
                                     -Dsonar.projectName=${projectName} \
                                     -Dsonar.projectVersion=${version} \
                                     -Dsonar.login=admin \
                                     -Dsonar.password=xxzx@123 \
                                     -Dsonar.ws.timeout=30 \
                                     -Dsonar.projectDescription="${projectDescription}" \
                                     -Dsonar.sources="${projectPath}" \
                                     -Dsonar.sourceEncoding=UTF-8 \
                                     -Dsonar.java.binnaries=target/classes \
"""
}
