package com.ds.etrip.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ds.etrip.Adapter.Vehi_VP_Adapter;
import com.ds.etrip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hymei on 2015/11/1.
 */
public class BWActivity extends Activity {
    ViewPager bw_title;
    LinearLayout dots;
    List<ImageView> titlepics;
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bwactivity);
        initPics();

        bw_title = (ViewPager) findViewById(R.id.byway_title);
        dots= (LinearLayout)findViewById(R.id.vehi_vp_dots);
        listView= (ListView) findViewById(R.id.com_list);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getDatas()));

        PagerAdapter vpAdapter=new Vehi_VP_Adapter(this,titlepics);
        bw_title.setAdapter(vpAdapter);

        setTitlepics(titlepics);
    }

    private List<String> getDatas() {
        List<String> datas=new ArrayList<String>();
        for(int i=0;i<10;i++){
            datas.add("非常好  不错");
        }
        return datas;
    }

    void initPics(){
        titlepics =new ArrayList<>();
        for (int i=0;i<3;i++){
            ImageView iv=new ImageView(this);
            iv.setBackgroundResource(R.drawable.xiangqing);
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
            ImageView iv=new ImageView(this);
            iv.setImageResource(R.drawable.vehi_titlevp_dots);
            dots.addView(iv);
        }
        dots.getChildAt(0).setSelected(true);
    }

}
