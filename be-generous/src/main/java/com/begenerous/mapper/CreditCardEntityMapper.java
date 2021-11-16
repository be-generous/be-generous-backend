package com.begenerous.mapper;

import com.begenerous.DTO.CreditCardDTO;
import com.begenerous.model.CreditCard;
import com.begenerous.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditCardEntityMapper implements Mapper<CreditCardDTO, CreditCard> {
    @Override
    public CreditCard convertOne(CreditCardDTO creditCardDTO) {
        return new CreditCard(
                null,
                creditCardDTO.getCardNumber(),
                creditCardDTO.getName(),
                creditCardDTO.getCVV(),
                creditCardDTO.getExpireDate(),
                creditCardDTO.getBalance(),
                new User(),
                new ArrayList<>()
        );
    }

    @Override
    public List<CreditCard> convertList(List<CreditCardDTO> creditCardDTOs) {
        return creditCardDTOs.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
