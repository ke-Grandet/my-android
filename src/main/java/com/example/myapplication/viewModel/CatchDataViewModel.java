package com.example.myapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MyApplication;
import com.example.myapplication.model.CatchData;
import com.example.myapplication.model.CatchDataRepository;


public class CatchDataViewModel extends ViewModel {
    private static final String TAG = "CatchDataViewModel";
    private static final int a = 1048560;
    private static final int b = 16711680;
    private static final int defaultMaxHP = 10;
    private static final int defaultNowHP = 1;
    private static final int defaultRate = 255;
    private static final float defaultRateCorrected = (float)1.0;
    private static final float defaultStatusCorrected = (float)2.0;

    MutableLiveData<CatchData> liveCatchData;


    private CatchDataViewModel(MutableLiveData<CatchData> liveCatchData) {
        this.liveCatchData = liveCatchData;
    }

    public MutableLiveData<CatchData> getCatchRateData () {
        return this.liveCatchData;
    }

    public void calculateB() throws Exception {
        CatchData catchData = liveCatchData.getValue();
        if (catchData == null)
            return;
        float maxHP = catchData.getRawMaxHP() == null ? defaultMaxHP : catchData.getRawMaxHP();
        float nowHP = catchData.getRawNowHP() == null ? defaultNowHP : catchData.getRawNowHP();
        float rate = catchData.getRawRate() == null ? defaultRate : catchData.getRawRate();
        float rateCorrected = catchData.getRawRateCorrected() == null ? defaultRateCorrected : catchData.getRawRateCorrected();
        float statusCorrected = catchData.getRawStatusCorrected() == null ? defaultStatusCorrected : catchData.getRawStatusCorrected();
        if (maxHP == 0) {
            throw new Exception("最大生命值不可为零");
        }
        if (nowHP > maxHP) {
            throw new Exception("当前生命值已超出最大生命值");
        }
        float result = (3 * maxHP - 2 * nowHP) / (3 * maxHP) * rate * rateCorrected * statusCorrected;
        catchData.setB(result);
        liveCatchData.setValue(catchData);
    }

    public void calculateSuccessRate() {
        CatchData catchData = liveCatchData.getValue();
        if (catchData == null)
            return;
        float result;
        float B = catchData.getRawB();
        if (B <= 0)
            result = 0;
        else if (B >= 255) {
            result = 1;
        } else {
            double G = a / Math.pow(b / B, 0.25);
            result = (float)Math.pow(G / 65535, 4);
        }
        catchData.setSuccessRate(result);
        liveCatchData.setValue(catchData);
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        CatchDataRepository repository;

        public Factory(Application application) {
            this.repository = ((MyApplication) application).catchRateRepository;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            CatchDataViewModel catchRateViewModel = new CatchDataViewModel(repository.loadCatchRateData());
            return (T) catchRateViewModel;
        }
    }
}
