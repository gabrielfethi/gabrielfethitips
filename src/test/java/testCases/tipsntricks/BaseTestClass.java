package testCases.tipsntricks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static utilities.Driver.getDriver;

public class BaseTestClass {

    public WebDriver driver;

    @BeforeSuite
    public void SuiteLevelSetup(){

        driver = getDriver();

    }

    @AfterSuite
    public void SuiteLevelExit(){
        driver.quit();
    }


}
