package com.walkway.account_service.feign.handler;

import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;
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

@Component
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    private final ApplicationContext applicationContext;
    private final Map<String, FeignHttpExceptionHandler> exceptionHandlerMap = new HashMap<>();
    private final Default defaultDecoder = new Default();

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
            HandleFeignError annotation = getHandleFeignErrorAnnotation(method);

            if (annotation != null) {
                FeignHttpExceptionHandler handler = applicationContext.getBean(annotation.value());
                exceptionHandlerMap.put(configKey, handler);
            }
        }
    }

    private HandleFeignError getHandleFeignErrorAnnotation(Method method){
        HandleFeignError result = method.getAnnotation(HandleFeignError.class);
        if(result == null){
            result = method.getDeclaringClass().getAnnotation(HandleFeignError.class);
        }
        return result;
    }

    @Override
    public Exception decode(String methodKey, Response response){
        FeignHttpExceptionHandler handler = exceptionHandlerMap.get(methodKey);
        if(handler!=null){
            return handler.handle(response);
        }
        return defaultDecoder.decode(methodKey, response);
    }
}
