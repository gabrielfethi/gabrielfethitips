package testCases.tipsntricks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static reportTemplates.ReportLinkVerificationTemplate.getHtmlCode;
import static reportTemplates.ReportLinkVerificationTemplate.setDynamicHTMLPortion;
import static utilities.UtilityMethods.createFileAndContent;
import static utilities.UtilityMethods.getTodaysDate;

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

public class Tip8FindBrokenLinksOnPageAndCreateHtmlReport extends BaseTestClass {

    // Get all link elements and store them in a variable
    // Verify each link gets a 200 status code
    // Check for each link situation

    @AfterClass
    public void createLinkHTMLReport(){

        String htmlCode = getHtmlCode(getTodaysDate());

        System.out.println(htmlCode);

        createFileAndContent("test-file", htmlCode);

    }

    @Test (dataProvider = "web-page-provider")
    public void verifyAllLinksWorkAndCreateHTMLReport(String pageLink){

        boolean testFlag;

        driver.get(pageLink);

        //
        SoftAssert softAssert = new SoftAssert();

        // get all the links on a web page
        List<WebElement> allLinkWebElements = driver.findElements(By.tagName("a"));
        List<String> urlList = getAllHrefAttributes(allLinkWebElements);

        for(String s: urlList){
            System.out.println("LINK: " + s);

            testFlag = verifyLinkStatus(s);
            setDynamicHTMLPortion(pageLink, s, testFlag);


            softAssert.assertTrue(testFlag,"LINK FAILED: " + s);
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

    public ArrayList<String> getAllHrefAttributes(List<WebElement> aTags){

        ArrayList<String> urlList = new ArrayList<>();

        for(WebElement e: aTags){
            urlList.add(e.getAttribute("href"));
        }

        return urlList;
    }

    @DataProvider (name = "web-page-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{"http://localhost:5500/sandbox/sandbox-QA-Tip-7-Find-Broken-Links-On-Page.html"},
                {"http://localhost:5500/sandbox/sandbox-QA-Tip-7-Find-Broken-Links-On-Page.html"},
                {"http://localhost:5500/sandbox/sandbox-QA-Tip-7-Find-Broken-Links-On-Page.html"}
        };
    }

}
