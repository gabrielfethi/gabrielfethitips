package AutomationTips;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Tip6VerifyAllLinksWorkFineDataProvider {

    // 1. Open webpage and get all link web elements
    // 2. Get All HrefAttributes from these web elements
    // 3. Create a Link Verification Method
    // 4. Iterate through the href links and do a soft assert verification

    WebDriver driver;
    JavascriptExecutor js;
    String testURL = "https://gabrielfethi.com/sandbox-Broken-Links-On-Page.html";


    @BeforeClass
    public void testSetup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        js = (JavascriptExecutor) driver;
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "linkURLProvider")
    public void verifyAllLinks(String testURLtoWebPage){

        driver.get(testURLtoWebPage);

        boolean flag = false;
        SoftAssert softAssert = new SoftAssert();


        List<WebElement> allLinkWebElements = driver.findElements(By.tagName("a"));
        List<String> hrefLinkList = getAllHrefAttributes(allLinkWebElements);

        for(String s: hrefLinkList){
            flag = verifyLinkStatus(s);
            softAssert.assertTrue(flag,"LINK FAILED: " + s );
        }

        softAssert.assertAll();

    }

    public ArrayList<String> getAllHrefAttributes(List<WebElement> allLinks){

        ArrayList<String> hrefList = new ArrayList<>();

        for(WebElement e: allLinks){

            hrefList.add(e.getAttribute("href"));

        }

        return hrefList;
    }

    public boolean verifyLinkStatus(String url){

        boolean flag = false;
        int statusCode;


        try{
            URL urlToTest = new URL(url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) urlToTest.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            statusCode = httpURLConnection.getResponseCode();

            if(statusCode == 200){
                flag = true;
            }

        }catch (Exception e){
            System.out.println(e);
        }

        return flag;
    }

    @DataProvider(name = "linkURLProvider" )
    public Object[][] dpMethod(){
        return new Object[][]{
                {"https://gabrielfethi.com/sandbox-Broken-Links-On-Page.html"},
                {"https://gabrielfethi.com/sandbox-Broken-Links-On-Page.html"},
                {"https://gabrielfethi.com/sandbox-Broken-Links-On-Page.html"}
        };
    }

}
