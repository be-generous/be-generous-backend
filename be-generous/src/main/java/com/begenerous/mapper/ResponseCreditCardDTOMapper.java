package com.begenerous.mapper;

import com.begenerous.DTO.ResponseCreditCardDTO;
import com.begenerous.model.CreditCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseCreditCardDTOMapper implements Mapper<CreditCard, ResponseCreditCardDTO> {
    @Override
    public ResponseCreditCardDTO convertOne(CreditCard creditCard) {
        return new ResponseCreditCardDTO(
                creditCard.getCreditCardId(),
                creditCard.getCardNumber(),
                creditCard.getName(),
                creditCard.getBalance()
        );
    }

    @Override
    public List<ResponseCreditCardDTO> convertList(List<CreditCard> creditCards) {
        return creditCards.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
