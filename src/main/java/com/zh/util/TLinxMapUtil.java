/**
 * @Filename: TLinxMapUtil.java
 * @Author：caiqf
 * @Date�?014-10-11
 */
package com.zh.util;

import java.util.*;

/**
 * @Class: TLinxMapUtil.java
 * @Description:
 * @Author：caiqf
 * @Date�?014-10-11
 */
@SuppressWarnings("all")
public class TLinxMapUtil {
    private Map map = new HashMap();
    private Set keySet = map.keySet();

    public Object get(String key) {
        return map.get(key);
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public void sort() {
        List list = new ArrayList(map.keySet());

//		Collections.sort(list, new Comparator() {
//			public int compare(Object a, Object b) {
//				return a.toString().compareTo(b.toString());
//			}
//		});

        this.keySet = new TreeSet(list);
    }

    public Set keySet() {
        return this.keySet;
    }


}
