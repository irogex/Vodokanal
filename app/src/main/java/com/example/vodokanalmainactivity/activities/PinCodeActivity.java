package com.example.vodokanalmainactivity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.vodokanalmainactivity.App;
import com.example.vodokanalmainactivity.R;
import com.example.vodokanalmainactivity.data.preferences.DataPreferences;

public class PinCodeActivity extends AppCompatActivity {
    DataPreferences dataPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code);
        //getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));
       // getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.light_none));
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        dataPreferences = ((App) getApplication()).dataPreferences;
    }
   public void onEditCode(View button){
        final EditText form = findViewById(R.id.pin_code_field);
        dataPreferences.setPinCode(form.getText().toString());
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void onEditCodeButton(View button){
        final EditText form = findViewById(R.id.pin_code_field);
        dataPreferences.setPinCode(form.getText().toString());
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
    }
}