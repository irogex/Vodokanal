package com.example.vodokanalmainactivity.domain;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class OpenPdf {
    public void execute(Context mContext, byte[] toast){
        openPdfByFile(mContext,toast);
    }
    private void execute2(PDFView pdfView, byte[] toast){
        openPdf(pdfView,toast);
    }
    private void openPdfByFile(Context mContext, byte[] toast){


        try {
            File dir = new File(mContext.getFilesDir(), "mydir");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir.getPath() + "/" + "FileName1.pdf");


            file.createNewFile();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Files.copy(
                        new ByteArrayInputStream(toast),
                        file.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            Uri uri = FileProvider.getUriForFile(
                    mContext, mContext.getPackageName() + ".provider",
                    file
            );
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
            mContext.startActivity(intent);


// 1           string contentDisposition;
//            if (Request.Browser.Browser == "IE" && (Request.Browser.Version == "7.0" || Request.Browser.Version == "8.0"))
//                contentDisposition = "attachment; filename=" + Uri.EscapeDataString(fileName);
//            else if (Request.Browser.Browser == "Safari")
//                contentDisposition = "attachment; filename=" + fileName;
//            else
//                contentDisposition = "attachment; filename*=UTF-8''" + Uri.EscapeDataString(fileName);
// 1           Response.AddHeader("Content-Disposition", contentDisposition);

//
//            //file.write(PDF.getBytes());
//            // file.close();
//            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
//
//            // PDFView pdfView = findViewById(R.id.pdfView);
//            PDFView pdfView = findViewById(R.id.pdfView);
//            //   pdfView.fromBytes(PDF.getBytes());
//            //  pdfView.fromAsset(dir.getPath()+ "/"+"FileName1.pdf");
////        if(pdfView==null){
////            Log.d("fregfaer","vreve");
////        }
////        Log.d("fregfaer",PDF);
//
////            pdfView.fromFile(file)
//                       pdfView.fromAsset("Prints2.pdf")
//                    .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
//                    .enableSwipe(true) // allows to block changing pages using swipe
//                    .swipeHorizontal(false)
//                    .enableDoubletap(true)
//                    .defaultPage(0)
//                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
//                    .password(null)
//                    .scrollHandle(null)
//                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
//                    // spacing between pages in dp. To define spacing color, set view background
//                    .spacing(0)
//                    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
//                    .load();
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//            //2

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LOGLOG", e.toString());
        }
    }
    private void openPdf(PDFView pdfView, byte[] bytes){


        try {


// 1           string contentDisposition;
//            if (Request.Browser.Browser == "IE" && (Request.Browser.Version == "7.0" || Request.Browser.Version == "8.0"))
//                contentDisposition = "attachment; filename=" + Uri.EscapeDataString(fileName);
//            else if (Request.Browser.Browser == "Safari")
//                contentDisposition = "attachment; filename=" + fileName;
//            else
//                contentDisposition = "attachment; filename*=UTF-8''" + Uri.EscapeDataString(fileName);
// 1           Response.AddHeader("Content-Disposition", contentDisposition);

//
//            //file.write(PDF.getBytes());
//            // file.close();
//            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
//
//            // PDFView pdfView = findViewById(R.id.pdfView);
//            PDFView pdfView = mContext.findViewById(R.id.pdfView);
//            //   pdfView.fromBytes(PDF.getBytes());
//            //  pdfView.fromAsset(dir.getPath()+ "/"+"FileName1.pdf");
////        if(pdfView==null){
////            Log.d("fregfaer","vreve");
////        }
////        Log.d("fregfaer",PDF);
//
            pdfView.fromBytes(bytes)
                    .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    // spacing between pages in dp. To define spacing color, set view background
                    .spacing(0)
                    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                    .load();
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//            //2

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LOGLOG", e.toString());
        }
    }
}
