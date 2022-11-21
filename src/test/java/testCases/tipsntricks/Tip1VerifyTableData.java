package testCases.tipsntricks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Tip1VerifyTableData extends BaseTestClass {


    @Test(description = "Verify the Total of the Listed TableData")
    public void verifyTableData(){

        // TEST CASE
        // 1. Access web page
        // 2. Verify that the total price column is correct by calculating price * units for each row

        // ARRANGE
        // . access web page
        driver.get("http://127.0.0.1:5500/qa-lesson/table-data-sample.html");
        // . get data for each row 1 column 3 and 4
        // . multiply and verify column 5 total price for each row

        // ACT
        // . get total number of rows and columns, exclude the header
        List<WebElement> rows    = driver.findElements(By.xpath("//tbody//tr"));
        List<WebElement> columns = driver.findElements(By.xpath("//tbody//td"));

        // total rows no header and total columns
        int totalRows = rows.size();
        int totalColumns = columns.size()/totalRows;

        for (int i = 1; i <= totalRows ; i++) {

            String itemPriceText = driver.findElement(By.xpath("//tr[" + i + "]//td[3]")).getText().replace("$", "");
            int itemPrice = Integer.valueOf(itemPriceText);

            String itemUnitsText = driver.findElement(By.xpath("//tr[" + i + "]//td[4]")).getText();
            int itemUnits = Integer.valueOf(itemUnitsText);

            String itemTotalPriceText = driver.findElement(By.xpath("//tr[" + i + "]//td[5]")).getText().replace("$", "");
            int itemTotalPrice = Integer.valueOf(itemTotalPriceText);

            System.out.println("---------" + i + "---------------");
            System.out.println("itemPrice: " + itemPrice );
            System.out.println("itemUnits: " + itemUnits );
            System.out.println("itemTotalPrice: " + itemTotalPrice );


            Assert.assertTrue((itemPrice * itemUnits) == itemTotalPrice);

        }

    }



}
