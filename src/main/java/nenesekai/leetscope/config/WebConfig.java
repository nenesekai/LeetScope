package nenesekai.leetscope.config;

import nenesekai.leetscope.intercepter.StudentInterceptor;
import nenesekai.leetscope.intercepter.TeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import nenesekai.leetscope.intercepter.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TeacherInterceptor teacherInterceptor;
    @Autowired
    private StudentInterceptor studentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/assignment/create")
                .addPathPatterns("/assignment/update");
        registry.addInterceptor(studentInterceptor)
                .addPathPatterns("/hello/sayHello");
    }
}