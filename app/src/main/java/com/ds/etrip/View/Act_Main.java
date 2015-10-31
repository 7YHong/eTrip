package com.ds.etrip.View;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.baidu.mapapi.SDKInitializer;
import com.ds.etrip.Adapter.Main_PagerAdapter;
import com.ds.etrip.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 7YHong on 2015/10/29.
 */
public class Act_Main extends AppCompatActivity{

    List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.act_main);
        initFragments();

//        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);//ToolBar
//        toolbar.setTitle("eTrip");
//        setSupportActionBar(toolbar);

        final ViewPager pager= (ViewPager) findViewById(R.id.viewpager);
        final BottomBar bottomBar= (BottomBar) findViewById(R.id.bottombar);

        final PagerAdapter pagerAdapter=new Main_PagerAdapter(getSupportFragmentManager(),fragments);
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("tag8", position+" ");
                bottomBar.setSelect(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomBar.setListener(new BottomBar.OnSelectChangedListener() {
            @Override
            public void onSelect(int index) {
                pager.setCurrentItem(index);
            }
        });

    }

    void initFragments(){
        fragments=new ArrayList<>();
        fragments.add(new Frag_Vehicle());
        fragments.add(new Frag_Rent());
        fragments.add(new Frag_Byway());
        fragments.add(new Frag_Mine());
    }
}
