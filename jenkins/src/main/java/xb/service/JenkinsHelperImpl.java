package xb.service;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xb.common_utils.HttpUtil;
import xb.entity.JenkinInstance;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class JenkinsHelperImpl implements JenkinsHelper {

    private String createJobWithToken(String jobName, String configXml) {
        String url = String.format("%s/createItem?name=%s", JenkinInstance.DefaultInstance().getJenkinsServerUrl(), jobName);
        String authInfo = JenkinInstance.DefaultInstance().getAuthInfo();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", ContentType.TEXT_XML.withCharset("utf-8").toString());
        headers.put("cache-control", "no-cache");
        headers.put("Authorization", "Basic " + authInfo);
        String result = HttpUtil.doPost(url, headers, configXml);
        return result;
    }

    private String createJobWithCrumb(String jobName, String configXml) throws IOException {
        String url = String.format("http://localhost:8080/jenkins/createItem?name=%s", jobName);
        String userName = "slowice";
        String pwd = "82315321";
        String authStr = userName + ":" + pwd;
        String basicAuth = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));  //username  password 自行修改  中间":"不可少
        String crumbUrl = "http://localhost:8080/jenkins/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,%22:%22,//crumb)";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + basicAuth);
        headers.put("cache-control", "no-cache");
        String crumb = HttpUtil.doGet(crumbUrl, headers).split(":")[1];
        return "";
    }

    private String customHttpMsg(String url, HttpRequest httpRequest) throws URISyntaxException, IOException {
        URI uri = new URI(url);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort());
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()),
                new UsernamePasswordCredentials("slowice", "111e77e482167dfc6ae47d27ea44db38aa"));
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicScheme = new BasicScheme();
        authCache.put(host, basicScheme);
        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build()) {
            HttpClientContext httpClientContext = HttpClientContext.create();
            httpClientContext.setAuthCache(authCache);
            CloseableHttpResponse response = httpClient.execute(host, httpRequest, httpClientContext);
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    @Override
    public String createJob(String jobName, String jobConfigXml) {
        if (StringUtils.isEmpty(jobConfigXml)) {
            jobConfigXml = "<flow-definition plugin=\"workflow-job@2.40\">\n" +
                    "<description/>\n" +
                    "<keepDependencies>false</keepDependencies>\n" +
                    "<properties/>\n" +
                    "<definition class=\"org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition\" plugin=\"workflow-cps@2.90\">\n" +
                    "<script>echo 'hello'</script>\n" +
                    "<sandbox>true</sandbox>\n" +
                    "</definition>\n" +
                    "<triggers/>\n" +
                    "<disabled>false</disabled>\n" +
                    "</flow-definition>";
        }
        try {
            //createJobWithCrumb(jobName, jobConfigXml);
            // 能用token的形式更好，可以免crumb，crumb会多查一次jenkins，有的jenkins没开crumb，又会有超时问题需要处理
            createJobWithToken(jobName, jobConfigXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public String startJob(String jobName) {
        String jenkinsServerUrl = JenkinInstance.DefaultInstance().getJenkinsServerUrl();

        String url = String.format("%s/job/%s/build?delay=0", jenkinsServerUrl, jobName);

        String authInfo = JenkinInstance.DefaultInstance().getAuthInfo();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", ContentType.TEXT_XML.withCharset("utf-8").toString());
        headers.put("cache-control", "no-cache");
        headers.put("Authorization", "Basic " + authInfo);

        String result = HttpUtil.doPost(url, headers, "");
        return result;
    }
}
