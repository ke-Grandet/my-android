package com.example.myapplication.utils;


public class IntegerParser {

    // String转int，异常值置为null
    public static Integer parseStrToInteger(String str) {
        Integer result;
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
}
