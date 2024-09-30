import PageObject.HomePageScooter;
import PageObject.UserInfoPage;
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
//Проверить ошибки для всех полей формы заказа.
@RunWith(Parameterized.class)

public class checkError {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    public final WebDriver browser;

    public checkError(String name, String surname, String address, String phone, WebDriver browser) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.browser = browser;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getDataAndBrowsers() {
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        optionsFirefox.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new Object[][] {
                { "а", "а", "аааа", "8888888888", new ChromeDriver(optionsChrome)},
                { "fff", "fff", "fffff", "8888888888f", new ChromeDriver(optionsChrome)},
                { "аааааааааааааааа", "ааааааааааааааааааааааааааааааааааааааааааааааааа", "аааааааааааааааааааааааааааааааааааааааааааааааааа", "88888888888888", new ChromeDriver(optionsChrome)},
                { "", "", "", "", new ChromeDriver(optionsChrome)},
                { "а", "а", "аааа", "8888888888", new FirefoxDriver(optionsFirefox)},
                { "fff", "fff", "fffff", "8888888888f", new FirefoxDriver(optionsFirefox)},
                { "аааааааааааааааа", "ааааааааааааааааааааааааааааааааааааааааааааааааа", "аааааааааааааааааааааааааааааааааааааааааааааааааа", "88888888888888", new FirefoxDriver(optionsFirefox)},
                { "", "", "", "", new FirefoxDriver(optionsFirefox)},
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
        // Нажатие на кнопку "Заказать"
        objPageScooter.pressUpperOrderButton();
    }

    @Test
    public void allErrorText(){
        UserInfoPage objUserInfoPage = new UserInfoPage(driver);
        // Передача некорректных данных в поля и проверка на наличие ошибок
        objUserInfoPage.findAllErrorText(name, surname, address, phone);
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
