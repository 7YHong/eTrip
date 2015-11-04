package com.ds.etrip.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.ds.etrip.Adapter.Byway_List_adapter;
import com.ds.etrip.Adapter.Vehi_VP_Adapter;
import com.ds.etrip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hymei on 2015/11/1.
 */
public class Bw_details extends Activity {
    ViewPager bw_title;
    LinearLayout dots;
    List<ImageView> titlepics;
    ListView listView;
    ImageView buy_btn;
    ScrollView scrollView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bwdetail);
        initPics();

        buy_btn = (ImageView) findViewById(R.id.buy_now);
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Bw_details.this,Bw_buynow.class));
            }
        });
        bw_title = (ViewPager) findViewById(R.id.byway_title);
        dots= (LinearLayout)findViewById(R.id.vehi_vp_dots);
        scrollView= (ScrollView) findViewById(R.id.bd_start);
        scrollView.setFocusable(false);
        listView= (ListView) findViewById(R.id.com_list);
        Byway_List_adapter myadapter =new Byway_List_adapter(this, R.layout.bw_comment);
        listView.setAdapter(myadapter);

        PagerAdapter vpAdapter=new Vehi_VP_Adapter(this,titlepics);
        bw_title.setAdapter(vpAdapter);
        myadapter.setMessage(new Byway_List_adapter.CustomInterface() {
            @Override
            public void onViewCreated(List<View> myViews) {
                Bitmap bitmap = toRoundBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
//                for (int i = 0; i < myViews.size(); i++) {
//                    View v = myViews.get(i);
//                    ImageView imageView = (ImageView) v.findViewById(R.id.man_logo);//getResources().getDrawable(R.drawable.startpage1)
//                    imageView.setImageBitmap(bitmap);
//                }
            }
        });
        setTitlepics(titlepics);
    }
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        //圆形图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //正方形的边长
        int r = 0;
        //取最短边做边长
        if(width > height) {
            r = height;
        } else {
            r = width;
        }
        //构建一个bitmap
        Bitmap backgroundBmp = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        //设置边缘光滑，去掉锯齿
        paint.setAntiAlias(true);
        //宽高相等，即正方形
        RectF rect = new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r / 2, r / 2, paint);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, paint);
        //返回已经绘画好的backgroundBmgp

        return backgroundBmp;
    }


    void initPics(){
        titlepics =new ArrayList<>();
        for (int i=0;i<3;i++){
            ImageView iv=new ImageView(this);
            iv.setBackgroundResource(R.drawable.startpage1);
            iv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setAdjustViewBounds(true);
            titlepics.add(iv);
        }

    }
    void setTitlepics(List<ImageView> titlepics) {
        dots.removeAllViews();
        for (int i=0;i<titlepics.size();i++){
            ImageView iv=new ImageView(this);
            iv.setImageResource(R.drawable.vehi_titlevp_dots);
            dots.addView(iv);
        }
        dots.getChildAt(0).setSelected(true);
    }

}
