package com.first.alarm.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.first.alarm.Model.City;

import java.util.List;
@Dao
public interface DaoCity {
    @Query("SELECT * FROM city")
    List<City> getAllUsers();
    @Insert
    void AddCity(City city);
    @Delete
    void deleteCity(City city);
}
