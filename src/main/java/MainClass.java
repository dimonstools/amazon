import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainClass {


    private static WebDriver driver;

    public MainClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void main(String[] args){
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        WebDriverWait wait = (new WebDriverWait(driver, Duration.ofSeconds(10)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Hello, sign in']")));

        MainPage mainPage = new MainPage(driver);
        Book book = new Book(driver);
        mainPage.searchBook("Java");
        List<String> listOfBooks = book.bookDescription(1);
        for (String element : listOfBooks){
            System.out.println(element);
        }
        List<String> expectedBook = book.bookDescription("https://www.amazon.com/Head-First-Java-Brain-Friendly-Guide/dp/1491910771/ref=sr_1_1?keywords=Java&qid=1707310752&s=books&sr=1-1");
        for (String element : expectedBook){
            System.out.println(element);
        }
        System.out.println(listOfBooks.contains(expectedBook));
    }

    public static void close(){
        driver.quit();
    }



}
