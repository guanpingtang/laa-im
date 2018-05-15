package com.dipsy.laa.common.base;

import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @auther cuiqiongyu
 * @create 2018/5/14 21:39
 */
public class DateSerializer implements JsonSerializer<Date> {

    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getTime());
    }

}
