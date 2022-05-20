package com.example.dogapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dogapp.model.DogBreed;

@Database(entities = {DogBreed.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DogDAO dogDAO();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "dogApp1914").allowMainThreadQueries().build();
        }
        return instance;
    }

}
