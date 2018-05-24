package com.dipsy.laa.common.util;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;
import static org.dozer.loader.api.TypeMappingOptions.mapEmptyString;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * 1. 持有Mapper的单例.
 * 2. 返回值类型转换.
 * 3. 批量转换Collection中的所有对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 *
 * @auther cuiqiongyu
 * @create 2018/5/24 13:05
 */
public class BeanMapper {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    static {
        List<String> myMappingFiles = new ArrayList<>();
        myMappingFiles.add("DozerMapping.xml");
        dozer.setMappingFiles(myMappingFiles);
    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    public static <T> T mapAndForce(final Object source, final Class<T> destinationClass, String... filedName) {
        if (source == null) {
            return null;
        }

        if (filedName == null || filedName.length == 0) {
            return dozer.map(source, destinationClass);
        }

        final String srcName;
        final String destName;
        if (filedName.length == 1) {
            srcName = filedName[0];
            destName = filedName[0];
        } else {
            srcName = filedName[0];
            destName = filedName[1];
        }
        DozerBeanMapper dozerTem = new DozerBeanMapper();
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(source.getClass(), destinationClass)
                        .fields(srcName, destName, copyByReference());

            }
        };
        dozerTem.addMapping(builder);

        return dozerTem.map(source, destinationClass);
    }

    public static <T> T mapAndExclude(Object source, Class<T> destinationClass,
                                      String... excludes) {
        if (source == null) {
            return null;
        }
        DozerBeanMapper dozerTem = getDozerBeanMapper(source, destinationClass, excludes);
        return dozerTem.map(source, destinationClass);
    }

    private static <T> DozerBeanMapper getDozerBeanMapper(final Object source, final Class<T> destinationClass, final String[] excludes) {
        DozerBeanMapper dozerTem = dozer;
        if (excludes != null && excludes.length >= 1) {
            dozerTem = new DozerBeanMapper();
            BeanMappingBuilder builder = new BeanMappingBuilder() {
                protected void configure() {
                    TypeMappingBuilder typeMappingBuilder = mapping(source.getClass(), destinationClass);
                    for (String exclude : excludes) {
                        typeMappingBuilder.exclude(exclude);
                    }
                }
            };
            dozerTem.addMapping(builder);
        }
        return dozerTem;
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> mapList(Collection sourceList,
                                      Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        if (sourceList == null) {
            return null;
        }
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    public static <T> List<T> mapListAndExclude(Collection sourceList, Class<T> destinationClass, String... excludes) {
        if (sourceList == null || sourceList.size() == 0) {
            return null;
        }

        List<T> destinationList = Lists.newArrayList();
        DozerBeanMapper dozerTem = getDozerBeanMapper(sourceList.toArray()[0], destinationClass, excludes);

        for (Object sourceObject : sourceList) {
            T destinationObject = dozerTem.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

    public static void copyWithOutNull(final Object source, final Object destinationObject) {

        if (source == null || destinationObject == null) {
            return;
        }

        DozerBeanMapper copyDozer = new DozerBeanMapper();
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(source.getClass(), destinationObject.getClass(), mapNull(false), mapEmptyString(false));
            }
        };
        copyDozer.addMapping(builder);
        copyDozer.map(source, destinationObject);
    }

    public static void copyAndExclude(Object source, Object destinationObject, String... excludes) {
        if (source == null || destinationObject == null) {
            return;
        }

        DozerBeanMapper copyDozer = getDozerBeanMapper(source, destinationObject.getClass(), excludes);
        copyDozer.map(source, destinationObject);
    }

}