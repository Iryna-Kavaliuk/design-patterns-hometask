package com.epam.ta.test;

import com.epam.ta.cloudProductFactory.ComputeEngineProduct;
import com.epam.ta.page.GoogleCloudHomePage;
import com.epam.ta.page.GoogleCloudPricingCalculatorPage;
import com.epam.ta.service.ComputeEngineProductBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.ta.test.SearchCloudPricingCalculatorTest.RESULTING_TERM;
import static com.epam.ta.test.SearchCloudPricingCalculatorTest.SEARCH_TERM;

public class GoogleCloudPricingCalculatorTest extends CommonConditions {
    private GoogleCloudHomePage homePage;
    private GoogleCloudPricingCalculatorPage calculatorPage;

    @Test(description = "Check Google Cloud Calculator is opened")
    public void GCPCalculatorIsOpenFromSearch() {
        homePage = new GoogleCloudHomePage(driver);
        homePage.openPage();
        homePage.enterSearchTerm(SEARCH_TERM);
        String targetLink = homePage.findTargetingLinkInSearchResults(RESULTING_TERM);
        calculatorPage = homePage.navigateWithTargetingLinkFromSearchResults(targetLink);
        calculatorPage.openPage();
        calculatorPage.closeCookiesAlert();
        Assert.assertTrue(calculatorPage.isPageOpened(), "Google Cloud Calculator is failed to open");
    }

    @Test(description = "Check estimation price is calculated correctly for Compute Engine",
        dependsOnMethods = "GCPCalculatorIsOpenFromSearch")
    public void GCPCalculatorPriceEstimatingTest() throws InterruptedException {

        ComputeEngineProduct testProduct = (ComputeEngineProduct) ComputeEngineProductBuilder.withAllDataFromProperty();
        calculatorPage.activateProductType(testProduct.getProductType());
        calculatorPage.enterNumberOfInstances(testProduct.getNumberOfInstances());
        calculatorPage.enterOperatingSystems(testProduct.getOperatingSystem());
        calculatorPage.enterProvisioningModel(testProduct.getProvisioningModel());
        calculatorPage.enterSeries(testProduct.getSeries());
        calculatorPage.enterMachineType(testProduct.getMachineType());
        calculatorPage.checkAddGPUsCheckbox();
        calculatorPage.enterGpuType(testProduct.getGpuType());
        calculatorPage.enterGpuNumber(testProduct.getNumberOfGPU());
        calculatorPage.enterLocalSSD(testProduct.getLocalSSD());
        calculatorPage.enterDataCenterLocation(testProduct.getDataCenterLocation());
        calculatorPage.enterCommittedUsage(testProduct.getCommittedUsage());
        calculatorPage.clickAddToEstimateButton();

        Assert.assertEquals(calculatorPage.getEstimationResult(), testProduct.calculateProductPrice(),
                "Estimations got manually and automatically are different!");
    }

}
