package com.ds.etrip.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hymei on 2015/11/3.
 */
public class Byway_List_adapter extends BaseAdapter {
    Context context;
    int LayoutResourceID;
    List<View> myViews;
    CustomInterface message =null;

    //外部接口，用于给按钮设置回调
    public void setMessage(CustomInterface listener){
        this.message =listener;
    }

    public Byway_List_adapter(Context context, int LayoutResourceID) {
        this.context=context;
        this.LayoutResourceID=LayoutResourceID;
        myViews=new ArrayList<View>();

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null) {
            view = LayoutInflater.from(context).inflate(LayoutResourceID, viewGroup, false);
        }
        myViews.add(view);
        if(message!=null&&i==getCount()-1) {
            message.onViewCreated(myViews);
        }
        return view;

    }
    public interface CustomInterface{
        public void onViewCreated(List<View> myViews);

    }
}
