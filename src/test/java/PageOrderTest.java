import model.PageOrderForWhomScooter;
import model.PageOrderAboutRent;
import org.openqa.selenium.WebDriver;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;
import java.time.Duration;

@RunWith(Parameterized.class)
public class PageOrderTest {
    private static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String BROWSER_NAME = "Chrome";
    private static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";

    private WebDriver driver;
    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String colorScooter;
    private final String commentForCourier;

    public PageOrderTest(String name, String lastName, String address, String metroStation, String phone,
                         String date, String rentalPeriod, String colorScooter, String commentForCourier) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
        this.commentForCourier = commentForCourier;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Василий", "Иванов", "Измайловское шоссе", "Измайлово", "89175718453",
                        "03.04.2025", "трое суток", "black", "Батон"},
                {"Мария", "Попова", "Проспект Вернадского", "Юго-Западная", "89123456789",
                        "31.12.2024", "сутки", "grey", "Отвезите домой"},
        };
    }

    @Before
    public void before() {
        driver = SelectBrowser.selectDriverBrowser(BROWSER_NAME);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testOrder() {
        driver.get(ORDER_PAGE_URL);

        PageOrderForWhomScooter pageOrderForWhomScooter = new PageOrderForWhomScooter(driver);
        pageOrderForWhomScooter.testFieldFirstName(name);
        pageOrderForWhomScooter.testFieldLastName(lastName);
        pageOrderForWhomScooter.testFieldAdress(address);
        pageOrderForWhomScooter.testFieldMetroStation(metroStation);
        pageOrderForWhomScooter.testFieldForPhone(phone);
        pageOrderForWhomScooter.pushButtonNext();

        PageOrderAboutRent pageOrderAboutRent = new PageOrderAboutRent(driver);
        assertTrue("Страница 'Про аренду' не отобразилась",
                pageOrderAboutRent.testPageAboutRentIsDisplayed());

        pageOrderAboutRent.testFieldWhenToBringScooter(date);
        pageOrderAboutRent.testFieldRentalPeriod(rentalPeriod);
        pageOrderAboutRent.testFieldColorScooter(colorScooter);
        pageOrderAboutRent.testCommentFieldForCourier(commentForCourier);
        pageOrderAboutRent.testOrderButton();
        pageOrderAboutRent.testButtonOrderYes();

        String actualMessage = pageOrderAboutRent.testOrderMessage();
        assertTrue("Сообщение о заказе не содержит ожидаемого текста. Актуальное сообщение: " + actualMessage,
                actualMessage.contains("Заказ оформлен"));
    }

    @After
    public void after() {
        driver.quit();
    }
}