package com.cmstestcases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class cctTestCase {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        navigateToSitePage(driver);
        handlePrivacyScreen(driver);
        performLogin(driver);
        performLodgement(driver);
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

    // Test-1
    private static void navigateToSitePage(WebDriver driver) {
        driver.get("https://10.0.25.37/arkcase/login#!/dashboard");
        //https://competition-tribunal-uat.gov.bw/arkcase/login#!/dashboard
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


    // Test-2
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
                System.out.println("Opening Referral!");
            } else {
                System.out.println("Mediation not Opened");
            }
        } else {
            System.out.println("Invalid Username Element");
        }
    }

    private static void performLodgement(WebDriver driver) {
        WebDriverWait waitNew = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement newButton = waitNew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'New')]")));
        newButton.click();

        WebDriverWait waitReferralButton = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement referralButton = waitReferralButton.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class, 'fa fa-folder')]")));
        referralButton.click();

        // Call the selectApplicant
        selectApplicant(driver);

        // Call the selectRespondent
        selectRespondent(driver);

        // Call the performConducts
        performConducts(driver, "Price Fixing", "Resale Price Maintenance");

        // Call the selectOfficeStation
        selectOfficeStation(driver);

        String descriptionText = "CCT Test Case -1";
        addDescription(driver, descriptionText);

        saveReferral(driver);

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
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[role='rowgroup'] div.ui-grid-row")));
        for (WebElement row : rows) {
            row.click(); // Click on the row
            break; // Exit loop after clicking on the first row
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

        // Iterate through the table and click on any row
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[role='rowgroup'] div.ui-grid-row")));
        for (WebElement row : rows) {
            row.click(); // Click on the row
            break; // Exit loop after clicking on the first row
        }

        // Click Submit Result
        WebElement submitResultButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Submit Result')]")));
        submitResultButton.click();

        // Click Save Person
        WebElement savePersonButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary' and @ng-click='onClickOk()']")));
        savePersonButton.click();
    }

    private static void performConducts(WebDriver driver, String leaveOption, String demotionOption) {
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
        dropdown.selectByVisibleText("Gaborone");
    }


    /* public static void selectOfficeStation(WebDriver driver) {
         WebElement stationDropdown = driver.findElement(By.id("station"));
         Select dropdown = new Select(stationDropdown);
         Get all options from the dropdown
         List<WebElement> options = dropdown.getOptions();
         Generate a random index
         Random rand = new Random();
         int index = rand.nextInt(options.size());
         Select the option at the random index
         dropdown.selectByIndex(index);
        }
      */

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

    public static void saveReferral(WebDriver driver) {
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
}
