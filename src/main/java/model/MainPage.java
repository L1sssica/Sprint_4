package model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class MainPage {
    private final WebDriver driver;
    

    //Сначала пишем локатор кнопки "Заказать", которая находится сверху главной страницы
    private final By topButton = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //Далее пишем локатор кнопки "Заказать", которая находится внизу главной страницы
    private final By bottomButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM'and text()='Заказать']");
    //Пишем локатор кнопки для принятия кук
    private final By closeCookie = By.xpath(".//button[text()='да все привыкли']");

    //Пишем конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
        
    }
    //Нажимаем на верхнюю кнопку "Заказать"
    public void clickOnTheTopButton() {
        WebElement driverElement = driver.findElement(topButton);
        driverElement.click();
    }
    //Нажимаем на нижнюю кнопку "Заказать"
    public void clickOnTheBottomButton(){
        WebElement driverElement = driver.findElement(bottomButton);
        driverElement.click();
    }
    //Теперь принимаем куки и закрываем окно
    public void closeCookie() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(closeCookie)).click();
    }

    //нажимаем кнопку "Сколько это стоит"
    public void howMuchCostQuestionClick(String question) {
        driver.findElement(By.xpath(".//div[contains(text(), '" + question + "')]")).click();
    }

    //проверяем, отображение текста
    public boolean answerIsDisplayed(String answer) {
        return driver.findElement(By.xpath(".//p[text() = '" + answer + "']")).isDisplayed();
    }
}

