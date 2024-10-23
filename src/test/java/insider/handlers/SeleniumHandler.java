package insider.handlers;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class SeleniumHandler {

    protected static WebDriver webDriver;
    protected static WebDriverWait webDriverWait;
    private static final ConfigHandler configReader = new ConfigHandler();
    private static final long ONE_SECOND = 1000L;

    public static final SecureRandom random = new SecureRandom();

    private static Actions actions;

    //static JavascriptExecutor jse;

    public void runWebDriver() {
        selectBrowserType();
    }

    public void navigateToMainPage() {
        webDriver.get(configReader.getEnvironments(configReader.getEnvironment()).get("urlMain"));
    }

    public void navigateToQAPage() {
        webDriver.get(configReader.getEnvironments(configReader.getEnvironment()).get("urlQA"));
    }

    public void clickWebElement(WebElement element,int timeout) {
        new WebDriverWait(webDriver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    public static void scrollToElement(WebElement element, Actions actions) {
        actions.moveToElement(element);
        actions.perform();
    }

    public static void clickAndHold(WebElement element, Actions actions) {
        actions.moveToElement(element);
        actions.click().clickAndHold();
        actions.build().perform();
    }

    public String randomNumber(int stringLength) {
        char[] chars = "123456789".toCharArray();
        StringBuilder stringRandom = new StringBuilder();
        for (int i = 0; i < stringLength; i++) {
            stringRandom.append(chars[random.nextInt(chars.length)]);
        }
        return stringRandom.toString();
    }

    public void switchChild() {
        String mainWindowHandle = webDriver.getWindowHandle();
        Set<String> allWindowHandles = webDriver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        while (iterator.hasNext()) {
            String childWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(childWindow)) {
                webDriver.switchTo().window(childWindow);
            }
        }
    }

    private void selectBrowserType() {

        long implicitTimeout = configReader.getImplicitTimeout();

        switch (configReader.getBrowserType().toLowerCase(Locale.ROOT)) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(configReader.getChromeProperties());
                webDriver = new ChromeDriver(chromeOptions);
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitTimeout));
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(configReader.getFirefoxProperties());
                webDriver = new FirefoxDriver(firefoxOptions);
                webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(implicitTimeout));
                if (configReader.getFirefoxProperties().getBooleanPreference("maximize", true)) {
                    webDriver.manage().window().maximize();
                }
            }
        }

        //jse = (JavascriptExecutor) webDriver;
        actions = new Actions(webDriver);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(implicitTimeout));
    }
}