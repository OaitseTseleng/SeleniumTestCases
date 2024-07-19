package com.cmstestcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.cmstestcases.mainComponents.initializeChromeDriverWithHeaders;

public class createPerson {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        //createPerson(driver, "Sebego", "Mpho", "Lerato", "+26777058292");
        // Uncomment the line below to close the WebDriver instance
        // driver.quit();
    }

    public static void createPerson(WebDriver driver, String lastName, String middleName, String firstName, String phoneNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the input to select applicant
        WebElement initiatorInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Applicant']")));
        initiatorInput.click();

        // Select People (Parties)
        WebElement selectPeopleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @ng-value='true']")));
        selectPeopleRadioButton.click();

        // Click on "Add new Person" button
        WebElement addNewPersonButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Add new Person')]")));
        addNewPersonButton.click();

        // Fill in the last name
        WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("familyName")));
        lastNameInput.sendKeys(lastName);

        // Fill in the middle name
        WebElement middleNameInput = driver.findElement(By.id("middleName"));
        middleNameInput.sendKeys(middleName);

        // Fill in the first name
        WebElement firstNameInput = driver.findElement(By.id("givenName"));
        firstNameInput.sendKeys(firstName);

        // Fill in the phone number
        WebElement phoneNumberInput = driver.findElement(By.cssSelector("input[placeholder='Enter Phone Number eg +26771234567']"));
        phoneNumberInput.sendKeys(phoneNumber);

        // Select "Mobile" from the dropdown
        WebElement phoneSubTypeDropdown = driver.findElement(By.id("defaultPhoneSubType"));
        Select dropdown = new Select(phoneSubTypeDropdown);
        dropdown.selectByVisibleText("Mobile");

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        System.out.println("Person saved successfully.");

        // Click the "Save Person" button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='saveButton']//span[contains(text(), 'Save Person')]")));
        saveButton.click();

        // Click Save Person
        WebElement savePersonButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary' and @ng-click='onClickOk()']")));
        savePersonButton.click();


    }
}
