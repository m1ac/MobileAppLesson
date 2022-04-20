package com.example.mobileapplessoncalculator;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/users?page=1")
    Call<TestResponse> getData();
}
