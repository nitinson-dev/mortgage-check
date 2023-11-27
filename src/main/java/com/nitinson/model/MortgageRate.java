package com.nitinson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MortgageRate {

    private int maturityPeriod;
    private BigDecimal interestRate;
    private ZonedDateTime lastUpdate;

}
