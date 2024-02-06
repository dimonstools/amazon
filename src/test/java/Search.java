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

private MainPage mainPage;

private MainClass mainClass;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.amazon.com/");
        WebDriverWait wait = (new WebDriverWait(driver, Duration.ofSeconds(10)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Hello, sign in']")));
        mainPage = new PageFactory().initElements(driver, MainPage.class);
        mainPage.searchBook("Java");
        mainClass = new PageFactory().initElements(driver, MainClass.class);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void showBookDescription(){
        List<String> books = MainClass.bookDescription(1);
        System.out.println(books.get(0));
        Assert.assertEquals("Best Seller", books.get(0));
    }

    @Test
    public void isPresence(){
        Assert.assertTrue(mainPage.isLinkHere("https://www.amazon.com/Head-First-Java-Brain-Friendly-Guide/dp/1491910771/ref=sr_1_1?keywords=Java"));
    }

}
