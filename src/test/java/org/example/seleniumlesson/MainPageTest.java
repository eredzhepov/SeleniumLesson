package org.example.seleniumlesson;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
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
        searchField.sendKeys("Selenium");
        searchField.submit();


        WebElement searchPageField = driver.findElement(By.cssSelector("#sb_form_q"));
        assertEquals(input, searchPageField.getAttribute("value"));
    }
    @Test
    public void getAllResults() {
        String searchWord = "Selenium";
        WebElement inputField = driver.findElement(By.xpath("//*[@name = 'q']"));
        inputField.sendKeys(searchWord);
        inputField.submit();
        List<WebElement> results = driver.findElements(By.cssSelector("h2  > a[href]"));

        clickElement(results, 0);
        String url = driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());
        assertEquals("https://www.selenium.dev/", url, "Не перешел по юрлу селениума");

    }

    public void clickElement(List<WebElement> results, int num) {
        results.get(num).click();
        System.out.println("Вы нажали на " + num + " элемент");
    }



}
