package com.example.myapplication.utils;

import java.text.DecimalFormat;


public class FloatParser {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    // String转float，异常值置为null
    public static Float parseStrToFloat(String strFloat) {
        Float result;
        try {
            result = Float.parseFloat(strFloat);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    // float转String，保留两位小数
    public static String formatFloat(Float f) {
        decimalFormat.applyPattern("0.00");
        return f == null ? null : decimalFormat.format(f);
    }

    // float转String，format格式由参数自定义
    public static String formatFloat(Float f, String pattern) {
        decimalFormat.applyPattern(pattern);
        return f == null ? null : decimalFormat.format(f);
    }
}
