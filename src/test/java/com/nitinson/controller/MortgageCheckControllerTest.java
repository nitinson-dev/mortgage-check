package com.nitinson.controller;

import com.nitinson.model.MortgageRatesList;
import com.nitinson.model.MortgageRequest;
import com.nitinson.model.MortgageResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MortgageCheckControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*@Before
    public void setup() {
        BasicAuthorizationInterceptor bai = new BasicAuthorizationInterceptor("admin", "password");
        restTemplate.getRestTemplate().getInterceptors().add(bai);
    }*/

    @Test
    public void getAllInterestRates() {
        MortgageRatesList mortgageRatesList = this.restTemplate.getForObject(
                "http://localhost:" + port + "/api/interest-rates",
                MortgageRatesList.class);

        assertThat(mortgageRatesList).isNotNull();
        assertThat(mortgageRatesList.getMortgageRateList().size()).isEqualTo(4);
    }

    @Test
    public void checkMortgageElgibility() {
        MortgageRequest mortgageRequest = new MortgageRequest(10,
                new BigDecimal("80000"), new BigDecimal("320000"), new BigDecimal("500000"));
        MortgageResponse mortgageResponse = this.restTemplate.postForObject(
                "http://localhost:" + port + "/api/mortgage-check", mortgageRequest,
                MortgageResponse.class);

        assertThat(mortgageResponse.isMortgageFeasible()).isTrue();
        assertThat(mortgageResponse.getMonthlyCosts()).isEqualTo("3.271,88 €");
    }

    @Test
    public void checkMortgageElgibilityLoanValueGreaterThanHomeValue() {
        MortgageRequest mortgageRequest = new MortgageRequest(10,
                new BigDecimal("80000"), new BigDecimal("320000"), new BigDecimal("200000"));
        MortgageResponse mortgageResponse = this.restTemplate.postForObject(
                "http://localhost:" + port + "/api/mortgage-check", mortgageRequest,
                MortgageResponse.class);

        assertThat(mortgageResponse.isMortgageFeasible()).isFalse();
        assertThat(mortgageResponse.getReason()).isEqualTo("Business Rule Validation Failed!");
    }

    @Test
    public void checkMortgageElgibilityIncomeLessThanLoanValue() {
        MortgageRequest mortgageRequest = new MortgageRequest(10,
                new BigDecimal("80000"), new BigDecimal("320000"), new BigDecimal("200000"));
        MortgageResponse mortgageResponse = this.restTemplate.postForObject(
                "http://localhost:" + port + "/api/mortgage-check", mortgageRequest,
                MortgageResponse.class);

        assertThat(mortgageResponse.isMortgageFeasible()).isFalse();
        assertThat(mortgageResponse.getReason()).isEqualTo("Business Rule Validation Failed!");
    }

}