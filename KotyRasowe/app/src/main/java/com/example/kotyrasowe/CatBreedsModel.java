package com.example.kotyrasowe;


import android.graphics.Bitmap;

public class CatBreedsModel {

    private String name;
    private Bitmap cat_bitmap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getCat_bitmap() {
        return cat_bitmap;
    }

    public void setCat_bitmap(Bitmap cat_bitmap) {
        this.cat_bitmap = cat_bitmap;
    }
}