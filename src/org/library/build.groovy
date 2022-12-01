package org.linrary

def Build(buildType, buildShell) {
    def buildTools = ["mvn": "maven", "ant": "ant", "gradle": "gradle"]
    
    println("当前构建类型为：${buildType}")
    
    buildHome = tool buildTools[buildType]
    
    sh "${buildHome}/bin/${buildType} ${buildShell}"
}
