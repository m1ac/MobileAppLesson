package com.example.mobileapplessoncalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>  {
    List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvFirstName.setText(user.getFirst_name());
        holder.tvLastName.setText(user.getLast_name());
        Glide.with(holder.itemView.getContext()).load(user.getAvatar()).into(holder.imageAvatar);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAvatar;
        TextView tvFirstName, tvLastName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageAvatar = itemView.findViewById(R.id.imageAvatar);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
        }
    }
}
