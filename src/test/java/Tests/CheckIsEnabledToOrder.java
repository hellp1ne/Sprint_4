package Tests;

import LocatorsAndMethods.AboutRentPage;
import LocatorsAndMethods.HomePageScooter;
import LocatorsAndMethods.UserInfoPage;
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
//Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных. Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.
@RunWith(Parameterized.class)

public class CheckIsEnabledToOrder {
    private WebDriver driver;
    private final String choiceOrderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    public final WebDriver browser;

    public CheckIsEnabledToOrder(String choiceOrderButton, String name, String surname, String address, String phone, String date, WebDriver browser) {
        this.choiceOrderButton = choiceOrderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
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
                { "Верхняя", "Иван", "Иванов", "ул. Ивановская д. 5", "89999999999", "1", new ChromeDriver(optionsChrome)},
                { "Нижняя", "Иван", "Иванов", "ул. Ивановская д. 5", "89999999999", "1", new ChromeDriver(optionsChrome)},
                { "Верхняя", "Иван", "Иванов", "ул. Ивановская д. 5", "89999999999", "1", new FirefoxDriver(optionsFirefox)},
                { "Нижняя", "Иван", "Иванов", "ул. Ивановская д. 5", "89999999999", "1", new FirefoxDriver(optionsFirefox)},
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
    public void isPossibleToMakeOrder(){
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        UserInfoPage objUserInfoPage = new UserInfoPage(driver);
        AboutRentPage objAboutRentPage = new AboutRentPage(driver);
        // Нажатие на кнопку "Заказать" в шапке страницы
        objHomePageScooter.pressOrderButton(choiceOrderButton);
        // Заполнение тестовыми данными
        objUserInfoPage.fillInputs(name, surname, address, phone);
        // Нажатие на кнопку "Далее"
        objUserInfoPage.clickNextButton();
        // Заполнение тестовыми данными на следующей странице
        objAboutRentPage.fillInputs(date);
        // Нажатие на кнопку "Заказать"
        objAboutRentPage.clickNextButton();
        // Нажатие на кнопку "Да" на поп-апе
        objAboutRentPage.clickYesButton();
        // Проверка открытия поп-апа
        Assert.assertTrue(objAboutRentPage.checkSuccess());
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}