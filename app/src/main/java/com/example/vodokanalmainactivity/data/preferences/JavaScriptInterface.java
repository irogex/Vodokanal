package com.example.vodokanalmainactivity.data.preferences;
import android.content.Context;
import android.util.Log;
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
        Log.d("LOGLOG","press-"+toast);
    }

    @JavascriptInterface
    public void processHTML(String login, String pass)
    {
        dataPreferences.setLogin(login);
        dataPreferences.setPassword(pass);
       // Log.d("LOGLOG","login: "+login + " pass: " + pass);
    }

}
