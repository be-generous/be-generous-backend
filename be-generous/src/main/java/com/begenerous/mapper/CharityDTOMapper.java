package com.begenerous.mapper;

import com.begenerous.DTO.CharityDTO;
import com.begenerous.model.Charity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CharityDTOMapper implements Mapper<Charity, CharityDTO> {
    @Override
    public CharityDTO convertOne(Charity charity) {
        return new CharityDTO(
                charity.getCharityId(),
                charity.getGoalAmount(),
                charity.getCurrentAmount(),
                charity.getCoverImageURL(),
                charity.getTitle(),
                charity.getDescription(),
                charity.getDateCreated(),
                charity.getUser().getUserId()
        );
    }

    @Override
    public List<CharityDTO> convertList(List<Charity> charities) {
        return charities.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
