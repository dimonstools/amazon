import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private static WebDriver driver;

    public Book(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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
        if (list.get(0).contains("Best Seller")){
            System.out.println("It is a Best Seller");
            String priceToRent = list.get(8)+"."+list.get(9);
            String priceToBuy = list.get(10)+"."+list.get(11);
            bookDesc.add(list.get(1) + "\n" + list.get(2) + "\n" + priceToRent + "\n" + priceToBuy);
        } else {
            System.out.println("This isn't Best Seller");
            String priceToRent = list.get(7)+"."+list.get(8);
            String priceToBuy = list.get(9)+"."+list.get(10);
            bookDesc.add(list.get(0)+"\n"+list.get(1)+list.get(2)+"\n"+priceToRent+"\n"+priceToBuy);
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
        price.add("\n"+stringers[0]+" to rent");
        price.add("\n"+stringers[1]+" to buy");
//        for (String words : stringers){
//            price.add("\n"+words);
//        }

        String str = series.getText()+" | "+author.getText()+" | "+date.getText();
//        System.out.println("Part of: Head First (44 books) | by Kathy Sierra, Bert Bates, et al. | Jun 21, 2022");
        str=str.replace(" (Author)", "");
        str=str.replace("& 1 more", "et al.");
        System.out.println(str);
        String bs;
        if (bestSeller.isDisplayed()){bs="It is a Best Seller";} else bs = "This isn't Best Seller";
        List<String> bookDesc = new ArrayList<>();
        bookDesc.add(bs+"\n"+title.getText()+"\n"+str+price);
        return bookDesc;
    }
}
