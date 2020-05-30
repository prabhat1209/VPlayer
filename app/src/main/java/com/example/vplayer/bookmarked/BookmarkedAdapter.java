package com.example.vplayer.bookmarked;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vplayer.R;

import java.util.List;

public class BookmarkedAdapter extends RecyclerView.Adapter<BookmarkedAdapter.Holder> {

    private List<BookMarkedVideo> list;
    BookmarkedAdapter.ItemClickListener itemClickListener;
    Context context;

    public void setOnItemClickListener(ItemClickListener listener){
        itemClickListener = listener;
    }

    public BookmarkedAdapter(List<BookMarkedVideo> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_bookmarked,parent,false);
        Holder holder = new Holder(v, itemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.videoName.setText(list.get(position).getBook_name());
        //holder.imageView.setImageResource(R.drawable.hawayein);
        if(list.get(position).getBook_name().equals("Shayad"))
            holder.imageView.setImageResource(R.drawable.shayad);
        else
        if(list.get(position).getBook_name().equals("Broken Angel"))
            holder.imageView.setImageResource(R.drawable.broken_angel);
        else
        if(list.get(position).getBook_name().equals("Hawayein"))
            holder.imageView.setImageResource(R.drawable.hawayein);
        else
        if(list.get(position).getBook_name().equals("Love me like you do"))
            holder.imageView.setImageResource(R.drawable.love_me_like_you_do);
        else
        if(list.get(position).getBook_name().equals("Tu Har Lamha"))
            holder.imageView.setImageResource(R.drawable.tu_har_lamha);
    }

    @Override
    public int getItemCount() { return list.size(); }

    public interface ItemClickListener{
        void playBookmark(String name);
    }

    public class Holder extends RecyclerView.ViewHolder{

        public TextView videoName;
        public ImageView imageView;

        public Holder(@NonNull View itemView, final BookmarkedAdapter.ItemClickListener listener) {
            super(itemView);
            videoName = itemView.findViewById(R.id.bookmarked_video_name);
            imageView = itemView.findViewById(R.id.book_image);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.playBookmark(list.get(getAdapterPosition()).getBook_name());
                }
            });
        }

    }
}
