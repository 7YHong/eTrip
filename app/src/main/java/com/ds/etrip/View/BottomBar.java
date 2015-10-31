package com.ds.etrip.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.etrip.R;


/**
 * Created by 7YHong on 2015/10/30.
 */
public class BottomBar extends LinearLayout{
    LinearLayout barLayout;
    int recent;//当前选中
    OnSelectChangedListener listener;

    //为主界面准备，用于通过触摸操作修改所指向
    public interface OnSelectChangedListener{
        void onSelect(int index);
    }

    public void setSelect(int index){
        changeSelect(index);
    }

    public void setListener(OnSelectChangedListener listener){
        this.listener=listener;
    }

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        recent=0;
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.bottombar, this);
        barLayout= (LinearLayout) getChildAt(0);
        ((TextView)barLayout.getChildAt(0)).getPaint().setFakeBoldText(true);
        ((TextView)barLayout.getChildAt(0)).setTextColor(Color.parseColor("#0000FF"));//蓝色
        for (int i=0;i<barLayout.getChildCount();i++){
            barLayout.getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int choose=0;
                    switch (v.getId()){
                        case R.id.bottom_0:choose=0;break;
                        case R.id.bottom_1:choose=1;break;
                        case R.id.bottom_2:choose=2;break;
                        case R.id.bottom_3:choose=3;break;
                    }
                    handleSelect(choose);
                }
            });
        }

    }
    //判断是否需要进行修改并通知外部
    private void handleSelect(int choose) {
        if (choose==recent) return;
        //通知外界选项发生了变化
        if(listener!=null) listener.onSelect(choose);
        changeSelect(choose);
    }
    //修改内部显示状态
    private void changeSelect(int choose){
        //修改bottombar状态
        ((TextView)barLayout.getChildAt(recent)).getPaint().setFakeBoldText(false);//非粗体
        ((TextView)barLayout.getChildAt(recent)).setTextColor(Color.parseColor("#89898B"));//恢复
        ((TextView)barLayout.getChildAt(choose)).getPaint().setFakeBoldText(true);
        ((TextView)barLayout.getChildAt(choose)).setTextColor(Color.parseColor("#0000FF"));//蓝色

        //修改指针
        recent=choose;
    }
}
