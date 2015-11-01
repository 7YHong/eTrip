package com.ds.etrip.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ds.etrip.Adapter.VehiList_VP_Adapter;
import com.ds.etrip.R;

/**
 * Created by 7YHong on 2015/11/1.
 */
public class Act_VehiList extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vehilist);
        Intent intent=getIntent();
        int type=intent.getIntExtra("type",0);
        tabLayout= (TabLayout) findViewById(R.id.vehi_list_tablayout);
        viewPager= (ViewPager) findViewById(R.id.vehi_list_viewpager);
        viewPager.setAdapter(new VehiList_VP_Adapter(getSupportFragmentManager(), getApplicationContext()));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(type);
    }
}
