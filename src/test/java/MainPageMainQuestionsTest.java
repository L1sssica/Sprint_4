import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import model.MainPage;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MainPageMainQuestionsTest {
    private static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String BROWSER_NAME = "Chrome";
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

    // Константы вопросов
    private static final String QUESTION_1 = "Сколько это стоит? И как оплатить?";
    private static final String QUESTION_2 = "Хочу сразу несколько самокатов! Так можно?";
    private static final String QUESTION_3 = "Как рассчитывается время аренды?";
    private static final String QUESTION_4 = "Можно ли заказать самокат прямо на сегодня?";
    private static final String QUESTION_5 = "Можно ли продлить заказ или вернуть самокат раньше?";
    private static final String QUESTION_6 = "Вы привозите зарядку вместе с самокатом?";
    private static final String QUESTION_7 = "Можно ли отменить заказ?";
    private static final String QUESTION_8 = "Я жизу за МКАДом, привезёте?";

    // Константы ответов
    private static final String ANSWER_1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String ANSWER_2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String ANSWER_3 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String ANSWER_4 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String ANSWER_5 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String ANSWER_6 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String ANSWER_7 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String ANSWER_8 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    private final String question;
    private final String answer;
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPageMainQuestionsTest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void before() {
        driver = SelectBrowser.selectDriverBrowser(BROWSER_NAME);
        wait = new WebDriverWait(driver, WAIT_TIMEOUT);
    }

    @Parameterized.Parameters
    public static Object[][] testQuestionsAnswers() {
        return new Object[][]{
                {QUESTION_1, ANSWER_1},
                {QUESTION_2, ANSWER_2},
                {QUESTION_3, ANSWER_3},
                {QUESTION_4, ANSWER_4},
                {QUESTION_5, ANSWER_5},
                {QUESTION_6, ANSWER_6},
                {QUESTION_7, ANSWER_7},
                {QUESTION_8, ANSWER_8}
        };
    }

    @Test
    public void testRecord() {
        driver.get(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookie();

        // Используем явное ожидание для клика по вопросу
        WebElement questionElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//div[contains(text(), '" + question + "')]")));
        questionElement.click();

        // Используем явное ожидание для проверки отображения ответа
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//p[text() = '" + answer + "']")));
        assertTrue(answerElement.isDisplayed());
    }

    @After
    public void after() {
        driver.quit();
    }
}




