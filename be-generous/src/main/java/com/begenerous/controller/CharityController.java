package com.begenerous.controller;

import com.begenerous.DTO.AmountDTO;
import com.begenerous.DTO.CharityDTO;
import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.CharityDTOMapper;
import com.begenerous.mapper.CharityEntityMapper;
import com.begenerous.model.Charity;
import com.begenerous.service.CharityService;
import com.begenerous.service.UserService;
import com.begenerous.util.ExceptionHandlerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/charity")
@RequiredArgsConstructor
public class CharityController {
    private final CharityService charityService;
    private final UserService userService;
    private final CharityDTOMapper charityDTOMapper;
    private final CharityEntityMapper charityEntityMapper;

    @PostMapping
    public ResponseEntity<?> saveCharity(@Valid @RequestBody CharityDTO charityDTO) {
        try {
            Charity charity = charityEntityMapper.convertOne(charityDTO);
            charity = charityService.saveCharity(charity, charityDTO.getUserId());
            return new ResponseEntity<>(charityDTOMapper.convertOne(charity), HttpStatus.OK);
        } catch (NegativeAmountException e) {
            return ExceptionHandlerUtils.negativeAmountException(e.getMessage());
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        } catch (Exception e) {
            return ExceptionHandlerUtils.internalException(e.getMessage());
        }
    }

    @PostMapping(path = "/update")
    public ResponseEntity<?> updateCharity(@Valid @RequestBody CharityDTO charityDTO) {
        try {
            Charity charity = charityService.updateCharity(charityEntityMapper.convertOne(charityDTO), charityDTO.getUserId());
            return new ResponseEntity<>(charityDTOMapper.convertOne(charity), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getCharities() {
        try {
            return new ResponseEntity<>(charityDTOMapper.convertList(charityService.getCharities()), HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("Couldn't fetch data!");
        }
    }

    @GetMapping(path = "/{charityId}")
    public ResponseEntity<?> getCharity(@PathVariable("charityId") Long charityId) {
        try {
            return new ResponseEntity<>(charityDTOMapper.convertOne(charityService.getCharity(charityId)), HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("No charity with the id: " + charityId);
        }
    }

    @PostMapping("/addamount")
    ResponseEntity<?> addAmountToCharity(@Valid @RequestBody AmountDTO amountDTO) {
        try {
            return new ResponseEntity<>(charityService.addAmountToCharity(amountDTO.getCharityId(), amountDTO.getAmount()), HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("No charity with the id: " + amountDTO.getCharityId());
        }
    }
}
