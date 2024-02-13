import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select")
    private WebElement searchFilter;

    @FindBy(xpath = "//select/option[text()='Books']")
    private WebElement booksOption;

    @FindBy(xpath = "//input[@type=\"text\"]")
    private WebElement inputField;

    public MainPage setFilter(String filter){
        searchFilter.click();
        String path = "//select/option[text()='%s']";
        WebElement getFilter = driver.findElement(By.xpath(String.format(path, filter)));
        getFilter.click();
        return this;
    }

    public MainPage searchBook(String bookTitle){
        inputField.clear();
        inputField.sendKeys(bookTitle+ Keys.ENTER);
        return this;
    }


    public boolean isLinkHere(String url){
        List<WebElement> listOfElements = driver.findElements(By.xpath("//span[@data-component-type=\"s-search-results\"]//a[@class=\"a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal\"]"));
        boolean result = false;
        for (WebElement link:listOfElements){
            String lol = link.getAttribute("href");
            boolean contains = lol.contains(url);
            if(contains) result=true;
        }
        System.out.println(result);
        return result;
    }


}