package com.rathana.room_persistance_demo.data.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

@Entity(tableName = "book")
public class Book implements Parcelable {

    public Book(){}

    @PrimaryKey(autoGenerate = true)
    public int id;
    //@ColumnInfo(name = "book_title")
    public String title;
    public String author;
    public String date;
    public String desc;
    public double price;
    @Ignore
    @DrawableRes
    public int thumb;
    @ColumnInfo(name = "user_id")
    public int userId;
    @Ignore
    public Bitmap bitmap;

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        date = in.readString();
        desc = in.readString();
        price = in.readDouble();
        thumb = in.readInt();
        userId = in.readInt();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(date);
        dest.writeString(desc);
        dest.writeDouble(price);
        dest.writeInt(thumb);
        dest.writeInt(userId);
        dest.writeParcelable(bitmap, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

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
