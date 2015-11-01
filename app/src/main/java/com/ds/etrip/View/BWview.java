package com.ds.etrip.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ds.etrip.R;

/**
 * Created by Hymei on 2015/11/1.
 */
public class BWview extends Fragment {
    View v;
    Button xq_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.bywayyulan,container,false);
        xq_btn= (Button) v.findViewById(R.id.xiangqing_btn);
        xq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), BWActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return v;
    }
    @SuppressLint("ValidFragment")
    public BWview(int i){

    }
}
