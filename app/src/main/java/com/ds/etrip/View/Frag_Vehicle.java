package com.ds.etrip.View;

import android.content.Intent;
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

import com.ds.etrip.Adapter.Custom_List_Adapter;
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
    ImageView taxi,bus;
    LinearLayout dots;
    List<ImageView> titlepics;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_vehicle,container,false);

        initPics();

        titlevp= (ViewPager) v.findViewById(R.id.vehi_titlevp);
        dots= (LinearLayout) v.findViewById(R.id.vehi_vp_dots);
        taxi= (ImageView) v.findViewById(R.id.vehi_taxi);
        bus= (ImageView) v.findViewById(R.id.vehi_bus);

        PagerAdapter vpAdapter=new Vehi_VP_Adapter(getActivity(),titlepics);
        titlevp.setAdapter(vpAdapter);


        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type=0;
                switch (v.getId()){
                    case R.id.vehi_taxi:type=0;break;
                    case R.id.vehi_bus:type=1;break;
                }
                Intent intent=new Intent(getActivity(),Act_VehiList.class);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        };
        taxi.setOnClickListener(onClickListener);
        bus.setOnClickListener(onClickListener);

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
