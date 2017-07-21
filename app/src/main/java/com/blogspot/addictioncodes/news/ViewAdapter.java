package com.blogspot.addictioncodes.news;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
public class ViewAdapter extends FragmentPagerAdapter {
    private String tile[]={"General","Politics","Sport","Entertainment","Technology"};
    public ViewAdapter(FragmentManager fm){super(fm);}
    @Override
    public Fragment getItem(int p){
        Fragment general=new General();
        Fragment entertainment=new Entertainment();
        Fragment politics=new Politics();
        Fragment technology=new Technology();
        Fragment sport=new Sport();
        Fragment fr;
        switch(p){
            case 0:
                fr= general;
                break;
            case 1:
                fr= politics;
                break;
            case 2:
                fr= sport;
                break;
            case 3:
                fr= entertainment;
                break;
            default:
                fr= technology;
        }
        Log.v("","V");
        return fr;
    }
    @Override
    public int getCount(){return 5;}
    @Override
    public CharSequence getPageTitle(int p){
        return tile[p];
    }
}