package com.blogspot.addictioncodes.news;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
public class Entertainment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<NewsFormat>>
{
    private ProgressBar p;
    private TextView t;
    private ListView elv;
    private ListAdapter ela;
    private View view;
    private String url="https://newsapi.org/v1/articles?&source=buzzfeed&sortBy=latest&apiKey=db72ab107b744827a1aa56e18b9d6177";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_entertainment, container, false);
        p=(ProgressBar)view.findViewById(R.id.progress_e);
        t=(TextView)view.findViewById(R.id.text_e);
        ConnectivityManager cm=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if(ni!=null&&ni.isConnectedOrConnecting()){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(2,null,this);
        }else{
            p.setVisibility(View.GONE);
            t.setText("No Active Internet Connection !");
        }
        return view ;
    }
    @Override
    public Loader<ArrayList<NewsFormat>> onCreateLoader(int id, Bundle args) {
        return new CustomAsyncTaskLoader(getContext(),url);
    }
    @Override
    public void onLoadFinished(Loader loader,final ArrayList data){
        t.setVisibility(View.GONE);
        p.setVisibility(View.GONE);
        elv=(ListView) view.findViewById(R.id.list_e);
        ela=new ListAdapter(getActivity(),data);
        elv.setAdapter(ela);
        elv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsFormat nf=(NewsFormat) data.get(position);
                Intent i=new Intent(getContext(),ReadFull.class);
                i.putExtra("title",nf.getTitle());
                i.putExtra("author",nf.getAuthor());
                i.putExtra("description",nf.getDescription());
                i.putExtra("date",nf.getPublishedAt());
                i.putExtra("url",nf.getUrl());
                i.putExtra("image",nf.getUrlToImage());
                startActivity(i);
            }
        });
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<NewsFormat>> loader){ }
}