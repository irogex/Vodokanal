package com.example.vodokanalmainactivity.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vodokanalmainactivity.App;
import com.example.vodokanalmainactivity.data.preferences.DataPreferences;

public class MainActivity extends AppCompatActivity {
    DataPreferences dataPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataPreferences = ((App) getApplication()).dataPreferences;

        Intent intent ;
        final  String pinCode = dataPreferences.getPinCode();
        if(pinCode==null){
            intent = new Intent(this,PinCodeActivity.class);
        }else{
            intent = new Intent(this,ValidActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}