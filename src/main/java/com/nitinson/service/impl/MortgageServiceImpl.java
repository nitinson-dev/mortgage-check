package com.nitinson.service.impl;

import com.nitinson.exception.InvalidInputException;
import com.nitinson.model.MortgageRate;
import com.nitinson.model.MortgageRequest;
import com.nitinson.model.MortgageResponse;
import com.nitinson.service.MortgageService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

@Service
public class MortgageServiceImpl implements MortgageService {

    @Override
    public MortgageResponse calculateMortgage(MortgageRequest mortgageRequest) {

        if (validateMortgageBusinessRules(mortgageRequest)) {

            BigDecimal principal = mortgageRequest.getLoanValue();
            int maturityPeriodInYears = mortgageRequest.getMaturityPeriod();
            int numberOfInstallments = maturityPeriodInYears * 12;

            Optional<MortgageRate> mortgageRateOptional = getAllInterestRates().getMortgageRateList().stream()
                    .filter(mortgageRate -> maturityPeriodInYears == mortgageRate.getMaturityPeriod())
                    .findAny();

            if (mortgageRateOptional.isPresent()) {
                float monthlyInterest = mortgageRateOptional.get().getInterestRate().floatValue() / 100 / 12;
                double mathPower = Math.pow(1 + monthlyInterest, numberOfInstallments);
                double monthlyPayment = principal.intValue() * (monthlyInterest * mathPower / (mathPower - 1));

                String monthlyPaymentFormatted = NumberFormat.getCurrencyInstance(Locale.GERMANY).format(monthlyPayment);

                return new MortgageResponse(true, monthlyPaymentFormatted, null);
            } else {
                throw new InvalidInputException("mortgage interest rate not defined for the given maturity period");
            }
        }
        return new MortgageResponse(false, null, "Business Rule Validation Failed!");
    }

    private boolean validateMortgageBusinessRules(MortgageRequest mortgageRequest) {
        return ((mortgageRequest.getLoanValue().doubleValue() <= (4 * mortgageRequest.getIncome().doubleValue()))
                && (mortgageRequest.getLoanValue().doubleValue() <= mortgageRequest.getHomeValue().doubleValue()));
    }
}
