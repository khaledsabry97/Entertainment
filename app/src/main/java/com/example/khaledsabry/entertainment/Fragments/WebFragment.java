package com.example.khaledsabry.entertainment.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import java.util.Timer;
import java.util.TimerTask;

import im.delight.android.webview.AdvancedWebView;

public class WebFragment extends Fragment implements AdvancedWebView.Listener{

    String url;
    AdvancedWebView webView;
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
        webView = view.findViewById(R.id.web_view);
webView.setInitialScale(1);

        webView.setListener(MainActivity.getActivity(),this);
      //  webView.setWebViewClient(new WebViewClient());
        webView.setDesktopMode(true);
        webView.setWebChromeClient(new MyChrome());
        webView.setKeepScreenOn(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
     //   webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        // webView.getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        //webView.loadUrl("https://www.youtube-nocookie.com/embed/BnwB8Oh628Q?autoplay=1&rel=0");
        //webView.loadUrl(url);
        webView.loadUrl("https://www.youtube-nocookie.com/embed/BnwB8Oh628Q?autoplay=1&modestbranding=1&rel=0");


        imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        webView.loadUrl("https://www.youtube-nocookie.com/embed/raYkwmxuXEc?autoplay=1&modestbranding=1&rel=0");

        MainActivity.getActivity().getSupportFragmentManager().popBackStack();
    }
});
        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }




    public static void finish() {
        if (fragment != null)
            fragment.onStop();

    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
      //  webView.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onPageFinished(String url) {

new Timer().schedule(new TimerTask() {
    @Override
    public void run() {
        MainActivity.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.setVisibility(View.VISIBLE);
              //  webView.setClickable(true);
                webView.performClick();



            }
        });
    }
},700);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {
url.toCharArray();
    }


    private class MyChrome extends WebChromeClient {

        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {
        }

        @Override
        public Bitmap getDefaultVideoPoster() {

            if (mCustomView == null) {
                return BitmapFactory.decodeResource(MainActivity.getActivity().getApplicationContext().getResources(), R.drawable.black);

       //         return null;
            }
           return BitmapFactory.decodeResource(MainActivity.getActivity().getApplicationContext().getResources(), R.drawable.avengers_infinity_war_back_drop);

        }

        public void onHideCustomView() {
            ((FrameLayout) MainActivity.getActivity().getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            MainActivity.getActivity().getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            MainActivity.getActivity().setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
            if (this.mCustomView != null) {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = MainActivity.getActivity().getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = MainActivity.getActivity().getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout) MainActivity.getActivity().getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            MainActivity.getActivity().getWindow().getDecorView().setSystemUiVisibility(3846);
        }
    }
}
