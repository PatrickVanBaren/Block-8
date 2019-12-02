package com.example.assets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button copyButton;
    Button showButton;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyButton = findViewById(R.id.copy_button);
        showButton = findViewById(R.id.show_button);
        image = findViewById(R.id.image_view);

        View.OnClickListener copyClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CopyTask().execute();
            }
        };

        View.OnClickListener showClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadFromPrivateStorageTask().execute();
            }
        };

        copyButton.setOnClickListener(copyClick);
        showButton.setOnClickListener(showClick);
    }

    private class CopyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                final OutputStream outputStream = new BufferedOutputStream(openFileOutput(
                        "image.jpg", Context.MODE_PRIVATE));
                final InputStream inputStream = new BufferedInputStream(getAssets()
                        .open("image.jpg"));
                final byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this, "Copy success", Toast.LENGTH_SHORT);
        }
    }

    private class LoadFromPrivateStorageTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                final File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                final File file = new File(dir, "image.jpg");
                final InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                return BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
}
