package com.example.user.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by user on 3/25/2018.
 */

public interface ApiInterfance {
    @POST("api/auth/login/")
    Call<ResponseBody> postData(@Body RequestBody requestBody);

}
