package com.cmstestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.cmstestcases.mainComponents.initializeChromeDriverWithHeaders;

public class createOrg {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        //eg ..createOrganization(driver, "Spar", "+2673567679");
        // Uncomment the line below to close the WebDriver instance
        // driver.quit();
    }

    public static void createOrganization(WebDriver driver, String name, String phoneNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the input to select applicant
        WebElement initiatorInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Respondent']")));
        initiatorInput.click();

        // Select Organization (Parties)
        WebElement selectOrganizationRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @ng-model='switchModalsOrganisation' and @ng-value='true']")));
        selectOrganizationRadioButton.click();

        // Click on "Add new Person" button
        WebElement addNewPersonButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Add new Organization')]")));
        addNewPersonButton.click();

        // Fill in the last name
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("organizationName")));
        nameInput.sendKeys(name);

        // Select "OrganizationType" from the dropdown
        WebElement orgType = driver.findElement(By.id("orgType"));
        Select dropdownOrg = new Select(orgType);
        dropdownOrg.selectByVisibleText("Government");

        // Fill in the phone number
        WebElement phoneNumberInput = driver.findElement(By.cssSelector("input[placeholder='Enter Phone Number eg +26771234567']"));
        phoneNumberInput.sendKeys(phoneNumber);

        // Select "Mobile" from the dropdown
        WebElement phoneSubTypeDropdown = driver.findElement(By.cssSelector("select[ng-model*='organization'][ng-model*='subType']"));
        String script = "arguments[0].value = 'Mobile'; arguments[0].dispatchEvent(new Event('change'));";
        ((JavascriptExecutor) driver).executeScript(script, phoneSubTypeDropdown);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        System.out.println("Organization saved successfully.");

        // Click the "Save Person" button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='saveButton']//span[contains(text(), 'Save Organization')]")));
        saveButton.click();

        // Click Save Person
        WebElement savePersonButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary' and @ng-click='onClickOk()']")));
        savePersonButton.click();


    }

}
