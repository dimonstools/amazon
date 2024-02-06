import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public MainPage searchBook(String bookTitle){
        searchFilter.click();
        booksOption.click();
        inputField.sendKeys(bookTitle+ Keys.ENTER);
        return this;
    }


}