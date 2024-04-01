package com.example.myapplication.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.models.Fur;
import com.example.myapplication.models.Student;

@Database(entities = {Fur.class, Student.class}, version = 4, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract FurDao furDao();
    public abstract StudentDao studentDao();
}