package com.first.alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.first.alarm.Model.CityRet;
import com.first.alarm.RetData.RetrofitCity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {
    TextView text,tem,hum,pre;
    Intent intent;
      String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        text = findViewById(R.id.textView4);
        tem = findViewById(R.id.tem);
        pre = findViewById(R.id.pre);
        hum = findViewById(R.id.hum);
      intent = getIntent();
        city = intent.getStringExtra("city");

        Call call = RetrofitCity.getInstance().getMyApi().getDetails("Morocco/"+city );

        call.enqueue(new Callback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Gson g= new Gson();
                JSONObject jsonObject = new JSONObject((Map) response.body());
                try {
                    JSONObject current = jsonObject.getJSONObject("current");
                    CityRet f = g.fromJson(String.valueOf(current),CityRet.class);
                   text.setText(f.condition.text+"");
                    tem.setText(f.temp_c+" C");
                    pre.setText(f.pressure_in+" hPa");
                   hum.setText(f.humidity+" %");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(Details.this,  "Da", Toast.LENGTH_SHORT).show();


            }






        });
    }

}
