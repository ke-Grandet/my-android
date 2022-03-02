package com.example.myapplication.model;

import com.example.myapplication.utils.FloatParser;
import com.example.myapplication.utils.IntegerParser;


public class CatchData {
    private static final String TAG = "CatchData";

    float successRate = 0;  // 成功率

    float B = 0;  // 计算结果B

    Integer maxHP;  // 最大生命值

    Integer nowHP;  // 当前生命值

    Integer rate;  // 捕获率

    Float rateCorrected;  // 捕获修正

    Float statusCorrected;  // 状态修正


    // getter and setter

    public String getSuccessRate() {
        return FloatParser.formatFloat(this.successRate * 100) + "%";
    }

    public void setSuccessRate(float successRate) {
        this.successRate = successRate;
    }


    public String getB() {
        return FloatParser.formatFloat(this.B);
    }

    public Float getRawB() {
        return this.B;
    }

    public void setB(float b) {
        this.B = b;
    }


    public String getMaxHP() {
        return this.maxHP == null ? null : String.valueOf(this.maxHP);
    }

    public Integer getRawMaxHP() {
        return this.maxHP;
    }

    public void setMaxHP(String maxHP) {
        this.maxHP = IntegerParser.parseStrToInteger(maxHP);
    }


    public String getNowHP() {
        return this.nowHP == null ? null : String.valueOf(this.nowHP);
    }

    public Integer getRawNowHP() {
        return this.nowHP;
    }

    public void setNowHP(String nowHP) {
        this.nowHP = IntegerParser.parseStrToInteger(nowHP);
    }


    public String getRate() {
        return this.rate == null ? null : String.valueOf(this.rate);
    }

    public Integer getRawRate() {
        return this.rate;
    }

    public void setRate(String rate) {
        this.rate = IntegerParser.parseStrToInteger(rate);
    }


    public String getRateCorrected() {
        return FloatParser.formatFloat(this.rateCorrected, "0.0");
    }

    public Float getRawRateCorrected() {
        return this.rateCorrected;
    }

    public void setRateCorrected(String rateCorrected) {
        this.rateCorrected  = FloatParser.parseStrToFloat(rateCorrected);
    }


    public String getStatusCorrected() {
        return FloatParser.formatFloat(this.statusCorrected, "0.0");
    }

    public Float getRawStatusCorrected() {
        return this.statusCorrected;
    }

    public void setStatusCorrected(String statusCorrected) {
        this.statusCorrected = FloatParser.parseStrToFloat(statusCorrected);
    }
}
