package com.example.myapplication.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.models.Fur;

@Database(entities = {Fur.class}, version = 3)

public abstract class AppDataBase extends RoomDatabase {
    public abstract FurDao furDao();
}
