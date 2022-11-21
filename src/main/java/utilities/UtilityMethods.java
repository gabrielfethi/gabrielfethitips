package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilityMethods {

    static String fileName;

    public static void createFileAndContent(String fileName, String fileContent){

        String filePath = "/Users/gabrielfethiguloglu/Projects/GabeTipsPrep/src/main/java/reportsCreated/" + fileName + "-" + getTodaysDate() + ".html";

        try {
            File reportFile = new File(filePath);
            if (reportFile.createNewFile()) {
                System.out.println("File created: " + reportFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //write to the file
        writeToFile(fileContent, filePath);


    }

    public static void writeToFile(String htmlContent, String filePath){

        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(htmlContent);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String getTodaysDate(){

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime todaysDate = LocalDateTime.now();

        return dateFormat.format(todaysDate);
    }

    public static By findElementByLocatorAndType(String elementPath, String elementType){

        By locator = null;

        if(elementPath != null && elementType != null){
            switch (elementType){

                case "XPATH":
                    locator = By.xpath(elementPath);
                    break;

                case "LINKTEXT":
                    locator = By.linkText(elementPath);
                    break;
                case "CLASSNAME":
                    locator = By.className(elementPath);
                    break;
                case "CSS":
                    locator = By.cssSelector(elementPath);
                    break;
                case "ID":
                    locator = By.id(elementPath);
                    break;
                case "NAME":
                    locator = By.name(elementPath);
                    break;
                case "TAGNAME":
                    locator = By.tagName(elementPath);
                    break;
                case "PARTIAL-LINK-TEXT":
                    locator = By.partialLinkText(elementPath);
                    break;
                }
            }else{
            System.out.println("Element Path: " + elementPath + " -- || -- " + "Element Type: " + elementType);
        }

        return locator;
    }

    public static void verifyElementIsNotDisplayed(WebDriver driver, By locator){

        boolean flag;

        try{
            Assert.assertTrue(driver.findElement(locator).isDisplayed());
            flag = false;
        }catch (Exception e){
            System.out.println(e);
            flag = true;
        }

        Assert.assertTrue(flag);
    }

    public static void verifyElementIsDisplayed(WebDriver driver, By locator){

        Assert.assertTrue(driver.findElement(locator).isDisplayed());

    }


}
