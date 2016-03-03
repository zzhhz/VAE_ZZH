package com.zzh.html5.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

/***
 * h5和Activity交互
 */
public class Html5Activity extends BaseActivity {

    private WebView webView;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        initView();
        initData();
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.setClickable(true); //设置webview是可点击的

        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); //支持JavaScript
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//缓存策略
        /**设置在页面加载完成之后，在异步加载图片文件，提高webview的性能，增加用户体验**/
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }

        /**使用本activity打开网页，而不是从浏览器中打开，指的是网页中的链接，点击在本webview中打开，而不是使用外部浏览器**/
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(!TextUtils.isEmpty(url)){
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!settings.getLoadsImagesAutomatically()) {
                    settings.setLoadsImagesAutomatically(true);
                }
                Log.e("chrome", "--------finish");
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.e("chrome", "--------" + newProgress);
            }
        });
        settings.setBuiltInZoomControls(true);
        //webView.addJavascriptInterface();
    }

    @Override
    protected void initData() {
        /**
         * 加载项目中assets中的网页文件，供webview显示。
         * file:///android_asset/是固定写法，定位到assets目录。demo1.html目标文件
         *
         * */
        webView.loadUrl("file:///android_asset/publish_good_info.html");

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
