package com.first.alarm.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city")
public class City {
    @PrimaryKey(autoGenerate = true)
    public int _id;
    private String nameCity;

    public City( String nameCity) {

        this.nameCity = nameCity;
    }

    public int getId() {
        return _id;
    }

    public String getNameCity() {
        return nameCity;
    }
}
