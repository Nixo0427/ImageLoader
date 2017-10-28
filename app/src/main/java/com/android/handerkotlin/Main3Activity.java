package com.android.handerkotlin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    ListView listView;
    private static  String URL1 = "http://www.imooc.com/api/teacher?type=4&num=30";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        new MyAsynTask().execute(URL1);




    }

    class MyAsynTask extends AsyncTask<String , Void ,List<Video>>{

        @Override
        protected List<Video> doInBackground(String... strings) {

            return getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Video> videos) {
            super.onPostExecute(videos);
            listView = findViewById(R.id.list_main3);
            VideoAdapter adapter = new VideoAdapter(Main3Activity.this,videos);
            listView.setAdapter(adapter);

        }
    }

    private String readStream (InputStream stream){
        InputStreamReader reader = null;
        String Result= "";
        String Line = "";
        try {
            reader = new InputStreamReader(stream,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader1 = new BufferedReader(reader);
        try {
            while ((Line = reader1.readLine())!=null){
                Result += Line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result;
    }



    private List<Video> getJsonData(String string) {
        List<Video>videos = new ArrayList<>();
        try {
            String data = readStream(new URL(URL1).openStream());
             JSONObject object = new JSONObject(data);
             JSONArray array = object.getJSONArray("data");
            for (int i = 0 ; i<array.length();i++){
                object = array.getJSONObject(i);
                Video video = new Video();
                video.Title = object.getString("name");
                videos.add(video);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return videos;
    }


}
