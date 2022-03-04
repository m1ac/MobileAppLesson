package com.example.mobileapplessoncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //variable_type variable_name = value;

        Button btnNum7 = findViewById(R.id.btn7);
        btnNum7.setText("0");

        int num1 = 15;
        int num2 = 25;
        multiply(num1, num2);
    }

    void multiply(int n1, int n2) {
        int result = n1 * n2;
        Log.e("Sonuç", result + "");
    }
}