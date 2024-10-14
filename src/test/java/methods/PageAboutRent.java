package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class PageAboutRent {
    private WebDriver driver;
    private boolean isButtonDisplayed = false;
    // Поле для ввода даты
    private By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Сегодняшняя дата в календаре
    //private By todayOption = By.xpath(".//div[contains(@class,'day--today')]");
    // Дата в календаре
    private String dateOption = ".//div[contains(@class,'react-datepicker__day--0";
    // Поле для ввода времени аренды
    private By timeOfRentInput = By.className("Dropdown-placeholder");
    // Выбор количества дней аренды (один день)
    private By oneDayOption = By.xpath(".//div[@class='Dropdown-menu']/div[1]");
    // Чекбокс для выбора черного цвета самоката
    private By blackScooterCheckbox = By.id("black");
    // Чекбокс для выбора серого цвета самоката
    //private By greyScooterCheckbox = By.id("grey");
    // Поле для ввода даты
    //private By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private By buttonNext = By.xpath(".//div[3]/button[text()='Заказать']");
    // Кнопка "Да" всплывающего окна "Хотите оформить заказ?"
    private By buttonPopUpYes = By.xpath(".//button[text()='Да']");
    // Кнопка "Посмотреть статус"
    private By buttonCheckStatus = By.xpath(".//button[text()='Посмотреть статус']");

    public PageAboutRent(WebDriver driver) {
        this.driver = driver;
    }
    // Заполнение обязательных полей для валидации формы
    public void fillInputs (String date) {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(dateInput).click();
        driver.findElement(By.xpath(dateOption + date + "')]")).click();
        driver.findElement(timeOfRentInput).click();
        driver.findElement(oneDayOption).click();
        driver.findElement(blackScooterCheckbox).click();
    }
    // Нажать по кнопке "Заказать"
    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }
    // Нажать по кнопке "Да" вплывающего окна
    public void clickYesButton() {
        driver.findElement(buttonPopUpYes).click();
    }
    // Проверить, что заказ успешно оформлен
    public boolean checkSuccess() {
        try {
            if (driver.findElement(buttonCheckStatus).isDisplayed()) {
                isButtonDisplayed = true;
            }
        } finally {
            return isButtonDisplayed;
        }
    }
}