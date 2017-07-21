package com.blogspot.addictioncodes.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewAdapter viewAdapter=new ViewAdapter(getSupportFragmentManager());

        ViewPager viewPager=(ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(viewAdapter);

        TabLayout tabLayout=(TabLayout) findViewById(R.id.simple_tap);
        tabLayout.setupWithViewPager(viewPager);
    }
}
