package tests;

import pageobject.PageHomeScooter;
import pageobject.PageUserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

//Проверить ошибки для всех полей формы заказа.

@RunWith(Parameterized.class)

public class TestCheckErrorTextFields extends TestBaseUI {
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;

    public TestCheckErrorTextFields(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getDataAndBrowsers() {
        return new Object[][] {
                { "а", "а", "аааа", "8888888888"},
                { "fff", "fff", "fffff", "8888888888f"},
                { "аааааааааааааааа", "ааааааааааааааааааааааааааааааааааааааааааааааааа", "аааааааааааааааааааааааааааааааааааааааааааааааааа", "88888888888888"},
                { "", "", "", ""},
        };
    }

    @Test
    public void allErrorText(){
        PageHomeScooter objPageHomeScooter = new PageHomeScooter(driver);
        PageUserInfo objUserInfoPage = new PageUserInfo(driver);
        final String expectedName = "Введите корректное имя";
        final String expectedSurname = "Введите корректную фамилию";
        final String expectedAddress = "Введите корректный адрес";
        final String expectedPhone = "Введите корректный номер";
        final String[] expectedText = {expectedName, expectedSurname, expectedAddress, expectedPhone};
        //
        objPageHomeScooter.pressUpperOrderButton();
        // Найти все ошибки на странице
        String[] actualText = objUserInfoPage.activateAndGetAllErrorText(name, surname, address, phone);
        // Передача некорректных данных в поля и проверка на наличие ошибок
        Assert.assertArrayEquals(expectedText,actualText);
    }
}
