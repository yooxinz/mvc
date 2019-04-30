package com.yooxinz.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-04-30
 */
public final class CollectionHelper {
    private final static Logger logger = LoggerFactory.getLogger(CollectionHelper.class);

    /**
     * 判断集合是否为空
     * @param c
     * @return
     */
    public static boolean isEmpty(Collection<?> c) {
        return null == c || c.size() == 0;
    }

    /**
     * 去除重复元素
     *
     * @param list 需要处理的list
     * @param <T> 泛型方法
     * @return 去重后的list
     */
    public static <T> List<T> removeDuplicate(List<T> list) {
        if (list == null || list.size() == 0) {
            logger.error("list is empty or is null");
            return new ArrayList<>();
        }
        return new ArrayList<>(new HashSet<>(list));

    }

    /**
     * 求俩个集合的并集
     */
    public static <T> List<T> unicon(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    /**
     * 求俩个集合的交集
     *
     * @param set1 set
     * @param set2 set
     * @param <T> 泛型
     * @return 交集
     */
    public static <T> Set<T> unicon(Set<T> set1, Set<T> set2) {
        set1.addAll(set2);
        return set1;
    }

    /**
     * 求俩个集合的交集
     *
     * @param queue1 队列
     * @param queue2 队列
     * @param <T> 泛型
     * @return 交集
     */
    public static <T> Queue<T> unicon(Queue<T> queue1, Queue<T> queue2) {
        queue1.addAll(queue2);
        return queue1;
    }

    /**
     * 求俩个map的交集
     *
     * @param map1 map
     * @param map2 map
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 交集
     */
    public static <K, V> Map<K, V> unicon(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> map = new HashMap<>(map1.size() + map2.size());
        map.putAll(map1);
        map.putAll(map2);
        return map;
    }

    /**
     * 将List以separator链接并以字符串的形式返回
     *
     * @param collection collection
     * @param separator 连接符
     * @param <T> 泛型
     * @return 差集
     */
    public static <T> String join(Collection<T> collection, String separator) {
        StringBuilder sb = new StringBuilder();
        for (T t : collection) {
            sb.append(t.toString()).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }


    /**
     * 将Map以separator链接并以字符串的形式返回
     *
     * @return 字符串
     */
    public static <K, V> String join(Map<K, V> map, String separator, String separator1) {
        if (map == null || map.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            sb.append(String.valueOf(entry.getKey())).append(separator1)
                    .append(String.valueOf(entry.getValue())).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }

    /**
     * 将map的key以separator链接并以字符串的形式返回
     *
     * @param map map
     * @param separator 连接符
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 字符串
     */
    public static <K, V> String keyJoin(Map<K, V> map, String separator) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            sb.append(String.valueOf(entry.getKey())).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }

    /**
     * 将map的value以separator链接并以字符串的形式返回
     *
     * @param map map
     * @param separator 连接符
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 字符串
     */
    public static <K, V> String valueJoin(Map<K, V> map, String separator) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<K, V> entry : map.entrySet()) {
            sb.append(String.valueOf(entry.getValue())).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }
}
