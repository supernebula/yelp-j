package evol.util;

import com.google.gson.Gson;
import java.util.List;

public class JSONUtil {

    public static <T> T parseObject(String text, Class<T> classT){
        T obj = com.alibaba.fastjson.JSON.parseObject(text, classT);
        return obj;
    }

    public static <T> List<T> parseArray(String text, Class<T> classT){
        List<T> array = com.alibaba.fastjson.JSON.parseArray(text, classT);
        return array;
    }

    public static <T> String parse2Json(T obj, Class<T> classT){

        String jsonStr = com.alibaba.fastjson.JSON.toJSONString(obj);
        return jsonStr;

//        Gson gson = new Gson();
//        String jsonStr = gson.toJson(obj, classT.getClass());
//        return jsonStr;

    }

    public static <T> String parse2Json(List<T> obj, Class<T> classT){

        String jsonStr = com.alibaba.fastjson.JSON.toJSONString(obj);
        return jsonStr;

//        Gson gson = new Gson();
//        String jsonStr = gson.toJson(obj, classT.getClass());
//        return jsonStr;

    }

}
