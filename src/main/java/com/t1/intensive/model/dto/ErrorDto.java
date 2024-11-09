package com.t1.intensive.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ErrorDto implements Serializable {
    @JsonProperty("stack_trace")
    private String stackTrace;
    @JsonProperty("message")
    private String message;
    @JsonProperty("method_signature")
    private String methodSignature;
    @JsonProperty("created_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime createdOn;
}
