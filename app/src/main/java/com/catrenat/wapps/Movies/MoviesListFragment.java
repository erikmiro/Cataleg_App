package com.catrenat.wapps.Movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catrenat.wapps.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MoviesListFragment extends Fragment {

    // Properties
    TabLayout tabLayout;
    ViewPager viewPager;
    MainAdapter adapter;
    private String selectedPlatform;

    public MoviesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        // Bundle
        Bundle bundle = getArguments();
        selectedPlatform = (String) bundle.getSerializable("moviePlatform");

        // Creating the fragments to be able to pass bundle to each
        SeriesFragment seriesFragment = new SeriesFragment();
        seriesFragment.setArguments(bundle);
        PelisFragment pelisFragment = new PelisFragment();
        pelisFragment.setArguments(bundle);
        DocusFragment docusFragment = new DocusFragment();
        docusFragment.setArguments(bundle);

        // Elements of the view
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        adapter = new MainAdapter(getChildFragmentManager());

        // Add fragments to the adapter
        adapter.AddFragment(seriesFragment, getString(R.string.seriesFragmentName));
        adapter.AddFragment(pelisFragment, getString(R.string.moviesFragmentName));
        adapter.AddFragment(docusFragment, getString(R.string.docsFragmentName));

        // Set Adapter
        viewPager.setAdapter(adapter);

        // Connect tab layout with view pager
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private class MainAdapter extends FragmentPagerAdapter {

        // Properties
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        public void AddFragment (Fragment fragment, String s) {
            // Add fragment
            fragmentArrayList.add(fragment);
            // Add string
            stringArrayList.add(s);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // Return fragment position
            return  fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            // Return fragment list size
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            // Return tab title
            return stringArrayList.get(position);
        }
    }
}