package com.begenerous.service;

import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.Donation;

import java.util.List;

public interface DonationService {
    Donation createDonation(Donation donation, Long creditCardId, Long charityId) throws RowNotFoundException, NegativeAmountException;

    List<Donation> getDonationByCharity(Long charityId) throws RowNotFoundException;

    List<Donation> getDonationByUser(Long userId) throws RowNotFoundException;

    List<Donation> getDonationByCreditCard(Long creditCardId) throws RowNotFoundException;
}
