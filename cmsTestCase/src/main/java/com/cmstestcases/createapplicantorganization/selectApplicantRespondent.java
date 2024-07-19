package com.cmstestcases.createapplicantorganization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static com.cmstestcases.mainComponents.initializeChromeDriverWithHeaders;

public class selectApplicantRespondent {

    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        selectApplicant(driver);
        selectRespondent(driver);
        // Uncomment the line below to close the WebDriver instance
        // driver.quit();
    }

    public static void selectApplicant(WebDriver driver) {
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
            Thread.sleep(5000); // Wait for 1 seconds
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

    public static void selectRespondent(WebDriver driver) {
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

        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[role='rowgroup'] div.ui-grid-row")));
        if (!rows.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(rows.size());
            WebElement randomRow = rows.get(randomIndex);
            randomRow.click();
            System.out.println("Selected random row: " + (randomIndex + 1));
        } else {
            System.out.println("No rows found in the grid");
        }

        // Click Submit Result
        WebElement submitResultButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Submit Result')]")));
        submitResultButton.click();

        // Click Save Person
        WebElement savePersonButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary' and @ng-click='onClickOk()']")));
        savePersonButton.click();
    }
}
