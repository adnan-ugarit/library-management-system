package com.maids.LibraryManagementSystem.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Adnan
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Log method entry
    @Before("execution(* com.maids.LibraryManagementSystem.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    // Log method exit
    @AfterReturning(pointcut = "execution(* com.maids.LibraryManagementSystem.service.*.*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", joinPoint.getSignature(), result);
    }

    // Log exceptions
    @AfterThrowing(pointcut = "execution(* com.maids.LibraryManagementSystem.service.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: {} with cause: {}", joinPoint.getSignature(), error.getCause());
    }
    
}
