package AutomationTips;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Tip1VerifyTableData {

    /*
    1. Add BeforeMethod, AfterMethod, Test --> from TestNG
        BeforeMethod -- create the driver instance
        AfterMethod -- quit the driver
        Test -- implement test script
    2. Add the test logic for one row
    3. Iterate through the rows and verify
    4. Implement logic to top the iteration with the row numbers
     */

    WebDriver driver;
    String testURL = "https://gabrielfethi.com/sandbox-dynamic-table-data.html";

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
    public void verifyTableData(){

        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
        int rowNumber = rows.size();

        System.out.println("Total Row Numbers: " + rowNumber);

        for (int i = 1; i <= rowNumber ; i++) {

            String itemPriceText = driver.findElement(By.xpath("//tbody//tr[" + i + "]//td[3]")).
                    getText().replace("$", "");
            int itemPrice = Integer.valueOf(itemPriceText);

            String itemUnitText = driver.findElement(By.xpath("//tbody//tr[" + i + "]//td[4]")).
                    getText().replace("$", "");
            int itemUnit = Integer.valueOf(itemUnitText);

            String itemTotalPriceText = driver.findElement(By.xpath("//tbody//tr[" + i + "]//td[5]")).
                    getText().replace("$", "");
            int itemTotalPrice = Integer.valueOf(itemTotalPriceText);

            System.out.println("Row Number: " + i +  " Item Price: " + itemPrice);
            System.out.println("Row Number: " + i +  " Item Unit: " + itemUnit);
            System.out.println("Row Number: " + i +  " Item Total Price: " + itemTotalPrice);

            Assert.assertTrue((itemPrice * itemUnit) == itemTotalPrice);

        }
    }

}
