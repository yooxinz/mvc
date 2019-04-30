package com.yooxinz.utils;

import java.lang.reflect.Field;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-04-30
 */
public class ObjectHelper {

    public static boolean isEmpty(Object o){
        Class target = o.getClass();

        Field[] fs = target.getDeclaredFields();
        Boolean flag = true;
        try{
            for (Field f: fs){
                f.setAccessible(true);
                Object val = f.get(o);

                if(val != null){
                    flag =false;
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
