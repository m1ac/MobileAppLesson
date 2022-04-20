package com.example.mobileapplessoncalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

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
        //variable_type variable_name = value;
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        tvExpression = findViewById(R.id.tvExpression);
        cardViewOnOff = findViewById(R.id.cardViewOnOff);

        ApiService apiService = RetrofitInstance.getInstance().create(ApiService.class);
        Call<TestResponse> call = apiService.getData();
        call.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {
                //Log.e("Response", (new Gson()).toJson(response.body()));
                for (User user : response.body().getData()) {
                    Log.e("User", user.getFirst_name() + " " + user.getEmail());
                }
                Log.e("Response", response.body().getTotal() + "");
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {
                Log.e("Hata", t.getMessage());
            }
        });
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
        Log.e("Sonuç", result + "");
    }
}