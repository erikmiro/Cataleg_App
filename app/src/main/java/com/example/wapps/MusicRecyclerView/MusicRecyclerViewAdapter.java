package com.example.wapps.MusicRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.R;

public class MusicRecyclerViewAdapter extends RecyclerView.Adapter<MusicRecyclerViewAdapter.MusicViewHolder> {


    //constructor
    public MusicRecyclerViewAdapter(){

    }

    //Creating a new onCreateViewHolder
    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_fragment, parent, false);
        MusicViewHolder holder = new MusicViewHolder(view);
        return holder;
    }

    //Setting values to every field item by item
    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
       // holder.nameTxt.setText(arrayAnime.get(position).getName());

    }

    //counting the items in a anime list
    @Override
    public int getItemCount() {
        //return arrayAnime.size();
        return 8;
    }

    //Creating properties and finding them in the view
    public class MusicViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        TextView genreTxt;
        TextView rankingTxt;
        ImageView animeImage;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
