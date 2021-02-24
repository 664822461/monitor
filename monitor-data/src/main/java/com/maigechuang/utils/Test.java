package com.maigechuang.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mgc on 2020/11/2.
 */
public class Test {

    public static void main(String[] args) {
       /* String s = "http://ww.baidu.com";//目标字符串
        //String regex = "\\b([A-Z][a-z]*\\s{0,1})*[A-Za-z]$";//正则表达式
        String regex = "^(http|https)://[\\w]{1,}(?:\\.?[\\w]{1,})+[\\w-_/?&=#%:]*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        if (m.matches()) {
            System.out.println("匹配");
        } else {
            System.out.println("不匹配");
        }*/
        System.out.println(count(15,48));
    }

    public static String count(int x,int y ){

        double temp1 = x * 1.0;
        double temp2 = y * 1.0;
        double temp3 = (x - y)/y;
        DecimalFormat df1 = new DecimalFormat("##.00%");
        String temp =  df1.format(temp3);
        if(temp.charAt(0) == '-'){
            return "减少"+ temp.substring(1);
        }else {
            return "增长" + temp;
        }

    }
}
