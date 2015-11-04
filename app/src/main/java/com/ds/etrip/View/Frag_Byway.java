package com.ds.etrip.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ds.etrip.Adapter.Main_PagerAdapter;
import com.ds.etrip.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 7YHong on 2015/10/29.
 */
public class Frag_Byway extends Fragment {
    View v;
    List<Fragment> fragments;




    Button map_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_byway,container,false);
        map_btn= (Button) v.findViewById(R.id.map_btn);
        initFragments();
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Bw_mapView.class);
                intent.putExtra("num",2);
                startActivity(intent);
            }
        });

        final ViewPager pager= (ViewPager) v.findViewById(R.id.itemchangr);
        final TopBar topBar= (TopBar)v. findViewById(R.id.topbar);



        PagerAdapter pagerAdapter=new Main_PagerAdapter(getActivity().getSupportFragmentManager(),fragments);
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                topBar.setSelect(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        topBar.setListener(new TopBar.OnSelectChangedListener() {
            @Override
            public void onSelect(int index) {
                pager.setCurrentItem(index);
            }
        });

        return v;
    }
    void initFragments(){
        fragments=new ArrayList<>();
        fragments.add(new Item_chaoshi());
        fragments.add(new Item_shitang());
        fragments.add(new Item_kuaidi());
        fragments.add(new Item_daying());
    }
}
