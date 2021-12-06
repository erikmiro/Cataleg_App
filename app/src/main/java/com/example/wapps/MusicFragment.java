package com.example.wapps;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import com.example.wapps.MusicRecyclerView.MusicRecyclerViewAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MusicFragment extends Fragment {

    public MusicFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        // Properties
        NestedScrollView nestedScrollView = view.findViewById(R.id.nestedScrollView);
        SearchView mainSearchView = view.findViewById(R.id.search_view);
        SearchView toolbarSearchView = view.findViewById(R.id.search_view_2);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                boolean isSearchViewVisible = isSearchViewVisible(nestedScrollView, mainSearchView);

                if (isSearchViewVisible){
                    toolbarSearchView.setVisibility(View.GONE);
                    mainSearchView.setVisibility(View.VISIBLE);
                } else{
                    toolbarSearchView.setVisibility(View.VISIBLE);
                    mainSearchView.setVisibility(View.GONE);
                }

            }
        });



        //initializing the RecyclerView for the list
        RecyclerView musicRecyclerView = view.findViewById(R.id.musicRecyclerView);
        MusicRecyclerViewAdapter adapter = new MusicRecyclerViewAdapter();
        musicRecyclerView.setAdapter(adapter);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager((getContext())) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            }
        );




        return view;
    }

    private boolean isViewVisible(NestedScrollView nestedScrollView, View view){
        Rect scrollBounds = new Rect();
        nestedScrollView.getDrawingRect(scrollBounds);

        float top = 0f;
        View view1 = view;

        while (!(view1 instanceof NestedScrollView)){
            top += (view1).getY();
            view1 = (View)view1.getParent();
        }

        float bottom = top + view.getHeight();
        return scrollBounds.top < bottom && scrollBounds.bottom >top;
    }

    private boolean isSearchViewVisible(NestedScrollView nestedScrollView, View view){
        Rect scrollBounds = new Rect();
        nestedScrollView.getDrawingRect(scrollBounds);

        float top = 0f;
        View view1 = view;

        while (!(view1 instanceof NestedScrollView)){
            top += (view1).getY();
            view1 = (View)view1.getParent();
        }

        float bottom = top + view.getHeight();
        return scrollBounds.top < bottom && scrollBounds.bottom >top;
    }
}