package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageNoSuchOrder {
    private final WebDriver driver;
    // Картинка "Такого заказа нет"
    private By imageNotFound = By.xpath(".//img[@alt='Not found']");
    // Конструктор для драйвера
    public PageNoSuchOrder(WebDriver driver) {
        this.driver = driver;
    }
    // Проверка отображения изображения
    public boolean checkNotFoundImage() {
        return driver.findElement(imageNotFound).isDisplayed();
    }
}