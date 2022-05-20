package com.example.dogapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.Serializable;

import kotlin.jvm.Transient;

@Entity
public class DogBreed implements Serializable {

    private static final long serialVersionUID = 1765583966357755201L;
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    public DogBreed() {
    }

    @ColumnInfo
    @SerializedName("name")
    private String name;

    @ColumnInfo
    @SerializedName("life_span")
    private String lifeSpan;

    @ColumnInfo
    @SerializedName("origin")
    private String origin;

    @ColumnInfo
    @SerializedName("url")
    private String url;

    @Ignore
    @SerializedName("weight")
    private Weight weight;

    @Ignore
    @SerializedName("height")
    private Height height;

    @ColumnInfo
    @SerializedName("bred_for")
    private String bred_for;

    @ColumnInfo
    @SerializedName("breed_group")
    private String breed_group;

    @ColumnInfo
    @SerializedName("temperament")
    private String temperament;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {

        this.height = height;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public DogBreed( String name, String lifeSpan, String origin, String url, Weight weight, Height height, String bred_for, String breed_group, String temperament) {
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.url = url;
        this.weight = weight;
        this.height = height;
        this.bred_for = bred_for;
        this.breed_group = breed_group;
        this.temperament = temperament;
    }
}
