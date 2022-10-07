package com.qiao.boot.FileUtils;

import java.io.File;

/**
 * @author 乔世伟
 * @Project_Nmae: spring-boot-web1
 * @Description:
 * @date 2022/10/5 21:36
 */

public class FileUtils {

    public static String[] getAllFile(File file) {
        File[] files = file.listFiles();
        int i = 0;
        String[] fileNames = null;
        if (files != null) {
            fileNames = new String[files.length];
            for (File f : files) {
                if (!f.isDirectory()) {
                    fileNames[i] = f.getAbsolutePath();
//                    System.out.println("文件名:" + f.getAbsolutePath());
//                    System.out.println("文件名列表长" + fileNames.length);
                    i++;
                }
            }
        }
        return fileNames;

    }
}
