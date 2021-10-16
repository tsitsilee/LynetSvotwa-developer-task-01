package com.econetwireless.epay.business.integrations.impl;

import com.econetwireless.epay.business.integrations.impl.ChargingPlatformImpl;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.in.webservice.IntelligentNetworkService;
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
import static org.mockito.Matchers.any;
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
public class ChargingPlatformImplTest {
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
    public void testEnquireBalance() {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setMsisdn("42");
        balanceResponse.setAmount(10.0);
        balanceResponse.setNarrative("42");
        balanceResponse.setResponseCode("42");
        IntelligentNetworkService intelligentNetworkService = mock(IntelligentNetworkService.class);
        when(intelligentNetworkService.enquireBalance((String) any(), (String) any())).thenReturn(balanceResponse);
        INBalanceResponse actualEnquireBalanceResult = (new ChargingPlatformImpl(intelligentNetworkService))
                .enquireBalance("Partner Code", "Msisdn");
        assertEquals(10.0, actualEnquireBalanceResult.getAmount());
        assertEquals("42", actualEnquireBalanceResult.getResponseCode());
        assertEquals("42", actualEnquireBalanceResult.getNarrative());
        assertEquals("42", actualEnquireBalanceResult.getMsisdn());
        verify(intelligentNetworkService).enquireBalance((String) any(), (String) any());
    }

    @Test
   public void testCreditSubscriberAccount() {
        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setMsisdn("42");
        creditResponse.setBalance(10.0);
        creditResponse.setNarrative("42");
        creditResponse.setResponseCode("42");
        IntelligentNetworkService intelligentNetworkService = mock(IntelligentNetworkService.class);
        when(intelligentNetworkService.creditSubscriberAccount((com.econetwireless.in.webservice.CreditRequest) any()))
                .thenReturn(creditResponse);
        ChargingPlatformImpl chargingPlatformImpl = new ChargingPlatformImpl(intelligentNetworkService);
        INCreditRequest inCreditRequest = mock(INCreditRequest.class);
        when(inCreditRequest.getReferenceNumber()).thenReturn("42");
        when(inCreditRequest.getPartnerCode()).thenReturn("Partner Code");
        when(inCreditRequest.getAmount()).thenReturn(10.0);
        when(inCreditRequest.getMsisdn()).thenReturn("Msisdn");
        INCreditResponse actualCreditSubscriberAccountResult = chargingPlatformImpl
                .creditSubscriberAccount(inCreditRequest);
        assertEquals(10.0, actualCreditSubscriberAccountResult.getBalance());
        assertEquals("42", actualCreditSubscriberAccountResult.getResponseCode());
        assertEquals("42", actualCreditSubscriberAccountResult.getNarrative());
        assertEquals("42", actualCreditSubscriberAccountResult.getMsisdn());
        verify(intelligentNetworkService).creditSubscriberAccount((com.econetwireless.in.webservice.CreditRequest) any());
        verify(inCreditRequest).getAmount();
        verify(inCreditRequest).getMsisdn();
        verify(inCreditRequest).getPartnerCode();
        verify(inCreditRequest).getReferenceNumber();
    }
}

