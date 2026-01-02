package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BeneficiaryManagementPage;
import utils.LoginHelper;

public class BeneficiaryManagementTest extends AuthenticatedBaseTest {

    @Test(description = "Verify search beneficiary using patient id for P-190")
    public void validateSearchBeneficiary() {
        BeneficiaryManagementPage beneficiaryPage = new BeneficiaryManagementPage();
        beneficiaryPage.searchPatientId("190");

        Assert.assertTrue(beneficiaryPage.isPatientIdVisible(),"Searched Patient ID was not displayed on Beneficiary page after search");
    }
}



