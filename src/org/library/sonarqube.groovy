package org.library

// 代码扫描
def SonarScan(projectName,projectDescription,projectPath){
    def sonarServer = "http://10.0.0.11:9000"
    sonarDate = sh returnStdout: true, script: 'date +%Y%m%d%H%M%S'
    sonarDate = sonarDate - "\n"
    sh """
    sonar-scanner -Dsonar.host.url=${sonarServer} \
              -Dsonar.projectKey=${projectName} \
              -Dsonar.projectName=${projectName} \
              -Dsonar.projectVersion=${sonarDate} \
              -Dsonar.login=admin \
              -Dsonar.password=xxzx@123 \
              -Dsonar.ws.timeout=30 \
              -Dsonar.projectDescription="${projectDescription}" \
              -Dsonar.sources="${projectPath}" \
              -Dsonar.sourceEncoding=UTF-8 \
              -Dsonar.java.binnaries=target/classes \
    """
}
