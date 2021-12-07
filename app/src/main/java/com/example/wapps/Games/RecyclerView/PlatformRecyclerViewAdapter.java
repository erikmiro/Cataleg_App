package com.example.wapps.Games.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wapps.R;

import java.util.ArrayList;

public class PlatformRecyclerViewAdapter extends RecyclerView.Adapter<PlatformRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> platforms;

    public PlatformRecyclerViewAdapter(ArrayList<String> array){
        platforms = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_platform, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.platformName.setText(platforms.get(position));
    }

    @Override
    public int getItemCount() {
        return platforms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView platformName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            platformName = itemView.findViewById(R.id.platformName);
        }
    }
}
