package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.business.config.RootConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.integrations.impl.ChargingPlatformImpl;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.jupiter.api.Test;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = {RootConfig.class})
})

public class CreditsServiceImplTest {

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
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CreditsServiceImpl.chargingPlatform
        //     CreditsServiceImpl.subscriberRequestDao

        new CreditsServiceImpl();
    }

    @Test
    void testConstructor2() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CreditsServiceImpl.chargingPlatform
        //     CreditsServiceImpl.subscriberRequestDao

        new CreditsServiceImpl(new ChargingPlatformImpl(null), mock(SubscriberRequestDao.class));

    }

    @Test
    void testConstructor3() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CreditsServiceImpl.chargingPlatform
        //     CreditsServiceImpl.subscriberRequestDao

        new CreditsServiceImpl();
    }

    @Test
    void testConstructor4() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CreditsServiceImpl.chargingPlatform
        //     CreditsServiceImpl.subscriberRequestDao

        new CreditsServiceImpl(new ChargingPlatformImpl(null), mock(SubscriberRequestDao.class));

    }

    @Test
    void testChangeSubscriberRequestStatusOnCredit() {
        SubscriberRequest subscriberRequest = new SubscriberRequest();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subscriberRequest.setDateLastUpdated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subscriberRequest.setMsisdn("Msisdn");
        subscriberRequest.setAmount(10.0);
        subscriberRequest.setReference("Reference");
        subscriberRequest.setStatus("Status");
        subscriberRequest.setPartnerCode("Partner Code");
        subscriberRequest.setId(123L);
        subscriberRequest.setBalanceAfter(10.0);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subscriberRequest.setDateCreated(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        subscriberRequest.setBalanceBefore(10.0);
        subscriberRequest.setRequestType("Request Type");
        subscriberRequest.setVersion(1L);

        INCreditResponse inCreditResponse = new INCreditResponse();
        inCreditResponse.setMsisdn("Msisdn");
        inCreditResponse.setBalance(10.0);
        inCreditResponse.setNarrative("Narrative");
        inCreditResponse.setResponseCode("Response Code");
        CreditsServiceImpl.changeSubscriberRequestStatusOnCredit(subscriberRequest, inCreditResponse);
        assertEquals("Failed", subscriberRequest.getStatus());
    }

    @Test
    void testPopulateSubscriberRequest() {
        AirtimeTopupRequest airtimeTopupRequest = new AirtimeTopupRequest();
        airtimeTopupRequest.setMsisdn("Msisdn");
        airtimeTopupRequest.setAmount(10.0);
        airtimeTopupRequest.setPartnerCode("Partner Code");
        airtimeTopupRequest.setReferenceNumber("42");
        SubscriberRequest actualPopulateSubscriberRequestResult = CreditsServiceImpl
                .populateSubscriberRequest(airtimeTopupRequest);
        assertEquals(10.0, actualPopulateSubscriberRequestResult.getAmount());
        assertEquals("Airtime Topup", actualPopulateSubscriberRequestResult.getRequestType());
        assertEquals("42", actualPopulateSubscriberRequestResult.getReference());
        assertEquals("Partner Code", actualPopulateSubscriberRequestResult.getPartnerCode());
        assertEquals("Msisdn", actualPopulateSubscriberRequestResult.getMsisdn());
    }

    @Test
    void testPopulate() {
        AirtimeTopupRequest airtimeTopupRequest = new AirtimeTopupRequest();
        airtimeTopupRequest.setMsisdn("Msisdn");
        airtimeTopupRequest.setAmount(10.0);
        airtimeTopupRequest.setPartnerCode("Partner Code");
        airtimeTopupRequest.setReferenceNumber("42");
        INCreditRequest actualPopulateResult = CreditsServiceImpl.populate(airtimeTopupRequest);
        assertEquals(10.0, actualPopulateResult.getAmount());
        assertEquals("42", actualPopulateResult.getReferenceNumber());
        assertEquals("Partner Code", actualPopulateResult.getPartnerCode());
        assertEquals("Msisdn", actualPopulateResult.getMsisdn());
    }
}

