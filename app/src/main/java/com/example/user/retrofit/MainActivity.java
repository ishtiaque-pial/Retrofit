package com.example.user.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ApiInterfance apiInterfance;
    TextView ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ok = findViewById(R.id.ok);
        init();
        final RequestBody requestBody = new RequestBody();
        requestBody.setEmail("muhitunazad@gmail.com");
        requestBody.setPassword("asdf");
        Call<ResponseBody> responseBodyCall = apiInterfance.postData(requestBody);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ok.setText(response.body().getToken().toString());
                } else {
                    Toast.makeText(MainActivity.this, "not ok", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ok.setText(t.getMessage());
            }
        });

    }

    private void init() {
        apiInterfance = new Retrofit.Builder()
                .baseUrl("http://deblah.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterfance.class);
    }
}
