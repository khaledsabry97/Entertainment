package com.example.khaledsabry.entertainment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Connection.TmdbConnection;
import com.example.khaledsabry.entertainment.Connection.TmdbType;
import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Items.Movie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

import static java.nio.file.Files.copy;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    private static final int IO_BUFFER_SIZE = 4 * 1024;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TmdbConnection.getInstance().setContext(this);
        TmdbType tmdbType = new TmdbType();
        tmdbType.getMovieGetDetails(351286);
        Controller.getInstance().setMainActivity(this);
        imageView = findViewById(R.id.imageView);
    }



    public void show(final Movie movie)
    {
        Toast.makeText(this,movie.getTitle(),Toast.LENGTH_LONG).show();
AsyncTask.execute(new Runnable() {
    @Override
    public void run() {

        String urlOfImage = movie.getPostorImage();
        Bitmap logo = null;
        try{
            InputStream is = new URL(urlOfImage).openStream();
            logo = BitmapFactory.decodeStream(is);
        }catch(Exception e){
            // Catch the download exception
            e.printStackTrace();
        }

        final Bitmap finalLogo = logo;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(finalLogo);
            }
        });

    }
});
    }

    public static Bitmap loadBitmap(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new URL(url).openStream(), IO_BUFFER_SIZE);

            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
            copy((Path) in, out);
            out.flush();

            final byte[] data = dataStream.toByteArray();
            BitmapFactory.Options options = new BitmapFactory.Options();
            //options.inSampleSize = 1;

            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,options);
        } catch (IOException e) {
            Log.e("sdf", "Could not load Bitmap from: " + url);
        }

        return bitmap;
    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
