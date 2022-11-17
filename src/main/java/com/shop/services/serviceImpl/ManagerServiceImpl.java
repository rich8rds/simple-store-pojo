package com.shop.services.serviceImpl;

import com.shop.enums.Qualification;
import com.shop.enums.Role;
import com.shop.models.Applicant;
import com.shop.models.Staff;
import com.shop.models.Store;
import com.shop.services.ManagerService;

import java.util.function.Predicate;

public class ManagerServiceImpl implements ManagerService {
    //MANAGER SERVICE
    @Override
    synchronized public String hireCashier(Applicant applicant) {
        Predicate<Applicant> applicantQualification = applicantTest -> applicantTest.getQualification().equals(Qualification.OND);
        Predicate<Applicant> applicantExperienceInYears = applicantTest -> applicantTest.getYearsOfExperience() >= 2;

        if(applicantQualification.and(applicantExperienceInYears).test(applicant)){
            Store companyDB = new Store();
            companyDB.getStaffList().add(
                    new Staff("STAF753", applicant.getFirstName(),
                            applicant.getLastName(),
                            applicant.getPhoneNumber(), applicant.getEmail(),
                            Role.CASHIER,
                            applicant.getAddress()));
            return "Applicant: " + applicant.getFirstName() + " hired!";
        }
        return "Applicant: " + applicant.getFirstName() + " not hired!";
    }

}
