package com.begenerous.service;

import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.NoCreditCardFoundException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    CreditCard saveCreditCard(CreditCard creditCard, Long userId) throws RowNotFoundException;

    List<CreditCard> getCreditCards(Long userId) throws NoCreditCardFoundException;

    void updateCreditCard(CreditCard creditCard) throws RowNotFoundException;

    void deleteCreditCard(Long creditCardId) throws RowNotFoundException;

    void payAmountFromCreditCard(Double amount, Long creditCardId) throws RowNotFoundException, NegativeAmountException;
}
