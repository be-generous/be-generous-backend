package com.begenerous.service;

import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.Donation;

public interface DonationService {
    Donation createDonation(Donation donation, Long creditCardId, Long charityId) throws RowNotFoundException, NegativeAmountException;
}
