package testCases.tipsntricks;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
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

public class Tip3ClickHighlightAndScrollToElement extends BaseTestClass {


    @BeforeMethod
    public void beforeEachMethod(){

    }



    @Test
    public void verifyElementIsVisibleHighlightAndScroll(){

        driver.get("http://localhost:5500/sandbox/sandbox-QA-Tip-2-Verify-Element-Not-Visible.html");

        verifyElementIsVisibleScrollAndHighlight(By.xpath("//button[@id='test-button-8']"));


    }


    void verifyElementIsVisibleScrollAndHighlight(By locator){

        //define element
        WebElement element = driver.findElement(locator);

        //scroll to element
        js.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", inline: \"nearest\"})", element);


        // highlight element
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color:yellow;border: 2px solid red;");

        // wait until element is visible
        wait.until(ExpectedConditions.elementToBeClickable(element));

        explicitWait(3);

        // click element
        element.click();

        // take highlight off element
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");


    }


    void scrollToElement(WebElement element){
        //scroll to element
        js.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", inline: \"nearest\"})",element);
    }

    void highlightElementOn(WebElement element){
        js.executeScript("arguments[0].setAttribute('style','arguments[1]);",element, "color:yellow;border: 2px solid red;");
    }

    void highlightElementOff(WebElement element){
        js.executeScript("arguments[0].setAttribute('style','arguments[1]);",element, "");
    }

}
