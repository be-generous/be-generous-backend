package com.begenerous.mapper;

import com.begenerous.DTO.CreditCardDTO;
import com.begenerous.model.CreditCard;
import com.begenerous.model.Donation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditCardDTOMapper implements Mapper<CreditCard, CreditCardDTO> {
    @Override
    public CreditCardDTO convertOne(CreditCard creditCard) {
        return new CreditCardDTO(
                creditCard.getCreditCardId(),
                creditCard.getCardNumber(),
                creditCard.getName(),
                creditCard.getCVV(),
                creditCard.getExpireDate(),
                creditCard.getBalance(),
                creditCard.getUser().getUserId(),
                creditCard.getDonation().stream().map(Donation::getDonationId).collect(Collectors.toList())
        );
    }

    @Override
    public List<CreditCardDTO> convertList(List<CreditCard> creditCards) {
        return creditCards.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
