package com.ds.etrip.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.ds.etrip.R;


/**
 * Created by 7YHong on 2015/11/1.
 */
public class Act_StartPage extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_startpage);
        final Handler handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1300);
                } catch (Exception e){
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Act_StartPage.this,Act_Main.class));
                        finish();
                    }
                });
            }
        }).start();
    }
}
