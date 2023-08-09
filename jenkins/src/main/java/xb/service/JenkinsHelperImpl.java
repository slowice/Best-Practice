package xb.service;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import xb.common_utils.HttpUtil;
import xb.iservice.JenkinsHelper;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class JenkinsHelperImpl implements JenkinsHelper {

    @Override
    public String doJenkins(String code) throws IOException, URISyntaxException {
        String configXml = "<flow-definition plugin=\"workflow-job@2.40\">\n" +
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
        String jobName = "TestJob0809";
        createJob(jobName, configXml);
        startJob(jobName);
        return "ok";
    }

    private String createJob(String jobName, String configXml) throws IOException, URISyntaxException {
        String url = String.format("http://localhost:8080/jenkins/createItem?name=%s",jobName);
        String userName = "slowice";
        String token = "111e77e482167dfc6ae47d27ea44db38aa";
        String authStr = userName + ":" + token;
        String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));  //username  password 自行修改  中间":"不可少
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", ContentType.TEXT_XML.withCharset("utf-8").toString());
        headers.put("cache-control", "no-cache");
        headers.put("Authorization", "Basic " + encoding);
        String result = HttpUtil.doPost(url,headers,configXml);
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
        authCache.put(host,basicScheme);
        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build()) {
            HttpClientContext httpClientContext = HttpClientContext.create();
            httpClientContext.setAuthCache(authCache);
            CloseableHttpResponse response = httpClient.execute(host, httpRequest, httpClientContext);
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private void startJob(String jobName) throws UnsupportedEncodingException {
        String url = String.format("http://localhost:8080/jenkins/job/%s/build?delay=0",jobName);
        String userName = "slowice";
        String token = "111e77e482167dfc6ae47d27ea44db38aa";
        String authStr = userName + ":" + token;
        String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));  //username  password 自行修改  中间":"不可少
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", ContentType.TEXT_XML.withCharset("utf-8").toString());
        headers.put("cache-control", "no-cache");
        headers.put("Authorization", "Basic " + encoding);
        String result = HttpUtil.doPost(url,headers,"");
    }
}
