package com.t1.intensive.aspect;

import com.t1.intensive.service.ErrorLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LogDataSourceAspect {

    private final ErrorLogService service;

    @AfterThrowing(
            pointcut = "@annotation(com.t1.intensive.annotation.LogDataSourceError)",
            throwing = "e")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logError(JoinPoint joinPoint, Exception e) {
        log.error("An error occurred while processing {}", joinPoint.getSignature().getName());
        try {
            service.sendError(e, joinPoint.getSignature().toShortString());
        } catch (Exception exc) {
            log.error("An error occurred while trying to send event to kafka {}", joinPoint.getSignature().getName());
            service.saveErrorInfo(exc, joinPoint.getSignature().toShortString());
        }
    }
}
