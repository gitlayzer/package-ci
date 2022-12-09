package org.library

// 封装HTTP请求
def HttpRequest(requestType,requestUrl,requestBody) {
    def sonarServer = "http://10.0.0.11:9000/api"
    result = httpRequest authentication: 'sonar-admin-password',
        httpMode: requestType,
        contentType: "APPLICATION_JSON",
        consoleLogResponseBody: true,
        ignoreSslErrors: true,
        requestBody: requestBody,
        url: "${sonarServer}/${requestUrl}"
    return result
}


// 获取Sonar项目状态
def GetProjectStatus(projectName) {
    apiUrl = "project_branches/list?project=${projectName}"
    response = HttpRequest("GET",apiUrl,'')
    response = readJSON text: """${response.content}"""
    return response
}
