package com.begenerous.mapper;

import com.begenerous.DTO.DonationDTO;
import com.begenerous.model.Donation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DonationDTOMapper implements Mapper<Donation, DonationDTO> {
    @Override
    public DonationDTO convertOne(Donation donation) {
        return new DonationDTO(
                donation.getDonationId(),
                donation.getAmount(),
                donation.getMessage(),
                donation.getTransactionDate(),
                donation.getCreditCard().getCreditCardId(),
                donation.getCharity().getCharityId()
        );
    }

    @Override
    public List<DonationDTO> convertList(List<Donation> donations) {
        return donations.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
