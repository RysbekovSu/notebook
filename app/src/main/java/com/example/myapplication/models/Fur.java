package com.example.myapplication.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "furs")
public class Fur {

    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "tel")
    private String tel;
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Fur(String name, String tel, byte[] image) {
        this.name = name;
        this.tel = tel;
        this.image = image;
    }
    @Ignore
    public Fur(long id, String name, String tel, byte[] image) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
