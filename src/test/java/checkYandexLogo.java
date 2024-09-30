import PageObject.HomePageScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
//Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
@RunWith(Parameterized.class)

public class checkYandexLogo {
    private WebDriver driver;
    public final WebDriver browser;

    public checkYandexLogo(WebDriver browser) {
        this.browser = browser;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getBrowsers() {
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        optionsFirefox.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new Object[][] {
                {new ChromeDriver(optionsChrome)},
                {new FirefoxDriver(optionsFirefox)},
        };
    }
    @Before
    public void startUp() {
        driver = browser;
        HomePageScooter objPageScooter = new HomePageScooter(driver);
        // Открытие страницы
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // Нажатие на кнопку кук
        objPageScooter.pressCookieButton();
    }

    @Test
    public void isYandexImageLogoOpensNewTabMainPageYandex(){
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        // Нажатие на логотип "Яндекс"
        objHomePageScooter.pressYandexImageInLogo();
        // Проверка url на корректность
        objHomePageScooter.checkCorrectYandexUrl();
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}