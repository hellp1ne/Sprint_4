package tests;

import pageobject.PageAboutRent;
import pageobject.PageHomeScooter;
import pageobject.PageUserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

//Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных. Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.

@RunWith(Parameterized.class)

public class TestCheckIsEnabledToOrder extends TestBaseUI {
    private final String choiceOrderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;

    public TestCheckIsEnabledToOrder(String choiceOrderButton, String name, String surname, String address, String phone, String date) {
        this.choiceOrderButton = choiceOrderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getDataAndBrowsers() {
        return new Object[][] {
                //Первая точка входа в сценарий
                { "Верхняя", "Иван", "Иванов", "ул. Ивановская д. 5", "89999999999", "1"},
                //Вторая точка входа в сценарий
                { "Нижняя", "Иван", "Иванов", "ул. Ивановская д. 5", "89999999999", "1"},
        };
    }

    @Test
    public void isPossibleToMakeOrder(){
        PageHomeScooter objHomePageScooter = new PageHomeScooter(driver);
        PageUserInfo objUserInfoPage = new PageUserInfo(driver);
        PageAboutRent objAboutRentPage = new PageAboutRent(driver);
        // Нажатие на кнопку "Заказать" в шапке страницы
        objHomePageScooter.pressOrderButton(choiceOrderButton);
        // Заполнение тестовыми данными
        objUserInfoPage.fillInputs(name, surname, address, phone);
        // Нажатие на кнопку "Далее"
        objUserInfoPage.clickNextButton();
        // Заполнение тестовыми данными на следующей странице
        objAboutRentPage.fillInputs(date);
        // Нажатие на кнопку "Заказать"
        objAboutRentPage.clickNextButton();
        // Нажатие на кнопку "Да" на поп-апе
        objAboutRentPage.clickYesButton();
        // Проверка открытия поп-апа
        Assert.assertTrue(objAboutRentPage.checkSuccess());
    }
}