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
    response = response["branches"][0]["status"]["qualityGateStatus"]
    return response
}

// 搜索Sonar项目
def SearchProject(projectName) {
    apiUrl = "projects/search?projects=${projectName}"
    response = HttpRequest("GET",apiUrl,'')
    response = readJSON text: """${response.content}"""
    response = response["paging"]["total"]
    if (response.toString() == "0") {
        return "false"
    } else {
        return "true"
    }
}

// 创建Sonar项目
def CreateProject(projectName) {
    apiUrl = "projects/create?name=${projectName}&project=${projectName}"
    response = HttpRequest("POST",apiUrl,'')
    return response
}

// 配置项目质量规则
def SetSonarRule(language,qualityProfile,projectName) {
    apiUrl = "qualityprofiles/add_project?language=${language}&qualityProfile=${qualityProfile}&project=${projectName}"
    response = HttpRequest("POST",apiUrl,'')
    return response
}

// 获取质量阈ID
def GetQualityGateID(gateName) {
    apiUrl = "qualitygates/show?name=${gateName}"
    response = HttpRequest("GET",apiUrl,'')
    response = readJSON text: """${response.content}"""
    response = response["id"]
    return response
}

// 配置项目质量阈
def SetQualitygates(projectKey,gateName) {
    gateId = GetQualityGateID("${gateName}")
    apiUrl = "qualitygates/select?projectKey=${projectKey}&gateId=${gateId}"
    response = HttpRequest("POST",apiUrl,'')
    return response
}
