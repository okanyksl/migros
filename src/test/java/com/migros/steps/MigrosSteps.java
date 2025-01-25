package com.migros.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.migros.pages.MigrosPetShopPage;
import com.migros.utils.Driver;
import com.migros.utils.PageUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MigrosSteps {
    WebDriver driver = Driver.getDriver();
    MigrosPetShopPage migrosPage = new MigrosPetShopPage(driver);

    @Given("Migros websitesini açarım")
    public void migros_websitesini_acarim() {
        driver.get("https://www.migros.com.tr/");
        PageUtils.waitForPageLoad(driver);
        PageUtils.waitForElementToBeClickable(driver, migrosPage.cookieAccept);
        PageUtils.jsClick(driver, migrosPage.cookieAccept);
        PageUtils.jsClick(driver, migrosPage.locationPopup);
    }

    @When("{string} kategorisine giderim")
    public void kategorisine_giderim(String category) {
        Actions action = new Actions(driver);
        action.moveToElement(migrosPage.categories).perform();     
        // Pet Shop kategorisinin tıklanabilir olmasını bekle
        PageUtils.waitForElementToBeClickable(driver, migrosPage.petShopCategory);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", migrosPage.petShopCategory);
    }

    @Then("Pet Shop sayfasının açıldığını doğrularım")
    public void pet_shop_sayfasinin_acildigini_dogrularim() {
       PageUtils.waitForPageLoad(driver);
       Actions action = new Actions(driver);
       action.moveByOffset(0, -100).perform();
       String currentUrl =  driver.getCurrentUrl();
       Assert.assertEquals(currentUrl, "https://www.migros.com.tr/pet-shop-c-a0");
    }

    @When("Ürünleri düşük fiyattan yükseğe doğru sıralarım")
    public void urunleri_dusuk_fiyattan_yuksege_dogru_siralarim() {
        PageUtils.waitForPageLoad(driver);
        PageUtils.waitForElementToBeClickable(driver, migrosPage.sortingDropdown);
        migrosPage.sortingDropdown.click();
        PageUtils.waitForElementToBeClickable(driver, migrosPage.lowToHighOption);
        migrosPage.lowToHighOption.click();

    }

    @Then("Ürünlerin fiyatlara göre sıralandığını doğrularım")
    public void urunlerin_fiyatlara_gore_siralandigini_dogrularim() {
        List<Double> allPrices = new ArrayList<>();
    
        List<WebElement> productCards = PageUtils.visibilityOfAllElements(driver, migrosPage.productCard);
    
        for (WebElement productCard : productCards) {
            WebElement salePriceElement = null;
            try {
                salePriceElement = migrosPage.getDiscountPrice(productCard);
            } catch (Exception e) {

            }
            WebElement noDiscountPriceElement = null;
            try {
                noDiscountPriceElement = migrosPage.getPrice(productCard);
            } catch (Exception e) {

            }
    
            String priceText = null;
            if (salePriceElement != null) {
                priceText = salePriceElement.getText();
            } else if (noDiscountPriceElement != null) {
                priceText = noDiscountPriceElement.getText();
            }
    
            if (priceText != null && !priceText.isEmpty()) {
                priceText = priceText.replace("TL", "").replace(",", ".").trim();
                double price = Double.parseDouble(priceText);
                allPrices.add(price);
                System.out.println("Fiyat: " + price);
            }
        }
        
        List<Double> sortedPrices = new ArrayList<>(allPrices);
        Collections.sort(sortedPrices);
        Assert.assertEquals(allPrices, sortedPrices, "Ürünler fiyatlara göre sıralı değil!");
    }

    @After
    public void takeScreenshotOnFailure(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("screenshots/" + scenario.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Driver.quitDriver();
    }
}
