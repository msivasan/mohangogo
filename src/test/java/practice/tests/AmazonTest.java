package practice.tests;

import base.ElementFinder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AmazonTest {

    private WebDriver driver;
    private ElementFinder finder;

    @BeforeClass
    public void setup() {

        /*
            If the following driver version doesn't work with your Chrome version
            see https://sites.google.com/a/chromium.org/chromedriver/downloads
            and update it as needed.
        */

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        finder = new ElementFinder(driver);
        driver.get("https://www.amazon.com/");


    }

    //@Test
    public void sloganPresent() {

        finder.findAndSelect(By.id("searchDropdownBox"), "Books");
        finder.findAndSetText(By.id("twotabsearchtextbox"), "data");
        finder.findAndClick(By.cssSelector("input[type='submit']"));

        WebElement title = finder.findElement(By.cssSelector("span[class=\"a-size-medium a-color-base a-text-normal\"]"));
        System.out.println("  %%%%%   " + title.getText());

        WebElement price = finder.findElement(By.cssSelector("span[class=\"a-price\"]"));
        System.out.println("  %%%%%   " + price.getText());

        WebElement author = finder.findElement(By.cssSelector("a[class=\"a-size-base a-link-normal\"]"));
        System.out.println("  %%%%%   " + author.getText());

        WebElement releaseDate = finder.findElement(By.cssSelector("span[class=\"a-size-base a-color-secondary a-text-normal\"]"));
        System.out.println("  %%%%%   " + releaseDate.getText());


    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.close();
        }
    }
}
