package com.arcosoft.arcLogChef.aspects;

import com.arcosoft.arcLogChef.exceptions.ChefException;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by princegupta on 04/12/17.
 */

@Aspect
@Component
public class LoggingAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    public static final String JSON_RESPONSE = "**** The JSON response to be returned is : ";
    public static final String JSON_REQUEST = "**** The JSON request received is : ";

    @Around("execution(* com.arcosoft.arcLogChef.resources..*(..))")
    public Object logOutputJSON(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info("logOutputJSON(around) Resource >>>");
        return logOutput(proceedingJoinPoint);

    }

    @AfterThrowing(value = "execution(* com.arcosoft.arcLogChef.resources..*(..))", throwing = "exception")
    public void logChefException(ChefException exception) throws Throwable {
        LOGGER.error("ChefException occured ----->", exception.getCause().getMessage());
    }

    private Object logOutput(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        LOGGER.info("logOutputJson(around) Controller>>> " + methodName);

        String jsonStringUser;
        Gson gson = new Gson();

        if (joinPoint.getArgs().length > 0) {
            jsonStringUser = gson.toJson(joinPoint.getArgs()[0] != null ? joinPoint.getArgs()[0] : "");
            LOGGER.info(JSON_REQUEST + jsonStringUser);
        }
        Object ret = joinPoint.proceed(); // continue on the intercepted method
        jsonStringUser = gson.toJson(ret != null ? ret : "");
        LOGGER.info(JSON_RESPONSE + jsonStringUser);
        return ret;
    }
}
