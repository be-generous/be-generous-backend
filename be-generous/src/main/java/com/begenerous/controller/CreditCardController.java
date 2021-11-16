package com.begenerous.controller;

import com.begenerous.DTO.CreditCardDTO;
import com.begenerous.DTO.ResponseCreditCardDTO;
import com.begenerous.exception.NoCreditCardFoundException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.CreditCardDTOMapper;
import com.begenerous.mapper.CreditCardEntityMapper;
import com.begenerous.mapper.ResponseCreditCardDTOMapper;
import com.begenerous.model.CreditCard;
import com.begenerous.service.CreditCardService;
import com.begenerous.util.ExceptionHandlerUtils;
import com.begenerous.util.ResponseBodyUtil;
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
    private final ResponseCreditCardDTOMapper responseCreditCardDTOMapper;

    @PostMapping
    public ResponseEntity<?> saveCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) {
        try {
            CreditCard creditCard = creditCardService.saveCreditCard(
                    creditCardEntityMapper.convertOne(creditCardDTO),
                    creditCardDTO.getUserId()
            );
            ResponseBodyUtil responseBodyUtil = new ResponseBodyUtil();
            responseBodyUtil.addToResponseBody("message", "Credit card successfully created!");
            responseBodyUtil.addToResponseBody("creditCardId", creditCard.getCreditCardId().toString());

            return new ResponseEntity<>(responseBodyUtil.createResponseBody(), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getCreditCard(@RequestParam String id) {
        try {
            Long userId = Long.valueOf(id);
            return new ResponseEntity<>(responseCreditCardDTOMapper.convertList(creditCardService.getCreditCards(userId)), HttpStatus.OK);
        } catch (NoCreditCardFoundException e) {
            return ExceptionHandlerUtils.noCreditCardFoundException(e.getMessage());
        } catch (Exception e) {
            return ExceptionHandlerUtils.unexpectedException(e.getMessage());
        }
    }

    @PostMapping(path = "/update")
    public ResponseEntity<?> updateCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) {
        try {
            creditCardService.updateCreditCard(
                    creditCardEntityMapper.convertOne(creditCardDTO)
            );

            ResponseBodyUtil responseBodyUtil = new ResponseBodyUtil();
            responseBodyUtil.addToResponseBody("message", "Credit card successfully updated!");

            return new ResponseEntity<>(responseBodyUtil.createResponseBody(), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.unexpectedException(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{creditCardId}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable("creditCardId") Long creditCardId) {
        try {
            creditCardService.deleteCreditCard(creditCardId);

            ResponseBodyUtil responseBodyUtil = new ResponseBodyUtil();
            responseBodyUtil.addToResponseBody("message", "Credit card successfully deleted!");

            return new ResponseEntity<>(responseBodyUtil.createResponseBody(), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.unexpectedException(e.getMessage());
        }
    }
}
