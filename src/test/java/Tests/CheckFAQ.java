import LocatorsAndMethods.HomePageScooter;
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
//Выпадающий список в разделе «Вопросы о важном». Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
@RunWith(Parameterized.class)

public class CheckFAQ {
    private WebDriver driver;
    private final String actualAnswer;
    private final String numberOfAnswerAndQuestion;
    public final WebDriver browser;

    public CheckFAQ(String actualAnswer, String numberOfAnswerAndQuestion, WebDriver browser) {
        this.actualAnswer = actualAnswer;
        this.numberOfAnswerAndQuestion = numberOfAnswerAndQuestion;
        this.browser = browser;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getComparingDataAndBrowsers() {
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        optionsFirefox.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new Object[][] {
                { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "0", new ChromeDriver(optionsChrome)},
                { "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "1", new ChromeDriver(optionsChrome)},
                { "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "2", new ChromeDriver(optionsChrome)},
                { "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "3", new ChromeDriver(optionsChrome)},
                { "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "4", new ChromeDriver(optionsChrome)},
                { "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "5", new ChromeDriver(optionsChrome)},
                { "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "6", new ChromeDriver(optionsChrome)},
                { "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "7", new ChromeDriver(optionsChrome)},
                { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "0", new FirefoxDriver(optionsFirefox)},
                { "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "1", new FirefoxDriver(optionsFirefox)},
                { "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "2", new FirefoxDriver(optionsFirefox)},
                { "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "3", new FirefoxDriver(optionsFirefox)},
                { "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "4", new FirefoxDriver(optionsFirefox)},
                { "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "5", new FirefoxDriver(optionsFirefox)},
                { "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "6", new FirefoxDriver(optionsFirefox)},
                { "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "7", new FirefoxDriver(optionsFirefox)},
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
    public void checkFAQList(){
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        // Сравнение текста ответов
        Assert.assertEquals(actualAnswer, objHomePageScooter.checkAnswers(numberOfAnswerAndQuestion));

    }
    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}