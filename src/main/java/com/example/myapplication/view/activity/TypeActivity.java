package com.example.myapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTypedexBinding;
import com.example.myapplication.view.fragment.SingleTypeFragment;


public class TypeActivity extends AppCompatActivity {
    private static final String TAG = "TypeActivity";

    private ActivityTypedexBinding binding;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_typedex);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.typeDexFrameLayout, new SingleTypeFragment()).commit();
    }

    // 隐式Intent打开相机
    public void openBrowser(View view){
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
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
}
