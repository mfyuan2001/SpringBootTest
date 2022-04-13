package com.ymf.springboot.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author yuanmengfan
 * @date 2021/11/15 11:06 下午
 * @description
 * 给容器中加入我们自己定义的ErrorAttributes
 */
@Component
public class MyErrorAttributes  extends DefaultErrorAttributes {
    //返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("company", "yuanmengfan");
        //我们的异常处理器携带的数据都可以在这里拿到
        Map<String, Object> myCode = (Map<String, Object>) webRequest.getAttribute("myCode", RequestAttributes.SCOPE_REQUEST);
        if(myCode!=null){
            errorAttributes.putAll(myCode);
        }
        return errorAttributes;
    }
}
