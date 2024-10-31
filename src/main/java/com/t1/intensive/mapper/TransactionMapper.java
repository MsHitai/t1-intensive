package com.t1.intensive.mapper;

import com.t1.intensive.model.dto.TransactionDto;
import com.t1.intensive.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toTransactionDto(Transaction transaction);

    List<TransactionDto> toTransactionDtoList(List<Transaction> transactions);

    @Mapping(target = "account", ignore = true)
    Transaction toTransactionEntity(TransactionDto transactionDto);
}
