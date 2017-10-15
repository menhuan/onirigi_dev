package com.manage.base;

import com.manage.interceptor.LoginRequiredInterceptor;
import com.manage.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ASUS on 2017/9/23.
 */
@Component
public class HandleWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    PassportInterceptor  passportInterceptor;

    @Autowired
    LoginRequiredInterceptor  loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/msg/*")
                .addPathPatterns("/like").addPathPatterns("dislike");
        super.addInterceptors(registry);

    }

}
