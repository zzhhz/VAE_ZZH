package com.zzh.vae.utils;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzh on 2016/3/28.
 */
public class NetUtils {
    private static class LazyHttp {
        private static final NetUtils zzh = new NetUtils();
    }



    private static int DEFAULT_COUNT_POOL_SIZE = 8;
    private static ExecutorService mService;
    public static NetUtils getInstance(){
        return LazyHttp.zzh;
    }

    public NetUtils(){
        init();
    }

    private void init() {
        mService = Executors.newFixedThreadPool(DEFAULT_COUNT_POOL_SIZE);
    }

    /**
     * 后台方法
     * @param urlPath 网络地址
     * @return 返回数据是字符串
     */
    public void getUrlConnectInBg(final String urlPath, final ZZHttpUtils.CallBack<String> callBack) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                HttpURLConnection conn = null;
                try {
                    StringBuffer buffer = new StringBuffer();
                    URL url = new URL(urlPath);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // 允许输入流，即允许下载
                    //conn.setDoOutput(true); //允许输出流，即允许上传
                    conn.setUseCaches(false);
                    /////设置头文件Content-Type
                    conn.setRequestProperty("deviceName", "Android");
                    //conn.setRequestProperty("user-agent","user-agent");
                    conn.setRequestProperty("accept-encoding","utf-8");
                    conn.setRequestProperty("Content-Type","application/json");
                    conn.setRequestMethod("GET");
                    conn.connect();
                    int code = conn.getResponseCode();
                    if (code == 200) { //连接成功
                        is = conn.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        String inputLine;
                        while ((inputLine = br.readLine()) != null) {
                            buffer.append(inputLine);
                        }
                        callBack.success(buffer.toString());
                    } else {
                        //return "error：errno = "+ code;
                        //error net
                        callBack.fail("error：errno = "+ code);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally { //关闭
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
            }
        };
        mService.execute(task);
    }
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
            conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
            conn.setRequestProperty("device", "Android");
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

    /**
     * 后台方法
     * @param urlPath 网络地址
     * @return 返回数据是字符串
     */
    public static void postUrlConnectInBg(final String urlPath, final Map<String, String> params, ZZHttpUtils.CallBack<String> callBack) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                HttpURLConnection conn = null;
                try {
/////携带参数
                    StringBuffer buffer = new StringBuffer();
                    Set<String> keys = params.keySet();
                    for (String key : keys) {
                        String content = params.get(key);
                        buffer.append(key).append("=").append(content).append("&");
                    }

                    buffer.deleteCharAt(buffer.length() -1);

                    byte[] bytes = buffer.toString().getBytes();

                    String result = null;
                    //StringBuffer buffer = new StringBuffer();
                    URL url = new URL(urlPath);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // 允许输入流，即允许下载
                    conn.setDoOutput(true); //允许输出流，即允许上传
                    conn.setUseCaches(false);
                    conn.setInstanceFollowRedirects(false);
                    conn.setConnectTimeout(10000);
                    /////设置头文件
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                    conn.setRequestProperty("Content-type", "application/json");
                    conn.setRequestProperty("deviceName", "Android");
                    conn.setRequestProperty("Content-Length", String.valueOf(bytes.length));
                    conn.setRequestMethod("POST");

                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                    //dos.writeBytes(buffer.toString());
                    dos.write(bytes);
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
                        //return result;
                    } else {
//                        return "error";
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
//                return "nothing data";
            }
        };
        mService.execute(task);
    }
}
