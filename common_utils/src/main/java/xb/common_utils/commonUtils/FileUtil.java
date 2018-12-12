package xb.common_utils.commonUtils;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    /**
     * 清空目录下所有文件
     * @param path
     * @throws IOException
     */
    public static void cleanDir(String path) throws IOException {
        File file = new File(path);
        FileUtils.cleanDirectory(file);
    }
}
