package com.econetwireless.epay.business.utils;

import com.econetwireless.epay.business.config.RootConfig;
import com.econetwireless.in.webservice.BalanceResponse;
import com.econetwireless.in.webservice.CreditRequest;
import com.econetwireless.in.webservice.CreditResponse;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.pojo.INBalanceResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = {RootConfig.class})
})

public class MessageConvertersTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    public String partnerCode;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        partnerCode = "hot-recharge";
    }

    @Test
    public void mconvertTest() {

 INCreditRequest inCreditRequest = new INCreditRequest();
        inCreditRequest.setAmount(100);
        inCreditRequest.setMsisdn("777283562");
        inCreditRequest.setReferenceNumber("TOPUP-REF-0123");

        assertEquals(inCreditRequest.getMsisdn(), MessageConverters.convert(inCreditRequest).getMsisdn());
        assertEquals(inCreditRequest.getAmount(), MessageConverters.convert(inCreditRequest).getAmount());
        assertEquals(inCreditRequest.getPartnerCode(), MessageConverters.convert(inCreditRequest).getPartnerCode());
        assertEquals(inCreditRequest.getReferenceNumber(), MessageConverters.convert(inCreditRequest).getReferenceNumber());


    }


    @Test
    public void mconvertTest1() {

        CreditRequest creditRequest = new CreditRequest();

        creditRequest.setPartnerCode(partnerCode);
        creditRequest.setMsisdn("777283562");
        creditRequest.setAmount(100);
        creditRequest.setReferenceNumber("YEINN45k");


        assertEquals(creditRequest.getMsisdn(), MessageConverters.convert(creditRequest).getMsisdn());
        assertEquals(creditRequest.getAmount(), MessageConverters.convert(creditRequest).getAmount());
        assertEquals(creditRequest.getPartnerCode(), MessageConverters.convert(creditRequest).getPartnerCode());
        assertEquals(creditRequest.getReferenceNumber(), MessageConverters.convert(creditRequest).getReferenceNumber());


    }


    @Test
    public void mconvertTest2() {
        INCreditResponse inCreditResponse = new INCreditResponse();

        inCreditResponse.setResponseCode("200");
        inCreditResponse.setNarrative("No Narative");
        inCreditResponse.setMsisdn("777283562");
        inCreditResponse.setBalance(5);

        assertEquals(inCreditResponse.getMsisdn(), MessageConverters.convert(inCreditResponse).getMsisdn());
        assertEquals(inCreditResponse.getResponseCode(), MessageConverters.convert(inCreditResponse).getResponseCode());
        assertEquals(inCreditResponse.getMsisdn(), MessageConverters.convert(inCreditResponse).getMsisdn());
        assertEquals(inCreditResponse.getBalance(), MessageConverters.convert(inCreditResponse).getBalance());
    }

    @Test
    public void mconvertTest3() {
        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setResponseCode("200");
        creditResponse.setNarrative("No Narative");
        creditResponse.setMsisdn("777283562");
        creditResponse.setBalance(5);

        assertEquals(creditResponse.getMsisdn(), MessageConverters.convert(creditResponse).getMsisdn());
        assertEquals(creditResponse.getResponseCode(), MessageConverters.convert(creditResponse).getResponseCode());
        assertEquals(creditResponse.getMsisdn(), MessageConverters.convert(creditResponse).getMsisdn());
        assertEquals(creditResponse.getBalance(), MessageConverters.convert(creditResponse).getBalance());
    }

    @Test
    public void mconvertTest4() {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setResponseCode("200");
        balanceResponse.setNarrative("No Narative");
        balanceResponse.setMsisdn("777283562");
        balanceResponse.setAmount(5);

        assertEquals(balanceResponse.getMsisdn(), MessageConverters.convert(balanceResponse).getMsisdn());
        assertEquals(balanceResponse.getResponseCode(), MessageConverters.convert(balanceResponse).getResponseCode());
        assertEquals(balanceResponse.getMsisdn(), MessageConverters.convert(balanceResponse).getMsisdn());
        assertEquals(balanceResponse.getAmount(), MessageConverters.convert(balanceResponse).getAmount());


    }

    @Test
    public void mconvertTest5() {

        INBalanceResponse inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setResponseCode("200");
        inBalanceResponse.setNarrative("No Narative");
        inBalanceResponse.setMsisdn("777283562");
        inBalanceResponse.setAmount(5);

        assertEquals(inBalanceResponse.getMsisdn(), MessageConverters.convert(inBalanceResponse).getMsisdn());
        assertEquals(inBalanceResponse.getResponseCode(), MessageConverters.convert(inBalanceResponse).getResponseCode());
        assertEquals(inBalanceResponse.getMsisdn(), MessageConverters.convert(inBalanceResponse).getMsisdn());
        assertEquals(inBalanceResponse.getAmount(), MessageConverters.convert(inBalanceResponse).getAmount());


    }

    @org.junit.jupiter.api.Test
    void mconvertTest6() {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setMsisdn("30");
        balanceResponse.setAmount(40.7);
        balanceResponse.setNarrative("30");
        balanceResponse.setResponseCode("30");
        INBalanceResponse actualConvertResult = MessageConverters.convert(balanceResponse);
        assertEquals(40.7, actualConvertResult.getAmount());
        assertEquals("30", actualConvertResult.getResponseCode());
        assertEquals("30", actualConvertResult.getNarrative());
        assertEquals("30", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void mconvertTest7() {
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setMsisdn("30");
        creditRequest.setAmount(40.7);
        creditRequest.setPartnerCode("30");
        creditRequest.setReferenceNumber("30");
        INCreditRequest actualConvertResult = MessageConverters.convert(creditRequest);
        assertEquals(40.7, actualConvertResult.getAmount());
        assertEquals("30", actualConvertResult.getReferenceNumber());
        assertEquals("30", actualConvertResult.getPartnerCode());
        assertEquals("30", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void mconvertTest8() {
        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setMsisdn("30");
        creditResponse.setBalance(40.7);
        creditResponse.setNarrative("30");
        creditResponse.setResponseCode("30");
        INCreditResponse actualConvertResult = MessageConverters.convert(creditResponse);
        assertEquals(40.7, actualConvertResult.getBalance());
        assertEquals("30", actualConvertResult.getResponseCode());
        assertEquals("30", actualConvertResult.getNarrative());
        assertEquals("30", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void mconvertTest9() {
        INBalanceResponse inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setMsisdn("Msisdn");
        inBalanceResponse.setAmount(40.7);
        inBalanceResponse.setNarrative("Narrative");
        inBalanceResponse.setResponseCode("Response Code");
        BalanceResponse actualConvertResult = MessageConverters.convert(inBalanceResponse);
        assertEquals(40.7, actualConvertResult.getAmount());
        assertEquals("Response Code", actualConvertResult.getResponseCode());
        assertEquals("Narrative", actualConvertResult.getNarrative());
        assertEquals("Msisdn", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void mconvertTest10() {
        CreditRequest actualConvertResult = MessageConverters
               .convert(new INCreditRequest("Partner Code", "Msisdn", 40.7, "30"));
       assertEquals(40.7, actualConvertResult.getAmount());
        assertEquals("30", actualConvertResult.getReferenceNumber());
        assertEquals("Partner Code", actualConvertResult.getPartnerCode());
        assertEquals("Msisdn", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void monvertTest11() {
        assertNull(MessageConverters.convert((INCreditRequest) null));
    }

    @org.junit.jupiter.api.Test
    void testConvert12() {
        INCreditRequest inCreditRequest = mock(INCreditRequest.class);
        when(inCreditRequest.getReferenceNumber()).thenReturn("30");
        when(inCreditRequest.getPartnerCode()).thenReturn("Partner Code");
        when(inCreditRequest.getAmount()).thenReturn(40.7);
        when(inCreditRequest.getMsisdn()).thenReturn("Msisdn");
        CreditRequest actualConvertResult = MessageConverters.convert(inCreditRequest);
        assertEquals(40.7, actualConvertResult.getAmount());
        assertEquals("30", actualConvertResult.getReferenceNumber());
        assertEquals("Partner Code", actualConvertResult.getPartnerCode());
        assertEquals("Msisdn", actualConvertResult.getMsisdn());
        verify(inCreditRequest).getAmount();
        verify(inCreditRequest).getMsisdn();
        verify(inCreditRequest).getPartnerCode();
        verify(inCreditRequest).getReferenceNumber();
    }

    @org.junit.jupiter.api.Test
    void mconvertTest13() {
        INCreditResponse inCreditResponse = new INCreditResponse();
        inCreditResponse.setMsisdn("Msisdn");
        inCreditResponse.setBalance(40.7);
        inCreditResponse.setNarrative("Narrative");
        inCreditResponse.setResponseCode("Response Code");
        CreditResponse actualConvertResult = MessageConverters.convert(inCreditResponse);
        assertEquals(40.7, actualConvertResult.getBalance());
        assertEquals("Response Code", actualConvertResult.getResponseCode());
        assertEquals("Narrative", actualConvertResult.getNarrative());
        assertEquals("Msisdn", actualConvertResult.getMsisdn());
    }


}