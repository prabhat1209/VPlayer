package com.example.vplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vplayer.VideoData.MyDbHelper;
import com.example.vplayer.disLikeDataPack.MyDbDisLike;
import com.example.vplayer.likeDataPack.MyDbLike;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Holder> {

    private List<Video> list;
    private LayoutInflater inflater;
    ItemClickListener itemClickListener;
    MyDbHelper db;
    MyDbLike myDbLike;
    MyDbDisLike myDbDisLike;

    public void setOnItemClickListener(ItemClickListener listener){
        itemClickListener = listener;
    }

    public VideoAdapter(List<Video> list, Context context, MyDbHelper db, MyDbLike myDbLike, MyDbDisLike myDbDisLike) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.db = db;
        this.myDbDisLike = myDbDisLike;
        this.myDbLike = myDbLike;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_layout,parent,false);
        Holder holder = new Holder(v,itemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.videoName.setText(list.get(position).getName());
        if(db.isExist(list.get(position).getName()))
            holder.bookmark.setChecked(true);
        if(myDbDisLike.isExist(list.get(position).getName()))
            holder.disLikeButton.setChecked(true);
        if(myDbLike.isExist(list.get(position).getName()))
            holder.likeButton.setChecked(true);
        if(list.get(position).getName().equals("Shayad"))
            holder.imageView.setImageResource(R.drawable.shayad);
        else
        if(list.get(position).getName().equals("Broken Angel"))
            holder.imageView.setImageResource(R.drawable.broken_angel);
        else
        if(list.get(position).getName().equals("Hawayein"))
            holder.imageView.setImageResource(R.drawable.hawayein);
        else
        if(list.get(position).getName().equals("Love me like you do"))
            holder.imageView.setImageResource(R.drawable.love_me_like_you_do);
        else
        if(list.get(position).getName().equals("Tu Har Lamha"))
            holder.imageView.setImageResource(R.drawable.tu_har_lamha);
    }

    @Override
    public int getItemCount() { return list.size(); }

    public interface ItemClickListener {
        void onItemClick(int pos);
        void onDisLikeClick(int pos,boolean state);
        void onLikeClick(int pos,boolean state);
        void onBookmarkClick(int pos,boolean state);
        void playVideo(String name);
    }

    public class Holder extends RecyclerView.ViewHolder{
        public TextView videoName;
        public ToggleButton likeButton;
        public ToggleButton disLikeButton;
        public ToggleButton bookmark;
        public ImageView imageView;

        public Holder(View itemView, final ItemClickListener listener) {
            super(itemView);
            videoName = itemView.findViewById(R.id.video_name);
            likeButton = itemView.findViewById(R.id.like_video);
            disLikeButton = itemView.findViewById(R.id.disLike_video);
            bookmark = itemView.findViewById(R.id.bookmark_on_video);
            imageView = itemView.findViewById(R.id.video_image);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = getAdapterPosition();
                    disLikeButton.setChecked(false);
                    listener.onDisLikeClick(p,false);
                    if (listener != null){
                        boolean b = likeButton.isChecked();
                        if (p != RecyclerView.NO_POSITION){
                            listener.onLikeClick(p,b);
                        }
                    }

                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.playVideo(list.get(getAdapterPosition()).getName());
                }
            });
            disLikeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = getAdapterPosition();
                    likeButton.setChecked(false);
                    listener.onLikeClick(p,false);
                    if (listener != null){
                        boolean b = disLikeButton.isChecked();
                        if (p != RecyclerView.NO_POSITION){
                            listener.onDisLikeClick(p,b);
                        }
                    }
                }});
            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int p = getAdapterPosition();
                        boolean b = bookmark.isChecked();
                        if(p != RecyclerView.NO_POSITION){
                            listener.onBookmarkClick(p,b);
                        }
                    }
                }
            });

        }
    }
}
