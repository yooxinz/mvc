package com.yooxinz.utils;

import com.yooxinz.enums.RegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-04-30
 */
public class RegexHelper {

    public static Boolean match(String value,RegexEnum regex){
        Pattern pattern = Pattern.compile(regex.getPattern());
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

}
