import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Search {

private WebDriver driver;

private MainPage mainPage = new PageFactory().initElements(driver, MainPage.class);

private MainClass mainClass = new PageFactory().initElements(driver, MainClass.class);

    @BeforeTest
    public void setUp(){
        mainClass.main();
    }

    @AfterTest
    public void tearDown(){
        mainClass.close();
    }

    @Test
    public void isPresence(){
        boolean result = MainClass.compareAllBooks("https://www.amazon.com/Head-First-Java-Brain-Friendly-Guide/dp/1491910771/ref=sr_1_1?keywords=Java&qid=1707310752&s=books&sr=1-1");
        System.out.println(result);
        Assert.assertTrue(result);
    }

}
