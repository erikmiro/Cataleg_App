package com.catrenat.wapps.Movies.RecyclerView.Documentals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.Models.Documental;
import com.catrenat.wapps.Models.DocusCategories;
import com.catrenat.wapps.Movies.RecyclerView.Series.SeriesRecyclerViewAdapter;
import com.catrenat.wapps.R;

import java.util.List;

public class AllDocusRecyclerViewAdapter extends RecyclerView.Adapter<AllDocusRecyclerViewAdapter.AllDocusViewHolder> {
    private List<DocusCategories> documentalsCategories;
    private Context context;

    public AllDocusRecyclerViewAdapter(List<DocusCategories> documentalsCategories, Context context) {
        this.context = context;
        this.documentalsCategories = documentalsCategories;
    }

    @NonNull
    @Override
    public AllDocusRecyclerViewAdapter.AllDocusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_all_categories, parent, false);
        AllDocusRecyclerViewAdapter.AllDocusViewHolder holder = new AllDocusRecyclerViewAdapter.AllDocusViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllDocusRecyclerViewAdapter.AllDocusViewHolder holder, int position) {
        // Set the movie category title
        holder.categoryTitle.setText(documentalsCategories.get(position).getTitle());
        setDocusRecycler(holder.docusRecyclerView, documentalsCategories.get(position).getDocumentals());
    }

    @Override
    public int getItemCount() {
        return documentalsCategories.size();
    }

    public class AllDocusViewHolder extends RecyclerView.ViewHolder{
        // View items
        TextView categoryTitle;
        RecyclerView docusRecyclerView;

        public AllDocusViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.movieCategoryTitle);
            docusRecyclerView = itemView.findViewById(R.id.seriesRecyclerView);
        }
    }

    // Set the Documental recycler view
    private void setDocusRecycler(RecyclerView docusRecyclerView, List<Documental> documentals) {
        DocusRecyclerViewAdapter docusRecyclerViewAdapter = new DocusRecyclerViewAdapter(documentals, context);
        docusRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        docusRecyclerView.setAdapter(docusRecyclerViewAdapter);
    }
}
