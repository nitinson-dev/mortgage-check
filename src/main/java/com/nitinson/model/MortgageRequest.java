package com.nitinson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
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
