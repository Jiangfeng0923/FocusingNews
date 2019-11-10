/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package com.miuix.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.AppCompatTextView;

public class SingleCenterTextView extends AppCompatTextView {

    private boolean mEnableSingleCenter;

    public SingleCenterTextView(Context context) {
        super(context);
    }

    public SingleCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleCenterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEnableSingleCenter(boolean enableSingleCenter) {
        mEnableSingleCenter = enableSingleCenter;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mEnableSingleCenter && getLayout().getLineCount() == 1 && getGravity() != Gravity.CENTER_HORIZONTAL) {
            setGravity(Gravity.CENTER_HORIZONTAL);
        }
    }
}
