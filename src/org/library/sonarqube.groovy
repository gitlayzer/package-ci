package org.library

// 代码扫描
def SonarScan(projectName,projectDescription,projectPath,version){
    def sonarServer = "http://10.0.0.11:9000"
    sh """
    sonar-scanner -Dsonar.host.url=${sonarServer} \
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
              # 因为我的代码没有测试，所以这里就不加了，大家自己找的项目应该是有测试的记得加
              # -Dsonar.java.test.binaries=target/test-classes \
              # -Dsonar.java.surefire.report=target/surefire-reports
    """
}
