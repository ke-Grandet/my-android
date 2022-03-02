package com.example.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.MainActivityTimeBean;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Disposable disposable = null;

    private ActivityMainBinding binding;

    private MainActivityTimeBean timeBean = new MainActivityTimeBean("1990-09-19 00:00:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ARouter.init(getApplication());
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setTimeBean(timeBean);
        initListener();
    }

    private void initListener() {
        binding.btnGotoNotificationActivity.setOnClickListener(v -> {
            Intent intent = new Intent(this, TypeActivity.class);
            startActivity(intent);
        });
        binding.btnGotoCatchActivity.setOnClickListener(v -> {
            ARouter.getInstance().build("/activity/catch").navigation();
        });
        binding.btnStartClock.setOnClickListener(v -> {
            timeBean.switchTimeRunning();

            binding.btnStartClock.setText(timeBean.getTimeRunningText());
            // rxJava异步时钟
            Observable<Long> ob;
            ob = Observable.interval(0, 10, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            if (timeBean.isTimeRunning()) {
                disposable = ob.subscribe(aLong -> {  // 在时钟运行时订阅
                    String time =
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                    .format(new Date());
                    timeBean.setTime(time);
                    binding.setTimeBean(timeBean);
                });
            }
            else {
                disposable.dispose();  // 在时钟停止时取消订阅
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
    }
    @Override
    public void onStop(){
        super.onStop();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    @Override
    public void onSaveInstanceState(@NotNull Bundle outState){
        outState.putString("data", "data from onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onRestoreInstanceState(@NotNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("data");
    }
}
