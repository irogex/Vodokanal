package com.example.vodokanalmainactivity.data.preferences;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    public void log(String toast) {
        Log.d("LOGLOG", toast.toString());
        //Log.d("LOGLOG","press-"+toast);
    }

    @JavascriptInterface
    public void showPdf(byte[] bytes)  {
      //  Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
//        Log.d("LOGLOG", Arrays.toString(toast));
//        openPdf(toast);
     //   Toast.makeText(mContext, "Установите приложение для - Чтения PDF файла", Toast.LENGTH_SHORT).show();
       // (new OpenPdf()).execute(mContext,bytes);
      //  (new OpenPdf()).execute(mContext,bytes);
        Intent i = new Intent( mContext, PdfActivity.class);
        i.putExtra("pdfsend", bytes);
        mContext.startActivity(i);
        //Скормить классу PDF
    }

//    private void openPdf(byte[] toast){
//
//
//        try {
//            File dir = new File(mContext.getFilesDir(), "mydir");
//            if (!dir.exists()) {
//                dir.mkdir();
//            }
//            File file = new File(dir.getPath() + "/" + "FileName1.pdf");
//
//
//            file.createNewFile();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                Files.copy(
//                        new ByteArrayInputStream(toast),
//                        file.toPath(),
//                        StandardCopyOption.REPLACE_EXISTING);
//            }
//            Uri uri = FileProvider.getUriForFile(
//                    mContext, mContext.getPackageName() + ".provider",
//                    file
//            );
//            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//            intent.setDataAndType(uri, "application/pdf");
//            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
//            mContext.startActivity(intent);
//
//
//// 1           string contentDisposition;
////            if (Request.Browser.Browser == "IE" && (Request.Browser.Version == "7.0" || Request.Browser.Version == "8.0"))
////                contentDisposition = "attachment; filename=" + Uri.EscapeDataString(fileName);
////            else if (Request.Browser.Browser == "Safari")
////                contentDisposition = "attachment; filename=" + fileName;
////            else
////                contentDisposition = "attachment; filename*=UTF-8''" + Uri.EscapeDataString(fileName);
//// 1           Response.AddHeader("Content-Disposition", contentDisposition);
//
////
////            //file.write(PDF.getBytes());
////            // file.close();
////            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
////
////            // PDFView pdfView = findViewById(R.id.pdfView);
////            PDFView pdfView = findViewById(R.id.pdfView);
////            //   pdfView.fromBytes(PDF.getBytes());
////            //  pdfView.fromAsset(dir.getPath()+ "/"+"FileName1.pdf");
//////        if(pdfView==null){
//////            Log.d("fregfaer","vreve");
//////        }
//////        Log.d("fregfaer",PDF);
////
//////            pdfView.fromFile(file)
////                       pdfView.fromAsset("Prints2.pdf")
////                    .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
////                    .enableSwipe(true) // allows to block changing pages using swipe
////                    .swipeHorizontal(false)
////                    .enableDoubletap(true)
////                    .defaultPage(0)
////                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
////                    .password(null)
////                    .scrollHandle(null)
////                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
////                    // spacing between pages in dp. To define spacing color, set view background
////                    .spacing(0)
////                    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
////                    .load();
//////        } catch (FileNotFoundException e) {
//////            e.printStackTrace();
//////        } catch (IOException e) {
//////            e.printStackTrace();
//////        }
////
////            //2
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("LOGLOG", e.toString());
//        }
//    }
    @JavascriptInterface
    public void processHTML(String login, String pass)
    {
        dataPreferences.setLogin(login);
        dataPreferences.setPassword(pass);
       // Log.d("LOGLOG","login: "+login + " pass: " + pass);
    }

}
