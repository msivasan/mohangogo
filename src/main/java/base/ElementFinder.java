package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;


/**
 * Utility class to wrap Selenium Find methods.
 */
public class ElementFinder {
    private WebDriver driver;

    public ElementFinder(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By by) {

        WebElement value = null;

        try {
            value = driver.findElement(by);
        } catch (NoSuchElementException e) {
            // the element can not be found
        }

        return value;
    }

    public void findAndSelect(By by, String name) {

        Select type = new Select(findElement(by));
        type.selectByVisibleText(name);

    }

    public void findAndSetText(By by, String name) {

        WebElement value = null;

        value = findElement(by);
        value.sendKeys(name);

    }

    public void findAndClick(By by) {
        WebElement value = null;

        value = findElement(by);
        value.click();

    }


    public void IfExistsThenClick(By by) {

        Set<String> ids = driver.getWindowHandles();
        Iterator<String> it = ids.iterator();
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        // Here we will wait until element is not visible, if element is visible then it will return web element
        // or else it will throw exception
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.click();

    }


}
