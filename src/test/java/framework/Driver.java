package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Driver {

    public static void get(String url) {
        MyRunner.webDriver.get(url);
        MyRunner.webDriver.manage().window().maximize();
        wait(1);
    }

    public static WebElement getElement(String elementName)   {
        WebElement webElement = null;
        ArrayList<String> objectData = MyRunner.allObjects.get(elementName);

        try {
            switch (objectData.get(0))  {
                case "ID":
                    webElement= MyRunner.webDriver.findElement(By.id(objectData.get(1)));
                    break;
                case "ClassName":
                    webElement= MyRunner.webDriver.findElement(By.className(objectData.get(1)));
                    break;
                case "LinkText":
                    webElement= MyRunner.webDriver.findElement(By.linkText(objectData.get(1)));
                    break;
                case "CSS_Selector":
                    webElement= MyRunner.webDriver.findElement(By.cssSelector(objectData.get(1)));
                    break;
                case "TagName":
                    webElement= MyRunner.webDriver.findElement(By.tagName(objectData.get(1)));
                    break;
                case "Name":
                    webElement= MyRunner.webDriver.findElement(By.name(objectData.get(1)));
                    break;
                case "PartialLinkText":
                    webElement= MyRunner.webDriver.findElement(By.partialLinkText(objectData.get(1)));
                    break;
                case "Xpath":
                    webElement= MyRunner.webDriver.findElement(By.xpath(objectData.get(1)));
                    break;
            }
        }
        catch (NullPointerException ex) {
            Report.fail(ex.getMessage());
        }
        return webElement;
    }

    public static void wait(int seconds) {
        synchronized (MyRunner.webDriver)   {
            try {
                MyRunner.webDriver.wait(seconds * 1000L);
            }
            catch (InterruptedException e)  {
                e.printStackTrace();
            }
        }
    }

    public static List<WebElement> getElements(String elementName)   {
        List<WebElement> webElements = new ArrayList<>();
        ArrayList<String> objectData = MyRunner.allObjects.get(elementName);

        try {
            switch (objectData.get(0))  {
                case "ID":
                    webElements= MyRunner.webDriver.findElements(By.id(objectData.get(1)));
                    break;
                case "ClassName":
                    webElements= MyRunner.webDriver.findElements(By.className(objectData.get(1)));
                    break;
                case "LinkText":
                    webElements= MyRunner.webDriver.findElements(By.linkText(objectData.get(1)));
                    break;
                case "CSS_Selector":
                    webElements= MyRunner.webDriver.findElements(By.cssSelector(objectData.get(1)));
                    break;
                case "TagName":
                    webElements= MyRunner.webDriver.findElements(By.tagName(objectData.get(1)));
                    break;
                case "Name":
                    webElements= MyRunner.webDriver.findElements(By.name(objectData.get(1)));
                    break;
                case "PartialLinkText":
                    webElements= MyRunner.webDriver.findElements(By.partialLinkText(objectData.get(1)));
                    break;
                case "Xpath":
                    webElements= MyRunner.webDriver.findElements(By.xpath(objectData.get(1)));
                    break;
            }
        }
        catch (NullPointerException ex) {
            Report.fail(ex.getMessage());
        }
        return webElements;
    }

    public static void safeClick(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)MyRunner.webDriver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public static String getCurrentUrl() {
        return MyRunner.webDriver.getCurrentUrl();
    }

    public static String getTitle() {
        return MyRunner.webDriver.getTitle();
    }

    public static String getPageSource() {
        return MyRunner.webDriver.getPageSource();
    }

    public static void close() {
        MyRunner.webDriver.close();
    }

    public static void quit() {
        MyRunner.webDriver.quit();
    }

    public static Set<String> getWindowHandles() {
        return MyRunner.webDriver.getWindowHandles();
    }

    public static String getWindowHandle() {
        return MyRunner.webDriver.getWindowHandle();
    }

    public static void refresh() {
        MyRunner.webDriver.navigate().refresh();
        wait(1);
    }

    public static TargetLocator switchTo() {
        return MyRunner.webDriver.switchTo();
    }

    public static Navigation navigate() {
        return MyRunner.webDriver.navigate();
    }

    public static Options manage() {
        return MyRunner.webDriver.manage();
    }

    public static void sendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.TAB);
    }
}
