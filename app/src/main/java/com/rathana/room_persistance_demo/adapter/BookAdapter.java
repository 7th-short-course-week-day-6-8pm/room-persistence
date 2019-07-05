package com.rathana.room_persistance_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.rathana.room_persistance_demo.R;
import com.rathana.room_persistance_demo.data.entity.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    List<Book> books;
    Context context;

    public BookAdapter(List<Book> books, Context context,OnItemClick listener) {
        this.books = books;
        this.context = context;
        this.listener= listener;
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book book=books.get(i);
        //if(book!=null){
            viewHolder.thumb.setImageResource(R.drawable.mavel);
            viewHolder.bookAuthor.setText(book.author);
            viewHolder.bookTitle.setText(book.title);
            viewHolder.bookDate.setText(book.date);
        //}

        viewHolder.btnMore.setOnClickListener(v->{
            PopupMenu menu=new PopupMenu(context,v);
            menu.inflate(R.menu.popup_menu);
            menu.setOnMenuItemClickListener(item->{
              switch (item.getItemId()){
                  case R.id.btnRemove:
                      listener.onRemoveItem(book,viewHolder.getAdapterPosition());
                      return true;
                  case R.id.btnEdit:
                      listener.onEditItem(book,viewHolder.getAdapterPosition());
                      return true;
              }

              return false;

            });
            menu.show();
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumb;
        TextView bookTitle,bookDate,bookAuthor;
        ImageView btnMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb=itemView.findViewById(R.id.thumb);
            bookTitle=itemView.findViewById(R.id.bookTitle);
            bookDate=itemView.findViewById(R.id.bookDate);
            bookAuthor=itemView.findViewById(R.id.bookAuthor);
            btnMore=itemView.findViewById(R.id.btnMore);
        }

    }

    public void addMoreItem(List<Book> books){
        int previousSize=getItemCount();
        this.books.addAll(books);
        Log.e("book size",""+this.books.size());
        notifyItemRangeInserted(previousSize-1,this.books.size());
        //notifyDataSetChanged();
    }

    public void removeItem(Book book,int pos){
        this.books.remove(book);
        notifyItemRemoved(pos);
    }
    OnItemClick listener;
    public interface OnItemClick{
        void onRemoveItem(Book book,int pos);
        void onEditItem(Book book,int pos);
    }


}
