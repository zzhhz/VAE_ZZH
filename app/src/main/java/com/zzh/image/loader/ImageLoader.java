package com.zzh.image.loader;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zzh on 2016/3/7.
 * 加载工具类
 */
public class ImageLoader {
    private static ImageLoader mInstance;
    /**
     * 图片缓存的核心对象
     **/
    private LruCache<String, Bitmap> mLruCache;

    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);


    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(DEAFULT_THREAD_COUNT, Type.LIFO);
                }
            }
        }
        return mInstance;
    }

    private ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    private void init(int threadCount, Type type) {
        /**后台轮训线程**/
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //线程池取出任务去执行

                        mThreadPool.execute(getTask());

                    }
                };
                //释放一个信号量
                mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };

        mPoolThread.start();

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes() * value.getHeight();
            }
        };

        /**线程池***/
        mThreadPool = Executors.newFixedThreadPool(threadCount);

        mTaskQueue = new LinkedList<>();
        mType = Type.LIFO;

    }

    /**
     * 从任务队列取出方法
     */
    private Runnable getTask() {
        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else {
            return mTaskQueue.removeLast();
        }
    }

    /**
     * 声明线程
     ***/
    private ExecutorService mThreadPool;
    /**
     * 线程池的数量
     **/
    private final int DEAFULT_THREAD_COUNT = 1;

    private Type mType = Type.LIFO;

    private LinkedList<Runnable> mTaskQueue;

    enum Type {
        FIFO, LIFO
    }


    /**
     * 后台轮训线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;
    /**
     * UI线程
     **/
    private Handler mUIHandler;


    /**
     * 设置图片
     *
     * @param path
     * @param imageView
     */
    public void loadImage(final String path, final ImageView imageView) {
        imageView.setTag(path);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 获取得到的图片
                    ImageBeanHolder holder = (ImageBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    String path = holder.path;
                    ImageView imageView = holder.imageView;
                    if (imageView.getTag().toString().equals(path)) {
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null) {
            sendImageViewToUI(path, imageView, bm);
        } else {
            addTasks(new Runnable() {
                @Override
                public void run() {
                    //加载图片
                    //图片压缩
                    //1.获取图片的宽高
                    ImageSize imageSize = getInageViewSize(imageView);
                    //2.压缩图片
                    Bitmap bitmap = decodeSampledBitmapFromPath(path, imageSize.width, imageSize.height);
    //3.加入到缓存
                    addBitmapToLruCache(path, bitmap);
                    sendImageViewToUI(path, imageView, bitmap);

                }
            });
        }
    }

    /**
     * 发送到UI线程，更新图片
     * @param path 图片路径
     * @param imageView 显示控件
     * @param bm bitmap
     */
    private void sendImageViewToUI(String path, ImageView imageView, Bitmap bm) {
        Message message = Message.obtain();
        ImageBeanHolder holder = new ImageBeanHolder();
        holder.bitmap = bm;
        holder.path = path;
        holder.imageView = imageView;
        message.obj = holder;
        mUIHandler.sendMessage(message);
    }

    private void addBitmapToLruCache(String path, Bitmap bitmap) {
        if (getBitmapFromLruCache(path) != null){
            if (bitmap != null){
                mLruCache.put(path, bitmap);
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    private Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //不加载到内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = caculateImSampleSize(options, width, height);
        //加载到内存
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /***
     * @param options 采样
     * @param reqWidth 宽
     * @param reqHeight 高
     * @return
     */
    private int caculateImSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round(width * 1f / reqWidth);
            int heightRadio = Math.round(height * 1f / reqHeight);

            inSampleSize = Math.max(widthRadio, heightRadio);

        }
        return inSampleSize;
    }

    /**
     * 根据ImageVew获得适当的宽高
     *
     * @param imageView
     * @return
     */
    @SuppressLint("NewApi")
    private ImageSize getInageViewSize(ImageView imageView) {
        ImageSize imageSize = new ImageSize();
        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        //int width = (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT? 0: imageView.getWidth());
        int width = imageView.getWidth();
        if (width <= 0) {
            width = lp.width;
        }

        if (width <= 0) {
            width = imageView.getMaxWidth();
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }


        int height = imageView.getHeight();

        if (height <= 0) {
            height = lp.height;
        }

        if (height <= 0) {
            height = imageView.getMaxHeight();
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }

        imageSize.height = height;
        imageSize.width = width;
        return imageSize;
    }

    private synchronized void addTasks(Runnable runnable) {
        mTaskQueue.add(runnable);
        try {
            if (mPoolThreadHandler == null)
                mSemaphorePoolThreadHandler.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPoolThreadHandler.sendEmptyMessage(0x110);
    }

    private Bitmap getBitmapFromLruCache(String path) {
        return null;
    }

    private class ImageBeanHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }

    private class ImageSize {
        int width;
        int height;
    }

}
