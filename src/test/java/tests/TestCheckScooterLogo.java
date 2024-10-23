package tests;

import pageobject.PageHomeScooter;
import org.junit.Assert;
import org.junit.Test;

//Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».

public class TestCheckScooterLogo extends TestBaseUI {
    @Test
    public void isScooterImageLogoReturnsToMainPage(){
        PageHomeScooter objHomePageScooter = new PageHomeScooter(driver);
        // Нажатие на кнопку "Заказать" в шапке страницы
        objHomePageScooter.pressOrderButton("Верхняя");
        // Нажатие на "Самокат" в логотипе
        objHomePageScooter.pressScooterImageInLogo();
        // Проверка на наличие картинки на главной странице
        Assert.assertTrue(objHomePageScooter.getBlueprintScooterImage());
    }
}