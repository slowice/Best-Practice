package xb.spring.temp;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ZipFolderExample {

    public static void main(String[] args) {
        String sourceFolder = "/Users/xubin/xb/temp/A";
        String zipFilePath = "/Users/xubin/xb/temp/A.zip";

        try {
            zipFolder(sourceFolder, zipFilePath);
            System.out.println("Folder compressed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFolder(String sourceFolder, String zipFilePath) throws IOException {
        try (ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(new FileOutputStream(zipFilePath))) {
            addFolderToZip("", sourceFolder, zipOut);
        }
    }

    private static void addFileToZip(String path, String srcFile, ZipArchiveOutputStream zipOut) throws IOException {
        File file = new File(srcFile);
        ZipArchiveEntry entry = new ZipArchiveEntry(path + "/" + file.getName());
        zipOut.putArchiveEntry(entry);

        try (FileInputStream fis = new FileInputStream(file)) {
            IOUtils.copy(fis, zipOut);
        }

        zipOut.closeArchiveEntry();
    }

    private static void addFolderToZip(String path, String srcFolder, ZipArchiveOutputStream zipOut) throws IOException {
        File folder = new File(srcFolder);

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                // 如果是文件夹，递归处理
                addFolderToZip(path + "/" + file.getName(), file.getAbsolutePath(), zipOut);
            } else {
                // 如果是文件，添加到压缩包
                addFileToZip(path, file.getAbsolutePath(), zipOut);
            }
        }
    }
}

