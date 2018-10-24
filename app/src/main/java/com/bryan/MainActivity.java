package com.bryan;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bryan.autolayout.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    ImageView ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen();
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        ll = findViewById(R.id.iv);
        ll.post(new Runnable() {
            @Override
            public void run() {
                int sw = UIUtils.getINSTANCE(mContext, false).getScreenSize()[0];
                int sh = UIUtils.getINSTANCE(mContext, false).getScreenSize()[1];
                int bgW = ll.getMeasuredWidth();
                int bgH = ll.getMeasuredHeight();

                String TA = "qqqqqqqqqqqqqqqq";
                Log.i(TA, "屏幕宽度： " + sw + "-----屏幕高度： " + sh);
                Log.i(TA, "Cover宽度： " + bgW + "----Cover高度： " + bgH);
//                LogUtils.i(TA, "水波纹宽度： " + waveW + "----水波纹高度: " + waveH);

            }
        });
    }

    private void fullScreen() {
        //隐藏虚拟按键，并且全屏,代码一般就用此方法来隐藏状态栏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

    }

}
