package com.qq.reader.widget.titler;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;

public class GrayBgEnableImageButton extends ImageButton implements b {
    private a a;

    public GrayBgEnableImageButton(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.a = new a(this, context);
    }

    public GrayBgEnableImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public GrayBgEnableImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    protected void onDraw(Canvas canvas) {
        this.a.a(canvas, getWidth(), getHeight());
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.a.a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    public void setEnable(boolean z) {
        this.a.setEnable(z);
    }
}
