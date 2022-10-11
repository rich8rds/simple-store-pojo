package com.shop.service.serviceImpl;

import com.shop.enums.Qualification;
import com.shop.models.Address;
import com.shop.models.Applicant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceImplTest {
    ManagerServiceImpl managerService = new ManagerServiceImpl();

    @Test
    void hireCashierShouldReturnApplicationNotSuccessfulIfQualificationIsNotOND() {
        Address tomAddress = new Address("Rabbi Street", "Port Harcourt", "Rivers");
        Applicant applicant = new Applicant("AP095", "Thompson", "Leke",
                234814098123L, "kolade.lola@gmail.com", tomAddress, Qualification.BSC, 2);
        assertFalse(managerService.hireCashier(applicant), "Applicant's application not successful!");
    }

    @Test
    void hireCashierShouldReturnApplicationNotSuccessfulIfExperienceIsLessThanTwoYears() {
        Address tomAddress = new Address("Rabbi Street", "Port Harcourt", "Rivers");
        Applicant applicant = new Applicant("AP095", "Thompson", "Leke",
                234814098123L, "kolade.lola@gmail.com", tomAddress, Qualification.HND, 1);
        assertFalse(managerService.hireCashier(applicant), "Applicant's application not successful!");
    }

    @Test
    void hireCashierShouldReturnApplicationNotSuccessfulIFQualificationIsNotOND() {
        Address tomAddress = new Address("Rabbi Street", "Port Harcourt", "Rivers");
        Applicant applicant = new Applicant("AP095", "Thompson", "Leke",
                234814098123L, "kolade.lola@gmail.com", tomAddress, Qualification.HND, 2);
        assertFalse(managerService.hireCashier(applicant), "Applicant's application not successful!");
    }

    @Test
    void hireCashierShouldReturnApplicationSuccessfulIfQualificationIsONDAndExperienceIsMoreThanTwoYears() {
        Address tomAddress = new Address("Rabbi Street", "Port Harcourt", "Rivers");
        Applicant applicant = new Applicant("AP095", "Thompson", "Leke",
                234814098123L, "kolade.lola@gmail.com", tomAddress, Qualification.OND, 3);
        assertTrue(managerService.hireCashier(applicant), "Applicant's application successful!");
    }

}