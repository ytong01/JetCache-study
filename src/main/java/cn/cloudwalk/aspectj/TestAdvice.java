package cn.cloudwalk.aspectj;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TestAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("操作前...");

        Object result = invocation.proceed();
        System.out.println("操作后...");
        return result;
    }
}
