package com.example.myapplication.model;

public class MainActivityTimeBean {

    private String time;
    private boolean isTimeRunning = false;

    public MainActivityTimeBean(String time) {
        this.time = time;
    }

    public void switchTimeRunning() {
        isTimeRunning = !isTimeRunning;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeRunningText() {
        return isTimeRunning? "时钟运行": "时钟停止";
    }

    public boolean isTimeRunning() {
        return isTimeRunning;
    }

    public void setTimeRunning(boolean timeRunning) {
        isTimeRunning = timeRunning;
    }
}
