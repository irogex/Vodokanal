package com.example.vodokanalmainactivity.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vodokanalmainactivity.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Bundle arguments = getIntent().getExtras();
        byte[] PDF = arguments.getByteArray("pdfsend");
        PDFView pdfView = findViewById(R.id.pdfView);
                     pdfView.fromBytes(PDF)
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
                    //.pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                    .load();
    }
}