package com.example.mobileapplessoncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvNumber;
    Float num1 = null;
    IOperation operation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //variable_type variable_name = value;

        IOperation operation = new Power();
        operation.Do(5, 6);

        tvNumber = findViewById(R.id.tvNumber);

        Button btnNum7 = findViewById(R.id.btn7);
        //btnNum7.setText("0");
        /*btnNum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvNumber.setText(((TextView) view).getText());
            }
        });*/

        Button btnNum9 = findViewById(R.id.btn9);
        //btnNum9.setOnClickListener(this);
    }

    public void BtnClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            //Toplama
            num1 = Float.parseFloat(tvNumber.getText() + "");
            operation = new Addiction();
            tvNumber.setText("");
        } else if (v.getId() == R.id.btnSub) {
            //Çıkarma
            num1 = Float.parseFloat(tvNumber.getText() + "");
            operation = new Subtraction();
            tvNumber.setText("");
        } else if (v.getId() == R.id.btnEqual) {
            //Eşittir
            Float result = operation.Do(num1, Float.parseFloat(tvNumber.getText() + ""));
            tvNumber.setText(result + "");
        } else {
            tvNumber.setText(tvNumber.getText() + String.valueOf(v.getTag()));
        }
    }

    void multiply(int n1, int n2) {
        int result = n1 * n2;
        Log.e("Sonuç", result + "");
    }

    @Override
    public void onClick(View view) {
        tvNumber.setText(view.getTag() + "");
    }
}