package com.arcosoft.arcLogChef.aspects;

import com.arcosoft.arcLogChef.dto.ActionResult;
import com.arcosoft.arcLogChef.exceptions.ChefException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by princegupta on 06/12/17.
 */

@Aspect
@Component
public class ResponseDecoderAspect {

    @Autowired
    ActionResult result;

    private static Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "execution(* com.arcosoft.arcLogChef.resources..*(..))")
    public Object decodeResponseCode(ProceedingJoinPoint joinPoint) throws Throwable {

        try{
            result = (ActionResult) joinPoint.proceed();
            result.setCode(200);
        }
        catch (ChefException exception){
            result.setCode(500);
            LOGGER.error("Got ChefException ---------> ", exception.getCause().getMessage());
        }

        return result;
    }
}
