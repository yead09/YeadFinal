package com.example.yead.yeadfinal;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Gesture extends BaseActivity implements View.OnTouchListener {

    private GestureDetector mGestureDetector;
    @BindView(R.id.main_fl)FrameLayout fl;

    @BindView(R.id.gesture) View gestureview;
    private boolean check = false;
    private int var;
    private int counter;

    @OnClick(R.id.leftbutton)
    public void basebt(){
        if(check){
            hw4btcl();
        }
        else{
            hw4btop();
        }
    }

    public void hw4btop(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(gestureview, "translationX", 0, 1000); //translationY
        animator.setDuration(1000);
        animator.start();
        check = true;
    }

    public void hw4btcl(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(gestureview, "translationX", 1000, 0); //translationY
        animator.setDuration(1000);
        animator.start();
        check = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        ButterKnife.bind(this);

        mGestureDetector = new GestureDetector(this, new simpleGestureListener());
        fl.setOnTouchListener(this);
    }
    public boolean onTouch(View v, MotionEvent event){
        return mGestureDetector.onTouchEvent(event);

    }
    private class simpleGestureListener extends
            GestureDetector.SimpleOnGestureListener{

        public boolean onDown(MotionEvent e){
//            UtilLog.logD("MyGesture", "onDown");
//            toastShort("onDown");
            return true;
        }
        public void onShowPress(MotionEvent e){
//            UtilLog.logD("MyGesture", "onShowPress");
//            toastShort("onShowPress");
        }
        public void onLongPress(MotionEvent e){
//            UtilLog.logD("MyGesture", "onLongPress");
//            toastShort("onLongPress");
        }
        public boolean onSingleTapUp(MotionEvent e){
//            UtilLog.logD("myGesture", "onSingleTapUp");
//            toastShort("onSingleTapUp");
            return true;
        }
        public boolean onSingleTapConfirmed(MotionEvent e){
//            UtilLog.logD("myGesture", "onSingleTapUp");
//            toastShort("onSingleTapConfirmed");
            return true;
        }
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
            //    UtilLog.logD("myGesture", "onScroll:" + (e2.getX() - e1.getX()) + " " + distanceX);
//            toastShort("onScroll");
            return true;
        }
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            if(counter==0){
                ObjectAnimator animator = ObjectAnimator.ofFloat(gestureview, "translationX", 0, 1000,1000,1000,1000); //translationY
                animator.setDuration(1000);
                animator.start();
                counter++;
            }
            else{
                ObjectAnimator animator = ObjectAnimator.ofFloat(gestureview, "translationX", 1000, 0,0,0,0); //translationY
                animator.setDuration(1000);
                animator.start();
                counter=0;
            }

            return true;
        }
        public boolean onDoubleTap(MotionEvent e){
            //           UtilLog.logD("myGesture", "onDoubleTap");
            //           toastShort("onDoubleTap");
            return true;
        }
        public boolean onDoubleTapEvent(MotionEvent e){
            //           UtilLog.logD("myGesture", "onDoubleTapEvent");
            //           toastShort("onDoubleTapEvent");
            return true;
        }

    }
}
