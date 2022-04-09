package com.example.vodokanalmainactivity.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.vodokanalmainactivity.App;
import com.example.vodokanalmainactivity.R;
import com.example.vodokanalmainactivity.data.preferences.DataPreferences;
import com.example.vodokanalmainactivity.data.preferences.JavaScriptInterface;

public class WebViewActivity extends AppCompatActivity {
    DataPreferences dataPreferences;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webiview_activity);
        final android.webkit.WebView webView = findViewById(R.id.webview);
        dataPreferences = ((App) getApplication()).dataPreferences;
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.light_blue));
        getWindow().getDecorView().setSystemUiVisibility(0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new JavaScriptInterface(this), "AndroidFunction");
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
//            {
//                WebView.HitTestResult result = view.getHitTestResult();
//                String data = result.getExtra();
//                Context context = view.getContext();
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/viewer?url="+data));
//                Log.d("LOGLOG","br-"+data);
//                return false;
//            }
        });


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //super.onPageFinished(view, url);
                view.loadUrl("javascript:var elem=document.getElementById('old'); elem.parentNode.removeChild(elem);");
                if (view.getUrl().contains("https://vdk03.m-pays.ru/Login2")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("var formxxx = document.getElementsByTagName('form')[0];");
                    sb.append("if(!(formxxx === undefined || formxxx === null)){");
                    sb.append(" formxxx.onsubmit = function () {");
                    sb.append(" var l3=document.getElementById('login').value;");
                    sb.append(" var l5=document.getElementById('password').value;");
                    sb.append(" window.AndroidFunction.processHTML(l3,l5);");
                    sb.append(" return true;");
                    sb.append(" };");
                    sb.append("}");
                    view.loadUrl("javascript:" + sb.toString());
                    if (dataPreferences.getLogin() != null) {
                        view.loadUrl("javascript:var l1=document.getElementById('login').value='" + dataPreferences.getLogin() + "'," +
                                "a1=document.getElementById('password').value='" + dataPreferences.getPassword() + "'");
                    }
//                    else {
//                        view.loadUrl("javascript:var l1=document.getElementById('login').value='9140000000'," +
//                                 "a1=document.getElementById('password').value='314159'");
//                    }
                }
                //Log.d("LOGLOG",url);
            }
        });
       webView.loadUrl("https://vdk03.m-pays.ru/Login2");
   }
}



