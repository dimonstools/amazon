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

    public static void main(){
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        WebDriverWait wait = (new WebDriverWait(driver, Duration.ofSeconds(10)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Hello, sign in']")));

        Book book = new Book(driver);

        MainPage mainPage = new MainPage(driver);
        mainPage.setFilter("Books");
        mainPage.searchEntities("Java");
//        for (String element : expectedBook){
//            System.out.println(element);
//        }

//        MainPage mainPage = new MainPage(driver);
//        mainPage.searchBook("Java");
//        List<String> listOfBooks = book.bookDescription(1);
//        for (String element : listOfBooks){
//            System.out.println(element);
//        }
//        System.out.println(listOfBooks.contains(expectedBook));
    }

    public static void close(){
        driver.quit();
    }

//    public static boolean compareAllBooks(String link){
//        Book book = new Book(driver);
//        MainPage mainPage = new MainPage(driver);
//        String expectedBook = book.bookDescription(link);
//        System.out.println("This is expected Book:"+"\n"+expectedBook);
////        for (String element : expectedBook) {
////            System.out.println(element);
////        }
//        mainPage.setFilter("Books");
//        mainPage.searchEntities("Java");
//        boolean result = false;
//        for (int i=1; i<19; i++) {
//            if(i!=4){
//                if(i!=12){
//            List<String> listOfBooks = book.bookDescription();
//
//                    System.out.println("\n"+"This is searched Book:"+"\n");
//            for (String element : listOfBooks) {
//                System.out.println(element);
//            }
//            if(listOfBooks.contains(expectedBook)) result=true;
////                    System.out.println("Size expected book list: "+ expectedBook.size());
////                    System.out.println("Size searched book list: "+ listOfBooks.size());
//            listOfBooks.clear();
//                }}
//        }
//        return result;
//    }

}
