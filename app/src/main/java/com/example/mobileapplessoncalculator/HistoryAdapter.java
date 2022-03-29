package com.example.mobileapplessoncalculator;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    List<Operation> operations;

    public HistoryAdapter(List<Operation> operations) {
        this.operations = operations;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, int position) {
        holder.tvNum1.setText(operations.get(position).num1 + "");
        holder.tvNum2.setText(operations.get(position).num2 + "");
        holder.tvSign.setText(operations.get(position).GetSign());
        holder.tvSign.setTextColor(operations.get(position).GetSignColor());
        if (position == operations.size() - 1) {
            holder.itemView.setAlpha(0);
            holder.itemView.animate().alpha(1).setStartDelay(500).setDuration(1500).start();
        }
        /*switch (operations.get(position).GetSign()) {
            case "+":
                holder.tvSign.setTextColor(Color.GREEN);
                break;
            case "-":
                holder.tvSign.setTextColor(Color.RED);
                break;
            case "*":
                holder.tvSign.setTextColor(Color.MAGENTA);
                break;
            case "/":
                holder.tvSign.setTextColor(Color.BLUE);
                break;
        }*/

        /*
        + -> GREEN
        - -> RED
        * -> MAGENTA
        / -> BLUE
         */
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNum1, tvSign, tvNum2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNum1 = itemView.findViewById(R.id.tvNum1);
            tvNum2 = itemView.findViewById(R.id.tvNum2);
            tvSign = itemView.findViewById(R.id.tvSign);
        }
    }
}
