package com.t1.intensive.service;

import com.t1.intensive.model.entity.DataSourceErrorLog;
import com.t1.intensive.repository.DataSourceErrorLogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final DataSourceErrorLogRepository repository;

    @Override
    @Transactional
    public void saveErrorInfo(Exception e, String methodSignature) {
        DataSourceErrorLog error = DataSourceErrorLog.builder()
                .stackTrace(ExceptionUtils.getStackTrace(e))
                .message(e.getMessage())
                .methodSignature(methodSignature)
                .build();
        repository.save(error);
    }
}
