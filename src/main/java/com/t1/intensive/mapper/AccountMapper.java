package com.t1.intensive.mapper;

import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toAccountDto(Account account);
}
