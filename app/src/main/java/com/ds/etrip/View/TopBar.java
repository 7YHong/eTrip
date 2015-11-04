package com.ds.etrip.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.etrip.R;


/**
 * Created by Hymei on 2015/11/1.
 */
public class TopBar extends LinearLayout {
    LinearLayout barLayout;
    LinearLayout itemLayout;
    int recent;
    OnSelectChangedListener listener;

    public interface OnSelectChangedListener{
        void onSelect(int index);
    }

    public void setSelect(int index){
        changeSelect(index);
    }

    public void setListener(OnSelectChangedListener listener){
        this.listener=listener;
    }

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        recent=0;
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.topbar, this);
        barLayout= (LinearLayout) getChildAt(0);
        itemLayout= (LinearLayout) barLayout.getChildAt(0);
        ((ImageView)itemLayout.getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.chaoshi));
        ((TextView)itemLayout.getChildAt(1)).getPaint().setFakeBoldText(true);
        ((TextView)itemLayout.getChildAt(1)).setTextColor(Color.parseColor("#CC0000"));//��ɫ

        for (int i=0;i<barLayout.getChildCount();i++){
            ((LinearLayout)barLayout.getChildAt(i)).getChildAt(1).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int choose = 0;
                    switch (v.getId()) {
                        case R.id.bottom_0:
                            choose = 0;
                            break;
                        case R.id.bottom_1:
                            choose = 1;
                            break;
                        case R.id.bottom_2:
                            choose = 2;
                            break;
                        case R.id.bottom_3:
                            choose = 3;
                            break;
                    }
                    handleSelect(choose);
                }
            });
        }

    }
    private void handleSelect(int choose) {
        if (choose==recent) return;
        if(listener!=null) listener.onSelect(choose);
        changeSelect(choose);
    }
    private void changeSelect(int choose){
        ((TextView)((LinearLayout)barLayout.getChildAt(recent)).getChildAt(1)).getPaint().setFakeBoldText(false);//�Ǵ���
        ((TextView)((LinearLayout)barLayout.getChildAt(recent)).getChildAt(1)).setTextColor(Color.parseColor("#89898B"));//�ָ�
        ((TextView)((LinearLayout)barLayout.getChildAt(choose)).getChildAt(1)).getPaint().setFakeBoldText(true);
        ((TextView)((LinearLayout)barLayout.getChildAt(choose)).getChildAt(1)).setTextColor(Color.parseColor("#CC0000"));//��ɫ
        switch (recent) {
            case 0:
                ((ImageView)((LinearLayout)barLayout.getChildAt(recent)).getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.chaoshi1));//�ָ�
                break;
            case 1:
                ((ImageView)((LinearLayout)barLayout.getChildAt(recent)).getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.shitang1));//�ָ�
                break;
            case 2:
                ((ImageView)((LinearLayout)barLayout.getChildAt(recent)).getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.kuaidi1));//�ָ�
                break;
            case 3:
                ((ImageView)((LinearLayout)barLayout.getChildAt(recent)).getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.daying1));//�ָ�
                break;
        }
        switch (choose) {
            case 0:
                ((ImageView)((LinearLayout)barLayout.getChildAt(choose)).getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.chaoshi));
                break;
            case 1:
                ((ImageView)((LinearLayout)barLayout.getChildAt(choose)).getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.shitang));
                break;
            case 2:
                ((ImageView)((LinearLayout)barLayout.getChildAt(choose)).getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.kuaidi));
                break;
            case 3:
                ((ImageView)((LinearLayout)barLayout.getChildAt(choose)).getChildAt(0)).setImageDrawable(getResources().getDrawable(R.drawable.daying));
                break;
        }
        recent=choose;
    }
}
