package com.migros.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MigrosPetShopPage {
    WebDriver driver;

    public MigrosPetShopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#accept-all")
    public WebElement cookieAccept;

    @FindBy(css = "div[class='title-close-btn-wrapper'] fa-icon[class='ng-fa-icon']")
    public WebElement locationPopup;

    @FindBy(css = ".categories-icon")
    public WebElement categories;

    @FindBy(xpath = "//*[@id=\"header-categories--pet-shop-c-a0\"]")
    public WebElement petShopCategory;

    @FindBy(css = "mat-select")
    public WebElement sortingDropdown;

    @FindBy(xpath = "//span[contains(text(),'Önce En Düşük Fiyat')]")
    public WebElement lowToHighOption;

    @FindBy(css = ".mat-mdc-card")
    public List<WebElement> productCard;

    public WebElement getPrice(WebElement productCard) {
        return productCard.findElement(By.cssSelector("#price-no-discount"));
    }

    public WebElement getDiscountPrice(WebElement productCard) {
        return productCard.findElement(By.cssSelector("#sale-price"));
    }

    @FindBy(css = "div.product-price")
    public WebElement productPrice;
}
