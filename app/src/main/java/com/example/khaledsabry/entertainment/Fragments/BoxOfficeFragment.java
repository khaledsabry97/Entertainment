package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.BoxOfficeAdapter;
import com.example.khaledsabry.entertainment.R;


public class BoxOfficeFragment extends Fragment {

    RecyclerView recyclerView;
    TextView header;
    BoxOfficeAdapter adapter;
    BoxOfficeAdapter dailyAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
LinearLayoutManager linearLayoutManager;
    FragmentPagerAdapter fragmentPagerAdapter;
    public static BoxOfficeFragment newInstance() {
        BoxOfficeFragment fragment = new BoxOfficeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_box_office, container, false);
        header = view.findViewById(R.id.header);
        // recyclerView = view.findViewById(R.id.recyclerid);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.view_pager_id);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        adapter = new BoxOfficeAdapter();
        dailyAdapter = new BoxOfficeAdapter();
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        fragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return RecyclerViewPager.newInstance(RecyclerViewPager.ViewPagerType.boxOffice);

                    case 1:
                        return RecyclerViewPager.newInstance(RecyclerViewPager.ViewPagerType.dailyBoxOffice);
                    default:
                        return BoxOfficeFragment.newInstance();
                }


            }

            @Override
            public int getCount() {
                return 2;
            }


        };

        viewPager.setAdapter(fragmentPagerAdapter);
        setTabLayout();
        setBoxOffice();

        return view;
    }


    private void setBoxOffice() {

        //set the header title to box office
        header.setText("Box Office");
        fragmentPagerAdapter.notifyDataSetChanged();

 }


    private void setDailyBoxOffice() {
        //set the header title to box office
        header.setText("Today's Box Office");
        fragmentPagerAdapter.notifyDataSetChanged();


    }



    private void setTabLayout() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setBoxOffice();
                        break;
                    case 1:
                        setDailyBoxOffice();
                        break;
                    default:
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
