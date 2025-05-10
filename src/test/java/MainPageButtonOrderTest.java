import model.PageOrderForWhomScooter;
import org.openqa.selenium.WebDriver;
import model.MainPage;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MainPageButtonOrderTest {
    private static final String MAIN_PAGE_URL="https://qa-scooter.praktikum-services.ru/"; //гл. страница
    private static final String BROWSER_NAME="Google Chrome";

    private static WebDriver driver;
    private Object SelectBrowser;

    //создали браузер..
    @Before
    public void before() {
        driver = SelectBrowser.selectDriverBrowser(BROWSER_NAME);
    }

    //создали конструктор класса
public void MainPageButtonOrderTest(String selectButton){
this.selectButton=selectButton;
}
    //создали поля класса
    public static String selectButton;

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
        };
        assertTrue(pageOrderForWhomScooter.pageisDisplayed()); //проверили переход в окошко оформления заказа
        driver.quit();
    }
    @After
    public void after(){
        driver.quit();
    }

}
