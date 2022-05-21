package com.example.vodokanalmainactivity.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vodokanalmainactivity.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfActivity extends AppCompatActivity  {
   // DataPreferences dataPreferences;
  //  public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    //String resultString = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dataPreferences = ((App) getApplication()).dataPreferences;
        Bundle arguments = getIntent().getExtras();
        String PDF = arguments.get("pdfsend").toString(); //Здесь Буффер Обмена //Нужно получить Байты
        String Test = "sdfs";
        //byte[] byteArray = PDF.getBytes();
        Toast.makeText(this, "Иду на запись", Toast.LENGTH_SHORT).show();
        try {
            FileOutputStream fileOutput = openFileOutput("FileName.pdf", MODE_PRIVATE);
           // fileOutput.write(PDF.getBytes(StandardCharsets.UTF_8));
            fileOutput.write(Test.getBytes());
            fileOutput.close();
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_pdf);


        PDFView pdfView = findViewById(R.id.pdfView);

        //2
        pdfView.fromAsset("FileName.pdf")
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
    }


}
