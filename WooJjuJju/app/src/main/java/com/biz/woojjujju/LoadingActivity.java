package com.biz.woojjujju;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler(  ) ;
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( getBaseContext(),MainActivity.class ) ;
                startActivity( intent );
                finish();
            }
        } , 3000 ) ;

    }

}
