package com.t1.intensive.service;

public interface ErrorLogService {
    void saveErrorInfo(Exception e, String methodSignature);
}
