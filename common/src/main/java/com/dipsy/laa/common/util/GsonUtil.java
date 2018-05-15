package com.dipsy.laa.common.util;

import com.dipsy.laa.common.base.DateDeserializer;
import com.dipsy.laa.common.base.DateSerializer;
import com.google.gson.*;

import java.io.Reader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;

public class GsonUtil {

    private static Gson gson = null;

    static{
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(Double.class, new DoubleDeserializer());
        gson = gb.setExclusionStrategies(new SetterExclusionStrategy()).create();
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Class<T> classOfT, Type... classOfU) {
        Type objectType = type(classOfT, classOfU);
        return gson.fromJson(json, objectType);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(Reader json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T[] toArray(String json, Class<T> classOfT) {
        Class<?> aClass = Array.newInstance(classOfT, 0).getClass();
        return (T[]) gson.fromJson(json, aClass);
    }

    public static <T> List<T> toList(String json, Class<T> classOfT) {
        T[] array = toArray(json, classOfT);
        return Arrays.asList(array);
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static String toJsonAndExclude(Object src, final String... fieldList) {
        if (fieldList == null || fieldList.length == 0) {
            return toJson(src);
        }

        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                for (String field : fieldList) {
                    if (field.equals(f.getName())) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
        return gson.toJson(src);
    }


    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    private static class SetterExclusionStrategy implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            Exclude exclude = f.getAnnotation(Exclude.class);
            return exclude != null;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Exclude {

    }

    private static class DoubleDeserializer implements JsonDeserializer<Double> {

        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String str = json.getAsString();
            if (str != null && !"".equals(str)) {
                return Double.valueOf(str);
            }
            return null;
        }
    }

}
