package com.blogspot.addictioncodes.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<NewsFormat> {
    public ListAdapter(Context context, ArrayList<NewsFormat> pwords){
        super(context,0,pwords);
    }
    @Override
    public View getView(int pos,View v,ViewGroup vg){
        View view=v;
        NewsFormat nf=(NewsFormat) getItem(pos);
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.list_adapter_layout,vg,false);
        }
        TextView title=(TextView) view.findViewById(R.id.title);
        title.setText(nf.getTitle());

        TextView date=(TextView) view.findViewById(R.id.date);
        date.setText(nf.getPublishedAt());
        TextView numbers=(TextView)view.findViewById(R.id.numbers);
        numbers.setText(""+pos);
        Log.e("","ArrayAdpter");
        return view;
    }
}
