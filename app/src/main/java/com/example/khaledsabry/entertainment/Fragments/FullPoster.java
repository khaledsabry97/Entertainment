package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.Toasts;
import com.example.khaledsabry.entertainment.R;

public class FullPoster extends Fragment {
    //poster link
    static String poster;
    //poster view
    ImageView fullPoster;
    //counter for clicks to detect the double click
    int i = 0;
    //to use the toast method
    Controller controller = new Controller();

    public static FullPoster newInstance(String poster) {
        FullPoster fragment = new FullPoster();
        FullPoster.poster = poster;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_full_poster, container, false);
        fullPoster = v.findViewById(R.id.fullposterid);
        ImageController.putImageHighQuality(poster, fullPoster);

        Toasts.info("press double click to exit");
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeTheFragment();

            }
        });


        return v;
    }

    /**
     * increment i every time you click and every  400 ms i = 0
     */
    void closeTheFragment() {
        i++;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i > 1)
                    getActivity().getSupportFragmentManager().popBackStack();
                i = 0;

            }
        }, 400);
    }

}
