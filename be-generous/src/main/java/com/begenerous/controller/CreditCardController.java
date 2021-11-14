package com.begenerous.controller;

import com.begenerous.DTO.CreditCardDTO;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.CreditCardDTOMapper;
import com.begenerous.mapper.CreditCardEntityMapper;
import com.begenerous.model.CreditCard;
import com.begenerous.service.CreditCardService;
import com.begenerous.util.ExceptionHandlerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/creditcard")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;
    private final CreditCardDTOMapper creditCardDTOMapper;
    private final CreditCardEntityMapper creditCardEntityMapper;

    @PostMapping
    public ResponseEntity<?> saveCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) {
        try {
            CreditCard creditCard = creditCardService.saveCreditCard(
                    creditCardEntityMapper.convertOne(creditCardDTO),
                    creditCardDTO.getUserId()
            );
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Credit card successfully created!");
            responseBody.put("creditCardId", creditCard.getCreditCardId().toString());

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getCreditCards() {
        return new ResponseEntity<>(creditCardDTOMapper.convertList(creditCardService.getCreditCards()), HttpStatus.OK);
    }

    @GetMapping(path = "/{creditCardId}")
    public ResponseEntity<?> getCreditCard(@PathVariable("creditCardId") Long creditCardId) {
        try {
            return new ResponseEntity<>(creditCardDTOMapper.convertOne(creditCardService.getCreditCard(creditCardId)), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        }
    }

    @PostMapping(path = "/update")
    public ResponseEntity<?> updateCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) {
        try {
            creditCardService.updateCreditCard(
                    creditCardEntityMapper.convertOne(creditCardDTO)
            );
            return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.unexpectedException(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{creditCardId}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable("creditCardId") Long creditCardId) {
        try {
            creditCardService.deleteCreditCard(creditCardId);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.unexpectedException(e.getMessage());
        }
    }
}
