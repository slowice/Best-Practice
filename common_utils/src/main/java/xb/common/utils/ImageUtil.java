package xb.common.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImageUtil {

    /**
     *  存储图片
     *  路径为filePath + filename，filename要自带后缀
     */
    public static void storeImg(MultipartFile image, String filepath, String filename) throws IOException {
        File f = new File(filepath);

        if(!f.exists() ){
            f.mkdirs();
        }
        StringBuilder pathname = new StringBuilder();
        pathname.append(filepath);
        pathname.append(filename);
        OutputStream outputStream = new FileOutputStream(new File(String.valueOf(pathname)));
        IOUtils.copy(image.getInputStream(),outputStream);
    }

    /**
     *  存储图片，并自动修改图片的大小
     *  路径为filePath + filename，filename要自带后缀
     */
    public static void storeImg(MultipartFile image, String filepath, String filename, int width, int height) throws IOException {
        StringBuilder pathname = new StringBuilder();
        pathname.append(filepath);
        pathname.append(filename);
        InputStream imageStream = image.getInputStream();
        Image bi = ImageIO.read(imageStream);
        //构建图片流
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //绘制改变尺寸后的图
        tag.getGraphics().drawImage(bi, 0, 0,width, height, null);
        //输出流
        BufferedOutputStream bos = null;
        if(null!=image && !image.isEmpty()){
            try {
                File file = new File(pathname.toString());
                bos = new BufferedOutputStream(new FileOutputStream(file));
                ImageIO.write(tag, "PNG",bos);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                    imageStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  将目标图片作为ResponseEntity返回，可以直接在web层返回，通过restApi查看图片
     */
    public static ResponseEntity preview(String imgPath, String fileName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(imgPath+fileName));
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
    }

    /**
     *  获取目录下所有的文件，并返回一个文件集合
     */
    public static List<File> getFiles(String path) throws Exception {
        //目标集合fileList
        List<File> fileList = new ArrayList<File>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                //如果这个文件是目录，则进行递归搜索
                if (fileIndex.isDirectory()) {
                    getFiles(fileIndex.getPath());
                } else {
                //如果文件是普通文件，则将文件句柄放入集合中
                    fileList.add(fileIndex);
                }
            }
        }
        return fileList;
    }

}
