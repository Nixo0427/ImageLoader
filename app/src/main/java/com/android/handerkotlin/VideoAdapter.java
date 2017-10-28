package com.android.handerkotlin;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nixo on 2017/10/26.
 */

public class VideoAdapter extends BaseAdapter {


    private List<Video> videoList;
    LayoutInflater inflater;


    public VideoAdapter(Context context, List<Video> videoList) {
        this.videoList = videoList;
        this.inflater = inflater.from(context);


    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list2main_item,parent,false);
            viewHolder.image = convertView.findViewById(R.id.image11);
            viewHolder.NeiRong = convertView.findViewById(R.id.NeiRong);
            viewHolder.Title = convertView.findViewById(R.id.Title);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

            viewHolder.Title.setText(videoList.get(position).Title);
            viewHolder.NeiRong.setText(videoList.get(position).Neirong);
            viewHolder.image.setImageResource(R.mipmap.ic_launcher);
            new ImageLoader().showImageByThread(viewHolder.image,videoList.get(position).Image);
            new ImageLoader1().getImageUrl(viewHolder.image,videoList.get(position).Image);
        return convertView;
    }

    class ViewHolder{
        public TextView Title,NeiRong;
        public ImageView image;
    }




}
