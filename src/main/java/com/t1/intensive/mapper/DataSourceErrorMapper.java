package com.t1.intensive.mapper;

import com.t1.intensive.model.dto.ErrorDto;
import com.t1.intensive.model.entity.DataSourceErrorLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataSourceErrorMapper {

    ErrorDto toDto(DataSourceErrorLog error);
}
