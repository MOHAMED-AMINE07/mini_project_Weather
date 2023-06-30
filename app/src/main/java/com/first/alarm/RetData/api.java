package com.first.alarm.RetData;

import com.first.alarm.Model.CityRet;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface api {


        String BASE_URL = "https://api.weatherapi.com/v1/";
        @GET("current.json?key=9b7747def2af408395b143518231006&lang=fr")
        Call<HashMap> getDetails(@Query("q") String city);

}
