package testCases.tipsntricks;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

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

public class Tip2 extends BaseTestClass {


    @Test
    public void verifyElementIsNotVisible(){


    }


    // based on some requirements sometimes you want to make sure an element is not displayed


    void verifyElementIsNotDisplayed(WebElement element){

        boolean flag;

        try{
            Assert.assertTrue(element.isDisplayed());
            flag = false;
        }catch (Exception e){
            System.out.println(e);
            flag = true;
        }

        Assert.assertTrue(flag);
    }

}
