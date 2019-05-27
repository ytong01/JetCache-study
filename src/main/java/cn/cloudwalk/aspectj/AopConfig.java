package cn.cloudwalk.aspectj;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {


    @Bean
    public TestPointcut testPointcut() {
        TestPointcut pointcut = new TestPointcut();
        pointcut.setMappedName("test");
        return pointcut;
    }

    @Bean
    public MethodInterceptor testAdvice() {
        return new TestAdvice();
    }

    @Bean
    public TestAdvisor testAdvisor() {
        TestAdvisor advisor = new TestAdvisor();
        advisor.setAdvice(testAdvice());
        advisor.setPointcut(testPointcut());
        return advisor;
    }
}
