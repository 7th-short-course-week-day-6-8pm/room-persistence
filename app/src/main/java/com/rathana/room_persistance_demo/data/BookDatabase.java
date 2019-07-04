package com.rathana.room_persistance_demo.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rathana.room_persistance_demo.data.dao.BookDao;
import com.rathana.room_persistance_demo.data.entity.Book;

@Database(version = 1,entities = {Book.class})
public abstract class BookDatabase extends RoomDatabase {

    static final String DB_NAME="book_db";

    public abstract BookDao bookDao();

    public static BookDatabase getInstance(Context context){
        //Room.inMemoryDatabaseBuilder();
        return  Room.databaseBuilder(context,BookDatabase.class,DB_NAME)
                .allowMainThreadQueries()
                .build();
    }
}
