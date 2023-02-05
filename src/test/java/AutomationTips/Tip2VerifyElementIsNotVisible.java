package AutomationTips;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tip2VerifyElementIsNotVisible {

    // 1. Verify Element Is Visible
    // 2. Verify Element Is Not Visible
    // 3. Create methods outside of the test annotation

    WebDriver driver;
    String testURL = "http://gabrielfethi.com/sandbox-Element-Visibility.html";

    @BeforeMethod
    public void testSetup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(testURL);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyElementVisibility(){

        By elementLocator = By.xpath("//button[@id='test-button-4']");
        By elementLocatorAbsence = By.xpath("//button[@id='test-button-4393939']");

        verifyElementVisibility(elementLocator);
        verifyElementIsNotVisible(elementLocatorAbsence);


    }

    // 3. Create methods outside of the test annotation
    void verifyElementVisibility(By locator){

        Assert.assertTrue(driver.findElement(locator).isDisplayed());

    }

    void verifyElementIsNotVisible(By locator){

        boolean flag = false;

        try{

            driver.findElement(locator);

        }catch(Exception e){
            flag = true;
        }

        Assert.assertTrue(flag);
    }

}
