package com.cmstestcases.closeComplaint;

import org.openqa.selenium.WebDriver;

import static com.cmstestcases.mainComponents.*;

public class closecomplaints {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        navigateToSitePage(driver);
        handlePrivacyScreen(driver);
        performLogin(driver);
        // Uncomment the line below to close the WebDriver instance
        driver.quit();
    }
    private static void navigateToSitePage(WebDriver driver) {
        driver.get("https://10.0.25.127/arkcase/login#!/dashboard");
        //https://industrial-court-uat.gov.bw/arkcase/login#!/dashboard
    }
}
