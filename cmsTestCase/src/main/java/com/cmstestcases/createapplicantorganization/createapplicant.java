package com.cmstestcases.createapplicantorganization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.cmstestcases.cctTestCase.handlePrivacyScreen;
import static com.cmstestcases.cctTestCase.performLogin;

public class createapplicant {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        navigateToCCTSitePage(driver);
        handlePrivacyScreen(driver);
        performLogin(driver);
        // performTask(driver);
        // Uncomment the line below to close the WebDriver instance
        // driver.quit();
    }

    private static WebDriver initializeChromeDriverWithHeaders() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> headers = new HashMap<>();
        headers.put("ngrok-skip-browser-warning", "1");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.setCapability("chrome:customHeaders", headers);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static void navigateToCCTSitePage(WebDriver driver) {
        driver.get("https://10.0.25.37/arkcase/home.html#!/dashboard");
        //https://competition-tribunal-uat.gov.bw/arkcase/home.html#!/dashboard
    }
}
