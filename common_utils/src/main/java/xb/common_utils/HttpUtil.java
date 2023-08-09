package xb.common_utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.SocketTimeoutException;
import java.util.Map;


public class HttpUtil {
    public static String doGet(String url,Map<String,String> headers) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            // 创建客户端对象
            client = HttpClients.createDefault();
            // 构建请求对象
            HttpGet get = new HttpGet(url);
            if(!headers.isEmpty()){
                for(String key : headers.keySet()){
                    get.addHeader(key, headers.get(key));
                }
            }
            // 设置超时时间 ConnectTimeout:客户端服务器建立链接的超时时间
            // ConnectionRequestTimeout:从连接池获取链接的超时时间 SocketTimeout从服务端读取数据的超时时间
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(10000)
                    .setConnectionRequestTimeout(3000)
                    .setSocketTimeout(20000)
                    .build();
            get.setConfig(config);
            // 获取返回对象
            response = client.execute(get);
            // 整理返回值
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (Exception e) {
            // log
            return null;
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                // log
            }
        }
    }

    public static String doPost(String url, Map<String,String> headers, String content) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            if(headers.isEmpty()){
                post.setHeader("Content-Type", "application/json;charset=utf-8");
                post.setHeader("Accept", "application/json;charset=utf-8");
            } else {
                for(String key : headers.keySet()){
                    post.addHeader(key, headers.get(key));
                }
            }
            RequestConfig config = RequestConfig.custom()

                    .setConnectTimeout(10000)
                    .setConnectionRequestTimeout(3000)
                    .setSocketTimeout(20000)
                    .build();
            post.setConfig(config);

//            JSONObject json = new JSONObject();
//            json.put("username", username);
//            json.put("password", DigestUtils.md5Hex(password));
//            json.put("tenantUrl", tenantUrl);
            post.setEntity(new StringEntity(StringUtils.normalizeSpace(content), "UTF-8"));
            response = client.execute(post);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            // log 获取token超时
        } catch (Exception e) {
            e.printStackTrace();
            // log 其它异常
        } finally {
            try {
                if (client != null) {
                    client.close();

                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                // log
            }
        }
        return result;
    }
}
