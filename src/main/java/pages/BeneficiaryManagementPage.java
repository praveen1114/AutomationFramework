package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class BeneficiaryManagementPage extends BasePage {
    // Locators
    private final By sideMenuBeneficiaryManagement = By.xpath("//a[contains(@class, 'menu-item') and not(contains(@class, 'active'))]");
    private final By beneficiaryManagementPageHeading = By.xpath("//h5[contains(text(), 'Beneficiary Management')]");
    private final By patientId= By.xpath("//button[contains(@class, 'search-btn') and text()='Patient ID']");
    private final By searchTextBox= By.xpath("//input[contains(@class, 'search-bar')]");
    private final By searchButton= By.xpath("//button[contains(@class, 'search-btn') and text()='Search']");
    private final By searchResult= By.xpath("//td[normalize-space(.)='P-190']");


    /* ===================== PAGE ACTIONS ===================== */

    public BeneficiaryManagementPage enterPatientId(String patientIdNumber) {
        logger.info("Entering patient Id");
        type(searchTextBox, patientIdNumber);
        return this;
    }

    public void clickBeneficiaryManagement() {
        logger.info("Clicking Beneficiary Management");
        click(sideMenuBeneficiaryManagement);
    }

    public boolean isBeneficiaryManagementDisplayed() {
        logger.info("Verifying successful navigation to beneficiary management page");
        return isDisplayed(beneficiaryManagementPageHeading);
    }

    public void clickPatientIdButton() {
        logger.info("Clicking patient Id button");
        click(patientId);
    }

    public void clickSearchButton() {
        logger.info("Clicking patient Id search button");
        click(searchButton);
    }

    public void clickSearchTextBox() {
        logger.info("Clicking search text box button");
        click(searchTextBox);
    }

    public boolean isPatientIdVisible() {
        logger.info("Verifying searched patient id is displayed");
        return isDisplayed(searchResult);
    }

    public void searchPatientId(String patientId)  {
        logger.info("Performing Search patient using patient id");
        clickBeneficiaryManagement();
        isBeneficiaryManagementDisplayed();
        clickPatientIdButton();
        clickSearchTextBox();
        enterPatientId(patientId);
        clickSearchButton();

    }
}
