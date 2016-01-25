package com.jackyshan.www.pregnantmotherate.General.Base;

import com.activeandroid.Model;
import com.jackyshan.www.pregnantmotherate.General.Config.LogUtil;
import com.jackyshan.www.pregnantmotherate.Utils.DateUtil;
import com.jackyshan.www.pregnantmotherate.Utils.JsonHelper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jackyshan on 15/5/8.
 */
public class BaseModel implements Serializable {

    public BaseModel() {

    }

    protected void logMsg(String msg) {
        LogUtil.LogMsg(this.getClass(), msg);
    }

    protected void logErr(Exception ex) {
        LogUtil.LogErr(this.getClass(), ex);
    }

    /**
     * set model by jsonString, support multi level jsonString
     * @param jsonString    the jsonString
     * @param level         witch level is the model's json
     * @param levelNames    all elementNames(from top) of the jsonString, the length must be equals level
     */
    public void setModelByJson(String jsonString, int level, String[] levelNames) {

        try {
            if (level == 0) {
                setModelByJson(jsonString);
                return;
            }

            if (level != levelNames.length) {
                return;
            }

            Map jsonMap = JsonHelper.json2Map(jsonString);
            while (level > 0) {

                level--;
                String elementName = levelNames[level];
                Map insideMap = (Map) jsonMap.get(elementName);
                jsonMap = insideMap;
                if (level == 0) {
                    setModelByMap(jsonMap);
                    return;
                }
            }

        } catch (Exception ex) {
            logErr(ex);
        }

    }

    public void setModelByJson(String jsonString) {
        Map map = JsonHelper.json2Map(jsonString);
        setModelByMap(map);

    }

    public void setModelByMap(Map map) {

        try {
            if (map == null) {
                return;
            }

            Field[] fields = getFields();

            for (Field field : fields) {
                try {

                    if (!map.containsKey(field.getName())) {
                        continue;
                    }

                    Object value = map.get(field.getName());

                    if (value == null) {continue;}

                    Class<?> type = field.getType();

                    if (type.equals(String.class)) {

                        String val = value.toString();
                        field.set(this, val);

                    } else if (type.equals(Date.class)) {

                        if (value instanceof Date) {
                            field.set(this, value);
                            continue;
                        }
                        String val = value.toString();
                        if (val == null)
                            continue;

                        Date date = null;
                        try {
                            long l = Long.valueOf(val);
                            date = new Date(l*1000);

                        } catch (Exception e) {
                        }
                        if (date == null) {
                            date = DateUtil.String2Date(val, "yyyy-MM-dd HH:mm:ss");
                            if (date == null) {
                                date = DateUtil.String2Date(val, "yyyy-MM-dd HH:mm");
                                if (date == null) {
                                    date = DateUtil.String2Date(val, "yyyy-MM-dd");
                                }
                            }
                        }
                        field.set(this, date);

                    } else if (type.equals(int.class) || type.equals(Integer.class)) {

                        String val = value.toString();
                        if (val == null)
                            continue;
                        int i = Integer.valueOf(val);
                        field.set(this, i);

                    } else if (type.equals(long.class) || type.equals(Long.class)) {

                        String val = value.toString();
                        if (val == null) {
                            continue;
                        }
                        long l = Long.valueOf(val);
                        field.set(this, l);

                    } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {

                        String val = value.toString();
                        if (val == null)
                            continue;
                        boolean b = Boolean.valueOf(val);
                        field.set(this, b);
                    } else if (type.equals(double.class) || type.equals(Double.class)) {

                        String val = value.toString();
                        if (val == null)
                            return;
                        double num = Double.valueOf(val);
                        field.set(this, num);
                    } else if (type.equals(float.class) || type.equals(Float.class)) {

                        String val = value.toString();
                        if (val == null)
                            return;
                        float num = Float.valueOf(val);
                        field.set(this, num);
                    } else if (value instanceof Map ) {

                        if (Map.class.isAssignableFrom(type) || type.equals(Object.class)) {
                            field.set(this, value);
                        } else if (BaseModel.class.isAssignableFrom(type)) {
                            BaseModel baseModel = (BaseModel) field.get(this);
                            if (baseModel == null)
                                baseModel = (BaseModel) type.newInstance();
                            baseModel.setModelByMap((Map) value);
                        }

                    } else if (value instanceof List ) {

                        if (List.class.isAssignableFrom(type)) {
                            //获取泛型类型
                            Type genericType = field.getGenericType();

                            if (genericType != null && genericType instanceof ParameterizedType) {
                                try {
                                    ParameterizedType parameterizedType = (ParameterizedType) genericType;
                                    Class cls = (Class) parameterizedType.getActualTypeArguments()[0];
                                    if (cls != null && BaseModel.class.isAssignableFrom(cls)) {

                                        List list = (List) value;
                                        List modelList = new ArrayList();
                                        boolean flag = true;
                                        for (int i = 0; i < list.size(); i++) {
                                            Object obj = list.get(i);
                                            if (obj instanceof Map) {
                                                BaseModel baseModel = (BaseModel) cls.newInstance();
                                                baseModel.setModelByMap((Map) obj);
                                                modelList.add(baseModel);
                                            } else {
                                                field.set(this, value);
                                                flag = false;
                                                break;
                                            }
                                        }
                                        if (flag) {
                                            field.set(this, modelList);
                                        }


                                    } else {
                                        field.set(this, value);
                                    }

                                } catch (Exception e) {
                                    field.set(this, value);
                                }

                            } else {
                                field.set(this, value);
                            }
                        } else {
                            field.set(this, value);
                        }
                    }

                } catch (Exception ex) {
                    logErr(ex);
                }
            }



        } catch (Exception ex) {
            logErr(ex);
        }

    }

    public Field[] getFields() {

        try {

            Class cls = this.getClass();

            Field[] fields = cls.getDeclaredFields();

            return fields;

        } catch (Exception ex) {
            logErr(ex);
            return null;
        }

    }

    //获取所有的属性名
    public String[] getPropertyNames() {
        try {

            Field[] fields = getFields();

            String[] result = new String[fields.length];

            for (int i = 0; i < fields.length; i++) {
                result[i] = fields[i].getName();
            }

            return result;

        } catch (Exception ex) {
            logErr(ex);
            return null;
        }
    }

    //获取所有属性的值
    public Object[] getValues() {
        try {

            //先获取实例中的所有属性名
            Field[] fields = getFields();

            int length = fields.length;

            Object []values = new Object[length];

            for (int i = 0; i < length; i++) {
                //获取属性的get方法
                Object obj = fields[i].get(this);

                values[i] = obj;
            }

            return values;

        } catch (Exception ex) {
            logErr(ex);
            return null;
        }
    }

    public Object getValueByName (String name) {

        try {
            Field field = this.getClass().getField(name);

            Object obj = field.get(this);
            return obj;

        } catch (Exception ex) {
            logErr(ex);
        }
        return null;
    }

    public String toJsonString() {
        try {
            return JsonHelper.map2Json(toMap());
        }catch (Exception ex) {
            logErr(ex);
        }
        return null;
    }

    public Map<String, Object> toMap() {

        try {

            Map<String, Object> resultMap = new HashMap<String, Object>();

            Field[] fields = getFields();

            for (Field field : fields) {

                String key = field.getName();
                Object value = field.get(this);

                resultMap.put(key, value);
            }

            return resultMap;

        } catch (Exception ex) {
            logErr(ex);
        }
        return null;
    }
}