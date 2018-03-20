package myprojects.automation.assignment4.pages;

import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainClientPage {

    private WebDriver webDriver;

    private By allProducts = By.className("all-product-link");
    private By logoImage = By.className("logo");
    private By mainPageBlock = By.xpath("//*[@id=\"main\"]/div[1]/h1");
    private By searchField = By.className("ui-autocomplete-input");
    private By searchResults = By.xpath("//*[@id=\"main\"]/h2");
    private By products = By.id("products");

    private By productName = By.cssSelector("#wrapper > div > nav > ol > li:nth-child(2) > a > span");
    private By productPrice = By.cssSelector("span[itemprop='price']");
    private By productCatalog = By.cssSelector("h1[itemprop='name']");
    private By productQte = By.cssSelector("#product-details > div.product-quantities > span");

    private By allProductsLocator = By.className("product-thumbnail");
    private By addToBucketButton = By.className("add-to-cart");
    private By modalDialog = By.id("myModalLabel");
    private By orderButton = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/a");
    private By bucketLogo = By.xpath("//*[@id=\"main\"]/div/div[1]/div[1]/div[1]/h1");
    private By cardProductsCount = By.className("cart-products-count");

    private By priceOnBucketPage = By.xpath("//*[@id=\"main\"]/div/div[1]/div[1]/div[2]/ul/li/div/div[2]/div[2]/span");
    private By productNameOnBucketPage = By.xpath("//*[@id=\"main\"]/div/div[1]/div[1]/div[2]/ul/li/div/div[2]/div[1]/a");

    private By startOrderButton = By.xpath("//*[@id=\"main\"]/div/div[2]/div/div[2]/div/a");

    private By myData = By.xpath("//*[@id=\"checkout-personal-information-step\"]/h1");

    private By firstName = By.name("firstname");
    private By lastName = By.name("lastname");
    private By email = By.name("email");
    private By continueButton = By.name("continue");
    private By address = By.xpath("//*[@id=\"checkout-addresses-step\"]/h1");

    private By address1 = By.name("address1");
    private By postcode = By.name("postcode");
    private By city = By.name("city");
    private By confirmAdress = By.name("confirm-addresses");

    private By deliver = By.xpath("//*[@id=\"checkout-delivery-step\"]/h1");
    private By confirmDeliveryOption = By.name("confirmDeliveryOption");
    private By pay = By.xpath("//*[@id=\"checkout-payment-step\"]/h1");
    private By paymentOption = By.id("payment-option-1");
    private By approvment = By.id("conditions_to_approve[terms-and-conditions]");
    private By next = By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button");
    private By created = By.xpath("//*[@id=\"content-hook_order_confirmation\"]/div/div/div/h3");

    public void open() {
        webDriver.get(Properties.getBaseUrl());
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoImage));
    }

    public MainClientPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickByLinkAllProd() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        webDriver.findElement(allProducts).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
    }

    public void clickOnRandomProduct() {
        webDriver.findElements(allProductsLocator).get(0).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productCatalog));
    }

    public void addProductToBucket() {
        webDriver.findElement(addToBucketButton).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalDialog));
    }

    public void goToOrder() {
        webDriver.findElement(orderButton).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bucketLogo));
    }

    public boolean isBucketCountOne() {
        return webDriver.findElement(cardProductsCount).getText().contains("1");
    }

    public String getName() {
        return webDriver.findElement(productCatalog).getText();
    }

    public String getPrice() {
        return webDriver.findElement(productPrice).getText();
    }

    public String getNameOnBucketPage() {
        return webDriver.findElement(productNameOnBucketPage).getText();
    }

    public String getPriceOnBucketPage() {
        return webDriver.findElement(priceOnBucketPage).getText();
    }

    public void goToOrderPage() {
        webDriver.findElement(startOrderButton).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(myData));
    }

    public void fillName(String name) {
        webDriver.findElement(firstName).sendKeys(name);
    }

    public void fillLastName(String name) {
        webDriver.findElement(lastName).sendKeys(name);
    }

    public void fillEmail(String name) {
        webDriver.findElement(email).sendKeys(name);
    }

    public void clickContinue() {
        webDriver.findElement(continueButton).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(address));
    }

    public void fillAddress(String name) {
        webDriver.findElement(address1).sendKeys(name);
    }

    public void fillPostCode(String name) {
        webDriver.findElement(postcode).sendKeys(name);
    }

    public void fillTown(String name) {
        webDriver.findElement(city).sendKeys(name);
    }

    public void clickConfirmAdress() {
        webDriver.findElement(confirmAdress).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(deliver));
    }

    public void clickConfirmDeliveryOption() {
        webDriver.findElement(confirmDeliveryOption).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pay));
    }

    public void clickConfirmPaymentOption() {
        webDriver.findElement(paymentOption).click();
        webDriver.findElement(approvment).click();
        webDriver.findElement(next).click();
    }

    public boolean isSuccess() {
        return webDriver.findElement(created).getText().contains("ВАШ ЗАКАЗ ПОДТВЕРЖДЁН");
    }
}
