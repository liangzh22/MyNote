package com.example.administrator.mynote.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.mynote.Model.Daily;
import com.example.administrator.mynote.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class DailyAdapter extends ArrayAdapter<Daily> {
    private int resourceId;

    public DailyAdapter(Context context, int resource, List<Daily> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Daily daily=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView date=(TextView)view.findViewById(R.id.daily_item_date);
        TextView weather=(TextView)view.findViewById(R.id.daily_item_weather);
        date.setText(daily.getDate());
        weather.setText(daily.getWeather());
        return view;
    }
}
