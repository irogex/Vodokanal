package com.example.vodokanalmainactivity.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

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
       //     @Override
//            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
//            {
//                WebView.HitTestResult result = view.getHitTestResult();
//                String data = result.getExtra();
//                Context context = view.getContext();
//               // Intent intent = new Intent(Intent.ACTION_VIEW);
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/viewer?url="+data));
//            //    WebView.loadUrl(data);
//               // WebViewActivity.startActivity(intent);
//                //WebView.loadUrl(browserIntent);
//                Log.d("LOGLOG","br-"+data);
//                return false;
//            }
       @Override
       public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
           webView.removeAllViews();
           WebView newView = new WebView(WebViewActivity.this);
           newView.setWebViewClient(new WebViewClient());
           // Create dynamically a new view
           newView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

           webView.addView(newView);

           WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
           transport.setWebView(newView);
           resultMsg.sendToTarget();
           return true;
//           WebView newWebView = new WebView(WebViewActivity.this);
//           WebSettings webSettings = newWebView.getSettings();
//           webSettings.setJavaScriptEnabled(true);
//           webSettings.setSupportMultipleWindows(true);
//           webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//           // Other configuration comes here, such as setting the WebViewClient
//
//           final Dialog dialog = new Dialog(WebViewActivity.this);
//           dialog.setContentView(newWebView);
//           dialog.show();
//
//           newWebView.setWebChromeClient(new WebChromeClient() {
//               @Override
//               public void onCloseWindow(WebView window) {
//                   dialog.dismiss();
//               }
//           });
//
//           ((WebView.WebViewTransport)resultMsg.obj).setWebView(newWebView);
//           resultMsg.sendToTarget();
//           return true;
       }
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
                view.getSettings().setDomStorageEnabled(true);
                view.getSettings().setAppCacheEnabled(true);
                view.getSettings().setDatabaseEnabled(true);
                view.getSettings().setDomStorageEnabled(true);
                view.getSettings().setSupportMultipleWindows(true);
                view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                view.loadUrl("javascript:var elem=document.getElementById('old'); elem.parentNode.removeChild(elem);");
                view.loadUrl("javascript:var elem=document.getElementById('old'); elem.parentNode.removeChild(elem);");
                if (view.getUrl().contains("https://vdk03.m-pays.ru/Login2")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("var formxxx = document.getElementsByTagName('form')[0];");
                    sb.append("if(!(formxxx === undefined || formxxx === null)){");
                    sb.append(" formxxx.onsubmit = function () {");
                    sb.append(" var l3=document.getElementById('login').value;");
                    sb.append(" var l5=document.getElementById('password').value;");
                   // sb.append(" var e1=document.getElementById('old'); e1.style.display='none';");
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
                if (view.getUrl().contains("https://vdk03.m-pays.ru/Prints2")) {
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append("var btn1 = document.querySelector('#charge_report #submit1');"); //Вызов Справка
                    sb1.append("btn1.onclick = function(){");
                    sb1.append("var token = document.getElementsByName('__RequestVerificationToken')[0];");
                   // sb1.append("var body = 'id=61213' + '&month=2022-05';");
                    sb1.append("var id = document.querySelector('#charge_report input[name=id]');");
                    sb1.append("var month = document.querySelector('#charge_report input[name=month]');");
                    sb1.append("var body = 'id=' + id.value + '&month=' + month.value;");
                    sb1.append("var req = new XMLHttpRequest();");
                    sb1.append("req.open('POST', '/Prints2?handler=ChargePrint', true);");
                    sb1.append("req.setRequestHeader('RequestVerificationToken', token.value);");
                    sb1.append("req.setRequestHeader('Accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8');");
                    sb1.append("req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
                    sb1.append("req.setRequestHeader('User-Agent', 'Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1');");
                     sb1.append("req.responseType = 'arraybuffer';");
                    sb1.append("req.send(body);");
                    sb1.append("req.onload = function () {\n" +
                            "window.AndroidFunction.showPdf(new Uint8Array(req.response));\n" +
                            "        };};");
                    sb1.append("var btn2 = document.querySelector('#period_report #submit1');"); //Вызов Периода
                    sb1.append("btn2.onclick = function(){");
                    sb1.append("var token = document.getElementsByName('__RequestVerificationToken')[0];");
                    sb1.append("var id = document.querySelector('#period_report input[name=id]');");
                    sb1.append("var dateIn = document.querySelector('#period_report input[name=dateIn]');");
                    sb1.append("var dateOut = document.querySelector('#period_report input[name=dateOut]');");
                    sb1.append("var body = 'id=' + id.value + '&dateIn='+ dateIn.value + '&dateOut=' + dateOut.value;");
                    //sb1.append("var body = 'id=61213' + '&dateIn=2021-06-01'+ '&dateOut=2022-06-01';");
                    sb1.append("var req = new XMLHttpRequest();");
                    sb1.append("req.open('POST', '/Prints2?handler=PeriodReportPrint', true);");
                    sb1.append("req.setRequestHeader('RequestVerificationToken', token.value);");
                    sb1.append("req.setRequestHeader('Accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8');");
                    sb1.append("req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
                    sb1.append("req.setRequestHeader('User-Agent', 'Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1');");
                    sb1.append("req.responseType = 'arraybuffer';");
                    sb1.append("req.send(body);");
                    sb1.append("req.onload = function () {\n" +
                            "window.AndroidFunction.showPdf(new Uint8Array(req.response));\n" +
                            "        };};");



                   // sb1.append("};"); //Закрытие Скрипта
                    view.loadUrl("javascript:" + sb1.toString());
                }
                //Log.d("LOGLOG",url);
            }
        });

       webView.loadUrl("https://vdk03.m-pays.ru/Login2");

   }
}



