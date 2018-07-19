/*
 *  Copyright [2017] [Muhammad Elsayed]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.muhammadelsayed.echo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.muhammadelsayed.echo.model.ResultArticles;
import com.muhammadelsayed.echo.network.NewsClient;
import com.muhammadelsayed.echo.network.RetrofitClientInstance;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started !!");

        testNetworkRequest();

    }

    private void testNetworkRequest() {
        Log.d(TAG, "testNetworkRequest: testing network calls...");
        Map<String, String> options = new HashMap<>();
        options.put("country", "eg");
        options.put("apiKey", getResources().getString(R.string.news_api_key));

        NewsClient service = RetrofitClientInstance.getRetrofitInstance()
                .create(NewsClient.class);

        Call<ResultArticles> call = service.getTopHeadLines(options);

        call.enqueue(new Callback<ResultArticles>() {
            @Override
            public void onResponse(@NonNull Call<ResultArticles> call, @NonNull Response<ResultArticles> response) {
                Log.d(TAG, "onResponse: SUCCEEDED");
                Log.d(TAG, "onResponse: RESPONSE = " + response.body().getArticles()[3].getTitle());
            }

            @Override
            public void onFailure(@NonNull Call<ResultArticles> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: FAILED");

            }
        });
    }

}