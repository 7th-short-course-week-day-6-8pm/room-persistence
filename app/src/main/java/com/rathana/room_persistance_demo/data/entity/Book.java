package com.rathana.room_persistance_demo.data.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

@Entity(tableName = "book")
public class Book {

    @PrimaryKey(autoGenerate = true)
    public int id;
    //@ColumnInfo(name = "book_title")
    public String title;
    public String author;
    public String date;
    public String desc;
    public double price;
    //public int thumb;
    @ColumnInfo(name = "user_id")
    public int userId;
    @Ignore
    public Bitmap bitmap;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                ", bitmap=" + bitmap +
                '}';
    }
}
