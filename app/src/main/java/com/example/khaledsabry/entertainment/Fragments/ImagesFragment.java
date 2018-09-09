package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.khaledsabry.entertainment.Adapters.ImagesAdapter;
import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class ImagesFragment extends Fragment {

    //images recyclerview
    RecyclerView recyclerView;
    //images adapter for recycler view
    ImagesAdapter imagesAdapter;
    //big poster
    ImageView poster;
    //images
    ArrayList<String> posters, backDrops;
    //choose quality of the images
    RadioButton fhd, hd, sd;
    //choose posters/backdrop
    Button postersButton, backDropsButton;
    //hide it when there is no images
    LinearLayout optionsLayout;
    //to use the toast method
    Controller controller = new Controller();
    public static ImagesFragment newInstance(ArrayList<String> posters, ArrayList<String> backDrops) {
        ImagesFragment fragment = new ImagesFragment();
        fragment.posters = posters;
        fragment.backDrops = backDrops;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_images, container, false);
        recyclerView = view.findViewById(R.id.contentPanel);
        poster = view.findViewById(R.id.posterzoom);
        //fullScreen = view.findViewById(R.id.fullscreen);
        fhd = view.findViewById(R.id.fhd);
        hd = view.findViewById(R.id.hd);
        sd = view.findViewById(R.id.sd);
        postersButton = view.findViewById(R.id.button_poster_id);
        backDropsButton = view.findViewById(R.id.button_back_drop_id);
        optionsLayout = view.findViewById(R.id.options_quality);

        controller.toast("press on the big poster\n to see it in full screen");

        setupRecyclerView();

        postersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPostersButtonClicked();
            }
        });

        backDropsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackDropsButtonClicked();
            }
        });


        postersButton.setPressed(true);
        setPostersButtonClicked();
        setOptionsQuality();
        return view;
    }

    void setupRecyclerView() {

        imagesAdapter = new ImagesAdapter(poster);
        imagesAdapter.setOptions(fhd, hd, sd);
        recyclerView.setAdapter(imagesAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    /**
     * called when the poster button is clicked
     */
    void setPostersButtonClicked() {
        imagesAdapter.setData(posters);
        if (posters == null || posters.size() == 0)
            optionsLayout.setVisibility(View.GONE);
        else
            optionsLayout.setVisibility(View.VISIBLE);


    }

    /**
     * called when the backDrop button is clicked
     */
    void setBackDropsButtonClicked() {
        imagesAdapter.setData(backDrops);

        if (backDrops == null || backDrops.size() == 0)
            optionsLayout.setVisibility(View.GONE);
        else
            optionsLayout.setVisibility(View.VISIBLE);
    }

    /**
     * to determine the quality you want to view
     */
    void setOptionsQuality() {

        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sd.toggle();
                ImageController.putImageLowQuality(imagesAdapter.currentImageUrl, poster);
            }
        });

        hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hd.toggle();
                ImageController.putImageMidQuality(imagesAdapter.currentImageUrl, poster);
            }
        });

        fhd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fhd.toggle();
                ImageController.putImageHighQuality(imagesAdapter.currentImageUrl, poster);
            }
        });

      /*  fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().loadFragmentWithReturn(R.id.mainContainer, FullPoster.newInstance(imagesAdapter.currentImageUrl));

            }
        });*/
    }




}
