package com.ds.etrip.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ds.etrip.R;

/**
 * Created by 7YHong on 2015/11/2.
 */
public class Act_RentDetail extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rentdetail);
        findViewById(R.id.rentdetail_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Act_Pay.class));
            }
        });
    }
}
