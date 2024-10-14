package tests;

import methods.PageHomeScooter;
import methods.PageUserInfo;
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

//Проверить ошибки для всех полей формы заказа.
@RunWith(Parameterized.class)

public class TestCheckErrorTextFields {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    public final WebDriver browser;

    public TestCheckErrorTextFields(String name, String surname, String address, String phone, WebDriver browser) {
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
        optionsChrome.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        optionsFirefox.addArguments("--no-sandbox", "--disable-dev-shm-usage");
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
        PageHomeScooter objPageScooter = new PageHomeScooter(driver);
        // Открытие страницы
        driver.get(urlScooter);
        // Нажатие на кнопку кук
        objPageScooter.pressCookieButton();
        // Нажатие на кнопку "Заказать"
        objPageScooter.pressUpperOrderButton();
    }

    @Test
    public void allErrorText(){
        final String expectedName = "Введите корректное имя";
        final String expectedSurname = "Введите корректную фамилию";
        final String expectedAddress = "Введите корректный адрес";
        final String expectedPhone = "Введите корректный номер";
        final String[] expectedText = {expectedName, expectedSurname, expectedAddress, expectedPhone};
        PageUserInfo objUserInfoPage = new PageUserInfo(driver);
        String[] actualText = objUserInfoPage.activateAndGetAllErrorText(name, surname, address, phone);
        // Передача некорректных данных в поля и проверка на наличие ошибок
        Assert.assertArrayEquals(expectedText,actualText);
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
