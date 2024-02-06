import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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
        mainPage.searchBook("Java");
        List<String> list = bookDescription(1);
        }

    public static List<String> bookDescription(int itemNumber){
        String path = "//div[@data-index=\"%d\" and @data-component-type=\"s-search-result\"]";
        WebElement firstBook = driver.findElement(By.xpath(String.format(path, itemNumber+1)));
        String str = firstBook.getText();
        List<String> list = new ArrayList<>();
        String[] stringers = str.split("\n");
        for (String words : stringers){
            list.add(words);
        }
        if (list.get(0).contains("Best Seller")){
            System.out.println("It is a Best Seller");
            String priceToRent = list.get(8)+"."+list.get(9);
            String priceToBuy = list.get(10)+"."+list.get(11);
            System.out.println(list.get(1)+"\n"+list.get(2)+"\n"+priceToRent+"\n"+priceToBuy);
        } else {
            System.out.println("This isn't Best Seller");
            String priceToRent = list.get(7)+"."+list.get(8);
            String priceToBuy = list.get(9)+"."+list.get(10);
            System.out.println(list.get(0)+"\n"+list.get(1)+list.get(2)+"\n"+priceToRent+"\n"+priceToBuy);
        }
        return list;
    }



}
