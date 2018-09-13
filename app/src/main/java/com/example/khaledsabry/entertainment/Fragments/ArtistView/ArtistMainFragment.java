package com.example.khaledsabry.entertainment.Fragments.ArtistView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapters.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Controllers.Toasts;
import com.example.khaledsabry.entertainment.Fragments.CategoryAddFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ArtistMainFragment extends Fragment {

    ViewPager viewPager;
    MainPosterViewPager mainPosterViewPager;
    CircleIndicator indicator;
     Artist artist;
    ImageView addToCategory, addFavourite, addWatchLater;
    DrawerLayout drawerLayout;
    CategoryController categoryController = new CategoryController();
    public  ArrayList<String> categoryNames;
    public  ArrayList<Integer> categoryIds;
    public  ArrayList<Boolean> categoryChecked;
    public static ArtistMainFragment newInstance(Artist artist) {
        ArtistMainFragment fragment = new ArtistMainFragment();
        fragment.artist = artist;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist_main, container, false);


        viewPager = view.findViewById(R.id.view_pager_id);
        indicator = view.findViewById(R.id.indicator);
        addToCategory = view.findViewById(R.id.add_to_category_id);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        addWatchLater = view.findViewById(R.id.add_to_watch_later_id);
        addFavourite = view.findViewById(R.id.add_to_favourite_id);
        categoryController = new CategoryController();
setObjects();
        return view;
    }

    private void setObjects() {

        drawerLayout.setOnDragListener(
                new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        Functions.closeDrawerLayout(drawerLayout);
                        return false;
                    }
                }
        );
        setImages();
setupOverViewFragment();
        setUpCategories();


    }

    private void setupOverViewFragment() {
        loadFragment(ArtistOverviewFragment.newInstance(artist));
    }

    private void loadFragment(Fragment fragment) {
        MainActivity.loadFragmentNoReturn(R.id.half_frame_layout,fragment);
    }
    private void setUpCategories() {
        loadCategories();
        addToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddToCategory();
            }
        });

        addFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddFavourite();
            }
        });


        addWatchLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddWatchLater();
            }
        });


    }

    private void loadCategories() {
        categoryController.getCategories(artist.getId(), 3, new OnSuccess.objects() {
            @Override
            public void onSuccess(ArrayList<Object> objects) {
                categoryIds = (ArrayList<Integer>) objects.get(0);
                categoryNames = (ArrayList<String>) objects.get(1);
                categoryChecked = (ArrayList<Boolean>) objects.get(2);
            }
        });
    }

    void setAddToCategory() {
        if (categoryIds != null)
            openCategoryAdd(categoryNames, categoryIds, categoryChecked);
        else
            loadCategories();
    }

    public void openCategoryAdd(ArrayList<String> names, ArrayList<Integer> ids, ArrayList<Boolean> booleans) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, CategoryAddFragment.newInstance(names, ids, booleans, 3, String.valueOf(artist.getId()))).commit();
    }


    void setAddFavourite() {
        categoryController.addFavourite(String.valueOf(artist.getId()), artist.getMovieImdbId(), categoryController.constants.artist, new OnSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                if (state)
                {
                    loadCategories();
                    Toasts.success(artist.getName() + " has been added to your favourites");

                }
                else
                    Toasts.error(artist.getName() + " failed to be added to your favourites");

            }
        });
    }

    void setAddWatchLater() {

    }

    private void setImages() {
        mainPosterViewPager = new MainPosterViewPager(artist.getImages());
        viewPager.setAdapter(mainPosterViewPager);
        indicator.setViewPager(viewPager);
        Functions functions = new Functions();

        mainPosterViewPager.movePoster(viewPager,mainPosterViewPager,3000,4000);

    }
/*
    private void loadFragment() {
        if(id != currentId)
        {
            tmdbController.getPersonDetails(id, new OnArtistDataSuccess() {
                @Override
                public void onSuccess(Artist artist) {
                    ArtistMainFragment.artist = artist;
                    currentId = id;
                    setObjects();
                }
            });
        }
        else
            setObjects();

    }
*/

}
