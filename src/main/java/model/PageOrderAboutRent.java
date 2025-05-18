package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;


public class PageOrderAboutRent {


    private final WebDriver driver;
    private final WebDriverWait wait;

    public PageOrderAboutRent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //Сначала разберем основные составляющие страницы "Про аренду".
    //Напишем локатор заголовка "Про аренду"
    private final By headerAboutRent = By.xpath(".//div[text()='Про аренду']");
    //Напишем локатор для поля "Когда привезти самокат"
    private final By fieldWhenToBringScooter = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Напишем локатор для поля "Срок аренды"
    private final By fieldRentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");
    //Напишем локатор для поля "Комментарий для курьера"
    private final By commentFieldForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Напишем локатор кнопки "Заказать" после формы
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //Напишем локатор кнопки "Да" во всплывающем окне "Хотите оформить заказ?"
    private final By buttonOrderYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Напишем локатор сообщения об успешном заказе
    private final By orderMessage = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    // Пишем метод перехода на страницу "Про аренду"
    public boolean testPageAboutRentIsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerAboutRent)).isDisplayed();
    }

    //указываем метод для поля "Когда привезти самокат"
    public void testFieldWhenToBringScooter(String date) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(fieldWhenToBringScooter));
        element.clear();
        element.sendKeys(date);
    }

    //Указываем метод для поля "Срок аренды"
    public void testFieldRentalPeriod(String rentalPeriod) {
        driver.findElement(fieldRentalPeriod).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }


    //Указываем метод выбора цвета самоката
    public void testFieldColorScooter(String colorScooter) {
        driver.findElement(By.id(colorScooter)).click();
    }

    //Указываем метод для поля ввода комментария
    public void testCommentFieldForCourier(String comment) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(commentFieldForCourier));
        element.clear();
        element.sendKeys(comment);
    }

    //Пишем метод для нажатия кнопки "Заказать"
    public void testOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    //Метод для нажатия кнопки "Да"
    public void testButtonOrderYes() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonOrderYes)).click();
    }

    //Метод для проверки, что заказ оформлен
    public String testOrderMessage() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(orderMessage));
        return element.getText();
    }
}
