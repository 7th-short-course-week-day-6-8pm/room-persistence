package com.rathana.room_persistance_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rathana.room_persistance_demo.data.BookDatabase;
import com.rathana.room_persistance_demo.data.dao.BookDao;
import com.rathana.room_persistance_demo.data.entity.Book;

import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    EditText etTitle,etDesc,etDate,etPrice,etAuthor;
    Button btnSave,btnGetBook;

    BookDatabase bookDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etTitle=findViewById(R.id.etTitle);
        etAuthor=findViewById(R.id.etAuthor);
        etDesc=findViewById(R.id.etDesc);
        etDate=findViewById(R.id.etDate);
        etPrice=findViewById(R.id.etPrice);
        btnSave=findViewById(R.id.btnSave);

        btnGetBook=findViewById(R.id.btnGetBook);
        //init object book database
        bookDatabase=BookDatabase.getInstance(this);

        btnSave.setOnClickListener(v->{
            Book book=new Book();
            book.title=etTitle.getText().toString();
            book.author=etAuthor.getText().toString();
            book.date=etDate.getText().toString();
            book.desc=etDesc.getText().toString();
            book.userId=0;

            book.price=Double.parseDouble(etPrice.getText().toString());
            saveBook(book);
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        });

        btnGetBook.setOnClickListener(v->{
            getBookList();
        });

    }

    private Long saveBook(Book book){
        if(book!=null){
           return bookDatabase.bookDao().save(book);
        }
        return 0l;
    }

    private static final String TAG = "AddBookActivity";
    private void getBookList(){
        List<Book> books=bookDatabase.bookDao().getBooks();
        Log.e(TAG, "getBookList: "+books.toString() );
    }

}
