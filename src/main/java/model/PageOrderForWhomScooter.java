package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageOrderForWhomScooter {

    private WebDriver driver;
    //Пишем локатор заголовка страницы "Для кого самокат"
    private By headerPage = By.xpath(".//div[text()='Для кого самокат']");
    //Пишем локтор для поля "Имя"
    private By fieldFirstName = By.xpath(".//input[@placeholder='* Имя']");
    //Пишем локатор для поля "Фамилия"
    private By fieldLastName = By.xpath(".//input[@placeholder='* Фамилия']");
    //Пишем локатор для поля "Адрес: куда привезти заказ"
    private By fieldAdress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Пишем локатор для поля "Станция метро"
    private By fieldMetroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    //Пишем локатор для поля "Телефон: на него позвонит курьер"
    private By fieldForPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Пишем локатор для кнопки "Далее"
    private By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    private Object ExpectedConditions;

    //конструктор класса
    public PageOrderForWhomScooter(WebDriver driver){
        this.driver = driver;
    }
    //Метод для отображения страницы
    public boolean pageIsDisplayed(){
        return driver.findElement(headerPage).isDisplayed();
    }
    //Метод заполнения поля "имя"
    public void testFieldFirstName(String name){
        driver.findElement(fieldFirstName).sendKeys(name);
    }
    //Метод заполнения поля "Фамилия"
    public void testFieldLastName(String lastName){
        driver.findElement(fieldLastName).sendKeys(lastName);
    }
    //Метод заполнения поля адрес
    public void testFieldAdress(String adress){
        driver.findElement(fieldAdress).sendKeys(adress);
    }
    //Метод заполнения поля "Станция метро"
    public void testFieldMetroStation(String metroStation){
        driver.findElement(fieldMetroStation).sendKeys(metroStation);
        driver.findElement(By.xpath(".//*[@class='Order_Text__2broi' and text() = '" + metroStation + "']")).click();
    }
    //Метод заполнения поля "Телефон"
    public void testFieldForPhone(String phone){
        driver.findElement(fieldForPhone).sendKeys(phone);
    }
    //Метод для нажатия на кнопку "Далее"
    public void pushButtonNext() {
        //Ждем 3 секунды
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.getClass());
        driver.findElement(buttonNext).click();
    }


    public boolean pageisDisplayed() {
    }
}
