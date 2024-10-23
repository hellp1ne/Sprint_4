package tests;

import pageobject.PageHomeScooter;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.containsString;

//Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.

public class TestCheckYandexLogo extends TestBaseUI {
    @Test
    public void isYandexImageLogoOpensNewTabMainPageYandex(){
        PageHomeScooter objHomePageScooter = new PageHomeScooter(driver);
        // Нажатие на логотип "Яндекс"
        objHomePageScooter.pressYandexImageInLogo();
        // Проверка url на корректность
        Assert.assertThat(objHomePageScooter.getUrl(), containsString("https://dzen.ru"));
    }
}