import model.MainPage;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MainPageMainQuestionsTest {
    private static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String BROWSER_NAME = "Chrome";
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

    // Пишем вопросы
    private static final String Q_COST_PAYMENT = "Сколько это стоит? И как оплатить?";
    private static final String Q_NUMBER_OF_SCOOTERS = "Хочу сразу несколько самокатов! Так можно?";
    private static final String Q_RENTAL_TIME = "Как рассчитывается время аренды?";
    private static final String Q_SCOOTER_TODAY = "Можно ли заказать самокат прямо на сегодня?";
    private static final String Q_EXTEND_OR_EARLY_RETURN = "Можно ли продлить заказ или вернуть самокат раньше?";
    private static final String Q_CHARGER = "Вы привозите зарядку вместе с самокатом?";
    private static final String Q_CANCEL_ORDER = "Можно ли отменить заказ?";
    private static final String Q_DELIVERY_OUTSIDE_MKAD = "Я жизу за МКАДом, привезёте?";

    // Ответы на вопросы
    private static final String A_COST_PAYMENT = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String A_NUMBER_OF_SCOOTERS = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String A_RENTAL_TIME = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String A_SCOOTER_TODAY = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String A_EXTEND_OR_EARLY_RETURN = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String A_CHARGER = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String A_CANCEL_ORDER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String A_DELIVERY_OUTSIDE_MKAD = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    private final String question;
    private final String answer;
    private WebDriver driver;
    private MainPage mainPage;

    public MainPageMainQuestionsTest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void before() {
        driver = SelectBrowser.selectDriverBrowser(BROWSER_NAME);
        mainPage = new MainPage(driver);
    }

    @Parameterized.Parameters
    public static Object[][] testQuestionsAnswers() {
        return new Object[][]{
                {Q_COST_PAYMENT, A_COST_PAYMENT},
                {Q_NUMBER_OF_SCOOTERS, A_NUMBER_OF_SCOOTERS},
                {Q_RENTAL_TIME, A_RENTAL_TIME},
                {Q_SCOOTER_TODAY, A_SCOOTER_TODAY},
                {Q_EXTEND_OR_EARLY_RETURN, A_EXTEND_OR_EARLY_RETURN},
                {Q_CHARGER, A_CHARGER},
                {Q_CANCEL_ORDER, A_CANCEL_ORDER},
                {Q_DELIVERY_OUTSIDE_MKAD, A_DELIVERY_OUTSIDE_MKAD}
        };
    }

    @Test
    public void testRecord() {
        driver.get(MAIN_PAGE_URL);
        mainPage.closeCookie();

        // Используем методы Page Object в тесте
        mainPage.clickQuestion(question);
        assertTrue(mainPage.answerIsDisplayed(answer));
    }

    @After
    public void after() {
        driver.quit();
    }
}



