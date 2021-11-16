package com.begenerous.mapper;

import com.begenerous.DTO.CharityDTO;
import com.begenerous.model.Charity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class CharityEntityMapper implements Mapper<CharityDTO, Charity> {
    @Override
    public Charity convertOne(CharityDTO charityDTO) {
        return new Charity(
                null,
                charityDTO.getGoalAmount(),
                0.0,
                charityDTO.getCoverImageURL(),
                charityDTO.getTitle(),
                charityDTO.getDescription(),
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()),
                null
        );
    }

    @Override
    public List<Charity> convertList(List<CharityDTO> charityDTOs) {
        return charityDTOs.stream().map(this::convertOne).collect(Collectors.toList());
    }
}