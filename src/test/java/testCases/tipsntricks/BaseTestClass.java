package testCases.tipsntricks;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static utilities.Driver.getDriver;

public class BaseTestClass {

    public WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;


    @BeforeSuite
    public void SuiteLevelSetup(){

        driver = getDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    }

    @AfterSuite
    public void SuiteLevelExit(){
        driver.quit();
    }

    public void explicitWait(long timeSeconds){
        try{

            Thread.sleep(timeSeconds * 1000);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
