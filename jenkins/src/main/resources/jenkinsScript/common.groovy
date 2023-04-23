def isLinux() {
    if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
        return false
    } else {
        return true
    }
}

def isBlank(str) {
    return isNullOrBlack(str)
}

def checkCodeCheckOutDir(checkoutDir) {
    if(isNullOrBlank(checkoutDir)){
        error "checkoutDir is null"
    }
    if(checkoutDir.startWith("..")){
        error "checkoutDir cannot start with '..'"
    }
}

def checkDockerImageExists(repository, tag, throwExceptionIfNotExists){
    def cmd = "docker images | grep ${repository} | grep ${tag} | wc -l"
    if(!isUnix()){
        echo "Only support unix/linux platform"
        return false
    }
    def out = sh script: cmd, returnStdout true
    if('0'=="${out}".trim()){
        if(throwExceptionIfNotExists){
            throw new Exception("Docker image: ${repository}:${tag} not found")
        }
        return false
    }
    return true
}

def checkHttpUrl(targetIps, checkPort, checkRequestMethod, checkUrl, checkStatusCode){
    if(checkUrl && checkUrl != ''){
        println "check app is started"
        println "targetIps : $targetIps"
        println "checkPort : $checkRequestMethod"
        println "checkRequestMethod : $checkRequestMethod"
        println "checkUrl : $checkUrl"
        println "checkStatusCode : $checkStatusCode"
        def targetIpArray = targetIps.splt(",")
        for(def i=0;i<targetIpArray.length;i++){
            def httpUrl = "http://" + targetIpArray[i] + ":" + checkPort + checkUrl
            println "httpUrl = $httpUrl"
            def index = 0
            def isStart = -1
            //两分钟超时
            while(index < 120){
                isStart = doCheckHttpUrl(httpUrl, checkRequestMethod, 5000, checkStatusCode)
                if(isStart <0){
                    sleep time: 1000, unit: "MILLISECONDS"
                }else {
                    break
                }
                index ++
            }
            if(isStart !=0) {
                error "The status code is not same as checkCode"
            }
        }
    }
}

@NonCPS
def doCheckHttpUrl(httpUrl, requestMethod, timeout, statusCode){
    def urlconnect = null
    try{
        urlconnect = new URL(httpUrl).openConnection()
        urlconnect.setRequestMethod(requestMethod)
        urlconnect.setConnectTimeout(timeout)
        urlconnect.setReadTimeout(timeout)
        urlconnect.connect()
        def code = urlconnect.getResponseCode()
        println "The statuscode returned by $httpUrl is $code"
        if("$code" == statusCode){
            return 0
        } else if("$code" == "503" || "$code" == "502"|| "$code" == "404"){
            return -2
        } else {
            return -1
        }
    }catch (e) {
        print "$e"
    } finally {
        try {
            urlconnect.disconnect();
        } catch (ignore){}
    }
    return -1
}






