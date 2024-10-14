package tests;

import methods.PageHomeScooter;
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

//Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
@RunWith(Parameterized.class)

public class TestCheckScooterLogo {
    private WebDriver driver;
    public final WebDriver browser;

    public TestCheckScooterLogo(WebDriver browser) {
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
    public void isScooterImageLogoReturnsToMainPage(){
        PageHomeScooter objHomePageScooter = new PageHomeScooter(driver);
        // Нажатие на кнопку "Заказать" в шапке страницы
        objHomePageScooter.pressOrderButton("Верхняя");
        // Нажатие на "Самокат" в логотипе
        objHomePageScooter.pressScooterImageInLogo();
        // Проверка на наличие картинки на главной странице
        Assert.assertTrue(objHomePageScooter.getBlueprintScooterImage());
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}