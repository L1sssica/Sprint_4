package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class PageOrderAboutRent {


    private Object driver;

    public PageOrderAboutRent(WebDriver driver) {
    }
    //Сначала разберем основные составляющие страницы "Про аренду".
    //Напишем локатор заголовка "Про аренду"
    private final By headerAboutRent = By.xpath(".//div[text()='Про арнеду']");
    //Напишем локтаор для поля "Когда привезти самокат"
    private final By fieldWhenToBringScooter = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Напишем локатор для поля "Срок аренды"
    private final By fieldRentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");
    //Напишем локотор для поля "Комментарий для курьера"
    private final By commentFieldForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Напишем локатор кнопки "Заказать" после формы
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //Напишем локатор кнопки "Да" во свсплывающем окне "Хотите оформить заказ?"
    private final By buttonOrderYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Напишем локатор сообщения об успешном заказе
    private final By orderMessage = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    // Пишем метод перехода на старницу "Про аренду"
    public boolean testPageAboutRentIsDisplayed(WebDriver driver){
        WebDriver.Timeouts timeouts;
        timeouts = driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver.findElement(headerAboutRent).isDisplayed();
    }
    //указываем метод для поля "Когда привезти самокат"
    public void testFieldWhenToBringScooter(String date, By driver) {
        driver.findElement(fieldWhenToBringScooter).sendKeys(date);
    }
    //Указываем метод для поля "Срок аренды"
    public void testFieldRentalPeriod(String rentalPeriod) {
        driver.finalize().click();
        driver.finalize(By.xpath(".//div[text()='" + rentalPeriod +"']")).click();
    }


    //Указываем метод выбора цвета самоката
    public void testFieldColorScooter(String colorScooter) {
        driver.finalize(By.id(colorScooter)).click();
    }

    //Указываем метод для поля ввода комментария
    public void testCommentFieldForCourier(String comment) {
        driver.finalize(commentFieldForCourier).sendKeys(comment);
    }

    //Пишем метод для нажатия кнопки "Заказать"
    public void testOrderButton() {
        driver.finalize(orderButton).click();
    }

    //Метод для нажатия кнопки "Да"
    public void testButtonOrderYes() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(buttonOrderYes));
        driver.finalize(buttonOrderYes).click();
    }

    //Метод для проверки, что заказ оформлен
    public String tesOrderMessage() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(orderMessage));
        String textMessage = driver.finalize(orderMessage).getText();
        return textMessage;
    }
}

