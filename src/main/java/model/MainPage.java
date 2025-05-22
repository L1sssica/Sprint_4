package model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    //Сначала пишем локатор кнопки "Заказать", которая находится сверху главной страницы
    private final By topButton = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //Далее пишем локатор кнопки "Заказать", которая находится внизу главной страницы
    private final By bottomButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM'and text()='Заказать']");
    //Пишем локатор кнопки для принятия кук
    private final By closeCookie = By.xpath(".//button[text()='да все привыкли']");

    //Пишем конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Нажимаем на верхнюю кнопку "Заказать"
    public void clickOnTheTopButton() {
        driver.findElement(topButton).click();
    }

    //Нажимаем на нижнюю кнопку "Заказать"
    public void clickOnTheBottomButton() {
        driver.findElement(bottomButton).click();
    }

    //Теперь принимаем куки и закрываем окно
    public void closeCookie() {
        wait.until(ExpectedConditions.elementToBeClickable(closeCookie)).click();
    }

    //нажимаем кнопку "Сколько это стоит"
    public void clickQuestion(String question) {
        By questionLocator = By.xpath(".//div[contains(text(), '" + question + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(questionLocator)).click();
    }

    //проверяем, отображение текста
    public boolean answerIsDisplayed(String answer) {
        By answerLocator = By.xpath(".//p[text() = '" + answer + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator)).isDisplayed();
    } // совсем не понимаю, как тут не использовать текст вопроса, не понимаю, что тогда писать в локаторе, или он не нужен
}

