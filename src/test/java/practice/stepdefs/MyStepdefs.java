package practice.stepdefs;

import base.Book;
import base.ElementFinder;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepdefs {

    private WebDriver driver;
    private ElementFinder finder;
    private Book bk;

    @Given("New Chrome Browser")
    public void newChromeBrowser() {


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


    @When("Enter Book Name")
    public void enterBookName() {
        finder.findAndSelect(By.id("searchDropdownBox"), "Books");
        finder.findAndSetText(By.id("twotabsearchtextbox"), "data");
    }

    @And("Click Search Button")
    public void clickSearchButton() {
        finder.findAndClick(By.cssSelector("input[type='submit']"));
    }


    @Then("Read Book Details")
    public void readBookDetails() {

        bk = new Book();

        WebElement title = finder.findElement(By.cssSelector("span[class=\"a-size-medium a-color-base a-text-normal\"]"));
        bk.setTitle(title.getText().trim());

        WebElement price = finder.findElement(By.cssSelector("span[class=\"a-price\"]"));
        bk.setPrice(price.getText().trim());

        WebElement author = finder.findElement(By.cssSelector("a[class=\"a-size-base a-link-normal\"]"));
        bk.setName(author.getText().trim());

        WebElement releaseDate = finder.findElement(By.cssSelector("span[class=\"a-size-base a-color-secondary a-text-normal\"]"));
        bk.setRelease(releaseDate.getText().trim());


    }

    @And("Close the Browser")
    public void closeTheBrowser() {

        System.out.println("  Title   :" + bk.getTitle());
        System.out.println("  Author  :" + bk.getName());
        System.out.println("  Price   :" + bk.getPrice());
        System.out.println("  Release :" + bk.getRelease());

        driver.quit();
    }
}
