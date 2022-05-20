package com.example.dogapp.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Weight {

    @ColumnInfo
    @SerializedName("imperial")
    private String imperial;

    @ColumnInfo
    @SerializedName("metric")
    private String metric;

    public Weight(String imperial, String metric) {
        this.imperial = imperial;
        this.metric = metric;
    }

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
