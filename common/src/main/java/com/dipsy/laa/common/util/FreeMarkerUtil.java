package com.dipsy.laa.common.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * fm工具类
 *
 * @auther cuiqiongyu
 * @create 2018/5/14 21:44
 */
public class FreeMarkerUtil {

    private final static Logger logger = LoggerFactory.getLogger(FreeMarkerUtil.class);

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
            logger.error("freeMarker模板分析失败" + context, e);
            return null;
        }
    }

}
