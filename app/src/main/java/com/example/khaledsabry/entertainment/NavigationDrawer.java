package com.example.khaledsabry.entertainment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class NavigationDrawer extends Fragment {

    NavigationDrawer navigationDrawer;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static NavigationDrawer newInstance() {
        NavigationDrawer fragment = new NavigationDrawer();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
drawerLayout = v.findViewById(R.id.drawer_layout);
       navigationView = v.findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();

               if(id== R.id.a)
                   Toast.makeText(getContext(),"a",Toast.LENGTH_LONG).show();
               else if(id== R.id.b)
                   Toast.makeText(getContext(),"b",Toast.LENGTH_LONG).show();

               drawerLayout.closeDrawer(GravityCompat.START,true);
               return true;

           }
       });
        return v;

    }

}
