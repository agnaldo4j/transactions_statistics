package com.agnaldo4j.restapi.controllers;

import com.agnaldo4j.restapi.services.TransactionService;
import com.agnaldo4j.restapi.view.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class Transactions {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public void saveTransaction(@Valid @NotNull @RequestBody TransactionInput input) {
        transactionService.save(input.toDomain());
    }
}
