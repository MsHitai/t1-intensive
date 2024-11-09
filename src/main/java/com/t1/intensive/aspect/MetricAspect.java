package com.t1.intensive.aspect;

import com.t1.intensive.annotation.Metric;
import com.t1.intensive.model.dto.ErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.t1.intensive.util.ConstantsUtil.METRICS;
import static com.t1.intensive.util.KafkaUtils.getKafkaKey;

@Async
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class MetricAspect {

    private static final AtomicLong START_TIME = new AtomicLong();

    private final KafkaTemplate<String, ErrorDto> kafkaTemplate;

    @Before("@annotation(com.t1.intensive.annotation.Metric)")
    public void logExecTime(JoinPoint joinPoint) {
        log.info("Старт метода: {}", joinPoint.getSignature().toShortString());
        START_TIME.addAndGet(System.currentTimeMillis());
    }

    @After("@annotation(metric)")
    public void calculateTime(JoinPoint joinPoint, Metric metric) {
        long afterTime = System.currentTimeMillis();
        logExecutionTime(afterTime, START_TIME.get());
        long resultTime = afterTime - START_TIME.get();
        log.info("ResultTime = {} and limit is {}", resultTime, metric.limit());
        if (resultTime > metric.limit()) {
            List<Header> headers = new ArrayList<>();
            headers.add(new RecordHeader("error_type", "METRICS".getBytes(StandardCharsets.UTF_8)));
            String key = getKafkaKey();
            ErrorDto error = new ErrorDto();
            error.setMessage("Execution time: " + resultTime + ". Method arguments: " + Arrays.toString(joinPoint.getArgs()));
            error.setMethodSignature(joinPoint.getSignature().toShortString());
            error.setCreatedOn(LocalDateTime.now());
            ProducerRecord<String, ErrorDto> record = new ProducerRecord<>(METRICS, 0, key, error, headers);
            kafkaTemplate.send(record);
        }
        START_TIME.set(0L);
    }

    @Around("@annotation(com.t1.intensive.annotation.Metric)")
    public Object logExecTime(ProceedingJoinPoint pJoinPoint) throws Throwable {
        log.info("Вызов метода: {}", pJoinPoint.getSignature().toShortString());
        long beforeTime = System.currentTimeMillis();
        Object result;
        try {
            result = pJoinPoint.proceed();
        } finally {
            long afterTime = System.currentTimeMillis();
            logExecutionTime(afterTime, beforeTime);
        }

        return result;
    }

    private void logExecutionTime(long afterTime, Long beforeTime) {
        log.info("Время исполнения: {} ms", (afterTime - beforeTime));
    }

}
