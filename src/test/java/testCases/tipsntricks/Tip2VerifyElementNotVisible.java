package testCases.tipsntricks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Verify that an element is not visible.
 * This may occur especially if you have multiple user roles. This user roles may have access and visibilitiy on the same page.
 * A very important check is to verify that in example elements that should only be visible to Admin roles should not be visible to
 * standard roles.
 *
 * @author  gabrielfethi -- www.gabrielfethi.com
 * @link www.gabrielfethi.com
 * @lesson www.gabrielfethi.com////
 * @github .....
 *
 */

public class Tip2VerifyElementNotVisible extends BaseTestClass {


    @Test
    public void verifyElementIsNotVisible(){




    }


    // based on some requirements sometimes you want to make sure an element is not displayed

    // need to change this since element is passed -- and if not found it will give an error before!!!!

    void verifyElementIsNotDisplayed(WebDriver driver, By locator){

        boolean flag;

        try{
            Assert.assertTrue(driver.findElement(locator).isDisplayed());
            flag = false;
        }catch (Exception e){
            System.out.println(e);
            flag = true;
        }

        Assert.assertTrue(flag);
    }

    void verifyElementIsVisible(WebDriver driver, By locator){
        Assert.assertTrue(driver.findElement(locator).isDisplayed());
    }



}
