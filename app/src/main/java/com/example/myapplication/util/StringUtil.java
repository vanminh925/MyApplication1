package com.example.myapplication.util;

import java.text.DecimalFormat;

public class StringUtil {
    public static String convertPriceToDisplay(double price){
        DecimalFormat dFormat = new DecimalFormat("####,###,###");
        return dFormat.format(price);
    }

    public static String formatProductName(String name){
        return name.length()>40?name.substring(0,37)+"...": name;
    }

    public static String formatProductCategory(String name){
        return name.length()>20?name.substring(0,17)+"...": name;
    }
}
