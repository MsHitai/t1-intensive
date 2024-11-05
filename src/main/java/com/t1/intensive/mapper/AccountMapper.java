package com.t1.intensive.mapper;

import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toAccountDto(Account account);

    List<AccountDto> toAccountDtoList(List<Account> account);

    @Mapping(target = "client", ignore = true)
    Account toAccountEntity(AccountDto accountDto);
}
