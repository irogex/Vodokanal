package com.example.vodokanalmainactivity.domain;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class OpenPdf {
    public void execute(Context mContext, byte[] toast){
        openPdfByFile(mContext,toast);
    }
//    public void execute2(Context pdfView, byte[] toast){
//        openPdf(pdfView,toast);
//    }
    private void openPdfByFile(Context mContext, byte[] toast){


        try {
            File dir = new File(mContext.getFilesDir(), "assets");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir.getPath() + "/" + "Prints2.pdf");
           // File file = new File("SHOW.pdf");

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
           // intent.setDataAndType(uri, "application/pdf");
            intent.setDataAndType(uri, "application/pdf");
           // intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           // intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
            mContext.startActivity(intent);
          //  Toast.makeText(mContext, "Все Ок", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //Toast.makeText(mContext, "Установите приложение для - Чтения PDF файла", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
           // Log.e("LOGLOG", e.toString());
        }
    }
    private void openPdf(Context pdfView, byte[] bytes){

    }
}
