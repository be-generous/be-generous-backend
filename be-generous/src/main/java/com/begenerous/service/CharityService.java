package com.begenerous.service;

import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.Charity;

import java.util.List;

public interface CharityService {
    Charity saveCharity(Charity charity, Long userId) throws NegativeAmountException, RowNotFoundException;

    Charity getCharity(Long charityId) throws RowNotFoundException;

    Charity updateCharity(Charity charity, Long userId) throws RowNotFoundException;

    Charity addAmountToCharity(Double amount, Long charityId) throws RowNotFoundException;

    List<Charity> getCharities();
}
