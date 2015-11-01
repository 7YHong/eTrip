package com.ds.etrip.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ds.etrip.View.Frag_VehiList_Simple;

import java.util.List;

/**
 * Created by 7YHong on 2015/11/1.
 */
public class VehiList_VP_Adapter extends FragmentPagerAdapter{
    private static final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"e-Dirt","e-Bus"};
    private Context context;
//    private List<Fragment> fragments;

    public VehiList_VP_Adapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
//
//        Frag_VehiList_Simple bus=new Frag_VehiList_Simple();
//        Bundle arg0=new Bundle();
//        arg0.putInt("type", 0);
//        bus.setArguments(arg0);
//        fragments.add(bus);
//
//        Frag_VehiList_Simple taxi=new Frag_VehiList_Simple();
//        Bundle arg1=new Bundle();
//        arg1.putInt("type", 1);
//        taxi.setArguments(arg1);
//        fragments.add(taxi);
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("position"+String.valueOf(position));
        return Frag_VehiList_Simple.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
