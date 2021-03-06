package com.example.mobileapplessoncalculator;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tvExpression;
    RecyclerView recyclerViewHistory;
    CardView cardViewOnOff;
    Operation operation = null;
    List<Operation> operations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Intent glideIntent = new Intent(this, GlideActivity.class);
        startActivity(glideIntent);


        //variable_type variable_name = value;
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
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

                LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
                recyclerViewHistory.setLayoutManager(layoutManager);
                recyclerViewHistory.setAdapter(new HistoryAdapter(operations));
                recyclerViewHistory.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerViewHistory.scrollToPosition(operations.size() - 1);
                    }
                });

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
        Log.e("Sonu??", result + "");
    }
}