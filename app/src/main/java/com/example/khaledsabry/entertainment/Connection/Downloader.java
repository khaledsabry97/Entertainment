package com.example.khaledsabry.entertainment.Connection;

import android.os.Environment;
import android.util.SparseArray;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.example.khaledsabry.entertainment.Activities.MainActivity;

import java.io.File;
import java.io.IOException;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

/**
 * Created by KhALeD SaBrY on 12-Aug-18.
 */

public class Downloader {
    private static final Downloader ourInstance = new Downloader();

    public static Downloader getInstance() {
        return ourInstance;
    }

    private Downloader() {
        PRDownloader.initialize(MainActivity.getActivity().getApplicationContext());

    }


public void downloadYoutube(String link,String fileName)
{
// Setting timeout globally for the download network requests:
    PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .setReadTimeout(30_000)
            .setConnectTimeout(30_000)
            .build();
    PRDownloader.initialize(MainActivity.getActivity().getApplicationContext(), config);
    File mydir = new File(Environment.getExternalStorageDirectory(), "MovitaDownload");
    mydir.mkdirs();

    new YouTubeExtractor(MainActivity.getActivity().getApplicationContext()) {
        @Override
        public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
            if (ytFiles != null) {
                int itag = 22;
                String downloadUrl = ytFiles.get(itag).getUrl();
                downloadUrl.toLowerCase();
            }
        }
    }.extract(link, true, true);

    /*
fileName +=".mp4";


    int downloadId = PRDownloader.download(link, mydir.getPath(), fileName)
            .build()
            .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                @Override
                public void onStartOrResume() {

                }
            })
            .setOnPauseListener(new OnPauseListener() {
                @Override
                public void onPause() {

                }
            })
            .setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel() {

                }
            })
            .setOnProgressListener(new OnProgressListener() {
                @Override
                public void onProgress(Progress progress) {

                }
            })
            .start(new OnDownloadListener() {
                @Override
                public void onDownloadComplete() {

                }

                @Override
                public void onError(Error error) {

                }


            });*/
}
}
