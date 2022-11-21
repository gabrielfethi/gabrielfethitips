package testCases.tipsntricks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.XLUtils;

import java.io.IOException;

/**
 * The HelloWorld program implements an application that
 * simply displays "Hello World!" to the standard output.
 *
 * @author  gabrielfethi -- www.gabrielfethi.com
 * @link www.gabrielfethi.com
 * @lesson www.gabrielfethi.com////
 * @github .....
 *
 */

public class Tip4VerifyElementsPullFromExcelFile extends BaseTestClass {

    String pathCore        = System.getProperty("user.dir");
    String pathToExcelFile = "";

    @BeforeMethod
    public void accessPage(){
        driver.get("http://localhost:5500/sandbox/sandbox-QA-Tip-2-Verify-Element-Not-Visible.html");
    }

    @Test(dataProvider = "tip4verifyelementsonpage")
    public void verifyElementIsNotVisible(String elementID, String elementPath, String elementType){

        System.out.println("Tested Element: " + elementID);

        By locator = findElementByLocatorAndType(elementPath, elementType);

        verifyElementIsDisplayed(driver.findElement(locator));

    }


    // based on some requirements sometimes you want to make sure an element is not displayed

    // need to change this since element is passed -- and if not found it will give an error before!!!!

    void verifyElementIsDisplayed(WebElement element){

        boolean flag;

        try{
            Assert.assertTrue(element.isDisplayed());
            flag = true;
        }catch (Exception e){
            System.out.println(e);
            flag = false;
        }

        Assert.assertTrue(flag);
    }

    By findElementByLocatorAndType(String elementPath, String elementType){

        By locator = null;

        if(elementPath != null && elementType != null){
            switch (elementType){

                case "XPATH":
                    locator = By.xpath(elementPath);
                    break;

                case "LINKTEXT":
                    locator = By.linkText(elementPath);
                    break;
                case "CLASSNAME":
                    locator = By.className(elementPath);
                    break;
                case "CSS":
                    locator = By.cssSelector(elementPath);
                    break;
                case "ID":
                    locator = By.id(elementPath);
                    break;
                case "NAME":
                    locator = By.name(elementPath);
                    break;
                case "TAGNAME":
                    locator = By.tagName(elementPath);
                    break;
                case "PARTIAL-LINK-TEXT":
                    locator = By.partialLinkText(elementPath);
                    break;
            }
        }else{
            System.out.println("Element Path: " + elementPath + " -- || -- " + "Element Type: " + elementType);
        }

        return locator;
    }




    @DataProvider(name="tip4verifyelementsonpage")
    Object[][] getPageElements() throws IOException {

        return XLUtils.getExcelData("sheetlabel", "path");
    }

}
