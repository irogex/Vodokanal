package com.example.vodokanalmainactivity.activities;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.vodokanalmainactivity.R;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PdfActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        Bundle arguments = getIntent().getExtras();
        String PDF = arguments.getString("pdfsend");
//        Toast.makeText(this, "Иду на запись", Toast.LENGTH_SHORT).show();
        try {
            // FileOutputStream fileOutput = openFileOutput("FileName1.txt", MODE_PRIVATE);
            File dir = new File(getApplicationContext().getFilesDir(), "mydir");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir.getPath() + "/" + "FileName1.pdf");
            file.createNewFile();
//            InputStream inputStream = getAssets().open("Prints2.pdf");
//
//            byte[] buffer = new byte[8192];
//            int bytesRead;
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                output.write(buffer, 0, bytesRead);
//            }
//            byte pdff[] = output.toByteArray();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Files.copy(
                        new ByteArrayInputStream(PDF.getBytes()),
                        file.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            Log.d("Freve",file.length()+"vreave");
            Uri uri = FileProvider.getUriForFile(
                    getApplicationContext(), getApplicationContext().getPackageName() + ".provider",
                    file
            );
//            val int = Intent(Intent.ACTION_VIEW, uri).apply {
//                setDataAndType(uri, "application/vnd.android.package-archive")
//                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            }
//            context.startActivity(int)
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
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

        } catch (IOException e) {
        }

    }
}
