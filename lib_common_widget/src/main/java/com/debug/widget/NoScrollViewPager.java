package com.debug.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    public void setDisableScrollAnimator(boolean disableScrollAnimator) {
        this.disableScrollAnimator = disableScrollAnimator;
    }

    private boolean disableScrollAnimator = false;

    // 是否禁止 viewpager 左右滑动
    private boolean noScroll = true;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll) {
            return false;
        } else {
            return super.onTouchEvent(arg0);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll) {
            return false;
        } else {
            return super.onInterceptTouchEvent(arg0);
        }
    }

    @Override
    public void setCurrentItem(int item) {
        //去除页面切换时的滑动翻页效果
        super.setCurrentItem(item, disableScrollAnimator);
    }
}