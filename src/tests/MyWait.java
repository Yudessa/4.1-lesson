package com.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class MyWait {
    private final Logger LOG = LoggerFactory.getLogger(MyWait.class);
    private final WebDriverWait wait;
    private final int secondToWait;

    public static MyWait myWait(int seconds) {
        return new MyWait(seconds);
    }

    public MyWait(int seconds) {
        secondToWait = seconds;
        wait = new WebDriverWait(com.example.MainPageTest.getDriver(), Duration.ofSeconds(seconds));
    }

    public WebElement clickable(WebElement element) {
        LOG.info("Ждем " + secondToWait + " секунд, пока элемент не станет кликабельным: " + element.toString());
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
