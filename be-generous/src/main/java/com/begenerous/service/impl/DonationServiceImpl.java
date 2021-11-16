package com.begenerous.service.impl;

import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.Charity;
import com.begenerous.model.CreditCard;
import com.begenerous.model.Donation;
import com.begenerous.repository.CharityRepo;
import com.begenerous.repository.CreditCardRepo;
import com.begenerous.repository.DonationRepo;
import com.begenerous.service.CharityService;
import com.begenerous.service.CreditCardService;
import com.begenerous.service.DonationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DonationServiceImpl implements DonationService {
    private final CreditCardService creditCardService;
    private final CharityService charityService;
    private final DonationRepo donationRepo;
    private final CreditCardRepo creditCardRepo;
    private final CharityRepo charityRepo;

    @Override
    public Donation createDonation(Donation donation, Long creditCardId, Long charityId) throws RowNotFoundException, NegativeAmountException {
        CreditCard creditCard = creditCardRepo.findById(creditCardId).orElseThrow(() -> new RowNotFoundException("No credit card exists with the id: " + creditCardId));
        Charity charity = charityRepo.findById(charityId).orElseThrow(() -> new RowNotFoundException("No charity exists with the id: " + charityId));

        creditCardService.payAmountFromCreditCard(donation.getAmount(), creditCardId);
        charityService.addAmountToCharity(donation.getAmount(), charityId);

        donation.setCreditCard(creditCard);
        donation.setCharity(charity);
        return donationRepo.save(donation);
    }
}
