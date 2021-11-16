package com.begenerous.service.impl;

import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.NoCreditCardFoundException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.CreditCard;
import com.begenerous.model.User;
import com.begenerous.repository.CreditCardRepo;
import com.begenerous.repository.UserRepo;
import com.begenerous.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {
    private CreditCardRepo creditCardRepo;
    private UserRepo userRepo;

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard, Long userId) throws RowNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() -> new RowNotFoundException("No user found with the id: " + userId));

        creditCard.setUser(user);
        creditCard.setBalance(10000.0);

        return creditCardRepo.save(creditCard);
    }

    @Override
    public List<CreditCard> getCreditCards(Long userId) throws NoCreditCardFoundException {
        List<CreditCard> listOfCreditCards = creditCardRepo.findAllByUserId(userId);
        if (listOfCreditCards.size() == 0) {
            throw new NoCreditCardFoundException("No credit card is registered to the user with the id: " + userId);
        }
        listOfCreditCards.forEach(creditCard -> {
            creditCard.setCardNumber(blurCreditCardNumber(creditCard.getCardNumber()));
        });

        return listOfCreditCards;
    }

    private String blurCreditCardNumber(String cardNumber) {
        return new StringBuilder()
                .append("**** **** **** ")
                .append(cardNumber.substring(cardNumber.length()-4))
                .toString();
    }

    @Override
    public void updateCreditCard(CreditCard creditCard) throws RowNotFoundException {
        try {
            creditCardRepo.save(creditCard);
        } catch (Exception e) {
            throw new RowNotFoundException("Couldn't update credit card: " + e.getMessage());
        }
    }

    @Override
    public void deleteCreditCard(Long creditCardId) throws RowNotFoundException {
        try {
            creditCardRepo.deleteById(creditCardId);
        } catch (Exception e) {
            throw new RowNotFoundException("Couldn't delete credit card: " + e.getMessage());
        }
    }

    @Override
    public void payAmountFromCreditCard(Double amount, Long creditCardId) throws RowNotFoundException, NegativeAmountException {
        CreditCard creditCard = creditCardRepo.findById(creditCardId).orElseThrow(() -> new RowNotFoundException("No credit card exists with the id: " + creditCardId));

        if(creditCard.getBalance()-amount < 0) {
            throw new NegativeAmountException("Transaction failed, credit card balance can't go bellow 0.");
        }
        creditCard.setBalance(creditCard.getBalance() - amount);
    }
}
