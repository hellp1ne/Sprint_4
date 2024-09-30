package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserInfoPage {
    private WebDriver driver;
    // Время ожидания ошибки
    java.time.Duration waitForError = Duration.ofSeconds(1);
    // Поле для ввода имени
    private By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    // Поле для ввода фамилии
    private By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле для ввода адреса
    private By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле для ввода станции метро
    private By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    // Один из вариантов у выпадающего списка у поля метро (Бульвар Рокоссовского)
    private By metroOption = By.xpath(".//button[@value='1']");
    //Поле для ввода телефона
    private By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Селектор для всех полей ввода
    //private By allInputs = By.xpath(".//input[starts-with(@placeholder, '*')]");
    // Кнопка "Далее"
    private By buttonNext = By.xpath(".//button[text()='Далее']");
    // Текст "Для кого самокат"
    //private By textForWhom = By.xpath(".//div[text()='Для кого самокат']");
    // Текст ошибки "Введите корректное имя"
    private By textErrorIncorrectName = By.xpath(".//div[text()='Введите корректное имя']");
    // Текст ошибки "Введите корректную фамилию"
    private By textErrorIncorrectSurname = By.xpath(".//div[text()='Введите корректную фамилию']");
    // Текст ошибки "Введите корректный адрес"
    private By textErrorIncorrectAddress = By.xpath(".//div[text()='Введите корректный адрес']");
    // Текст ошибки "Введите корректный номер"
    private By textErrorIncorrectNumber = By.xpath(".//div[text()='Введите корректный номер']");
    // Селектор для всех ошибок
    //private By allTextErrors = By.xpath(".//div[starts-with(text(), 'Введите корректн')]");

    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
    }
    // Заполнение полей и передача в них данных
    public void fillInputs(String name, String surname, String address, String phone) {
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).click();
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).click();
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroInput).click();
        driver.findElement(metroOption).click();
        driver.findElement(phoneInput).click();
        driver.findElement(phoneInput).sendKeys(phone);
    }
    // Проверка на наличии текста ошибки
    public void findAllErrorText(String name, String surname, String address, String phone) {
        WebElement elementsNameTextError = driver.findElement(textErrorIncorrectName);
        WebElement elementsSurnameTextError = driver.findElement(textErrorIncorrectSurname);
        WebElement elementsAddressTextError = driver.findElement(textErrorIncorrectAddress);
        WebElement elementsPhoneTextError = driver.findElement(textErrorIncorrectNumber);
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).click();
        new WebDriverWait(driver, waitForError)
                .until(ExpectedConditions.visibilityOf(elementsNameTextError));
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).click();
        new WebDriverWait(driver, waitForError)
                .until(ExpectedConditions.visibilityOf(elementsSurnameTextError));
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(phoneInput).click();
        new WebDriverWait(driver, waitForError)
                .until(ExpectedConditions.visibilityOf(elementsAddressTextError));
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(buttonNext).click();
        new WebDriverWait(driver, waitForError)
                .until(ExpectedConditions.visibilityOf(elementsPhoneTextError));
    }
    //Нажать на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }
}