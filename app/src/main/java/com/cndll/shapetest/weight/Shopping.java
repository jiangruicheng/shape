package com.cndll.shapetest.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cndll.shapetest.R;

/**
 * Created by jiangruicheng on 2017/7/22.
 */

public class Shopping extends View {
    private String priceText;
    private String sureText;
    private String countText = "12";
    private Bitmap bitmap;
    private BitmapShader bitmapShader;

    public Shopping(Context context) {
        super(context);
    }

    public Shopping(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Shopping(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int offsetx = getWidth() /10;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shopping);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLACK);
        paint.setAlpha(0xcf);
        RectF shopping = new RectF(0, getHeight() / 6, getWidth() - getWidth() / 7 * 2, getHeight());
        // RectF rectF = new RectF(0, 0, 120, 120);
        canvas.drawRect(shopping, paint);
        RectF pay = new RectF(getWidth() - getWidth() / 7 * 2, getHeight() / 6, getWidth(), getHeight());
        paint.setColor(Color.rgb(0xf0, 0x39, 0x3c));
        canvas.drawRect(pay, paint);
        paint.setColor(Color.BLACK);
        paint.setAlpha(0xcf);
        canvas.drawCircle(offsetx, (getHeight() - getHeight() / 6) / 2, (getHeight() - getHeight() / 6) / 2, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(offsetx, (getHeight() - getHeight() / 6) / 2, (getHeight() - getHeight() / 3) / 2, paint);
        // paint.setShader(bitmapShader);
        paint.setColor(Color.WHITE);
        paint.setTextSize(getWidth() / 24);
        canvas.drawText("￥150", offsetx + (getHeight() - getHeight() / 6) / 2 + 12, getHeight() - getHeight() / 3, paint);
        canvas.drawText("去结算", getWidth() / 7 * 5 + getWidth() / 7 - paint.measureText("去结算") / 2, getHeight() - getHeight() / 3, paint);
        Rect bitmapRectF = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Rect showRectF = new Rect(0, 0, (getHeight() - getHeight() / 3) / 2, (getHeight() - getHeight() / 3) / 2);
        canvas.translate(offsetx - (getHeight() - getHeight() / 3) / 4, (getHeight() - getHeight() / 6) / 2 - ((getHeight() - getHeight() / 3) / 2) / 2);
        canvas.drawBitmap(bitmap, bitmapRectF, showRectF, paint);
        if (countText != null) {
            canvas.translate((getHeight() - getHeight() / 3) / 4 + (getHeight() - getHeight() / 6) / 2 / 3 * 2, -((getHeight() - getHeight() / 3) / 2) / 2);
            paint.setColor(Color.rgb(0xf0, 0x39, 0x3c));
            canvas.drawCircle(0, 0, getHeight() / 10, paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(getHeight() / 6);
            String t = countText;
            if (t == null) {
                t = "1";
            }
            if (Float.valueOf(t)>=10){
                t=",,";
            }
            canvas.drawText(t, -getWidth()/150, getHeight() / 25, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < getWidth() / 7 * 5) {
                    if (onClickListener != null) {
                        onClickListener.onShoppingCart(this);
                    }
                } else {
                    if (onClickListener != null) {
                        onClickListener.onSure(this);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private OnClickListener onClickListener;

    public interface OnClickListener {
        void onSure(View view);

        void onShoppingCart(View view);
    }
}
