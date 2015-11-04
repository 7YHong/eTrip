package com.ds.etrip.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ds.etrip.R;

public class Bw_buynow extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bw_buy_now);
        findViewById(R.id.bw_buy_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Act_Pay.class));
            }
        });
    }


}
