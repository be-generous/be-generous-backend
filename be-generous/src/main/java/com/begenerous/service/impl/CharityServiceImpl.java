package com.begenerous.service.impl;

import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.CharityDTOMapper;
import com.begenerous.model.Charity;
import com.begenerous.model.User;
import com.begenerous.repository.CharityRepo;
import com.begenerous.repository.UserRepo;
import com.begenerous.service.CharityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CharityServiceImpl implements CharityService {
    CharityRepo charityRepo;
    CharityDTOMapper charityDTOMapper;
    UserRepo userRepo;

    @Override
    public Charity saveCharity(Charity charity, Long userId) throws NegativeAmountException, RowNotFoundException {
        if (charity.getGoalAmount() <= 0) {
            throw new NegativeAmountException("The amount given can't be less then or equal 0");
        }
        User user = userRepo.findById(userId).orElseThrow(
                () -> new RowNotFoundException("No user exists with the id: " + userId)
        );

        charity.setUser(user);
        return charityRepo.save(charity);
    }

    @Override
    public Charity getCharity(Long charityId) throws RowNotFoundException {
        return charityRepo.findById(charityId).orElseThrow(() -> new RowNotFoundException("No charity exists with the id: " + charityId));
    }

    @Override
    public List<Charity> getCharities() {
        return charityRepo.findAll();
    }

    @Override
    public Charity updateCharity(Charity charity, Long userId) throws RowNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() -> new RowNotFoundException("No user exists with the id: " + userId));
        charity.setUser(user);
        return charityRepo.save(charity);
    }

    @Override
    public Charity addAmountToCharity(Double amount, Long charityId) throws RowNotFoundException {
        Charity charity = charityRepo.findById(charityId).orElseThrow(() -> new RowNotFoundException("No charity exists with the id: " + charityId));
        charity.setCurrentAmount(charity.getCurrentAmount() + amount);

        return null;
    }
}
