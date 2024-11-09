package com.t1.intensive.service;

import com.t1.intensive.mapper.DataSourceErrorMapper;
import com.t1.intensive.model.dto.ErrorDto;
import com.t1.intensive.model.entity.DataSourceErrorLog;
import com.t1.intensive.repository.DataSourceErrorLogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.t1.intensive.util.ConstantsUtil.METRICS;
import static com.t1.intensive.util.KafkaUtils.getKafkaKey;

@Service
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final DataSourceErrorLogRepository repository;

    private final DataSourceErrorMapper errorMapper;

    private final KafkaTemplate<String, ErrorDto> kafkaTemplate;

    @Override
    @Transactional
    public void saveErrorInfo(Exception e, String methodSignature) {
        DataSourceErrorLog error = getDataSourceErrorLog(e, methodSignature);
        repository.save(error);
    }

    @Override
    public void sendError(Exception e, String methodSignature) {
        DataSourceErrorLog error = getDataSourceErrorLog(e, methodSignature);
        List<Header> headers = new ArrayList<>();
        headers.add(new RecordHeader("error_type", "DATA_SOURCE".getBytes(StandardCharsets.UTF_8)));
        String key = getKafkaKey();
        ProducerRecord<String, ErrorDto> record = new ProducerRecord<>(METRICS, 1, key, errorMapper.toDto(error), headers);
        kafkaTemplate.send(record);
    }

    private DataSourceErrorLog getDataSourceErrorLog(Exception e, String methodSignature) {
        return DataSourceErrorLog.builder()
                .stackTrace(ExceptionUtils.getStackTrace(e))
                .message(e.getMessage())
                .methodSignature(methodSignature)
                .createdOn(LocalDateTime.now())
                .build();
    }
}
