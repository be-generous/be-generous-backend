package com.begenerous.service.impl;

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
    public CreditCard getCreditCard(Long creditCardId) throws RowNotFoundException {
        return creditCardRepo.findById(creditCardId).orElseThrow(() -> new RowNotFoundException("No credit card with the id: " + creditCardId));
    }

    @Override
    public List<CreditCard> getCreditCards() {
        return creditCardRepo.findAll();
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
}
