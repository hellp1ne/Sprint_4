package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PageHomeScooter {
    public static final String urlScooter = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;
    // Время ожидания кнопки
    java.time.Duration waitForButton = Duration.ofSeconds(3);
    // Время ожидания ссылки
    java.time.Duration waitForLink = Duration.ofSeconds(7);
    // Верхняя кнопка "Заказать"
    private By buttonOrderUpper = By.className("Button_Button__ra12g");
    // Нижняя кнопка "Заказать"
    private By buttonOrderLower = By.xpath(".//button[contains(@class,'Button_Middle')]");
    // Кнопка кук (да все привыкли)
    private By buttonCookie = By.id("rcc-confirm-button");
    // Вопрос в разделе «Вопросы о важном» (Поиск по ID)
    private String someQuestion = "accordion__heading-";
    // Ответ в разделе «Вопросы о важном» (Поиск по ID)
    private String someAnswer = "accordion__panel-";
    // Кнопка "Статус заказа"
    private By buttonOrderStatus = By.xpath(".//button[text()='Статус заказа']");
    // Поле для ввода номера заказа
    private By inputOrderNumber = By.xpath(".//input[@placeholder='Введите номер заказа']");
    // Кнопка "Go!"
    private By buttonGo = By.xpath(".//button[text()='Go!']");
    // Картинка "Яндекс" в логотипе
    private By yandexImageInLogo = By.xpath(".//img[@alt='Yandex']");
    // Картинка "Самокат" в логотипе
    private By scooterImageInLogo = By.xpath(".//img[@alt='Scooter']");
    // Картинка чертежа самоката
    private By blueprintScooterImage = By.xpath(".//div[@style='opacity: 1; transform: translateY(0px);']/img[@alt='Scooter blueprint']");
    // Конструктор для драйвера
    public PageHomeScooter(WebDriver driver) {
        this.driver = driver;
    }
    // Нажать на кнопку кук, чтобы пропал поп-ап
    public void pressCookieButton() {
        driver.findElement(buttonCookie).click();
    }
    // Пролистать до блока FAQ, кликнуть по вопросу и проверить текст
    public String checkAnswers(String numberOfAnswerAndQuestion) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id(someQuestion + numberOfAnswerAndQuestion)));
        driver.findElement(By.id(someQuestion + numberOfAnswerAndQuestion)).click();
        //textToBePresentInElementLocated(By.id(someAnswer + numberOfAnswerAndQuestion), actualAnswer);
        return driver.findElement(By.id(someAnswer + numberOfAnswerAndQuestion)).getText();
    }
    // Нажать кнопку "Заказать"
    public void pressOrderButton(String choiceOrderButton) {
        WebElement element = driver.findElement(buttonOrderLower);
        if (choiceOrderButton.equals("Верхняя")) {
            new WebDriverWait(driver, waitForButton)
                    .until(ExpectedConditions.elementToBeClickable(buttonOrderUpper));
            driver.findElement(buttonOrderUpper).click();
        } else if (choiceOrderButton.equals("Нижняя")) {
            new WebDriverWait(driver, waitForButton)
                    .until(ExpectedConditions.elementToBeClickable(buttonOrderLower));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(buttonOrderLower).click();
        }
    }
    // Нажать верхнюю кнопку "Заказать"
    public void pressUpperOrderButton() {
        new WebDriverWait(driver, waitForButton)
                .until(ExpectedConditions.elementToBeClickable(buttonOrderUpper));
        driver.findElement(buttonOrderUpper).click();
    }
    // Нажать кнопку "Статус заказа"
    public void pressButtonOrderStatus() {
        driver.findElement(buttonOrderStatus).click();
    }
    // Нажать кнопку "Go!"
    public void pressButtonGo() {
        new WebDriverWait(driver, waitForButton)
                .until(ExpectedConditions.elementToBeClickable(buttonGo));
        driver.findElement(buttonGo).click();
    }
    // Заполнение поля "Введите номер заказа"
    public void fillInputOrderNumber(String orderNumber) {
        new WebDriverWait(driver, waitForButton)
                .until(ExpectedConditions.elementToBeClickable(inputOrderNumber));
        driver.findElement(inputOrderNumber).sendKeys(orderNumber);
    }
    // Нажатие на картинку "Яндекс" в логотипе
    public void pressYandexImageInLogo() {
        driver.findElement(yandexImageInLogo).click();

    }
    // Переход между окнами, ожидание загрузки ссылки, получение ссылки
    public String getUrl() {
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains("https://dzen.ru"));
        return driver.getCurrentUrl();
    }
    // Нажатие на картинку "Самокат" в логотипе
    public void pressScooterImageInLogo() {
        driver.findElement(scooterImageInLogo).click();
    }
    // Проверка на отображение картинки
    public boolean getBlueprintScooterImage() {
        return driver.findElement(blueprintScooterImage).isDisplayed();
    }
}