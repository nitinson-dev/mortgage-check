package com.nitinson.service;

import com.nitinson.model.MortgageRate;
import com.nitinson.model.MortgageRatesList;
import com.nitinson.model.MortgageRequest;
import com.nitinson.model.MortgageResponse;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;

public interface MortgageService {

    default MortgageRatesList getAllInterestRates() {

        return new MortgageRatesList(Arrays.asList(
                new MortgageRate(10, new BigDecimal("4.21"), ZonedDateTime.now()),
                new MortgageRate(15, new BigDecimal("4.40"), ZonedDateTime.now()),
                new MortgageRate(20, new BigDecimal("4.45"), ZonedDateTime.now()),
                new MortgageRate(30, new BigDecimal("4.70"), ZonedDateTime.now())
        ));
    }

    MortgageResponse calculateMortgage(MortgageRequest mortgageRequest);
}
