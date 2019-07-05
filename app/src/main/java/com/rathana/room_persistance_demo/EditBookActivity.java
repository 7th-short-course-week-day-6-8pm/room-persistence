package com.rathana.room_persistance_demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rathana.room_persistance_demo.data.BookDatabase;
import com.rathana.room_persistance_demo.data.entity.Book;

public class EditBookActivity extends AppCompatActivity {
    EditText etTitle,etDesc,etDate,etPrice,etAuthor;
    Button btnSave;
    Book book;
    BookDatabase bookDatabase;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        etTitle=findViewById(R.id.etTitle);
        etAuthor=findViewById(R.id.etAuthor);
        etDesc=findViewById(R.id.etDesc);
        etDate=findViewById(R.id.etDate);
        etPrice=findViewById(R.id.etPrice);
        btnSave=findViewById(R.id.btnSave);

        bookDatabase=BookDatabase.getInstance(this);

        Intent intent=getIntent();
        if(intent!=null){

            book = intent.getParcelableExtra("book");
            Log.e("data", "onCreate: "+book);
            if(book!=null) {
                etTitle.setText(book.title);
                etAuthor.setText(book.author);
                etDate.setText(book.date);
                etDesc.setText(book.desc);
                etPrice.setText("" + book.price);
            }
        }


        btnSave.setOnClickListener(v->{
            //save room  and book list
            if(book==null)
                book=new Book();

            book.title = etTitle.getText().toString();
            book.author=etAuthor.getText().toString();
            book.price=Double.parseDouble(etPrice.getText().toString());
            book.date=etDate.getText().toString();
            book.desc=etDesc.getText().toString();
            bookDatabase.bookDao().update(book);
            Toast.makeText(this, "save change success", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this,MainActivity.class));
            finish();

        });
    }
}
