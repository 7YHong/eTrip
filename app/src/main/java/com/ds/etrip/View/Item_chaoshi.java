package com.ds.etrip.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ds.etrip.Adapter.Byway_List_adapter;
import com.ds.etrip.R;

/**
 * Created by Hymei on 2015/11/1.
 */
public class Item_chaoshi extends Fragment {
    View v;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.bw_item_chaoshi,container,false);
        listView= (ListView) v.findViewById(R.id.bw_chaoshi);
        final Byway_List_adapter myAdapter=new Byway_List_adapter(getActivity(), R.layout.bw_adapter_listview);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),Bw_details.class));
            }
        });
        return v;
    }

}
