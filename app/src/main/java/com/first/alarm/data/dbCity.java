package com.first.alarm.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.first.alarm.Model.City;

@Database(entities = City.class,exportSchema = false,version = 1)
public abstract class dbCity extends RoomDatabase {
    private  static  final  String db_name ="Ciit.db";
    private  static  dbCity instance;
    public  static  synchronized dbCity getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),dbCity.class,db_name).fallbackToDestructiveMigration().build();

        }
        return  instance;
    }
    public abstract  DaoCity daocity();


}
