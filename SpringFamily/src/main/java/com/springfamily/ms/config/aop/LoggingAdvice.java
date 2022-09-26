package com.springfamily.ms.config.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    @Pointcut(value = "@annotation(com.springfamily.ms.config.aop.LogTime)")
    public void loggingAnnotationPointCut() {
    }

    @Around("loggingAnnotationPointCut()")
    public Object annotationLogger(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();

        ObjectMapper mapper = new ObjectMapper();
        Object[] array = pjp.getArgs();

        log.info("method invoked " + className + " : " + methodName + "()" + "arguments : " + mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        log.info(className + " : " + methodName + "()" + "Response : " + mapper.writeValueAsString(object));
        return object;
    }

    //AOP expression for all the service methods
    @Around("execution(* com.springfamily.ms.service..*(..)))")
    public Object logExecutionTimeForAllService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        log.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }
}
