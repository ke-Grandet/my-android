package com.example.myapplication.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.IntProperty;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCatchBinding;
import com.example.myapplication.utils.IntegerParser;
import com.example.myapplication.viewModel.CatchDataViewModel;

import org.w3c.dom.Text;


@Route(path = "/activity/catch")
public class CatchActivity extends AppCompatActivity {
    private static final String TAG = "CatchRateActivity";

    ActivityCatchBinding binding;
    CatchDataViewModel catchRateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_catch);
        this.initViewModel();
        this.initListener();
    }

    private void initViewModel() {
        CatchDataViewModel.Factory factory = new CatchDataViewModel.Factory(getApplication());
        catchRateViewModel = new ViewModelProvider(this, factory).get(CatchDataViewModel.class);
        binding.setCatchRateViewModel(catchRateViewModel);
    }

    private void initListener() {
        // 第一个事件在文本改变时触发，第二个事件在焦点转移时触发，第三个事件在点击虚拟键盘提交时触发
        binding.editTextMaxHP.addTextChangedListener(textWatcher);
        binding.editTextMaxHP.setOnFocusChangeListener((view, b) -> calculate());
        binding.editTextMaxHP.setOnEditorActionListener((textView, i, keyEvent) -> {
            calculate();
            return false;
        });

        binding.editTextNowHP.addTextChangedListener(textWatcher);
        binding.editTextNowHP.setOnFocusChangeListener((view, b) -> calculate());
        binding.editTextNowHP.setOnEditorActionListener((textView, i, keyEvent) -> {
            calculate();
            return false;
        });

        binding.editTextRate.addTextChangedListener(textWatcher);
        binding.editTextNowHP.setOnFocusChangeListener((view, b) -> calculate());
        binding.editTextRate.setOnEditorActionListener((textView, i, keyEvent) -> {
            calculate();
            return false;
        });

        // 因为有小数点，所有不绑定文本改动时触发的事件，避免不好的UI表现
        binding.editTextRateCorrected.setOnFocusChangeListener((view, b) -> calculate());
        binding.editTextRateCorrected.setOnEditorActionListener((textView, i, keyEvent) -> {
            calculate();
            return false;
        });

        binding.editTextStatusCorrected.setOnFocusChangeListener((view, b) -> calculate());
        binding.editTextStatusCorrected.setOnEditorActionListener((textView, i, keyEvent) -> {
            calculate();
            return false;
        });
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void afterTextChanged(Editable editable) {
            calculate();
        }
    };

    private void calculate() {
        try {
            catchRateViewModel.calculateB();
            catchRateViewModel.calculateSuccessRate();
            binding.setCatchRateViewModel(catchRateViewModel);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
