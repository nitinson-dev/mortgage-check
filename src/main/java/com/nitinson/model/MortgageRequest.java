package com.nitinson.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MortgageRequest {

    @NotNull
    private Integer maturityPeriod;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull
    private BigDecimal income;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull
    private BigDecimal loanValue;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull
    private BigDecimal homeValue;
}
