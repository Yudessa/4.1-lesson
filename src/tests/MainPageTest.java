package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {

    private WebDriver driver;

    public static WebDriver getDriver() {
    }

    @BeforeEach
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        searchField.sendKeys(input);
        searchField.submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[text()='Selenium' and @target='_blank']")
        ));

        List<WebElement> results = driver.findElements(By.xpath("//a[text()='Selenium' and @target='_blank']"));

        String originalTab = driver.getWindowHandle();

        clickElement(results, 0);

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        wait.until(ExpectedConditions.urlContains("selenium.dev"));
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("selenium.dev"), "Неверный URL");
    }

    public void clickElement(List<WebElement> results, int num) {
        results.get(num).click();
        System.out.println(results);
    }
}
