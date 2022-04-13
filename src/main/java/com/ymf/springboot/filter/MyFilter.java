package com.ymf.springboot.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author yuanmengfan
 * @date 2021/11/16 10:57 下午
 * @description
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("----进入Filter拦截器---");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
