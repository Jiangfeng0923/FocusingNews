/**
 * Copyright (C) 2017, Xiaomi Inc. All rights reserved.
 */

package com.miuix.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import android.widget.LinearLayout;

import com.miuix.R;

public class GroupButton extends AppCompatButton {

    private static final int[] STATE_FIRST_V = new int[]{
            R.attr.state_first_v
    };

    private static final int[] STATE_MIDDLE_V = new int[]{
            R.attr.state_middle_v
    };

    private static final int[] STATE_LAST_V = new int[]{
            R.attr.state_last_v
    };

    private static final int[] STATE_FIRST_H = new int[]{
            R.attr.state_first_h
    };

    private static final int[] STATE_MIDDLE_H = new int[]{
            R.attr.state_middle_h
    };

    private static final int[] STATE_LAST_H = new int[]{
            R.attr.state_last_h
    };

    private static final int[] STATE_SINGLE_H = new int[]{
            R.attr.state_single_h
    };

    public GroupButton(Context context) {
        super(context);
    }

    public GroupButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent == null) {
            return super.onCreateDrawableState(extraSpace);
        }
        if (parent instanceof LinearLayout) {
            int orientation = ((LinearLayout) parent).getOrientation();
            final int indexOfThis = parent.indexOfChild(this);
            boolean isFirst = true;
            boolean isLast = true;
            int visibleCount = 0;
            for (int i = 0; i < parent.getChildCount(); i++) {
                if (parent.getChildAt(i).getVisibility() == VISIBLE) {
                    visibleCount++;
                    if (i < indexOfThis) {
                        isFirst = false;
                    }
                    if (i > indexOfThis) {
                        isLast = false;
                    }
                }
            }
            final boolean isSingle = visibleCount == 1;
            if (orientation == LinearLayout.VERTICAL) {
                int[] states = super.onCreateDrawableState(extraSpace + 2);
                mergeDrawableStates(states, STATE_SINGLE_H);
                if (!isSingle) {
                    if (isFirst) {
                        mergeDrawableStates(states, STATE_FIRST_V);
                    } else if (isLast) {
                        mergeDrawableStates(states, STATE_LAST_V);
                    } else {
                        mergeDrawableStates(states, STATE_MIDDLE_V);
                    }
                }
                return states;
            } else {
                //todo
                boolean isRtl= false;
//                boolean isRtl = ViewUtils.isLayoutRtl(this);
                int[] states = super.onCreateDrawableState(extraSpace + 1);
                if (isSingle) {
                    mergeDrawableStates(states, STATE_SINGLE_H);
                } else if (isFirst) {
                    mergeDrawableStates(states, isRtl ? STATE_LAST_H : STATE_FIRST_H);
                } else if (isLast) {
                    mergeDrawableStates(states, isRtl ? STATE_FIRST_H : STATE_LAST_H);
                } else {
                    mergeDrawableStates(states, STATE_MIDDLE_H);
                }
                return states;
            }
        }
        return super.onCreateDrawableState(extraSpace);
    }
}
