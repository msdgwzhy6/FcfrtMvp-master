package com.fcfrt.baselib.help;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 项目名称：
 * 类名称：FcfrtJsonHelper.java
 * 类描述：
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:15
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
public class FcfrtJsonHelper {
    /**
     * 标记.
     */
    private static final String TAG = "FcfrtJsonUtil";
    /**
     * gson.
     */
    private static Gson gson;

    /**
     * gson初始化块.
     */
    static{
        GsonBuilder gsonBuilder = new GsonBuilder();
        //注册TimeStamp序列化适配器
        gson =  gsonBuilder.create();
    }

    /***
     * json转对象.
     *
     * @param jsonString json
     * @param clazz class
     * @param <T> 泛型
     * @return Java Object
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz){
        T t = null;
        try{
            t = gson.fromJson(jsonString, clazz);
        }catch (Exception e){
            Log.e(TAG, "parseObject: " + e.getMessage(), e);
        }
        return t;
    }
    /***
     * json转对象.
     *
     * @param jsonString json
     * @param clazz class
     * @param <T> 泛型
     * @return Java Object
     */
    public static <T> T parseObject(String jsonString, final TypeToken<T> clazz){
        T t = null;
        try{
            t = gson.fromJson(jsonString, clazz.getType());
        }catch (Exception e){
            Log.e(TAG, "parseObject: " + e.getMessage(), e);
        }
        return t;
    }

    /**
     *json字符转对象.
     * @param jsonString jsonString
     * @return json对象
     */
    public static JSONObject parseObject(String jsonString){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(jsonString);
        }catch (Exception e){
            Log.e(TAG, "parseObject: " + e.getMessage(), e);
        }
        return jsonObject;
    }

    /***
     * json转list.
     *
     * @param jsonString json string
     * @param clazz class
     * @param <T> 泛型
     * @return Java List
     */
    public static <T> List<T> parseArray(String jsonString, Class<T> clazz){
        try {
            Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
            ArrayList<JsonObject> jsonObjs = gson.fromJson(jsonString, type);

            ArrayList<T> listOfT = new ArrayList<>();
            for (JsonObject jsonObj : jsonObjs) {
                listOfT.add(gson.fromJson(jsonObj, clazz));
            }

            return listOfT;
        }catch (Exception e){
            Log.e(TAG, "parseArray: " + e.getMessage(), e);
            return  null;
        }
    }

    /**
     * 数据.
     * @param jsonString jsonString
     * @param type type
     * @param <T> <T>
     * @return 结果
     */
    public static <T> List<T> parseArray(String jsonString, Type type){
        List<T> list = new ArrayList<T>();
        list = gson.fromJson(jsonString, type);
        return list;
    }

    /**
     * List转JSON
     * @param list
     * @param <T>
     * @return
     */
    public static <T> String parseJson(List<T> list){

        return gson.toJson(list);
    }

    /***
     * json string to List<Map<String, Object>>.
     *
     * @param jsonString jsonString
     * @return 结果
     */
    public static List<Map<String, Object>> parseToListMaps(String jsonString){
        List<Map<String, Object>> list = null ;
        list = gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>(){
                }.getType());
        return list;
    }

    /***
     * Java Objectתjson.
     *
     * @param object object
     * @return 结果
     */
    public static String toJSONString(Object object){
        String gsonString = gson.toJson(object);
        return gsonString;
    }

}
