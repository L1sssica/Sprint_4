package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PageOrderForWhomScooter {

    private final WebDriver driver;
    //Пишем локатор заголовка страницы "Для кого самокат"
    private final By headerPage = By.xpath(".//div[text()='Для кого самокат']");
    //Пишем локатор для поля "Имя"
    private final By fieldFirstName = By.xpath(".//input[@placeholder='* Имя']");
    //Пишем локатор для поля "Фамилия"
    private final By fieldLastName = By.xpath(".//input[@placeholder='* Фамилия']");
    //Пишем локатор для поля "Адрес: куда привезти заказ"
    private final By fieldAdress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Пишем локатор для поля "Станция метро"
    private final By fieldMetroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    //Пишем локатор для поля "Телефон: на него позвонит курьер"
    private final By fieldForPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Пишем локатор для кнопки "Далее"
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    //Локатор для выпадающего списка станций метро (Юго-Западная)
    private final By southwest = By.xpath("//div[contains(text(), 'Юго-Западная')]");
    //Локатор для выпадающего списка станций метро (Юго-Западная)
    private final By izmailovo = By.xpath("//div[contains(text(), 'Измайлово')]");
    //конструктор класса
    public PageOrderForWhomScooter(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для отображения страницы
    public boolean pageIsDisplayed() {
        return driver.findElement(headerPage).isDisplayed();
    }

    //Метод заполнения поля "имя"
    public void testFieldFirstName(String name) {
        driver.findElement(fieldFirstName).sendKeys(name);
    }

    //Метод заполнения поля "Фамилия"
    public void testFieldLastName(String lastName) {
        driver.findElement(fieldLastName).sendKeys(lastName);
    }

    //Метод заполнения поля адрес
    public void testFieldAdress(String adress) {
        driver.findElement(fieldAdress).sendKeys(adress);
    }

    //Метод заполнения поля "Станция метро"
    public void testFieldMetroStation(String metroStation) {
        driver.findElement(fieldMetroStation).sendKeys(metroStation);
        By stationLocator = metroStation.contains("Измайлово") ? izmailovo : southwest;
        driver.findElement(stationLocator).click();
    }

    //Метод заполнения поля "Телефон"
    public void testFieldForPhone(String phone) {
        driver.findElement(fieldForPhone).sendKeys(phone);
    }

    //Метод для нажатия на кнопку "Далее"
    public void pushButtonNext() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonNext));
        driver.findElement(buttonNext).click();
    }


}





