package org.leon.finch.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * json相关方法
 *
 * @author Leon Song
 * @date 2020-11-14
 */
public class JsonUtil {

    public final static String EMPTY_JSON_ARRAY = "[]";

    public final static String EMPTY_JSON_OBJECT = "{}";

    /**
     * 将json串序列化为对象
     */
    public static <T> T toObject(String content, Class<T> clazz) {

        return JSON.parseObject(content, clazz, new Feature[0]);

    }

    /**
     * 将对象序列化，对于null值也序列化
     */
    public static String toJson(Object obj) {

        return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);

    }

    /**
     * 将对象反序列化为数组对象
     */
    public static <T> List<T> toArray(String content, Class<T> clazz) {

        return JSON.parseArray(content, clazz);

    }

    /**
     * 用于处理泛化对象
     * 例如  List<T>
     */
    public static <T> T toGenericObject(String content, TypeReference<T> typeReference) {

        return JSON.parseObject(content, typeReference);

    }

    /**
     * 对象转成map
     */
    public static Map<String, Object> objectToMap(Object object) {

        String content = toJson(object);

        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
        };

        return toGenericObject(content, typeReference);
    }

}
