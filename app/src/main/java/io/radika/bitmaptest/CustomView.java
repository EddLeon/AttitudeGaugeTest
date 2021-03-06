package io.radika.bitmaptest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by greenapsis on 24/06/16.
 */
public class CustomView extends View {

    private final Paint paint;
    private float mPitch = 0; // Degrees
    private float mRoll = 0; // Degrees, left roll is positive

    // These are created once and reused in subsequent onDraw calls.
    private Bitmap mSrcBitmap;
    private Canvas mSrcCanvas;
    private Bitmap mDstBitmap;

    private int mWidth;
    private int mHeight;

    public CustomView(Context context) {
        //super(context);
        this(context,null);

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        //paint.setColor(R.color.md_green_600);
        paint.setColor(0XFF259b24);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        int viewWidthHalf = getWidth()/2;
        int viewHeightHalf = getHeight()/2;

        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-10;
        else
            radius=viewWidthHalf-10;

        //canvas.drawCircle(viewWidthHalf,viewHeightHalf,radius,paint);
        canvas.drawArc(new RectF(viewWidthHalf-radius,viewHeightHalf-radius,viewWidthHalf+radius,viewHeightHalf+radius),mRoll+mPitch*radius/90,180-2*mPitch*radius/90,false,paint);//wArc(wingsCircleBounds, 0, 180, false, mMinPlanePaint);
        Log.d("START :", Float.toString(mRoll));
        Log.d("SWEEP :",Float.toString(180+mRoll));

    }

    public void setAttitude(float pitch, float roll) {
        mPitch = pitch;
        mRoll = roll;
        invalidate();
    }
}
