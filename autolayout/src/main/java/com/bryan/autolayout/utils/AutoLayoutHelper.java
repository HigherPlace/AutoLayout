/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bryan.autolayout.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.bryan.autolayout.AutoLayoutInfo;
import com.bryan.autolayout.R;
import com.bryan.autolayout.attrs.HeightAttr;
import com.bryan.autolayout.attrs.MarginAttr;
import com.bryan.autolayout.attrs.MarginBottomAttr;
import com.bryan.autolayout.attrs.MarginLeftAttr;
import com.bryan.autolayout.attrs.MarginRightAttr;
import com.bryan.autolayout.attrs.MarginTopAttr;
import com.bryan.autolayout.attrs.MaxHeightAttr;
import com.bryan.autolayout.attrs.MaxWidthAttr;
import com.bryan.autolayout.attrs.MinHeightAttr;
import com.bryan.autolayout.attrs.MinWidthAttr;
import com.bryan.autolayout.attrs.PaddingAttr;
import com.bryan.autolayout.attrs.PaddingBottomAttr;
import com.bryan.autolayout.attrs.PaddingLeftAttr;
import com.bryan.autolayout.attrs.PaddingRightAttr;
import com.bryan.autolayout.attrs.PaddingTopAttr;
import com.bryan.autolayout.attrs.TextSizeAttr;
import com.bryan.autolayout.attrs.WidthAttr;
import com.bryan.autolayout.config.AutoLayoutConifg;

/**
 * 这个类是关键类
 */
public class AutoLayoutHelper {
    private final ViewGroup mHost;

    private static final int[] LL = new int[]
            { //
                    android.R.attr.textSize,
                    android.R.attr.padding,//
                    android.R.attr.paddingLeft,//
                    android.R.attr.paddingTop,//
                    android.R.attr.paddingRight,//
                    android.R.attr.paddingBottom,//
                    android.R.attr.layout_width,//
                    android.R.attr.layout_height,//
                    android.R.attr.layout_margin,//
                    android.R.attr.layout_marginLeft,//
                    android.R.attr.layout_marginTop,//
                    android.R.attr.layout_marginRight,//
                    android.R.attr.layout_marginBottom,//
                    android.R.attr.maxWidth,//
                    android.R.attr.maxHeight,//
                    android.R.attr.minWidth,//
                    android.R.attr.minHeight,//16843072
            };

    private static final int INDEX_TEXT_SIZE = 0;
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MAX_HEIGHT = 14;
    private static final int INDEX_MIN_WIDTH = 15;
    private static final int INDEX_MIN_HEIGHT = 16;

    private static AutoLayoutConifg mAutoLayoutConifg;

    public AutoLayoutHelper(ViewGroup host) {
        mHost = host;

        if (mAutoLayoutConifg == null) {
            initAutoLayoutConfig(host);
        }

    }

    private void initAutoLayoutConfig(ViewGroup host) {
        mAutoLayoutConifg = AutoLayoutConifg.getInstance();
        mAutoLayoutConifg.init(host.getContext());
    }


    /**
     * 遍历当前ViewGroup下面的所有子View,并对子View进行缩放处理
     */
    public void adjustChildren() {
        AutoLayoutConifg.getInstance().checkParams();

        for (int i = 0, n = mHost.getChildCount(); i < n; i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();

            if (params instanceof AutoLayoutParams) {
                AutoLayoutInfo info =
                        ((AutoLayoutParams) params).getAutoLayoutInfo();
                if (info != null) {
                    //进行缩放处理，对对应LayoutParams进行值得设置
                    info.fillAttrs(view);
                }
            }
        }

    }

    /**
     * 获取所有有设置的目标属性，在生成LayoutParams的时候会被调用
     *
     * @param context
     * @param attrs
     * @return
     */
    public static AutoLayoutInfo getAutoLayoutInfo(Context context,
                                                   AttributeSet attrs) {

        AutoLayoutInfo info = new AutoLayoutInfo();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLayout_Layout);
        int baseWidth = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_basewidth, 0);
        int baseHeight = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_baseheight, 0);
        a.recycle();

        TypedArray array = context.obtainStyledAttributes(attrs, LL);

        int n = array.getIndexCount();


        for (int i = 0; i < n; i++) {
            int index = array.getIndex(i);
            //先判断是不是设置成px，不是的话直接蹦出
            if (!DimenUtils.isPxVal(array.peekValue(index))) continue;

            int pxVal;
            try {
                pxVal = array.getDimensionPixelOffset(index, 0);
            } catch (Exception ignore)//not dimension
            {
                continue;
            }
            switch (index) {
                case INDEX_TEXT_SIZE:
                    info.addAttr(new TextSizeAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING:
                    info.addAttr(new PaddingAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_LEFT:
                    info.addAttr(new PaddingLeftAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_TOP:
                    info.addAttr(new PaddingTopAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_RIGHT:
                    info.addAttr(new PaddingRightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_BOTTOM:
                    info.addAttr(new PaddingBottomAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_WIDTH:
                    info.addAttr(new WidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_HEIGHT:
                    info.addAttr(new HeightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN:
                    info.addAttr(new MarginAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_LEFT:
                    info.addAttr(new MarginLeftAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_TOP:
                    info.addAttr(new MarginTopAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_RIGHT:
                    info.addAttr(new MarginRightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_BOTTOM:
                    info.addAttr(new MarginBottomAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_WIDTH:
                    info.addAttr(new MaxWidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_HEIGHT:
                    info.addAttr(new MaxHeightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_WIDTH:
                    info.addAttr(new MinWidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_HEIGHT:
                    info.addAttr(new MinHeightAttr(pxVal, baseWidth, baseHeight));
                    break;
            }
        }
        array.recycle();
        return info;
    }

    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }
}