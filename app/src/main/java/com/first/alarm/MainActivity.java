package com.first.alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.AlarmManager;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.first.alarm.Model.City;
import com.first.alarm.data.DaoCity;
import com.first.alarm.data.dbCity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     ListView lv;
    SimpleAdapter adb;
    List<City> cx;
    ArrayList<HashMap<String,String>> arrayAdapter;
    dbCity appdb;
    DaoCity daocity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar  tosolbar = findViewById(R.id.toolbar);
        lv = findViewById(R.id.lve);
        arrayAdapter =  new ArrayList<HashMap<String,String>>();
        setSupportActionBar(tosolbar);


        //onclick city
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Details.class);
                intent.putExtra("city",arrayAdapter.get(position).get("name"));
                startActivity(intent);
             }
        });

        //call method getcity
        getCity();
    }

    //get menu to appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //for menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
                showAlertDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void getdb(){
     appdb =  dbCity.getInstance(this);
     daocity = appdb.daocity();
  }


  //get all citys
  void getCity(){
      new Thread(()->{
          arrayAdapter.clear();
          getdb();
           cx = daocity.getAllUsers();
          for(int i =0;i<cx.size();i++){
              HashMap<String, String> hm = new HashMap<>();
              hm.put("name",cx.get(i).getNameCity());
              arrayAdapter.add(hm);
          }
          runOnUiThread(()->{
              String[] from = new String[]{"name"};
              int[] to = new int[]{R.id.name};


               adb = new SimpleAdapter(getApplicationContext(),arrayAdapter,R.layout.card_ville,from,to){
                   @Override
                   public View getView(int position, View convertView, ViewGroup parent) {
                       View view = super.getView(position, convertView, parent);

                       ImageView btnRemove = view.findViewById(R.id.btnRemove);
                       btnRemove.setOnClickListener(v -> {
                           new Thread(()->{

                               getdb();
                               daocity.deleteCity(cx.get(position));
                               getCity();
                           }).start();

                       });

                       return view;
                   }

               };
            lv.setAdapter(adb);






          });
      }).start();
  }
  //alert for entre your city
     void showAlertDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Add");
        builder.setMessage("Add your city" );
         final EditText editText = new EditText(this);
         editText.setInputType(InputType.TYPE_CLASS_TEXT);
         builder.setView(editText);
         builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              new Thread(()->{
                  getdb();
                  daocity.AddCity(new City(editText.getText()+""));
                  getCity();
              }).start();
            }
        }).show();
}}