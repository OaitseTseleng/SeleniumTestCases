package com.cmstestcases.createtask;

import com.cmstestcases.mainComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static com.cmstestcases.cctTestCase.handlePrivacyScreen;
import static com.cmstestcases.cctTestCase.performLogin;
import static com.cmstestcases.mainComponents.initializeChromeDriverWithHeaders;

public class createtask {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        navigateToSitePage(driver);
        mainComponents.handlePrivacyScreen(driver);
        mainComponents.performLogin(driver);
        performTask(driver);
        // Uncomment the line below to close the WebDriver instance
        // driver.quit();
    }

    private static void navigateToSitePage(WebDriver driver) {
        driver.get("https://10.0.25.126/arkcase/login#!/dashboard");
        //https://labour-uat.gov.bw/arkcase/login
    }

    private static void performTask(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on "New" button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'New')]"))).click();

        // Click on "Referral" button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class, 'fa fa-check-circle')]"))).click();

        // Click on "Please Choose Assignee" input
        wait.until(ExpectedConditions.elementToBeClickable(By.id("assignee"))).click();

        // Type "Thabang" in the search field
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search for the user or group to assign to this task.']"))).sendKeys("Thabang");

        // Click on the search button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and @ng-click='queryExistingItems()']"))).click();

        // Select the searched item
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'ui-grid-cell-contents')][contains(text(), 'thabang teddy')]"))).click();

        // Select another item
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'ui-grid-cell-contents')][contains(text(), 'TEST@ARKCASE.ORG')]"))).click();

        // Click on confirm
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'btn btn-primary')][contains(text(), 'Confirm')]"))).click();

        // Select Dispute
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@ng-model='config.data.attachedToObjectType']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='string:COMPLAINT';", dropdown);
        ((JavascriptExecutor) driver).executeScript("angular.element(arguments[0]).triggerHandler('change')", dropdown);

        // Click on the Case # input field
        wait.until(ExpectedConditions.elementToBeClickable(By.id("associateNumber"))).click();

        // Type 'adm' in the search box
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search for the mediation or dispute to this task.']"))).sendKeys("adm");

        // Click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@ng-click='queryExistingItems()']"))).click();

        // Select a random row
        WebElement randomRow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ui-grid-row ng-scope'][position()=" + (new Random().nextInt(10) + 1) + "]")));
        String rowText = randomRow.getText();
        System.out.println("Selected row " + rowText);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomRow);
        wait.until(ExpectedConditions.elementToBeClickable(randomRow)).click();

        // Click on confirm
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'btn btn-primary')][contains(text(), 'Confirm')]"))).click();

        // Enter subject
        WebElement subjectInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Subject']")));
        subjectInput.sendKeys("Update Thero Monagana to Thero Pilane");
        System.out.println("Entered subject:" + subjectInput.getAttribute("value"));

        // Select task type
        WebElement taskTypeSelect = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@ng-model='config.data.type']")));
        Select taskType = new Select(taskTypeSelect);
        taskType.selectByVisibleText("General");
        System.out.println("Selected task type: General");

        // Click on Save Task button
        WebElement saveTaskButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveButton")));
        saveTaskButton.click();
        System.out.println("Clicked on Save Task button");

        System.out.println("Task creation process completed");
    }
}
