package com.example.mobileapplessoncalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvExpression;
    RecyclerView recyclerView;
    CardView cardViewOnOff;
    Operation operation = null;
    List<IOperation> operations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //variable_type variable_name = value;
        recyclerView = findViewById(R.id.recyclerView1);
        tvExpression = findViewById(R.id.tvExpression);
        cardViewOnOff = findViewById(R.id.cardViewOnOff);
    }

    public void CardNumberClick(View v) {
        tvExpression.setText(tvExpression.getText().toString() + v.getTag());
    }

    public void CardOperationClick(View v) {
        switch (v.getTag().toString()) {
            case "/":
                operation = new Division();
                TakeNumberAndClear();
                break;
            case "*":
                operation = new Multiplication();
                TakeNumberAndClear();
                break;
            case "+":
                operation = new Addiction();
                TakeNumberAndClear();
                break;
            case "-":
                operation = new Subtraction();
                TakeNumberAndClear();
                break;
            case "=":
                operation.num2 = Float.parseFloat(tvExpression.getText().toString());
                operations.add(operation);

                float result = operation.Do(operation.num1, operation.num2);
                tvExpression.setText(result + "");
                /*tvHistory.setText(tvHistory.getText() + "\n" +
                        operation.num1 + operation.GetSign() + operation.num2 + "=" + result);*/
                //tvExpression.setText("");
                break;
            default:
                break;
        }
    }

    private void TakeNumberAndClear() {
        operation.num1 = Float.parseFloat(tvExpression.getText().toString());
        tvExpression.setText("");
    }

    public void OnOff(View view) {
        if (view.getTag().toString().equals("0")) {
            //cardViewOnOff.setVisibility(View.GONE);
            cardViewOnOff.animate().alpha(0).setDuration(1000).start();
            view.setTag("1");
        } else {
            //cardViewOnOff.setVisibility(View.VISIBLE);
            cardViewOnOff.animate().alpha(1).setDuration(1000).start();
            view.setTag("0");
            tvExpression.setText("");
        }
    }

    void multiply(int n1, int n2) {
        int result = n1 * n2;
        Log.e("Sonuç", result + "");
    }
}