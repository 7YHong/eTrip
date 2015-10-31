package com.ds.etrip.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.etrip.R;


/**
 * Created by 7YHong on 2015/10/29.
 */
public class Frag_Mine extends Fragment{
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_mine,container,false);

        return v;
    }
}
