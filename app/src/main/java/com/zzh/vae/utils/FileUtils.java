package com.zzh.vae.utils;

import android.text.TextUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

/**
 * Created by zzh on 2016/3/29.
 * 文件操作类
 * 保存文件到sd卡
 */
public class FileUtils {

    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String CHARSET_GBK = "GBK";

    /**
     * 默认写到Download文件夹
     * @param fileName 文件夹名称
     * @param is 输入流
     */
    public static void save2SdCard(String fileName, InputStream is){
        String filePath = SDCardUtils.getExternalStorageDownloadPath();
        save2SdCard(filePath, fileName, is);
    }

    /**
     * @param filePath 文件的路径
     * @param fileName 文件的名称，需要加类型，例如 a.txt
     * @param is       输入流
     */
    public static void save2SdCard(String filePath, String fileName, InputStream is) {

        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        if (TextUtils.isEmpty(fileName)) {
            return;
        }
        if (is == null) {
            return;
        }
        File tmp = new File(filePath);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }

        File file = new File(filePath, fileName);
        if (file.exists()) {
            file.renameTo(new File(filePath, UUID.randomUUID().toString() + fileName));
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024*8];
            int len = -1;
            while ((len = is.read(bytes)) != -1){
                fos.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /**
     * 保存文件的路径SD卡的Download目录下
     *
     * @param content 文件的内容，String类型
     *                文件编码；默认是UTF-8
     */
    public static void save2SdCard(String fileName, String content) {
        String filePath = SDCardUtils.getExternalStorageDownloadPath();
        save2SdCard(filePath, fileName, content);
    }

    /**
     * @param filePath 保存文件的路径
     * @param content  文件的内容，String类型
     *                 文件编码；默认是UTF-8
     */
    public static void save2SdCard(String filePath, String fileName, String content) {
        save2SdCard(filePath, fileName, content, CHARSET_UTF_8);
    }

    /**
     * @param filePath 保存文件的路径
     * @param content  文件的内容，String类型
     * @param encoding 文件编码；默认是UTF-8
     */
    public static void save2SdCard(String filePath, String fileName, String content, String encoding) {

        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            return;
        }

        BufferedWriter writer = null;
        File fileTemp = new File(filePath);

        if (!fileTemp.exists()) {
            fileTemp.mkdirs();
        }
        String actFilePath = filePath + File.pathSeparator + fileName;
        File file = new File(actFilePath);
        if (file.exists()) { //已经有了文件,则不再保存
            return;
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), encoding));
            writer.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
