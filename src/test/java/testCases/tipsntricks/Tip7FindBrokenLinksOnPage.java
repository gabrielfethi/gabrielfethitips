package testCases.tipsntricks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  gabrielfethi -- www.gabrielfethi.com
 * @link www.gabrielfethi.com
 * @lesson www.gabrielfethi.com////
 * @github .....
 *
 * HTTP STATUS CODES --> https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
 *
 * LIST OF STATUS CODES WE DON'T WANT TO SEE
 * 400 - Bad Request
 * 403 - Forbidden
 * 408 - Request Time Out
 * 504 - Service Unavailable
 *
 * WE ALWAYS WANT TO SEE --> 200
 *
 * -----------
 * STEPS:
 * 1. Collect all the links on a page.
 * 2. Check each link and verify they are valid and filter mail_to, is not empty, tel out.
 * 4. Check each link and verify that response code is valid
 *
 */

public class Tip7FindBrokenLinksOnPage extends BaseTestClass {

    // Get all link elements and store them in a variable
    // Verify each link gets a 200 status code
    // Check for each link situation
    // put all links into a List Set -- so it is unique -- since set does not accept dups
    // iterate through the set and verify that each http request contains 200 or some other logic
    //


    @Test
    public void verifyAllLinksWork(){

        driver.get("http://localhost:5500/sandbox/sandbox-QA-Tip-7-Find-Broken-Links-On-Page.html");

        //
        SoftAssert softAssert = new SoftAssert();

        // get all the links on a web page
        List<WebElement> allLinkWebElements = driver.findElements(By.tagName("a"));
        List<String> urlList = getAllHrefAttributes(allLinkWebElements);

        for(String s: urlList){
            System.out.println("LINK: " + s);
            softAssert.assertTrue(verifyLinkStatus(s),"LINK FAILED: " + s);
        }

        softAssert.assertAll();

    }


    public boolean verifyLinkStatus(String url){

        boolean flag = false;
        int statusCode;

        try {

            URL urlToTest = new URL(url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) urlToTest.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            statusCode = httpURLConnection.getResponseCode();

            if(statusCode == 200){
                flag = true;
            }else {
                flag = false;
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return flag;
    }

    public ArrayList<String> getAllHrefAttributes(List<WebElement> hrefAttributes){

        ArrayList<String> urlList = new ArrayList<>();

        for(WebElement e: hrefAttributes){
            urlList.add(e.getAttribute("href"));
        }

        return urlList;
    }



}
