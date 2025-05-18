import model.PageOrderForWhomScooter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import model.MainPage;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MainPageButtonOrderTest {
    private static final String MAIN_PAGE_URL="https://qa-scooter.praktikum-services.ru/"; //гл. Страница
    private static final String BROWSER_NAME="Google Chrome";

    private static WebDriver driver;
    private final String selectButton;

    //создали браузер
    @Before
    public void setUp() {
        // Инициализация драйвера
        driver = new ChromeDriver();
    }

    //создали конструктор класса
    public MainPageButtonOrderTest(String selectButton) {
        this.selectButton = selectButton;
}

    @Parameterized.Parameters

    public static Object[][] TestButtonOrder() {
        return new Object[][]{
                {"Проверить кнопку сверху"},
                {"Проверить кнопку снизу"},
        };
    }
    @Test
    public void testButtonOrder() {
        driver.get(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage (driver);
        PageOrderForWhomScooter pageOrderForWhomScooter = new PageOrderForWhomScooter(driver);
        mainPage.closeCookie();
        if (selectButton.equals("Проверить кнопку сверху")) {
            mainPage.clickOnTheTopButton();
        } else if (selectButton.equals("Проверить кнопку снизу")) {
            mainPage.clickOnTheBottomButton();
        } else {
            throw new RuntimeException("Проверить сверху или снизу  кнопку");
        }
        assertTrue(pageOrderForWhomScooter.pageIsDisplayed()); //проверили переход в окошко оформления заказа
        driver.quit();
    }
    @After
    public void after(){
        driver.quit();
    }

}
