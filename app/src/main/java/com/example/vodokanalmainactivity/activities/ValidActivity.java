package com.example.vodokanalmainactivity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.vodokanalmainactivity.App;
import com.example.vodokanalmainactivity.R;
import com.example.vodokanalmainactivity.data.preferences.DataPreferences;

public class ValidActivity extends AppCompatActivity {
    DataPreferences dataPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid);


        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.light_none));
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
       // getWindow().getDecorView().setSystemUiVisibility(0);

      //  getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));
      //  getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        dataPreferences = ((App) getApplication()).dataPreferences;
    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        try {
//            super.onConfigurationChanged(newConfig);
//            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                // land
//            } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                // port
//            }
//        } catch (Exception ex) {
//        }
//    }
        public void onCheckCode(View button){
        final EditText form = findViewById(R.id.pin_code_field);

        if(form.getText().toString().equals(dataPreferences.getPinCode())){
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Неверный ПИН-код!", Toast.LENGTH_SHORT).show();
        }

    }
    public void onExit(View view){
        finishAffinity();
        System.exit(0);
    }
    public void onDel(View view){
        EditText simpleEditText = findViewById(R.id.pin_code_field);
        simpleEditText.setText("");
    }
    public void onTapButton(View view){
        Button button = (Button) view;
        EditText simpleEditText = findViewById(R.id.pin_code_field);
        simpleEditText.append(button.getText().toString());
       // Log.d("LOGLOG",button.getText().toString());
    }
}