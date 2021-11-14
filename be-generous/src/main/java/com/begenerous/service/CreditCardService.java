package com.begenerous.service;

import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    CreditCard saveCreditCard(CreditCard creditCard, Long userId) throws RowNotFoundException;

    CreditCard getCreditCard(Long charityId) throws RowNotFoundException;

    List<CreditCard> getCreditCards();

    void updateCreditCard(CreditCard creditCard) throws RowNotFoundException;

    void deleteCreditCard(Long creditCardId) throws RowNotFoundException;
}
