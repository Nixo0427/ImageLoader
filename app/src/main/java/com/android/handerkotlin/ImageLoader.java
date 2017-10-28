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

public class ImageLoader {

    public void showImageByThread(ImageView imageView , String url){

        new Thread(){

            @Override
            public void run() {
                super.run();
            }
        }.start();


    }

    public Bitmap getBitMap(String urlString){
        Bitmap bitmap;
        InputStream reader = null;
        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //获取资源
            reader = new BufferedInputStream(connection.getInputStream()); //资源缓存流
            bitmap = BitmapFactory.decodeStream(reader); //读取流
            connection.disconnect(); //资源释放
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();//释放读取流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

}
