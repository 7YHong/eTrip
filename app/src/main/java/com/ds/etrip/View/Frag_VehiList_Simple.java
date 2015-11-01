package com.ds.etrip.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ds.etrip.Adapter.Custom_List_Adapter;
import com.ds.etrip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 7YHong on 2015/11/1.
 */
public class Frag_VehiList_Simple extends Fragment{
    static int LayoutResID;

    public static Frag_VehiList_Simple newInstance(int type){
        if (type==0) LayoutResID=R.layout.item_vehilist_taxi;
        else LayoutResID=R.layout.item_vehilist_bus;
        return new Frag_VehiList_Simple();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView listView=new ListView(getContext());
        listView.setDividerHeight(8);
        List<String> data=new ArrayList();

        for (int i=0;i<10;i++){
            data.add("Listdata:"+String.valueOf(i));
        }
        ListAdapter adapter=new Custom_List_Adapter<>(getContext(),LayoutResID,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),Act_VehiDetail.class));
            }
        });
        return listView;
    }
}
