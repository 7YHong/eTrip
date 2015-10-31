package com.ds.etrip.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ds.etrip.Adapter.Vehi_List_Adapter;
import com.ds.etrip.Adapter.Vehi_VP_Adapter;
import com.ds.etrip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 7YHong on 2015/10/29.
 */
public class Frag_Vehicle extends Fragment{
    View v;
    ViewPager titlevp;
    ListView busList;
    LinearLayout dots;
    List<ImageView> titlepics;
    List<String> buses; ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_vehicle,container,false);

        initPics();

        titlevp= (ViewPager) v.findViewById(R.id.vehi_titlevp);
        dots= (LinearLayout) v.findViewById(R.id.vehi_vp_dots);
        busList= (ListView) v.findViewById(R.id.vehi_list);

        PagerAdapter vpAdapter=new Vehi_VP_Adapter(getActivity(),titlepics);
        titlevp.setAdapter(vpAdapter);

        ListAdapter busAdapter=new Vehi_List_Adapter(getActivity(),buses);
        busList.setAdapter(busAdapter);

        setTitlepics(titlepics);

        return v;
    }

    void initPics(){
        titlepics =new ArrayList<>();
        for (int i=0;i<3;i++){
            ImageView iv=new ImageView(getActivity());
            iv.setImageResource(R.drawable.vehi_titlepic1);
            iv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setAdjustViewBounds(true);
            titlepics.add(iv);
        }

        buses=new ArrayList<>();    //添加线路列表
        for (int i=0;i<10;i++){
            buses.add("bus1"+String.valueOf(i));
        }
    }
    void setTitlepics(List<ImageView> titlepics) {
        dots.removeAllViews();
        for (int i=0;i<titlepics.size();i++){
            ImageView iv=new ImageView(getActivity());
            iv.setImageResource(R.drawable.vehi_titlevp_dots);
            dots.addView(iv);
        }
        dots.getChildAt(0).setSelected(true);
    }
}
