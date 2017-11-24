package com.itmaoo.oa.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class SpringContextUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

    private static ApplicationContext applicationContext;
    private static StringValueResolver resolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        SpringContextUtil.resolver = stringValueResolver;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return applicationContext.getBean(name, clazz);
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBeanByName(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    public static String getValue(String name) {
        if (name == null) {
            return null;
        }
        String key = null;
        if (name.startsWith("${") && name.endsWith("}")) {
            key = name;
        } else {
            key = "${" + name + "}";
        }
        String value = resolver.resolveStringValue(key);
        if (!name.equals(value)) {
            return value;
        }
        return null;
    }
}
