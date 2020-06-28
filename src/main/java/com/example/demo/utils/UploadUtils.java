package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 上传文件工具类
 */
@Component
public class UploadUtils {

    private static String address;

    public String getAddress() {
        return address;
    }
    @Value("${upload.address}")
    public void setAddress(String address) {
        this.address = address;
    }

    public static File getImgDirFile(){
        // 构建上传文件的存放 “文件夹” 路径
        File fileDir = new File(address);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }
}
