package com.ymf.springboot.component;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author yuanmengfan
 * @date 2021/11/4 10:52 下午
 * @description
 * SpringBoot自动注入了一个LocaleResolver的Bean
 * 条件为@ConditionalOnMissingBean(name = DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME)
 * 如果ioc容器中没有这样名字的bean 那么默认配置就不会被替代
 * 所以这里我们创建Bean的名称要是DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME 才能正真替换掉SpringBoot帮我们自动配置的LocaleResolver
 */
@Component(DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME)
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String locale = request.getParameter("locale");
        Locale result = Locale.getDefault();
        //判断locale这个参数是否存在，如果不存在还是返回当前请求头中的位置信息
        //如果包括locale这个参数 我们将其设置为使用的语言
        if (locale != null && !"".equals(locale)) {
            String[] split = locale.split("_");
            result =  new Locale(split[0], split[1]);
        }
        return result;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
