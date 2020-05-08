package com.teachingchild.teachingautisticchild.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class PaintView extends View {

    public ViewGroup.LayoutParams params;
    public static Path path = new Path();
    public static Paint brush = new Paint();

    private Bitmap bitmap;
    private Paint bitmapPaint;
    private int currentColor;
    private Canvas mCanvas;
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    public PaintView(Context context) {
        super(context);

        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        brush.setAntiAlias(true);
        brush.setColor(Color.RED);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(40);
        brush.setStrokeCap(Paint.Cap.ROUND);
        brush.setDither(true);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(bitmap);

        currentColor = Color.RED;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                touchStart(pointX, pointY);
                path.moveTo(pointX,pointY);
                return true;
            case MotionEvent.ACTION_MOVE :
                touchMove(pointX, pointY);
                path.lineTo(pointX,pointY);
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                Log.e("Last points", "onTouchEvent: "+pointX+"  "+pointY );
            default:
                return false;

        }

        postInvalidate();
        return false;
    }

    private void touchStart(float x, float y) {
        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        path.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(path, brush);
        // kill this so we don't double draw
        path.reset();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        canvas.drawPath(path,brush);
    }


    public void clear() {
        bitmap.eraseColor(Color.TRANSPARENT);
        path.reset();
        bitmapPaint.reset();
        invalidate();
    }


    @Override
    public void invalidate() {
        super.invalidate();
    }


}
