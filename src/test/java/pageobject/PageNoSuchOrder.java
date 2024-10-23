package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(imageNotFound));
        return driver.findElement(imageNotFound).isDisplayed();
    }
}