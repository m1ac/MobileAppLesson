package com.example.mobileapplessoncalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        /*String imageUrl = "https://images.pexels.com/photos/158827/field-corn-air-frisch-158827.jpeg";
        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        Glide.with(this).asBitmap().load(imageUrl).addListener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                Toast.makeText(GlideActivity.this, "Resim indirilirken hata oluştu!", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                Toast.makeText(GlideActivity.this, "Resim başarıyla indirildi", Toast.LENGTH_SHORT).show();
                Log.e("BitmapSize", resource.getWidth() + "x" + resource.getHeight());
                return false;
            }
        }).into(imageView1);*/
        //Glide.with(this).load(imageUrl).into(imageView1);
        //Picasso.get().load(imageUrl).into(imageView1);

        RecyclerView recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        ApiService apiService = RetrofitInstance.getInstance().create(ApiService.class);
        Call<TestResponse> call = apiService.getData();
        call.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {

                recyclerViewUsers.setAdapter(new UserAdapter(response.body().getData()));

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
}