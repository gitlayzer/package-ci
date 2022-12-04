package org.library


def HttpRequest(requestType,requestUrl,requestBody) {
    def gitServer = "http://10.0.0.11/api/v4"
    withCredentials([string(credentialsId: 'gitlab-token', variable: 'gitlab')]) {
      println("${gitlab}")
      result = httpRequest customHeaders: [[maskValue: true, name: 'PRIVATE-TOKEN', value: "${gitlab}"]], 
          httpMode: requestType, 
          contentType: "APPLICATION_JSON", 
          consoleLogResponseBody: true, 
          ignoreSslErrors: true, 
          requestBody: requestBody, 
          url: "${gitServer}/${requestUrl}"
    }
    return result
}

// 更改提交状态
def ChangeCommitStatus(projectId,commitSha,status) {
    commitApi = "/projects/${projectId}/statuses/${commitSha}?state=${status}"
    response = HttpRequest("POST",commitApi,'')
    println(response) 
    return response
}
