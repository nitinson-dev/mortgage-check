package com.nitinson.service;

import com.nitinson.model.MortgageRate;
import com.nitinson.model.MortgageRatesList;
import com.nitinson.model.MortgageRequest;
import com.nitinson.model.MortgageResponse;
import com.nitinson.service.impl.MortgageServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class MortgageServiceTests {

    @InjectMocks
    private MortgageServiceImpl mortgageService;

    @Test
    public void getAllInterestRates() {

        // when
        MortgageRatesList mortgageRatesList = mortgageService.getAllInterestRates();

        // then
        assertThat(mortgageRatesList).isNotNull();
        assertThat(mortgageRatesList.getMortgageRateList().size()).isEqualTo(4);
    }

    @Test
    public void calculateMortgage() {
        MortgageResponse mortgageResponse = mortgageService.calculateMortgage(new MortgageRequest(10,
                new BigDecimal("80000"), new BigDecimal("320000"), new BigDecimal("500000")));

        assertThat(mortgageResponse.isMortgageFeasible()).isTrue();
        assertThat(mortgageResponse.getMonthlyCosts()).isEqualTo("3.271,88 €");
    }

    @Test
    public void calculateMortgageTestWhereMortgageExceedsIncome() {
        MortgageResponse mortgageResponse = mortgageService.calculateMortgage(new MortgageRequest(10,
                new BigDecimal("80000"), new BigDecimal("330000"), new BigDecimal("500000")));

        assertThat(mortgageResponse.isMortgageFeasible()).isFalse();
    }

    @Test
    public void calculateMortgageTestWhereMortgageExceedsHomeValue() {
        MortgageResponse mortgageResponse = mortgageService.calculateMortgage(new MortgageRequest(10,
                new BigDecimal("80000"), new BigDecimal("300000"), new BigDecimal("200000")));

        assertThat(mortgageResponse.isMortgageFeasible()).isFalse();
    }
}