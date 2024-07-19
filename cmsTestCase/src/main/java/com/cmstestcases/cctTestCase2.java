package com.cmstestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static com.cmstestcases.createapplicantorganization.selectApplicantRespondent.selectApplicant;
import static com.cmstestcases.createapplicantorganization.selectApplicantRespondent.selectRespondent;
import static com.cmstestcases.mainComponents.*;
import static com.cmstestcases.mainComponents.setDate;

public class cctTestCase2 {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriverWithHeaders();
        navigateToSitePage(driver);
        handlePrivacyScreen(driver);
        performLogin(driver);
        performReferral(driver);
        // Uncomment the line below to close the WebDriver instance
        // driver.quit();
    }
    private static void navigateToSitePage(WebDriver driver) {
        driver.get("https://10.0.25.37/arkcase/login#!/dashboard");
    }

    private static void performReferral(WebDriver driver) {
        WebDriverWait waitNew = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement newButton = waitNew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'New')]")));
        newButton.click();

        WebDriverWait waitReferralButton = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement referralButton = waitReferralButton.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class, 'fa fa-exclamation-triangle')]")));
        referralButton.click();

        // Call the selectApplicant
        selectApplicant(driver);
        //createPerson(driver, "Sebego", "Mpho", "Lerato", "77058922");

        // Call the selectRespondent
        selectRespondent(driver);
        //createOrganization(driver, "Spar", "3567679");

        // Select the office station
        selectOfficeStation(driver);

        // Select the claims
        performClaims(driver, "Leave", "Transfer");

        setDate(driver, "01/07/2024");

        selectParties(driver);

        selectOrgs(driver);

        // Save the case
        //clickSaveButton(driver);

    }

    public static void selectParties(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        // Define a helper method to click elements
        Consumer<By> clickElement = locator -> wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

        clickElement.accept(By.xpath("//a[@href='#openingClass']"));
        clickElement.accept(By.id("parties_people"));
        clickElement.accept(By.id("searchExisting"));
        clickElement.accept(By.id("person"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type in to search']")))
                .sendKeys("adm");

        clickElement.accept(By.xpath("//button[contains(text(), 'Search')]"));
        clickElement.accept(By.xpath("//div[@class='ui-grid-canvas']/div[5]"));
        clickElement.accept(By.xpath("//button[contains(text(), 'Submit Result')]"));

        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("types"))))
                .selectByVisibleText("Applicant");

        clickElement.accept(By.xpath("//button[contains(., 'Save Person')]"));
    }



    public static void selectOrgs(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        Consumer<By> clickElement = locator -> wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

        clickElement.accept(By.id("parties_organization"));
        clickElement.accept(By.xpath("//input[@placeholder='Search for organisation']"));
        clickElement.accept(By.id("organization"));
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type in to search']")));
        searchInput.sendKeys("adm");
        clickElement.accept(By.xpath("//button[contains(text(), 'Search')]"));
        clickElement.accept(By.xpath("//div[@class='ui-grid-canvas']/div[1]"));
        clickElement.accept(By.xpath("//button[contains(text(), 'Submit Result')]"));
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[contains(@class, 'input-md form-control')]"))))
                .selectByVisibleText("Respondent");

        // Click on "Save Organization"
        clickElement.accept(By.xpath("//button[contains(., 'Save Organization')]"));
    }

    public static void clickSaveButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='saveButton' and contains(@class, 'btn-primary')]")
        ));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(saveButton, "disabled", "disabled")));

        saveButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(), 'Save Case')]")));
    }

}
