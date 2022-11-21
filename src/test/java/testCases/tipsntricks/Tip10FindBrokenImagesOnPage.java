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
 * The HelloWorld program implements an application that
 * simply displays "Hello World!" to the standard output.
 *
 * @author  gabrielfethi -- www.gabrielfethi.com
 * @link www.gabrielfethi.com
 * @lesson www.gabrielfethi.com////
 * @github .....
 *
 */

public class Tip10FindBrokenImagesOnPage extends BaseTestClass {

    // Get all link elements and store them in a variable
    // Verify each link gets a 200 status code
    // Check for each link situation
    // put all links into a List Set -- so it is unique -- since set does not accept dups
    // iterate through the set and verify that each http request contains 200 or some other logic
    //


    @Test
    public void verifyAllImagesWork(){

        driver.get("https://the-internet.herokuapp.com/broken_images");

        //
        SoftAssert softAssert = new SoftAssert();

        // get all the links on a web page
        List<WebElement> allImageWebElements = driver.findElements(By.tagName("img"));
        List<String> urlList = getAllSrcAttributes(allImageWebElements);

        for(String s: urlList){
            System.out.println("IMAGE: " + s);
            softAssert.assertTrue(verifyImageStatus(s),"IMAGE FAILED: " + s);
        }

        softAssert.assertAll();

    }


    public boolean verifyImageStatus(String url){

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

    public ArrayList<String> getAllSrcAttributes(List<WebElement> srcAttributes){

        ArrayList<String> urlList = new ArrayList<>();

        for(WebElement e: srcAttributes){
            urlList.add(e.getAttribute("src"));
        }

        return urlList;
    }



}
