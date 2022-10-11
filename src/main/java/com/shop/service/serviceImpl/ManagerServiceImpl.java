package com.shop.service.serviceImpl;

import com.shop.enums.Qualification;
import com.shop.enums.Role;
import com.shop.models.Applicant;
import com.shop.models.Staff;
import com.shop.models.Store;
import com.shop.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
    //MANAGER SERVICE
    @Override
    public boolean hireCashier(Applicant applicant) {
        if(applicant.getQualification().equals(Qualification.OND)
                && applicant.getYearsOfExperience() >= 2){
            Store companyDB = new Store();
            companyDB.getStaff().add(
                    new Staff("STAF753", applicant.getFirstName(),
                            applicant.getLastName(),
                            applicant.getPhoneNumber(), applicant.getEmail(),
                            Role.CASHIER,
                            applicant.getAddress()));
            return true;
        }
        return false;
    }

}
