package com.ds.etrip.Adapter;

/**
 * Created by 7YHong on 2015/11/4.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ds.etrip.ViewHolder;

import java.util.List;


public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    /**
     *
     * @param viewHolder
     * @param item mDatas里的第position条数据
     * 在这里修改了VH里面的内容
     */
    public abstract void convert(ViewHolder viewHolder, T item);

    private ViewHolder getViewHolder( View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId);
    }

}
