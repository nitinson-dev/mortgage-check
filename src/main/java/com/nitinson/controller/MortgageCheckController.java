package com.nitinson.controller;

import com.nitinson.model.MortgageRatesList;
import com.nitinson.model.MortgageRequest;
import com.nitinson.model.MortgageResponse;
import com.nitinson.service.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class MortgageCheckController {

    @Autowired
    private MortgageService mortgageService;

    @GetMapping("/interest-rates")
    public ResponseEntity<MortgageRatesList> getAllInterestRates(){
        return ResponseEntity.ok(mortgageService.getAllInterestRates());
    }

    @PostMapping("/mortgage-check")
    public ResponseEntity<MortgageResponse> checkMortgageElgibility(@Valid @RequestBody MortgageRequest mortgageRequest){
        return ResponseEntity.ok(mortgageService.calculateMortgage(mortgageRequest));
    }

}
