package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import com.qq.reader.liveshow.a.j;

public class CountDownView extends View {
    private Paint a;
    private int b;
    private int c;
    private int d;
    private float e;
    private float f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private int k;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CountDownView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 100;
        this.j = false;
        this.a = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.CountDownAttr);
        this.b = obtainStyledAttributes.getColor(j.CountDownAttr_backgroudColor, -65536);
        this.c = obtainStyledAttributes.getColor(j.CountDownAttr_progressColor, -16711936);
        this.d = obtainStyledAttributes.getColor(j.CountDownAttr_countTextColor, -16711936);
        this.e = obtainStyledAttributes.getDimension(j.CountDownAttr_countTextSize, 15.0f);
        this.f = obtainStyledAttributes.getDimension(j.CountDownAttr_progressWidth, 5.0f);
        this.g = obtainStyledAttributes.getInteger(j.CountDownAttr_progressMax, 100);
        this.j = obtainStyledAttributes.getBoolean(j.CountDownAttr_showProgressText, true);
        this.k = obtainStyledAttributes.getInt(j.CountDownAttr_progressStyle, 0);
        obtainStyledAttributes.recycle();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int i = ((int) this.f) / 2;
        this.a.setColor(this.b);
        this.a.setStyle(Style.FILL);
        this.a.setAntiAlias(true);
        canvas.drawCircle((float) width, (float) width, (float) i, this.a);
        if (this.j) {
            this.a.setStrokeWidth(0.0f);
            this.a.setColor(this.d);
            this.a.setTextSize(this.e);
            this.a.setTypeface(Typeface.DEFAULT_BOLD);
            int i2 = (int) ((((float) this.h) / ((float) this.g)) * 100.0f);
            float measureText = this.a.measureText(i2 + "%");
            if (i2 != 0 && this.k == 0) {
                canvas.drawText(i2 + "%", ((float) width) - (measureText / 2.0f), ((float) width) + (this.e / 2.0f), this.a);
            }
        }
        this.a.setColor(this.c);
        RectF rectF = new RectF((float) (width - i), (float) (width - i), (float) (width + i), (float) (width + i));
        switch (this.k) {
            case 0:
                this.a.setStyle(Style.STROKE);
                canvas.drawArc(rectF, 0.0f, (float) ((this.h * 360) / this.g), false, this.a);
                return;
            case 1:
                this.a.setStyle(Style.FILL);
                if (this.h != 0) {
                    canvas.drawArc(rectF, -90.0f, (float) (this.h * this.i), true, this.a);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public synchronized int getMax() {
        return this.g;
    }

    public synchronized void setMax(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.g = i;
    }

    public synchronized int getProgress() {
        return this.h;
    }

    public synchronized void setProgress(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (i > this.g) {
            i = this.g;
        }
        if (i <= this.g) {
            this.h = i;
            postInvalidate();
        }
    }

    public void setIntAngle(int i) {
        this.i = i;
    }

    public int getCricleColor() {
        return this.b;
    }

    public void setCricleColor(int i) {
        this.b = i;
    }

    public int getCricleProgressColor() {
        return this.c;
    }

    public void setCricleProgressColor(int i) {
        this.c = i;
    }

    public int getTextColor() {
        return this.d;
    }

    public void setTextColor(int i) {
        this.d = i;
    }

    public float getTextSize() {
        return this.e;
    }

    public void setTextSize(float f) {
        this.e = f;
    }

    public float getRoundWidth() {
        return this.f;
    }

    public void setRoundWidth(float f) {
        this.f = f;
    }

    public void setProgressStyle(int i) {
        this.k = i;
    }
}
