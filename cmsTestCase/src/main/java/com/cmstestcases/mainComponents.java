package com.cmstestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class mainComponents {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        handlePrivacyScreen(driver);
        performLogin(driver);
        selectOfficeStation(driver);

        // Uncomment the line below to close the WebDriver instance
        // driver.quit();
    }

    public static WebDriver initializeChromeDriverWithHeaders() {
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

    // Use if DNS does not work
    public static void handlePrivacyScreen(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the "Hide advanced" button
        WebElement hideAdvancedButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("details-button")));
        hideAdvancedButton.click();

        // Click on the "Proceed to 10.0.25.37 (unsafe)" link
        WebElement proceedLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("proceed-link")));
        proceedLink.click();
    }

    public static void performLogin(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username_input")));

        if (usernameElement != null) {
            System.out.println("Found Username Input");
            usernameElement.sendKeys("arkcase-admin@arkcase.org");

            WebElement passwordElement = driver.findElement(By.id("password_input"));
            passwordElement.sendKeys("@rKc@3e");

            WebElement loginButton = driver.findElement(By.id("submit"));
            loginButton.click();

            WebDriverWait waitNewPage = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement newPageElement = waitNewPage.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-toggle")));

            if (newPageElement != null) {
                System.out.println("Opening!");
            } else {
                System.out.println("Mediation not Opened");
            }
        } else {
            System.out.println("Invalid Username Element");
        }
    }

    public static void selectOfficeStation(WebDriver driver) {
        WebElement stationDropdown = driver.findElement(By.id("station"));
        Select dropdown = new Select(stationDropdown);

        // Assuming "Office Station" is the value you want to select
        dropdown.selectByVisibleText("Gaborone");
    }

    public static void performClaims(WebDriver driver, String leaveOption, String demotionOption) {
        // Locate the multiselect & click the dropdown button
        WebElement dropdownButton = driver.findElement(By.cssSelector("button.btn.error"));
        dropdownButton.click();

        // Locate the option to be selected based on the provided text and select it
        WebElement option = driver.findElement(By.xpath("//a[text()='" + leaveOption + "']"));
        option.click();

        // Locate the option to be selected based on the provided text and select it
        WebElement option2 = driver.findElement(By.xpath("//a[text()='" + demotionOption + "']"));
        option2.click();
        try {
            Thread.sleep(2000); // Wait for 1 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the dropdown
        dropdownButton.click();
    }

    public static void setDate(WebDriver driver, String date) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the input element for the date picker
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.moment-picker-input")));

        // Click on the input field to focus it
        dateInput.click();

        // Clear any existing date
        dateInput.clear();

        // Input the new date
        dateInput.sendKeys(date);

        // Press Enter to confirm the date
        dateInput.sendKeys(Keys.ENTER);

        System.out.println("Date set to: " + date);
    }
}
