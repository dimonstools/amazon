import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private static WebDriver driver;

    public Book(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    @FindBy(xpath = "\"//a/i[@class=\\\"a-icon a-icon-addon p13n-best-seller-badge\\\"]\"")
//    private static WebElement bestSeller;
//
//    @FindBy(xpath = "//span[@class=\"author notFaded\"]/..")
//    private static WebElement author;
//
//    @FindBy(xpath = "//span[@id=\"productTitle\"]")
//    private static WebElement title;
//
//    @FindBy(xpath = "//div[@id=\"rpi-attribute-book_details-publication_date\"]/div[3]/span")
//    private WebElement date;
//
//    @FindBy(xpath = "//div[@data-csa-c-slot-id=\"seriesBulletWidget_feature_div\"]")
//    private WebElement series;
//
//    @FindBy(xpath = "//span[@class=\"slot-price\"]")
//    private static WebElement priceElement;


    public static List<String> bookDescription(int itemNumber){

        String path = "//div[@data-index=\"%d\" and @data-component-type=\"s-search-result\"]";
        WebElement firstBook = driver.findElement(By.xpath(String.format(path, itemNumber+1)));
        String str = firstBook.getText();
        List<String> list = new ArrayList<>();
        List<String> bookDesc = new ArrayList<>();
        String[] stringers = str.split("\n");
        for (String words : stringers){
            list.add(words);
        }
//        List<String> authorList = new ArrayList<>();
//        stringers = list.get(2).split("by ");
//        for (String words : stringers){
//            authorList.add("by "+words);
//        }
//        stringers = authorList.get(1).split("et al.");
//        for (String words : stringers){
//            authorList.add(words+"Trisha Gee et al.");
//        }
///////////////////////////////////////////////////////////////////////////////////////////

        List<WebElement> authorElements = driver.findElements(By.xpath((String.format(path, itemNumber+1)+"//span[@class=\"a-size-base\"]")));
        String author ="";
        for (WebElement link:authorElements){
           String lol = link.getText();
           author = author+" "+lol;
        }
        author=author.replace(" ,", ",");
        author=author.replace(" by", "by");


        //////////////////////////////////////////////////////////////////////
        if (list.get(0).contains("Best Seller")){
            String bs = "It is a Best Seller";
            String priceToRent = list.get(8)+"."+list.get(9);
            String priceToBuy = list.get(10)+"."+list.get(11);
            bookDesc.add(bs);
            bookDesc.add(list.get(1));
//            bookDesc.add(authorList.get(2));
            bookDesc.add(author);
            bookDesc.add(priceToRent);
            bookDesc.add(priceToBuy);
        } else {
            String bs = "This isn't Best Seller";
            String priceToRent = list.get(7)+"."+list.get(8);
            String priceToBuy = list.get(9)+"."+list.get(10);
            bookDesc.add(bs);
            bookDesc.add(list.get(0));
            bookDesc.add(author);
            bookDesc.add(priceToRent);
            bookDesc.add(priceToBuy);
        }
        return bookDesc;
    }

    public static List<String> bookDescription(String url){
        driver.get(url);
        WebElement bestSeller = driver.findElement(By.xpath("//a/i[@class=\"a-icon a-icon-addon p13n-best-seller-badge\"]"));
        WebElement author = driver.findElement(By.xpath("//span[@class=\"author notFaded\"]/.."));
        WebElement title = driver.findElement(By.xpath("//span[@id=\"productTitle\"]"));
        WebElement date = driver.findElement(By.xpath("//div[@id=\"rpi-attribute-book_details-publication_date\"]/div[3]/span"));
        WebElement series = driver.findElement(By.xpath("//div[@data-csa-c-slot-id=\"seriesBulletWidget_feature_div\"]"));
        WebElement priceElement = driver.findElement(By.xpath("//span[@class=\"slot-price\"]"));
        String[] stringers = priceElement.getText().split(" - ");
        List<String> price = new ArrayList<>();
        price.add(stringers[0]+" to rent");
        price.add(stringers[1]+" to buy");
//        for (String words : stringers){
//            price.add("\n"+words);
//        }

//        String str = series.getText()+" | "+author.getText()+" | "+date.getText();
        String str = author.getText();
//        System.out.println("Part of: Head First (44 books) | by Kathy Sierra, Bert Bates, et al. | Jun 21, 2022");
        str=str.replace(" (Author)", "");
        str=str.replace("& 1 more", "et al.");
        String bs;
        if (bestSeller.isDisplayed()){bs="It is a Best Seller";} else bs = "This isn't Best Seller";
        List<String> bookDesc = new ArrayList<>();
        bookDesc.add(bs);
        bookDesc.add(title.getText());
        bookDesc.add(str);
        bookDesc.addAll(price);
        return bookDesc;
    }
}
