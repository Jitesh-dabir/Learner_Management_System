package com.bl.learningmanagementsystem.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Configuration
public class LoggingConfiguration {

    Logger log = LoggerFactory.getLogger(LoggingConfiguration.class);

    @Pointcut(value = "execution(* com.bl.learningmanagementsystem.service.*.*(..) )")
    public void myPointcut() {
    }

    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        final StopWatch stopWatch = new StopWatch();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        log.info("Method invoked" + className + ":" + methodName + "()"
                + "arguments" + Arrays.toString(array));
        stopWatch.start();
        Object object = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info(" Allowed execution for {}", proceedingJoinPoint);
        log.info(className + ":" + methodName + "()"
                + "Response" + String.valueOf(object));
        log.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
        return object;
    }
}