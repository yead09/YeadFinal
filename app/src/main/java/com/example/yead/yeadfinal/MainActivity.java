package com.example.yead.yeadfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yead.yeadfinal.audio.BaseAudioOb;
import com.example.yead.yeadfinal.audio.MusicController;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by Yead on 3/23/2017.
 */

public class MainActivity extends BaseActivity implements MusicController.IPlayerStatus {

    public Button welcome;
    private ArrayList<BaseAudioOb> contentList = new ArrayList<BaseAudioOb>();
    private MusicController controller;

    @BindView(R.id.main_play_play)
    ImageView playbt;

    @BindView(R.id.pb_play_loading)
    ProgressBar progressBar;

    @OnClick(R.id.main_play_play)
    public void play(){
        if (!controller.isPlaying) {
            controller.play();
        }
        else{
            controller.pause();
        }
    }

    @OnClick(R.id.welcome)
    public void toOpener(){
        toActivity(Opener.class);
    }

    private Animation rotateAnimation;

    @BindView(R.id.anim_tv)
    TextView tv;

    @OnClick(R.id.welcome)
    public void rotate(){
        tv.startAnimation(rotateAnimation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialAnimaton();
        BaseAudioOb m1 = new BaseAudioOb();
        m1.setURL("http://other.web.rh01.sycdn.kuwo.cn/resource/n3/77/1/1061700123.mp3");
        contentList.add(m1);
        controller = MusicController.getInstance(this.getBaseContext());
        controller.setPlayList(contentList);
        controller.addListener("PlayView",this);

    }


    @Override
    public void onWindowFocusChanged (boolean start) {
        if (start){
            tv.startAnimation(rotateAnimation);
        }
    }

    private void initialAnimaton() {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.animation);
    }




    @Override
    public void onLoading() {
        prepareStatus();
    }
    private void prepareStatus() {
        playbt.setBackgroundResource(R.drawable.playscreen_play_pause);
        playbt.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void pauseStatus() {
        playbt.setBackgroundResource(R.drawable.playscreen_play_pause);
        playbt.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
    private void startStatus() {
        playbt.setBackgroundResource(R.drawable.playscreen_play_play);
//        playbt.setVisibility(View.VISIBLE);
//        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onProgress(int i) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onPrepared() {

    }

    @Override
    public void onSeekComplete() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onUpdateCache(int i) {

    }

    @Override
    public void onPause() {
        super.onPause();
        startStatus();
    }

    @Override
    public void onResume() {
        super.onResume();
        startStatus();
    }

    @Override
    public void onStart(int i) {
        pauseStatus();
    }

    @Override
    public void onInitComplete() {

    }

    @Override
    public void onDestroy() {
        MusicController controller = MusicController.getInstance(this);
        controller.destroy();
        super.onDestroy();
    }
}
