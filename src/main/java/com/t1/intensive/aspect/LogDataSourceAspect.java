package com.t1.intensive.aspect;

import com.t1.intensive.service.ErrorLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LogDataSourceAspect {

    private final ErrorLogService service;

    @AfterThrowing(
            pointcut = "@annotation(LogDataSourceError)",
            throwing = "e")
    public void logError(JoinPoint joinPoint, Exception e) {
        log.error("An error occurred while processing {}", joinPoint.getSignature().getName());
        service.saveErrorInfo(e, joinPoint.getSignature().toShortString());
    }
}
