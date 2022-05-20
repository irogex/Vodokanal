package com.example.vodokanalmainactivity.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vodokanalmainactivity.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

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
       // String PDF = arguments.get("pdfsend").toString(); //Здесь Буффер Обмена
        String PDF = arguments.get("pdfsend").toString(); //Здесь Буффер Обмена //Нужно получить Байты
        //String PDF = getBytes(arguments.get("pdfsend"));
        byte[] byteArray = PDF.getBytes();


       // byte[] bytes = new byte[byteArray];
//        fin.read(bytes);
//        String text = new String (bytes);
//        textView.setText(text);

       // Toast.makeText(this, PDF, Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_pdf);
        PDFView pdfView = findViewById(R.id.pdfView);

      //  File file = new File(String.valueOf(byteArray), "/assets/FileName.pdf"); //Создаю файл
        //File file = new File("asdasd", "assets/FileName.pdf"); //Создаю файл

        String filepath = "assets/phone.png";
        Path path = Paths.get(filepath);
        if (Files.notExists(path)) {
//            throw new IllegalArgumentException("File is not exists!");
//        }
//        if(!filepath.exists()) {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            try {
               // file.createNewFile();
               // boolean created = file.createNewFile();
              //  if(created)
                   // System.out.println("File has been created");

                // convert the file's content to byte[]
                byte[] bytes = Files.readAllBytes(path);

                // encode, byte[] to Base64 encoded string
                String s = Base64.getEncoder().encodeToString(bytes);
                System.out.println(s);

                // decode, Base64 encoded string to byte[]
                byte[] decode = Base64.getDecoder().decode(s);

                // save into another image file.
                Files.write(Paths.get("assets/phone.png"), decode);
               // EditText textBox = findViewById(R.id.editor);
               // String text = textBox.getText().toString();
              //  file.write(Paths.get("/Users/mkyong/phone2.png"), decode);
//                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
//                fos.write(text.getBytes());
                Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
           // InputStream()
            //inputStream //Запись файла
        }


       // String myURL = "https://irogex.ru/api/test";
//        try {
//            String myURL = "https://irogex.ru/api/test";
//            String parammetrs = "ids=123";
//            byte[] data;
//            InputStream is;
//
//            try {
//                URL url = new URL(myURL);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("POST");
//                conn.setDoOutput(true);
//                conn.setDoInput(true);
//
//                conn.setRequestProperty("Content-Length", "" + Integer.toString(parammetrs.getBytes().length));
//                OutputStream os = conn.getOutputStream();
//                data = parammetrs.getBytes("UTF-8");
//                os.write(data);
//                data = null;

//                conn.connect();
//                int responseCode= conn.getResponseCode();

//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//                if (responseCode == 200) {
//                    is = conn.getInputStream();
//
//                    byte[] buffer = new byte[8192]; // Такого вот размера буфер
//                    // Далее, например, вот так читаем ответ
//                    int bytesRead;
//                    while ((bytesRead = is.read(buffer)) != -1) {
//                        baos.write(buffer, 0, bytesRead);
//                    }
//                    data = baos.toByteArray();
//                    resultString = new String(data, "UTF-8");
//                } else {
//                }



//            } catch (MalformedURLException e) {
//
//                //resultString = "MalformedURLException:" + e.getMessage();
//            } catch (IOException e) {
//
//                //resultString = "IOException:" + e.getMessage();
//            } catch (Exception e) {
//
//                //resultString = "Exception:" + e.getMessage();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
       // return null;
        //https://irogex.ru/api/test
       // pdfView.fromAsset("pdfexmple.pdf")
      //  Log.d("LOGLOG","press-"+PDF);

        //2
//        pdfView.fromAsset("Prints2.pdf")
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
