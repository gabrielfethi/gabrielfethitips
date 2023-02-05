package AutomationTips;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.XLUtils;

import java.io.IOException;
import java.time.Duration;

public class Tip4VerifyElementsPullFromExcel {

    /*
        1. Pull data from excel file
        2. Verify WebElement
        3. Create universal method to pull in all types of locators
        4. Final execution
 */

    WebDriver driver;
    JavascriptExecutor js;
    String testURL = "https://gabrielfethi.com/sandbox-Element-Visibility.html";

    String sheetLabel = "gabrielfethi";
    String path = System.getProperty("user.dir") + "/src/main/resources/sample-excel-file.xlsx";


    @BeforeClass
    public void testSetup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(testURL);

        js = (JavascriptExecutor) driver;
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "tip4")
    public void verifyElementPullFromExcelFile(String elementID, String elementPath, String elementType){

        System.out.println("Element ID: " + elementID);
        System.out.println("Element Path: " + elementPath);
        System.out.println("Element Type: " + elementType);
        System.out.println("******************************");

        Assert.assertTrue(driver.findElement(findElementByLocatorAndType(elementPath, elementType)).isDisplayed());

    }

    By findElementByLocatorAndType(String elementPath, String elementType){

        By locator = null;

        if(elementPath != null && elementType != null){

            switch(elementType){

                case "XPATH":
                    locator = By.xpath(elementPath);
                    break;

                case "ID":
                    locator = By.id(elementPath);
                    break;

                case "CLASSNAME":
                    locator = By.className(elementPath);
                    break;

                case "CSS":
                    locator = By.cssSelector(elementPath);
                    break;

                case "NAME":
                    locator = By.name(elementPath);
                    break;

                case "TAGNAME":
                    locator = By.tagName(elementPath);
                    break;

                case "LINKTEXT":
                    locator = By.linkText(elementPath);
                    break;

                case "PARTIAL-LINK-TEXT":
                    locator = By.partialLinkText(elementPath);
                    break;

            }


        }

        return locator;
    }

    @DataProvider(name="tip4")
    Object[][] getElements() throws IOException{

        return XLUtils.getExcelData(sheetLabel, path);

    }
}















