package com.muhammadelsayed.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.muhammadelsayed.echo.model.Retrosponse;
import com.muhammadelsayed.echo.network.NewsClient;
import com.muhammadelsayed.echo.network.RetrofitClientInstance;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate: has been instantiated");

        testNetworkCalls();

    }

    private void testNetworkCalls() {

        NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);
        Map<String, Object> options = new HashMap<>();
        options.put("section", "business");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("q", "business");
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Call<Retrosponse> call = service.search(options);

        Log.d(TAG, "testNetworkCalls: " + call.request().url());
        call.enqueue(new Callback<Retrosponse>() {
            @Override
            public void onResponse(Call<Retrosponse> call, Response<Retrosponse> response) {
                Log.e(TAG, "onResponse: RESPONSE = " + response.body());

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Retrosponse> call, Throwable t) {
                Log.e(TAG, "onFailure: FAILED !!");
            }
        });

    }
}

