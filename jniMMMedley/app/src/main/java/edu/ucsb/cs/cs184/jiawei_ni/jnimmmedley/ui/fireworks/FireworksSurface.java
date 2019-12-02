package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.fireworks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;


public class FireworksSurface extends SurfaceView implements SurfaceHolder.Callback {

    static final int MAX_DX = 18;
    static final int MAX_DY = 18;
    static final int DOT_RADIUS = 15;
    static final int FPS = 50;

    DrawingThread thread;
    SurfaceHolder holder;
    Paint paint;

    ArrayList<Dot> dots;
    ArrayList<Dot> toRemove;
    Random random;

    public FireworksSurface(Context context, AttributeSet attrs) {
        super(context, attrs);

        holder = getHolder();
        holder.addCallback(this);

        dots = new ArrayList<>();
        toRemove = new ArrayList<>();
        random = new Random();

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        thread = new DrawingThread(this, FPS);

        setBackgroundColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch(action) {
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                dots.clear();
                toRemove.clear();
                for (int i=0; i<6; i++) {
                    int dx = random.nextInt(MAX_DX) - 9;
                    int dy = random.nextInt(MAX_DY) - 9;
                    if (dx == 0) dx = 1;
                    if (dy == 0) dy = 1;
                    dots.add(new Dot((int)event.getX(), (int)event.getY(),
                            dx, dy));
                }
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x;
        int y;
        for (Dot dot : dots) {
            x = dot.getX();
            y = dot.getY();

            canvas.drawCircle(x, y, DOT_RADIUS, paint);

            int newX = x + dot.getDx();
            int newY = y + dot.getDy();

            if (newX >= 0 && newX <= getWidth() && newY >= 0 && newY <= getHeight()) {
                dot.setX(newX);
                dot.setY(newY);
            }
            else toRemove.add(dot);
        }

        for (Dot dot : toRemove) {
            dots.remove(dot);
        }
        toRemove.clear();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.stop();
    }

}