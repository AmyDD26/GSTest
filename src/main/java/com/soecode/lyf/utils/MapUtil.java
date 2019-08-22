package com.soecode.lyf.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author zun_love
 */
public class MapUtil {
    /**
     * 根据map中的某个key 去除List中重复的map
     * @author  shijing
     * @param list
     * @param mapKey
     * @return
     */
    public static List<Map<String, Object>> removeRepeatMapByKey(List<Map<String, Object>>
                                                                         list, String mapKey){
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        //把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Map> msp = new HashMap<>();
        for(int i = list.size()-1 ; i>=0; i--){
            Map map = list.get(i);
            String id = (String)map.get(mapKey);
            map.remove(mapKey);
            msp.put(id, map);
        }
        //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
        Set<String> mspKey = msp.keySet();
        for(String key: mspKey){
            Map newMap = msp.get(key);
            newMap.put(mapKey, key);
            listMap.add(newMap);
        }
        return listMap;
    }

}
