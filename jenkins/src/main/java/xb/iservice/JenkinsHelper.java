package xb.iservice;

import java.io.IOException;
import java.net.URISyntaxException;

public interface JenkinsHelper {
    String doJenkins(String code) throws IOException, URISyntaxException;
}
