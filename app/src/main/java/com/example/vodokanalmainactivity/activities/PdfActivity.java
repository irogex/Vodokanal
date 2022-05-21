package com.example.vodokanalmainactivity.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vodokanalmainactivity.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PdfActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Bundle arguments = getIntent().getExtras();
        String PDF = arguments.get("pdfsend").toString();
        Toast.makeText(this, "Иду на запись", Toast.LENGTH_SHORT).show();
        try {
           // FileOutputStream fileOutput = openFileOutput("FileName1.txt", MODE_PRIVATE);
            File dir = new File(getApplicationContext().getFilesDir(), "mydir");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dir.getPath()+"/" + "FileName1.pdf");
            file.createNewFile();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Files.copy(
                        new ByteArrayInputStream(PDF.getBytes()),
                        file.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }

            //file.write(PDF.getBytes());
           // file.close();
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();

            PDFView pdfView = findViewById(R.id.pdfView);
            pdfView.fromAsset(dir.getPath()+ "/"+"FileName1.pdf")
                    //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2

    }


}
