package tests;

import methods.PageHomeScooter;
import methods.PageNoSuchOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static methods.PageHomeScooter.urlScooter;

//Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет.
@RunWith(Parameterized.class)

public class TestCheckIncorrectNumber {
    private WebDriver driver;
    public final WebDriver browser;

    public TestCheckIncorrectNumber(WebDriver browser) {
        this.browser = browser;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getBrowsers() {
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        optionsFirefox.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        return new Object[][] {
                {new ChromeDriver(optionsChrome)},
                {new FirefoxDriver(optionsFirefox)},
        };
    }
    @Before
    public void startUp() {
        driver = browser;
        PageHomeScooter objPageScooter = new PageHomeScooter(driver);
        // Открытие страницы
        driver.get(urlScooter);
        // Нажатие на кнопку кук
        objPageScooter.pressCookieButton();
    }

    @Test
    public void tryingToFindIncorrectOrder(){
        PageHomeScooter objHomePageScooter = new PageHomeScooter(driver);
        PageNoSuchOrder objNoSuchOrderPage = new PageNoSuchOrder(driver);
        // Нажатие на кнопку "Статус заказа"
        objHomePageScooter.pressButtonOrderStatus();
        // Заполнение появившегося поле неверными данными
        objHomePageScooter.fillInputOrderNumber("1");
        // Нажатие на кнопку "Go"
        objHomePageScooter.pressButtonGo();
        // Проверка на наличие разобранного самоката
        Assert.assertTrue(objNoSuchOrderPage.checkNotFoundImage());
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}