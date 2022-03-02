package com.example.myapplication.model;

import androidx.lifecycle.MutableLiveData;

public class CatchDataRepository {

    MutableLiveData<CatchData> liveCatchData;

    public MutableLiveData<CatchData> loadCatchRateData() {
        if (liveCatchData != null) {
            return liveCatchData;
        }
        liveCatchData = new MutableLiveData<>();
        CatchData data = new CatchData();
        liveCatchData.setValue(data);
        return liveCatchData;
    }



}
