package tests;

import pageobject.PageHomeScooter;
import pageobject.PageNoSuchOrder;
import org.junit.Assert;
import org.junit.Test;

//Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет.

public class TestCheckIncorrectNumber extends TestBaseUI {

    @Test
    public void tryingToFindIncorrectOrder(){
        PageHomeScooter objHomePageScooter = new PageHomeScooter(driver);
        PageNoSuchOrder objNoSuchOrderPage = new PageNoSuchOrder(driver);
        // Нажатие на кнопку "Статус заказа"
        objHomePageScooter.pressButtonOrderStatus();
        // Заполнение появившегося поле неверными данными
        objHomePageScooter.fillInputOrderNumber("1");
        // Нажатие на кнопку "Go"
        objHomePageScooter.pressButtonGo();
        // Проверка на наличие разобранного самоката
        Assert.assertTrue(objNoSuchOrderPage.checkNotFoundImage());
    }
}