package com.ds.etrip;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 7YHong on 2015/11/4.
 */
public class ViewHolder {

    private final SparseArray<View> mViews;
    //缓存的基本单位
    private View mConvertView;

    /**
     *
     * @param context
     * @param parent
     * @param layoutId
     * 生成一个VH，放缓存，需要的时候直接从缓存里提VH
     */
    private ViewHolder(Context context, ViewGroup parent, int layoutId) {
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        //setTag
        mConvertView.setTag(this);


    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     * 外部方法，得到缓存的VH
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId) {

        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId);
        }
        return (ViewHolder) convertView.getTag();
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        //index直接用ID了，6到飞起
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

}
