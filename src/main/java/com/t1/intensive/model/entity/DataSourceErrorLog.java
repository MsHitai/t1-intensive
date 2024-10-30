package com.t1.intensive.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@ToString
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "error_logs")
public class DataSourceErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stack_trace", nullable = false)
    private String stackTrace;

    @Column(name = "message")
    private String message;

    @Column(name = "method_signature", nullable = false)
    private String methodSignature;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSourceErrorLog that = (DataSourceErrorLog) o;
        return Objects.equals(id, that.id)
                && Objects.equals(message, that.message)
                && Objects.equals(methodSignature, that.methodSignature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, methodSignature);
    }
}
