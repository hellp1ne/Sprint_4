package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageobject.PageHomeScooter;
import static pageobject.PageHomeScooter.urlScooter;

public class TestBaseUI {
    protected WebDriver driver;

    @Before
    public void startUp() {
        // Выбор браузера
        initFirefox();
        //initChrome();
        // Открытие страницы
        driver.get(urlScooter);
        // Увеличить размер окна
        driver.manage().window().maximize();
        // Нажатие на кнопку кук
        PageHomeScooter objPageScooter = new PageHomeScooter(driver);
        objPageScooter.pressCookieButton();
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
    //Метод для инициализации хрома
    public void initChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }
    //Метод для инициализации фаерфокса
    public void initFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
    }
}
