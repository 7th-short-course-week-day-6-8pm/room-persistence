package com.rathana.room_persistance_demo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rathana.room_persistance_demo.adapter.BookAdapter;
import com.rathana.room_persistance_demo.data.BookDatabase;
import com.rathana.room_persistance_demo.data.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
 implements BookAdapter.OnItemClick {

    BookAdapter bookAdapter;
    RecyclerView rvBook;
    List<Book> books=new ArrayList<>();
    BookDatabase bookDatabase;
    FloatingActionButton btnAddBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvBook=findViewById(R.id.rvBook);
        btnAddBook=findViewById(R.id.btnAddBook);
        bookDatabase=BookDatabase.getInstance(this);

        bookAdapter=new BookAdapter(books,this,this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rvBook.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,((LinearLayoutManager) layoutManager).getOrientation());
        rvBook.addItemDecoration(dividerItemDecoration);
        rvBook.setAdapter(bookAdapter);

        getBooks();

        btnAddBook.setOnClickListener(v->{
            startActivity(new Intent(this,AddBookActivity.class));
            finish();
        });
    }

    private void getBooks(){
//        for(int i=0;i<50;i++){
////            Book book=new Book();
////            book.title="the Hook "+i;
////            book.price=23.3d;
////            book.thumb=R.drawable.mavel;
////            book.date="10/04/1997";
////            book.userId=0;
////            book.author="Jame Smith";
////            books.add(book);
////        }

        List<Book> books =bookDatabase.bookDao().getBooks();
        Log.e("activity",""+books);
        //this.books.addAll(books);
        //bookAdapter.notifyDataSetChanged();
        bookAdapter.addMoreItem(books);
    }

    @Override
    public void onRemoveItem(Book book, int pos) {
        bookDatabase.bookDao().remove(book);
        bookAdapter.removeItem(book,pos);
        Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditItem(Book book, int pos) {
        Intent intent=new Intent(this,EditBookActivity.class);
        Bundle bundle=new Bundle();
        bundle.putParcelable("book",book);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
}
