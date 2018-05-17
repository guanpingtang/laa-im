package com.dipsy.laa.common.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * fm工具类
 *
 * @auther cuiqiongyu
 * @create 2018/5/14 21:44
 */
@Slf4j
public class FreeMarkerUtil {

    public static String parse(String context, Object object) {
        if (StringUtils.isEmpty(context)) {
            return context;
        }

        StringTemplateLoader stringLoader = new StringTemplateLoader();
        Configuration cfg = new Configuration();
        cfg.setTemplateLoader(stringLoader);
        stringLoader.putTemplate("myTemplate", context);

        try {
            Template template = cfg.getTemplate("myTemplate", "utf-8");
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, object);
        } catch (Exception e) {
            log.error("freeMarker模板分析失败" + context, e);
            return null;
        }
    }

}
