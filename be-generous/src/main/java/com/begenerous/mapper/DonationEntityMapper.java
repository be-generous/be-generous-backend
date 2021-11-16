package com.begenerous.mapper;

import com.begenerous.DTO.DonationDTO;
import com.begenerous.model.Donation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class DonationEntityMapper implements Mapper<DonationDTO, Donation> {
    @Override
    public Donation convertOne(DonationDTO donationDTO) {
        return new Donation(
                donationDTO.getAmount(),
                donationDTO.getMessage(),
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
                );
    }

    @Override
    public List<Donation> convertList(List<DonationDTO> donationDTOs) {
        return donationDTOs.stream().map(this::convertOne).collect(Collectors.toList());

    }
}
