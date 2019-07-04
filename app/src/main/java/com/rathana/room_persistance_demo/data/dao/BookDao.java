package com.rathana.room_persistance_demo.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.util.Log;

import com.rathana.room_persistance_demo.data.entity.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    long save(Book book);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] save(List<Book> books);

    @Update
    void update(Book book);
    @Delete
    void remove(Book book);

    @Query("SELECT * FROM book order by id asc")
    List<Book> getBooks();

}
