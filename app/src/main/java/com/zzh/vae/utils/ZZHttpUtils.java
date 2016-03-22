package com.zzh.vae.utils;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by zzh on 2016/3/7.
 * 封装网络访问接口
 */
public class ZZHttpUtils {

    /**
     * @param urlPath 网络地址
     * @return 返回数据是字符串
     */
    public static String getUrlConnection(String urlPath) {
        InputStream is = null;
        HttpURLConnection conn = null;
        try {

            String result = null;
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // 允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false);
            /////设置头文件
//            conn.setRequestProperty("Content-type","application/x-java-serialized-object");
//            conn.setRequestProperty("device","Android");
            conn.setRequestMethod("GET");
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) { //连接成功
                is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String inputLine = "";
                while ((inputLine = br.readLine()) != null) {
                    result += inputLine;
                }
                return result;
            } else {
                return "error";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (is != null) {
                    is.close();
                }
                if (conn != null)
                    conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "nothing data";
    }

    /**
     * @param urlPath 网络地址
     * @return 返回数据是字符串
     */
    public static String postUrlConnection(String urlPath, Map<String, String> params) {
        InputStream is = null;
        HttpURLConnection conn = null;
        try {

            String result = null;
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // 允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(10000);
            /////设置头文件
            conn.setRequestProperty("Content-type","application/x-java-serialized-object");
            conn.setRequestProperty("device","Android");
            conn.setRequestMethod("POST");
            /////携带参数
            StringBuffer buffer = new StringBuffer();
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String content = params.get(key);
                buffer.append(key).append("=").append(content).append("&");
            }

            dos.writeBytes(buffer.toString());
            dos.flush();
            dos.close();

            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) { //连接成功
                is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String inputLine = "";
                while ((inputLine = br.readLine()) != null) {
                    result += inputLine;
                }
                return result;
            } else {
                return "error";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (is != null) {
                    is.close();
                }
                if (conn != null)
                    conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "nothing data";
    }
}
