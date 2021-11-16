package com.begenerous.controller;

import com.begenerous.DTO.DonationDTO;
import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.DonationDTOMapper;
import com.begenerous.mapper.DonationEntityMapper;
import com.begenerous.model.Donation;
import com.begenerous.service.DonationService;
import com.begenerous.util.ExceptionHandlerUtils;
import com.begenerous.util.ResponseBodyUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/api/donation")
public class DonationController {
    private final DonationService donationService;
    private final DonationDTOMapper donationDTOMapper;
    private final DonationEntityMapper donationEntityMapper;

    @PostMapping
    public ResponseEntity<?> saveDonation(@Valid @RequestBody DonationDTO donationDTO) {
        try {
            Donation donation = donationService.createDonation(
                    donationEntityMapper.convertOne(donationDTO),
                    donationDTO.getCreditCardId(),
                    donationDTO.getCharityId()
            );

            ResponseBodyUtil responseBodyUtil = new ResponseBodyUtil();
            responseBodyUtil.addToResponseBody("message", "Donation successfully created!");
            responseBodyUtil.addToResponseBody("donationId", donation.getDonationId().toString());

            return new ResponseEntity<>(responseBodyUtil.createResponseBody(), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        } catch (NegativeAmountException e) {
            return ExceptionHandlerUtils.negativeAmountException(e.getMessage());
        } catch (Exception e) {
            return ExceptionHandlerUtils.unexpectedException(e.getMessage());
        }
    }
}
