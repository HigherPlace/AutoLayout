package com.bryan.autolayout;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.bryan.autolayout.widget.AutoCardView;
import com.bryan.autolayout.widget.AutoCoordinatorLayout;
import com.bryan.autolayout.widget.AutoFrameLayout;
import com.bryan.autolayout.widget.AutoLinearLayout;
import com.bryan.autolayout.widget.AutoRelativeLayout;

;

public class AutoLayoutActivity extends AppCompatActivity {
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    private static final String LAYOUT_CARDVIEW = "CardView";
    private static final String LAYOUT_COORDINATORLAYOUT = "CoordinatorLayout";
    private static final String LAYOUT_DRAWERLAYOUT = "DrawerLayout";


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (name.equals(LAYOUT_COORDINATORLAYOUT)) {
            view = new AutoCoordinatorLayout(context, attrs);
        }

        if (name.equals(LAYOUT_CARDVIEW)) {
            view = new AutoCardView(context, attrs);
        }

        if (name.equals(LAYOUT_DRAWERLAYOUT)) {
            view = new DrawerLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }


}
