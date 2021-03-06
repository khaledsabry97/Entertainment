package com.example.khaledsabry.entertainment.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.PreferencesController;
import com.example.khaledsabry.entertainment.Controllers.Toasts;
import com.example.khaledsabry.entertainment.Database.UserData;
import com.example.khaledsabry.entertainment.Fragments.MainMenu.CustomMainMenu;
import com.example.khaledsabry.entertainment.Fragments.MainMenu.MainMenuFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieViews.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.Search.SearchFragment;
import com.example.khaledsabry.entertainment.Fragments.TvViews.TvNavigationFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.R;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;


public class MainFragment extends Fragment {
    //this is the left side navigation in the app
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //for nav header
    ImageView poster;
    ImageView backDrop;
    TextView title;
    TextView email;
    //to upload images and get them
    PreferencesController preferencesController;

    //for intent result for
    private static final int REQUEST_CODE_BACKDROP = 1000;
    private static final int REQUEST_CODE_POSTER = 2000;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        poster = header.findViewById(R.id.poster);
        backDrop = header.findViewById(R.id.backdrop);
        title = header.findViewById(R.id.title);
        email = header.findViewById(R.id.email);

        preferencesController = new PreferencesController();

        setObjects();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                Functions.stopConnectionsAndStartImageGlide();
                switch (id) {
                    case R.id.home:
                        MainActivity.getActivity().loadFragmentWithReturn(R.id.mainContainer, MainMenuFragment.newInstance());
                        break;
                    case R.id.searchid:
                        MainActivity.getActivity().loadFragmentNoReturn(R.id.mainContainer, SearchFragment.newInstance());
                        break;

                    case R.id.boxofficeid:
                        MainActivity.getActivity().loadFragmentNoReturn(R.id.mainContainer, BoxOfficeFragment.newInstance());
                        break;

                    case R.id.category:
                        MainActivity.loadFragmentWithReturn(R.id.mainContainer, CategoryListFragment.newInstance());
                        break;
                    case R.id.news:
                        MainActivity.getActivity().loadFragmentNoReturn(R.id.mainContainer, NewsFragment.newInstance());
                        break;
                    case R.id.topGenres:
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, TopGenresFragment.newInstance());
                        break;
                    default:
                        break;
                }


                navigationView.setCheckedItem(id);
                Functions.closeDrawerLayout(drawerLayout);
                return true;

            }
        });


         MainActivity.loadFragmentNoReturn(R.id.mainContainer, MainMenuFragment.newInstance());
      //  MainActivity.loadFragmentNoReturn(R.id.mainContainer, TvNavigationFragment.newInstance(62127, 1));

        return view;
    }

    //set the nav header object
    private void setObjects() {
        //set the profile Image if found
        preferencesController.setProfileImage(poster);
        //set the backdrop image if found
        preferencesController.setBackDropImage(backDrop);

        //upload an image from the gallery when i click on the poster imageview
        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFromGallery(REQUEST_CODE_POSTER);

            }
        });

        //same as the posterview
        backDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFromGallery(REQUEST_CODE_BACKDROP);
            }
        });

        //set the title text with the username
        title.setText(UserData.getInstance().getUsername());
        //set email
        email.setText(UserData.getInstance().getEmail());

    }

    //get an image from the gallery
    private void getFromGallery(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        startActivityForResult(intent, requestCode);

    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, final Intent data) {

        if (requestCode == REQUEST_CODE_POSTER && resultCode == Activity.RESULT_OK) {
            Glide.with(getContext()).load(data.getData()).apply(RequestOptions.circleCropTransform()).into(poster);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    final Bitmap bitmap;
                    try {
                        bitmap = Glide.with(getContext()).asBitmap().load(data.getData()).submit().get();

                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sendImage(bitmap, "Profile Image");

                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                }
            });


        } else if (requestCode == REQUEST_CODE_BACKDROP && resultCode == Activity.RESULT_OK) {
            Glide.with(getContext()).load(data.getData()).into(backDrop);

            Glide.with(getContext()).asBitmap().load(data.getData()).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    sendImage(resource, "BackDrop Image");
                }
            });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //upload image to the server
    private void sendImage(Bitmap bitmap, String type) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        if (type.equals("Profile Image")) {
            preferencesController.uploadProfileImage(encoded, new OnSuccess.bool() {
                @Override
                public void onSuccess(boolean state) {

                    if (state)
                        Toasts.success("Profile Image successfully added!");
                    else
                        Toasts.error("Failed to add the profile image");
                }
            });
        } else {
            preferencesController.uploadBackDropImage(encoded, new OnSuccess.bool() {
                @Override
                public void onSuccess(boolean state) {

                    if (state)
                        Toasts.success("BackDrop Image successfully added!");
                    else
                        Toasts.error("Failed to add the backdrop image");

                }
            });
        }


    }


}
