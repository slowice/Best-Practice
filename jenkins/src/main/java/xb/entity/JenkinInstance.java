package xb.entity;

import lombok.Data;

import javax.xml.bind.DatatypeConverter;

@Data
public class JenkinInstance {
    private String userName;
    private String userPwd;
    private String token;
    private String ip;
    private String port;
    private String authInfo;
    private String jenkinsServerUrl;


    public String getAuthInfo(){
        String authStr = userName + ":" + token;
        String authInfo = "";
        try{
            authInfo = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));  //username  password 自行修改  中间":"不可少
        }catch (Exception e){
            e.printStackTrace();
        }
        return authInfo;
    }

    public String getJenkinsServerUrl(){
        return String.format("http://%s:%s/jenkins", ip, port);
    }

    // todo 改造为单例模式
    public static JenkinInstance DefaultInstance(){
        JenkinInstance instance = new JenkinInstance();
        instance.setIp("localhost");
        instance.setPort("8080");
        instance.setUserName("slowice");
        instance.setUserPwd("");
        instance.setToken("111e77e482167dfc6ae47d27ea44db38aa");
        return instance;
    }
}
