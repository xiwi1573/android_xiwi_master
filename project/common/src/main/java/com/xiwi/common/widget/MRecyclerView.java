package com.xiwi.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by mango on 2017/4/8.
 */

public class MRecyclerView extends RecyclerView {
    public MRecyclerView(Context context) {
        super(context);
    }

    public MRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE://空闲状态
                imageResume();
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING://滚动状态
                imagePause();
                break;
            case RecyclerView.SCROLL_STATE_SETTLING://触摸后滚动
                break;
        }
    }

    // 暂停图片请求
    public static void imagePause() {
        if (!Fresco.getImagePipeline().isPaused()) {
            Fresco.getImagePipeline().pause();
        }
    }

    // 恢复图片请求
    public static void imageResume() {
        if (Fresco.getImagePipeline().isPaused()) {
            Fresco.getImagePipeline().resume();
        }
    }
}
