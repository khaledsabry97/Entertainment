package com.example.khaledsabry.entertainment.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import im.delight.android.webview.AdvancedWebView;

public class WebFragment extends Fragment implements AdvancedWebView.Listener {
    private String url;
    AdvancedWebView webView;
    ProgressBar progressBar;
    ImageView imageView;

    public enum Type {
        all,
        youtube,
        youtube_music
    }

    Type type;

    public static WebFragment newInstance(Type type) {
        WebFragment fragment = new WebFragment();
        fragment.type = type;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_youtube_web, container, false);
        webView = view.findViewById(R.id.web_view);
        progressBar = view.findViewById(R.id.progress_bar_id);
        imageView = view.findViewById(R.id.back);

        setupWepView();


        return view;
    }

    private void setupWepView() {
        webView.setListener(MainActivity.getActivity(), this);
        webView.setDesktopMode(true);
        webView.setWebChromeClient(new WebFragment.MyChrome());
        webView.setKeepScreenOn(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
        if (type == Type.youtube) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
            imageView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        } else if (type == Type.all) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MainActivity.getActivity().getSupportFragmentManager().popBackStack();
                }
            });
        }
    }


    public void setType(Type type) {
        this.type = type;
    }

    public void loadYoutubeVideoId(String videoId) {
        if (type == Type.youtube_music)
            webView.loadUrl("https://www.youtube-nocookie.com/embed/" + videoId + "?modestbranding=1&rel=0");
        else
            webView.loadUrl("https://www.youtube-nocookie.com/embed/" + videoId + "?autoplay=1&modestbranding=1&rel=0");

    }

    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        if (type == Type.youtube)
            return;
        webView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(String url) {
        if (type == Type.youtube)
            return;
        webView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

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
