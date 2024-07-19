package com.cmstestcases;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;


import static com.cmstestcases.mainComponents.*;

public class labourTestCase {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        navigateToSitePage(driver);
        handlePrivacyScreen(driver);
        performLogin(driver);
        performMediation(driver);
        // Uncomment the line below to close the WebDriver instance
        // driver.quit();
    }


    // Test-1
    private static void navigateToSitePage(WebDriver driver) {
        driver.get("https://10.0.25.126/arkcase/home.html#!/dashboard");
        //https://labour-uat.gov.bw/arkcase/home.html#!/dashboard
    }

    private static void performMediation(WebDriver driver) {
        WebDriverWait waitNew = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement newButton = waitNew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'New')]")));
        newButton.click();

        WebDriverWait waitReferralButton = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement referralButton = waitReferralButton.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class, 'fa fa-exclamation-triangle')]")));
        referralButton.click();

        // Call the selectApplicant
        selectApplicant(driver);

        // Call the selectRespondent
        selectRespondent(driver);

        // Call the performConducts
        perfoAllegedMediation(driver, "Overtime", "Paid Public Holidays");

        // Call the selectOfficeStation
        selectOfficeStation(driver);

        String descriptionText = "Labour Test Case -1";
        addDescription(driver, descriptionText);

        // Add this line to scroll down before saving
        scrollDown(driver);

        saveDispute(driver);

    }

    private static void selectApplicant(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the input to select applicant
        WebElement initiatorInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Applicant']")));
        initiatorInput.click();
        try {
            Thread.sleep(2000); // Wait for 1 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Select People (Parties)
        WebElement selectPeopleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @ng-value='true']")));
        selectPeopleRadioButton.click();

        // Click on the input to select existing person
        WebElement personInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("person")));
        personInput.click();
        try {
            Thread.sleep(2000); // Wait for 1 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Select the search box, type "adm"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type in to search']")));
        searchBox.sendKeys("adm");

        // Click on the search button
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and @ng-click='queryExistingItems()']")));
        searchButton.click();
        try {
            Thread.sleep(2000); // Wait for 1 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /// Iterate through the table and click on any row
        List<WebElement> cells = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[role='rowgroup'] div.ui-grid-row div.ui-grid-cell")));
        if (!cells.isEmpty()) {
            Random random = new Random();
            WebElement randomCell = cells.get(random.nextInt(cells.size()));
            randomCell.click();
            System.out.println("Selected random cell: " + randomCell.getText());
        } else {
            System.out.println("No cells found in the grid");
        }

        // Click Submit Result
        WebElement submitResultButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Submit Result')]")));
        submitResultButton.click();

        // Click Save Person
        WebElement savePersonButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary' and @ng-click='onClickOk()']")));
        savePersonButton.click();
    }

    private static void selectRespondent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the input to select applicant
        WebElement initiatorInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Respondent']")));
        initiatorInput.click();
        try {
            Thread.sleep(2000); // Wait for 1 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Select Organization (Parties)
        WebElement selectOrganizationRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @ng-model='switchModalsOrganisation' and @ng-value='true']")));
        selectOrganizationRadioButton.click();

        // Click on the input to select existing person
        WebElement personInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("organization")));
        personInput.click();
        try {
            Thread.sleep(2000); // Wait for 1 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Select the search box, type "adm"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type in to search']")));
        searchBox.sendKeys("adm");

        // Click on the search button
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and @ng-click='queryExistingItems()']")));
        searchButton.click();

        /// Iterate through the table and click on any row
        List<WebElement> cells = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[role='rowgroup'] div.ui-grid-row div.ui-grid-cell")));
        if (!cells.isEmpty()) {
            Random random = new Random();
            WebElement randomCell = cells.get(random.nextInt(cells.size()));
            randomCell.click();
            System.out.println("Selected random cell: " + randomCell.getText());
        } else {
            System.out.println("No cells found in the grid");
        }

        // Click Submit Result
        WebElement submitResultButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Submit Result')]")));
        submitResultButton.click();

        // Click Save Person
        WebElement savePersonButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary' and @ng-click='onClickOk()']")));
        savePersonButton.click();
    }

    private static void perfoAllegedMediation(WebDriver driver, String leaveOption, String demotionOption) {
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

    public static void selectOfficeStation(WebDriver driver) {
        WebElement stationDropdown = driver.findElement(By.id("station"));
        Select dropdown = new Select(stationDropdown);

        // Assuming "Office Station" is the value you want to select
        dropdown.selectByVisibleText("Gaborone Block 8");
    }


    public static void addDescription(WebDriver driver, String descriptionText) {
        // Click on the description to expand it
        WebElement descriptionToggle = driver.findElement(By.cssSelector("div.panel-heading a[data-toggle='collapse'][href='#collapseDesc']"));
        descriptionToggle.click();

        // Wait for the description textbox to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement descriptionTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.note-editable")));

        // Clear the textbox if it has any existing text
        descriptionTextbox.clear();

        // Add description text to the textbox
        descriptionTextbox.sendKeys(descriptionText);
    }

    public static void saveDispute(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the "Save Referral" button using its unique attributes
        WebElement saveReferralButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@ng-show='!isEdit' and text()='Save Referral']")
        ));

        // Click the "Save Referral" button
        saveReferralButton.click();

        // Optionally, you can add a wait to ensure the save action is completed
        wait.until(ExpectedConditions.invisibilityOf(saveReferralButton));
    }

    private static void scrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Add a small delay to allow the page to settle after scrolling
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
