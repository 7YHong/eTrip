package com.ds.etrip.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ds.etrip.R;


/**
 * Created by 7YHong on 2015/10/29.
 */
public class Frag_Byway extends Fragment {
    View v;

    Button map_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_byway,container,false);
        map_btn= (Button) v.findViewById(R.id.map_btn);
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
