def Email(status,emailUser){
    emailext body: """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Alter</title>
        </head>
        <body marginheight="0" offset="0" style="text-align: center">
            <img src="http://10.0.0.11:8080/static/c1130d2a/images/svgs/logo.svg" alt="logo" width="80" height="100">
            <h2>项目名称: ${JOB_NAME}</h2>
            <h2>构建人：${emailUser}</h2>
            <h2>构建状态: ${status}</h2>
            <h2>构建编号: ${BUILD_ID}</h2>
            <h2>项目地址：<a href="${BUILD_URL}" target="_blank">${BUILD_URL}</a></h2>
            <h2>构建日志：<a href="${BUILD_URL}console" target="_blank">${BUILD_URL}console</a></h2>
        </body>
        </html>""",
        subject: "Jenkins-${JOB_NAME}项目构建信息",
        to: emailUser
}
