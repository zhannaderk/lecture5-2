package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.pages.MainClientPage;
import myprojects.automation.assignment4.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class CreateProductTest extends BaseTest {

    private MainClientPage mainClientPage;
    private String price;
    private String productName;

    @DataProvider
    public Object[][] getLoginData() {
        return new String[][]{
                {"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}
        };
    }

//    @Test(dataProvider = "getLoginData")
//    public void createNewProduct(String login, String password) {
//        actions.login(login, password);
//        productData = ProductData.generate();
//        ProductPage productPage = new ProductPage(driver);
//        productPage.open();
//        productPage.clickProductSubmenu();
//        Assert.assertTrue(productPage.isDisplayedHeaderProducts(), "Products header is not displayed");
//        productPage.openNewProductPageByClick();
//        productPage.fillNameOfProduct(productData.getName());
//        productPage.fillNumberOfProduct(productData.getQty());
//        productPage.fillPriceOfProduct(productData.getPrice());
//        productPage.toggleSwitcher();
//        Assert.assertTrue(productPage.isSuccsessAlertDisplayed(), "Failed toggle.");
//        productPage.closeAlert();
//        productPage.saveButton();
//        Assert.assertTrue(productPage.isSuccsessAlertDisplayed(), "Failed to save");
//        productPage.closeAlert();
//    }


    @Test(dataProvider = "getLoginData")
    public void createNewProduct(String login, String password) {
        actions.login(login, password);
        mainClientPage = new MainClientPage(driver);
        mainClientPage.open();
        mainClientPage.clickByLinkAllProd();
        mainClientPage.clickOnRandomProduct();
        mainClientPage.addProductToBucket();
        productName = mainClientPage.getName();
        price = mainClientPage.getPrice();
        mainClientPage.goToOrder();
    }

    @Test(dependsOnMethods = "createNewProduct")
    public void checkOrderedProduct() {
        Assert.assertTrue(mainClientPage.isBucketCountOne(), "Count not matched");
        Assert.assertTrue(productName.equalsIgnoreCase(mainClientPage.getNameOnBucketPage()), "Name not matched");
        Assert.assertTrue(price.equalsIgnoreCase(mainClientPage.getPriceOnBucketPage()), "Price not matched");
        mainClientPage.goToOrderPage();
        mainClientPage.fillName("asdf");
        mainClientPage.fillLastName("sdfag");
        mainClientPage.fillEmail("adm" + new Random().nextInt(1000) + "@gmail.com");
        mainClientPage.clickContinue();
        mainClientPage.fillAddress("Kiev");
        mainClientPage.fillPostCode("45474");
        mainClientPage.fillTown("Kiev");
        mainClientPage.clickConfirmAdress();
        mainClientPage.clickConfirmDeliveryOption();
        mainClientPage.clickConfirmPaymentOption();
        Assert.assertTrue(mainClientPage.isSuccess(), "Error to create order.");
    }
}
