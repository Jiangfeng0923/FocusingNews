/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package com.miuix.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.miuix.R;

public class DialogParentPanel extends LinearLayout {

    private TypedValue mFixedWidthMinor;

    private TypedValue mFixedHeightMajor;

    private TypedValue mFixedWidthMajor;

    private TypedValue mFixedHeightMinor;

    private TypedValue mMaxWidthMinor;

    private TypedValue mMaxWidthMajor;

    private TypedValue mMaxHeightMinor;

    private TypedValue mMaxHeightMajor;

    public DialogParentPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Window);
        if (a.hasValue(R.styleable.Window_windowFixedWidthMinor)) {
            mFixedWidthMinor = new TypedValue();
            a.getValue(R.styleable.Window_windowFixedWidthMinor, mFixedWidthMinor);
        }
        if (a.hasValue(R.styleable.Window_windowFixedHeightMajor)) {
            mFixedHeightMajor = new TypedValue();
            a.getValue(R.styleable.Window_windowFixedHeightMajor, mFixedHeightMajor);
        }
        if (a.hasValue(R.styleable.Window_windowFixedWidthMajor)) {
            mFixedWidthMajor = new TypedValue();
            a.getValue(R.styleable.Window_windowFixedWidthMajor, mFixedWidthMajor);
        }
        if (a.hasValue(R.styleable.Window_windowFixedHeightMinor)) {
            mFixedHeightMinor = new TypedValue();
            a.getValue(R.styleable.Window_windowFixedHeightMinor, mFixedHeightMinor);
        }
        if (a.hasValue(R.styleable.Window_windowMaxWidthMinor)) {
            mMaxWidthMinor = new TypedValue();
            a.getValue(R.styleable.Window_windowMaxWidthMinor, mMaxWidthMinor);
        }
        if (a.hasValue(R.styleable.Window_windowMaxWidthMajor)) {
            mMaxWidthMajor = new TypedValue();
            a.getValue(R.styleable.Window_windowMaxWidthMajor, mMaxWidthMajor);
        }
        if (a.hasValue(R.styleable.Window_windowMaxHeightMajor)) {
            mMaxHeightMajor = new TypedValue();
            a.getValue(R.styleable.Window_windowMaxHeightMajor, mMaxHeightMajor);
        }
        if (a.hasValue(R.styleable.Window_windowMaxHeightMinor)) {
            mMaxHeightMinor = new TypedValue();
            a.getValue(R.styleable.Window_windowMaxHeightMinor, mMaxHeightMinor);
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = getWidthMeasureSpec(widthMeasureSpec);
        heightMeasureSpec = getHeightMeasureSpec(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getWidthMeasureSpec(int widthMeasureSpec) {
        return getMeasureSpec(widthMeasureSpec, true, mFixedWidthMinor, mFixedWidthMajor,
                mMaxWidthMinor, mMaxWidthMajor);
    }

    private int getHeightMeasureSpec(int heightMeasureSpec) {
        return getMeasureSpec(heightMeasureSpec, false, mFixedHeightMinor, mFixedHeightMajor,
                    mMaxHeightMinor, mMaxHeightMajor);
    }

    private int getMeasureSpec(int measureSpec, boolean isWidth, TypedValue fixedMinor, TypedValue fixedMajor,
            TypedValue maxMinor, TypedValue maxMajor) {
        final int mode = MeasureSpec.getMode(measureSpec);

        if (mode == MeasureSpec.AT_MOST) {
            int value;
            final DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
            final boolean isPortrait = metrics.widthPixels < metrics.heightPixels;
            TypedValue typedValue = isPortrait ? fixedMinor : fixedMajor;
            value = resolveDimension(metrics, typedValue, isWidth);

            if (value > 0) {
                measureSpec = MeasureSpec.makeMeasureSpec(value, MeasureSpec.EXACTLY);
            } else {
                typedValue = isPortrait ? maxMinor : maxMajor;
                value = resolveDimension(metrics, typedValue, isWidth);
                if (value > 0) {
                    final int size = MeasureSpec.getSize(measureSpec);
                    measureSpec = MeasureSpec.makeMeasureSpec(Math.min(value, size), MeasureSpec.AT_MOST);
                }
            }
        }
        return measureSpec;
    }

    private int resolveDimension(DisplayMetrics displayMetrics, TypedValue typedValue, boolean isWidth) {
        int value = 0;
        if (typedValue != null) {
            if (typedValue.type == TypedValue.TYPE_DIMENSION) {
                value = (int) typedValue.getDimension(displayMetrics);
            } else if (typedValue.type == TypedValue.TYPE_FRACTION) {
                final float base = isWidth ? displayMetrics.widthPixels : displayMetrics.heightPixels;
                value = (int) typedValue.getFraction(base, base);
            }
        }
        return value;
    }
}
