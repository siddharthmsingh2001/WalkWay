package com.walkway.account_service.feign.interceptor;

import com.walkway.account_service.feign.handler.FeignHttpExceptionHandler;
import com.walkway.account_service.feign.handler.HandleFeignError;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class FeignInterceptor implements RequestInterceptor {

    private final ApplicationContext applicationContext;
    private final Map<String, FeignHttpInterceptorHandler> interceptorHandlerMap = new ConcurrentHashMap<>();

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> feignClients = applicationContext.getBeansWithAnnotation(FeignClient.class);
        List<Method> clientMethods = feignClients.values().stream()
                .map(Object::getClass)
                .map(aClass -> aClass.getInterfaces()[0])
                .map(ReflectionUtils::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .toList();
        for (Method method : clientMethods) {
            String configKey = Feign.configKey(method.getDeclaringClass(), method);
            AddFeignInterceptor annotation = getAddFeignInterceptorAnnotation(method);

            if (annotation != null) {
                FeignHttpInterceptorHandler handler = applicationContext.getBean(annotation.value());
                interceptorHandlerMap.put(configKey, handler);
            }
        }
    }

    private AddFeignInterceptor getAddFeignInterceptorAnnotation(Method method){
        AddFeignInterceptor result = method.getAnnotation(AddFeignInterceptor.class);
        if(result == null){
            result = method.getDeclaringClass().getAnnotation(AddFeignInterceptor.class);
        }
        return result;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String methodKey = requestTemplate.methodMetadata().configKey();
        FeignHttpInterceptorHandler handler = interceptorHandlerMap.get(methodKey);
        if(handler!=null){
            handler.apply(requestTemplate);
        }
    }
}
