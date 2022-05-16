package com.example.vodokanalmainactivity.data.preferences;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.vodokanalmainactivity.App;
import com.example.vodokanalmainactivity.activities.WebViewActivity;

public class JavaScriptInterface
{
    DataPreferences dataPreferences;
    Context mContext;

    public JavaScriptInterface(WebViewActivity c) {
        mContext = c;
        dataPreferences = ((App) c.getApplication()).dataPreferences;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        //Log.d("LOGLOG","press-"+toast);
    }
    @JavascriptInterface
    public void showPdf(String toast) {
        //Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        //Log.d("LOGLOG","press-"+toast);
//        File file = new File(toast);
//        Intent target = new Intent(Intent.ACTION_VIEW);
//        target.setDataAndType(Uri.fromFile(file),"application/pdf");
//        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//        Intent intent = Intent.createChooser(target, "Open File");
        //Toast.makeText(intent, toast, Toast.LENGTH_SHORT).show();
        //Log.d("LOGLOG","press-"+intent);
          //  WebViewActivity(intent);
        String doc="https://docs.google.com/viewer?url="+toast;
       // String doc="/Prints2?handler=ChargePrint";
       // String doc="https://docs.google.com/viewer?url="+toast;
        //WebView.loadUrl(intent);
        //String url = "http://www.google.com";
       // webView.loadUrl("https://vdk03.m-pays.ru/Prints2?handler=ChargePrint");
        //Intent openPage= new Intent(Intent.ACTION_VIEW, Uri.parse(doc));
        Intent openPage= new Intent(Intent.ACTION_VIEW, Uri.parse(doc));
        mContext.startActivity(openPage);
//        try {
//            ValidActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            // Instruct the user to install a PDF reader here, or something
//        }
    }

    @JavascriptInterface
    public void processHTML(String login, String pass)
    {
        dataPreferences.setLogin(login);
        dataPreferences.setPassword(pass);
       // Log.d("LOGLOG","login: "+login + " pass: " + pass);
    }

}
