package com.example.vodokanalmainactivity.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vodokanalmainactivity.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Bundle arguments = getIntent().getExtras();
        String PDF = arguments.get("pdfsend").toString();
        Toast.makeText(this, "Иду на запись", Toast.LENGTH_SHORT).show();
        try {
            FileOutputStream fileOutput = openFileOutput("assets/FileName1.txt", MODE_PRIVATE);
            fileOutput.write(PDF.getBytes());
            fileOutput.close();
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2
      //  PDFView pdfView = findViewById(R.id.pdfView);
//        pdfView.fromAsset("FileName1.pdf")
//                //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
//                .enableSwipe(true) // allows to block changing pages using swipe
//                .swipeHorizontal(false)
//                .enableDoubletap(true)
//                .defaultPage(0)
//                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
//                .password(null)
//                .scrollHandle(null)
//                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
//                // spacing between pages in dp. To define spacing color, set view background
//                .spacing(0)
//                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
//                .load();
    }


}
