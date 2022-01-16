package com.catrenat.wapps.Games.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.R;

import java.util.ArrayList;

public class PlatformLogoRecyclerViewAdapter extends RecyclerView.Adapter<PlatformLogoRecyclerViewAdapter.ViewHolder>{
    private ArrayList<String> platforms;

    public PlatformLogoRecyclerViewAdapter(ArrayList<String> platforms) {
        this.platforms = platforms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_platformlogo, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatformLogoRecyclerViewAdapter.ViewHolder holder, int position) {
        switch(platforms.get(position)) {
            case "Play Store":
                holder.platformLogo.setImageResource(R.drawable.googleplay_logo);
                break;
            case "Switch":
                holder.platformLogo.setImageResource(R.drawable.switch_logo);
                break;
            case "Epic Games":
                holder.platformLogo.setImageResource(R.drawable.epicgames_logo);
                break;
            case "Steam":
                holder.platformLogo.setImageResource(R.drawable.steam_logo);
                break;
            case "Xbox":
                holder.platformLogo.setImageResource(R.drawable.xbox_logo);
                break;
            case "Playstation":
                holder.platformLogo.setImageResource(R.drawable.playstation_logo);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return platforms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView platformLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            platformLogo = itemView.findViewById(R.id.platformLogoItemImage);
        }
    }
}
