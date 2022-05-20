package com.example.dogapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dogapp.model.DogBreed;

import java.util.List;

@Dao
public interface DogDAO {

    @Query("select * from DogBreed")
    public List<DogBreed>getAllContacts();

    @Insert
    public void insertAllDogs(DogBreed... DogBreed);
}
