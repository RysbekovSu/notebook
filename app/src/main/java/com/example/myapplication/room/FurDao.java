package com.example.myapplication.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.models.Fur;

import java.util.List;
@Dao
public interface FurDao {

    @Query("SELECT*FROM furs")
    List<Fur> getAll();
    @Insert
    void insert(Fur fur);
    @Delete
    void delete(Fur fur);
    @Update
    void  update(Fur fur);
    @Query("SELECT*FROM furs ORDER BY name ASC")
    List<Fur> sortAll();
}
