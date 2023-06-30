package com.first.alarm.RetData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCity {
    private static RetrofitCity instance = null;
    private api myApi;

    private RetrofitCity() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(api.class);
    }

    public static synchronized RetrofitCity getInstance() {
        if (instance == null) {
            instance = new RetrofitCity();
        }
        return instance;
    }

    public api getMyApi() {
        return myApi;
    }
}
