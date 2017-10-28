package com.android.handerkotlin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nixo on 2017/10/27.
 */

public class ImageLoader1 {

    public void  getImageUrl(ImageView imageView , String url){

        new Thread(){
            @Override
            public void run() {
                super.run();

            }
        }.start();

    }

  public Bitmap getImage(String urlString){
      InputStream reader = null;
        Bitmap bitmap ;
      URL url = null;
      try {
          url = new URL(urlString);
      } catch (MalformedURLException e) {
          e.printStackTrace();
      }
      try {
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();

          reader = new BufferedInputStream(connection.getInputStream());
          bitmap = BitmapFactory.decodeStream(reader);
          connection.disconnect();
          return bitmap;

      } catch (IOException e) {
          e.printStackTrace();
      }finally {
          try {
              reader.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
        return null;
  }


}
