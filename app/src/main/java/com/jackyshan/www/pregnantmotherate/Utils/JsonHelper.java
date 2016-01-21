package com.jackyshan.www.pregnantmotherate.Utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: League
 * Date: 14-10-22
 * Time: 下午12:00
 * To change this template use File | Settings | File Templates.
 */
public class JsonHelper {

    /**
     * 将json字符串转换成Map
     * @param json json字符串
     * @return     map对象
     */
    public static Map<?, ?> json2Map(String json) {
        try {

//            JSONObject jo = new JSONObject(json);
//
//            return handleJsonObject(jo);

            ObjectMapper mapper = new ObjectMapper();

            Map<?, ?> map = mapper.readValue(json, Map.class);

            return map;


        } catch (Exception ex) {
//            new JsonHelper().logError(ex);
            return null;
        }
    }

    public static List<?> json2List(String json) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            List<?> list = mapper.readValue(json, List.class);

            return list;

        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * 将Map对象转换成接送字符串
     * @param map Map对象
     * @return    Json字符串
     */
    public static String map2Json(Map<String, Object> map) {
        try {
            String result;

            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(map);

            return result;

        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将对象转换成json字符串
     * @param obj ->对象实例
     * @return    ->json字符串
     */
    public static String object2Json (Object obj) {

        try {
            String result;

            ObjectMapper mapper = new ObjectMapper();

            result = mapper.writeValueAsString(obj);

            return result;

        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将json字符串转换成对象
     * @param json json字符串
     * @param cls  对象的类
     * @return     对象的实例
     */
    public static Object json2Object (String json, Class cls) {

        try {

            Object result;

            ObjectMapper mapper = new ObjectMapper();
            result = mapper.readValue(json, cls);

            return result;

        } catch (Exception ex) {
//            new JsonHelper().logError(ex);
            return null;
        }

    }

    public static Object getValueByName(String json, String name) {
        Map map = json2Map(json);
        return getValueByName(map, name);
    }

    public static Object getValueByName(Map map, String name) {

        try {

            Object result = null;

            if (map.containsKey(name)) {

                result = map.get(name);

            } else {

                Object[] values = map.values().toArray();
                for (Object obj : values) {

                    if (obj instanceof Map) {

                        result = getValueByName((Map)obj, name);
                        if (result != null) {
                            break;
                        }

                    } else if (obj instanceof List) {

                        result = getValueByName((List)obj, name);
                        if (result != null) {
                            break;
                        }


                    } else {
                        continue;
                    }
                }
            }

            return result;

        } catch (Exception ex) {
            return null;
        }
    }

    private static Object getValueByName(List list, String name) {

        try {
            Object result = null;
            for (Object obj : list) {

                if (obj instanceof Map) {

                    result = getValueByName((Map)obj, name);
                    if (result != null) {
                        break;
                    }

                } else if (obj instanceof List) {

                    result = getValueByName((List)obj, name);
                    if (result != null) {
                        break;
                    }

                } else {
                    continue;
                }

            }

            return result;

        } catch (Exception ex) {
            return null;
        }
    }
}
