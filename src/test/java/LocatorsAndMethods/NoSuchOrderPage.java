package LocatorsAndMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NoSuchOrderPage {
    private final WebDriver driver;
    // Картинка "Такого заказа нет"
    private By imageNotFound = By.xpath(".//img[@alt='Not found']");
    // Конструктор для драйвера
    public NoSuchOrderPage(WebDriver driver) {
        this.driver = driver;
    }
    // Проверка отображения изображения
    public boolean checkNotFoundImage() {
        return driver.findElement(imageNotFound).isDisplayed();
    }
}