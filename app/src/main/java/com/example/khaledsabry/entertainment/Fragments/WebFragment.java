package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

public class WebFragment extends Fragment {

    String url;
    WebView webView;
    ImageView imageView;
    static WebFragment fragment = null;

    public static WebFragment newInstance(String url) {
        fragment = new WebFragment();
        fragment.url = url;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);
imageView = view.findViewById(R.id.back);
        webView = view.findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MainActivity.getActivity().getSupportFragmentManager().popBackStack();
    }
});

        return view;
    }

    public static void finish() {
        if (fragment != null)
            fragment.onStop();

    }

}
