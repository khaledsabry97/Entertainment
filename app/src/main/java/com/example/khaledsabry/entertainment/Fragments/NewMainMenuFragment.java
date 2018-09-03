package com.example.khaledsabry.entertainment.Fragments;


import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.YoutubeOptionAdapter;
import com.example.khaledsabry.entertainment.R;


public class NewMainMenuFragment extends Fragment {

    RecyclerView newsRecycler;
    public static NewMainMenuFragment newInstance(String param1, String param2) {
        NewMainMenuFragment fragment = new NewMainMenuFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_new_main_menu, container, false);
/*
  LinearLayoutManager      linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
linearLayoutManager.setSmoothScrollbarEnabled(true);
newsRecycler.setLayoutManager(linearLayoutManager);

RecyclerView.SmoothScroller smoothScroller = new RecyclerView.SmoothScroller() {
    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onSeekTargetStep(int dx, int dy, RecyclerView.State state, Action action) {

    }

    @Override
    protected void onTargetFound(View targetView, RecyclerView.State state, Action action) {

    }
}
        moveNews();*/
        return view;
    }
/*
    private void moveNews() {
        final int speedScroll = 1200;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            boolean flag = true;
            @Override
            public void run() {
                if(count < adapter.getItemCount()){
                    if(count==adapter.getItemCount()-1){
                        flag = false;
                    }else if(count == 0){
                        flag = true;
                    }
                    if(flag) count++;
                    else count--;

                    recyclerView.getLayoutManager().smoothScrollToPosition(newsRecycler, new RecyclerView.State(),count);
                    handler.postDelayed(this,speedScroll);
                }
            }
        };

        handler.postDelayed(runnable,speedScroll);
    }
*/
}
