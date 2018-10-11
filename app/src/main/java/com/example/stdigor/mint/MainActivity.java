package com.example.stdigor.mint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

//https://www.sunhome.ru/i/wallpapers/238/manhetten-most-nyu-york.orig.jpg

public class MainActivity extends AppCompatActivity {

    URL url;
    ImageView img;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        try {
            url = new URL("https://thumbs.dreamstime.com/z/person-wearing-brown-sweater-grey-backpack-brown-hair-dreadlocks-top-mountain-overlooking-waterfalls-128067092.jpg");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        text = findViewById(R.id.textView);
        img = findViewById(R.id.imageView);
        img.setImageResource(0);

        new TaskExecuting().execute();
    }
    private class TaskExecuting extends AsyncTask<Void, Integer, Void> {
        InputStream stream;
        Bitmap image;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text.setText("Executing...");
        }
        protected Void doInBackground(Void... args) {
            boolean flag = true;
            while (flag) {
                try {
                    Thread.sleep(1000);
                    stream = (InputStream) url.getContent(); //error is here, infa sotka
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
                flag = false;
            }
            image = BitmapFactory.decodeStream(stream);
            return null;
        }
        protected void onPostExecute(Void im) {
            text.setText("Done");
            img.setImageBitmap(image);
        }
    }
}
