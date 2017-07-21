package com.blogspot.addictioncodes.news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class ReadFull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_full);
        Intent my=getIntent();
        TextView author=(TextView)findViewById(R.id.author);
        TextView date=(TextView)findViewById(R.id.date_time);
        TextView description=(TextView)findViewById(R.id.description);
        author.setText(my.getStringExtra("author"));
        date.setText(my.getStringExtra("date"));
        description.setText(my.getStringExtra("description"));
        ImageView image=(ImageView)findViewById(R.id.image);
        DownloadImageTask dm=new DownloadImageTask(image);
        dm.execute(my.getStringExtra("image"));
        setTitle(my.getStringExtra("title"));
        final String url=my.getStringExtra("url");
        Button more=(Button)findViewById(R.id.button);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
