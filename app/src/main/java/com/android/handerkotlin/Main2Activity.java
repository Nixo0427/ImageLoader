package com.android.handerkotlin;

import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

   private ListView listView;
   private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.list_main2);
        new myAsynTask().execute(URL);
    }



    private String readStream(InputStream is){
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is,"utf-8");
            BufferedReader reader = new BufferedReader(isr);
            while ((line=reader.readLine())!=null){
                result += line;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    class myAsynTask extends AsyncTask<String,Void,List<Video>>{

        @Override
        protected List<Video> doInBackground(String...params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<Video> videos) {
            super.onPostExecute(videos);
            VideoAdapter adapter = new VideoAdapter(Main2Activity.this,videos);
            listView.setAdapter(adapter);

        }
    }

    private List<Video> getJsonData(String param) {
        List<Video> viewList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(URL).openStream());
            Log.d("Test", jsonString);
            JSONObject jsonObject;
            Video video;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0 ; i<jsonArray.length();i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    video = new Video();
                    video.Image  = jsonObject.getString("picSmall");
                    video.Title  = jsonObject.getString("name");
                    video.Neirong= jsonObject.getString("description");
                    viewList.add(video);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return viewList;
    }


}
