package com.zzh.vae.utils;

import android.text.TextUtils;

import com.zzh.image.bean.Picture;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzh on 2016/3/7.
 * 封装网络访问接口
 */
public class ZZHttpUtils {

    private static class LazyZZH {
        private static final ZZHttpUtils zzh = new ZZHttpUtils();
    }

    private static int DEFAULT_COUNT_POOL_SIZE = 8;
    //private static Executors mExecutors;
    private static ExecutorService mService;
    //private Thread mPoolThreadBg;//后台轮训线程


   // private Type mType;

    //private static LinkedList<Runnable> mTaskQueue; //存放任务队列

   /* public ZZHttpUtils(Type mType, int poolSize) {
        this.mType = mType;
    }*/

    public ZZHttpUtils() {
        init();
    }

    private void init() {
        mService = Executors.newFixedThreadPool(DEFAULT_COUNT_POOL_SIZE);
        //mTaskQueue = new LinkedList<>();
    }

   /* private Runnable getTask() { //从线程池里取的任务
        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else {
            return mTaskQueue.removeLast();
        }
    }*/

    /**
     * FIFO:First in first out, 先进先出
     * LIFO:Last in first out, 后进先出
     */
   /* public enum Type { //选择任务的进出方式
        FIFO, LIFO
    }*/

    /**
     * 后台方法, 在这里进行数据的解析
     *
     * @param urlPath 网络地址
     * @return 返回数据是字符串
     */
    public static void getUrlConnectInBg(final String urlPath, final CallBack<String> callBack) {
        NetUtils.getInstance().getUrlConnectInBg(urlPath, new CallBack<String>() {
            @Override
            public void success(String s) {
                if (TextUtils.isEmpty(s)) {
                    callBack.success(s);
                } else {
                    callBack.fail("error : nothing data");
                }
            }

            @Override
            public void fail(String err) {
                callBack.fail(err);
            }
        });
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
     * 单个Bean的形式返回
     *
     * @param <T>
     */
    public interface CallBack<T> {
        void success(T t);

        void fail(String err);
    }

    /**
     * 集合的形式返回
     *
     * @param <T>
     */
    public interface CallBacks<T> {
        void success(List<T> lists);

        void fail(String err);
    }

}
