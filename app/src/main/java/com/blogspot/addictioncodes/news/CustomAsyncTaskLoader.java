package com.blogspot.addictioncodes.news;
import android.content.Context;
import android.util.Log;
import android.support.v4.content.AsyncTaskLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomAsyncTaskLoader extends AsyncTaskLoader<ArrayList<NewsFormat>> {
    private String url;
    public CustomAsyncTaskLoader(Context context, String url){
        super(context);
        this.url=url;
    }
    @Override
    protected void onStartLoading(){
        forceLoad();
    }
    @Override
    public ArrayList<NewsFormat> loadInBackground()
    {
        return getList(getJsonResponseString(url));
    }
    private String getJsonResponseString(String urlJ){
        String json="";
        try{
            URL ur=new URL(urlJ);
            HttpURLConnection uc=(HttpURLConnection) ur.openConnection();
            uc.setRequestMethod("GET");
            uc.setReadTimeout(10000);
            uc.setConnectTimeout(150000);
            uc.connect();
            BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String k=br.readLine();
            json+=k;
            while ((k=br.readLine())!=null){
                json+=k;
            }
        }catch (MalformedURLException m){
            Log.e("getJsonResponseString","MalformedUrlException");
        }catch (IOException e){
            Log.e("getJsonResponseString","IOException");
        }
        return json;
    }
    private ArrayList<NewsFormat> getList(String operate){
        String author,title,description,url,urtToImage,publishedAt;
        ArrayList<NewsFormat> E=new ArrayList<NewsFormat>();
        try{
            JSONObject root=new JSONObject(operate);
            JSONArray articles=root.getJSONArray("articles");
            for(int i=0;i<articles.length();i++){
                JSONObject details=(JSONObject) articles.get(i);
                author=details.getString("author");
                title=details.getString("title");
                description=details.getString("description");
                url=details.getString("url");
                urtToImage=details.getString("urlToImage");
                publishedAt=details.getString("publishedAt");
                try{
                    publishedAt=publishedAt.substring(0,10)+", "+publishedAt.substring(11,16);
                }catch(Exception e){
                    Log.v("Error : ","Date");
                    publishedAt="N/A";
                }
                E.add(new NewsFormat(author,title,description,url,urtToImage,publishedAt));
            }
        }catch (JSONException j){
        }
        return E;
    }
}