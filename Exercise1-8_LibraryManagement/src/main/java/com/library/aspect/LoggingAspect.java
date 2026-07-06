package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Exercise 3: LoggingAspect - Logs method execution times using Spring AOP.
 * Exercise 8: Basic AOP with Before and After advice for logging and transaction management.
 */
@Aspect
@Component
public class LoggingAspect {

    // Exercise 3: Around advice to log execution time
    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("[AOP] " + joinPoint.getSignature().toShortString()
                + " executed in " + executionTime + " ms");

        return result;
    }

    // Exercise 8: Before advice
    @Before("execution(* com.library.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[AOP - Before] Entering method: "
                + joinPoint.getSignature().getName());
    }

    // Exercise 8: After advice
    @After("execution(* com.library.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AOP - After] Exiting method: "
                + joinPoint.getSignature().getName());
    }

    // Exercise 8: AfterReturning advice
    @AfterReturning(pointcut = "execution(* com.library.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AOP - AfterReturning] Method: "
                + joinPoint.getSignature().getName()
                + " returned: " + result);
    }

    // Exercise 8: AfterThrowing advice
    @AfterThrowing(pointcut = "execution(* com.library.service.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("[AOP - AfterThrowing] Method: "
                + joinPoint.getSignature().getName()
                + " threw exception: " + exception.getMessage());
    }
}
