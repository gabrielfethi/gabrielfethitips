package AutomationTips;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tip3ClickHighlightAndScrollToElement {

    // 1. Scroll to the element
    // 2. Highlight the element
    // 3. Click the element
    // 4. Take off the highlight

    WebDriver driver;
    JavascriptExecutor js;
    String testURL = "http://gabrielfethi.com/sandbox-Highlight-And-Scroll-To-Element.html";

    @BeforeMethod
    public void testSetup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(testURL);

        js = (JavascriptExecutor) driver;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyScrollingHighlightingClickingElement(){

        By elementLocator = By.xpath("//button[@id='test-button-8']");

        scrollClickHighlightElement(elementLocator);

    }

    public void scrollClickHighlightElement(By locator){

        WebElement element = driver.findElement(locator);

        // 1. Scroll to the element
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", element);

        testObserverThreadSleep();

        // 2. Highlight the element
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", element);
        testObserverThreadSleep();

        // 3. Click the element
        //element.click();
        js.executeScript("arguments[0].click()", element);
        testObserverThreadSleep();

        // 4. Take off the highlight
        js.executeScript("arguments[0].setAttribute('style', '');", element);
        testObserverThreadSleep();
    }

    void testObserverThreadSleep(){

        try{
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
