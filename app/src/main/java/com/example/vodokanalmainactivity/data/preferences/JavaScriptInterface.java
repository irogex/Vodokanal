package com.example.vodokanalmainactivity.data.preferences;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vodokanalmainactivity.App;
import com.example.vodokanalmainactivity.activities.PdfActivity;
import com.example.vodokanalmainactivity.activities.WebViewActivity;

public class JavaScriptInterface extends AppCompatActivity {

    DataPreferences dataPreferences;
    Context mContext;

    public JavaScriptInterface(WebViewActivity c) {
        mContext = c;
        dataPreferences = ((App) c.getApplication()).dataPreferences;
    }
   // @JavascriptInterface
  //  public void onCreate(Bundle savedInstanceState) {
  //      Toast.makeText(mContext,"asd", Toast.LENGTH_SHORT).show();
   //     super.onCreate(savedInstanceState);
   //     setContentView(R.layout.activity_valid);
       // Intent intent = new Intent(this, PdfActivity.class);
       // startActivity(intent);
//        Toast.makeText(mContext, "lsd", Toast.LENGTH_SHORT).show();
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pdf);
//        final PDFView pdfView = findViewById(R.id.pdfView);
//        pdfView.fromAsset("pdfexmple.pdf")
//                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
//                .enableSwipe(true) // allows to block changing pages using swipe
//                .swipeHorizontal(false)
//                .enableDoubletap(true)
//                .defaultPage(0)
//
//                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
//                .password(null)
//                .scrollHandle(null)
//                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
//                // spacing between pages in dp. To define spacing color, set view background
//                .spacing(0)
//                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
              //  .load();
 //   }
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        //Log.d("LOGLOG","press-"+toast);
    }

    @JavascriptInterface
    public void showPdf(String toast)  {
      //  Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
       // Log.d("LOGLOG",toast);
        Intent i = new Intent( mContext, PdfActivity.class);
        i.putExtra("pdfsend", toast);
        mContext.startActivity(i);
        //Скормить классу PDF
    }

    @JavascriptInterface
    public void processHTML(String login, String pass)
    {
        dataPreferences.setLogin(login);
        dataPreferences.setPassword(pass);
       // Log.d("LOGLOG","login: "+login + " pass: " + pass);
    }

}
