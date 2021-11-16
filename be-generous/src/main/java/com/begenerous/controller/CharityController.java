package com.begenerous.controller;

import com.begenerous.DTO.CharityDTO;
import com.begenerous.exception.NegativeAmountException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.CharityDTOMapper;
import com.begenerous.mapper.CharityEntityMapper;
import com.begenerous.model.Charity;
import com.begenerous.service.CharityService;
import com.begenerous.service.UserService;
import com.begenerous.util.ExceptionHandlerUtils;
import com.begenerous.util.ResponseBodyUtil;
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
            Charity charity = charityService.saveCharity(
                    charityEntityMapper.convertOne(charityDTO),
                    charityDTO.getUserId()
            );

            ResponseBodyUtil responseBodyUtil = new ResponseBodyUtil();
            responseBodyUtil.addToResponseBody("message", "Charity successfully created!");
            responseBodyUtil.addToResponseBody("charityId", charity.getCharityId().toString());

            return new ResponseEntity<>(responseBodyUtil.createResponseBody(), HttpStatus.OK);
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
            charityService.updateCharity(
                    charityEntityMapper.convertOne(charityDTO),
                    charityDTO.getUserId()
            );

            ResponseBodyUtil responseBodyUtil = new ResponseBodyUtil();
            responseBodyUtil.addToResponseBody("message", "Charity successfully updated!");

            return new ResponseEntity<>(responseBodyUtil.createResponseBody(), HttpStatus.OK);
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
}
