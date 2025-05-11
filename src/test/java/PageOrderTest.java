import model.PageOrderForWhomScooter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;
import model.PageOrderAboutRent;

@RunWith(Parameterized.class)
public class PageOrderTest {
        private static final String MAIN_PAGE_URL="https://qa-scooter.praktikum-services.ru/"; //гл. страница
        private static final String BROWSER_NAME="Google Chrome"; //Браузер, в котором работаем
    private static final String ORDER_PAGE_URL="https://qa-scooter.praktikum-services.ru/order" ; //урл страницы заказа

    private static WebDriver driver;
    private Object SelectBrowser;
    private String comment;

    //создали браузер..
    @Before
    public void before() {
        driver = SelectBrowser.selectDriverBrowser(BROWSER_NAME);
}
    //поля класса
    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String colorScooter;
    private final String commentForCourier;

    //конструктор класса
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
    //параметризованные тестовые данные
    @Parameterized.Parameters
    public Object[][] getTest() {
        return new Object[][]{
                {"Василий", "Иванов", "Измайловское шоссе", "Измайлово", "89175718453", "03.04.2025", "Трое суток", "Black pearl", "Батон"},
                {"Мария", "Попова", "Проспект Вернадского", "Юго-Западная", "89123456789", "31.12.2024", "Сутки", "Grey hopelessness", "Отвезите домой"},
        };

        @Test
        public void testOrder() {
            driver.get(ORDER_PAGE_URL);
            PageOrderForWhomScooter pageOrderForWhomScooter = new PageOrderForWhomScooter(driver);
            pageOrderForWhomScooter.testFieldFirstName(name); //заполняем поле "имя" значением, которое указали в тестовых данных
            pageOrderForWhomScooter.testFieldLastName(lastName); //заполняем поле "фамилия" значением, которое указали в тестовых данных
            pageOrderForWhomScooter.testFieldAdress(address); //заполняем поле "адрес" значением, которое указали в тестовых данных
            pageOrderForWhomScooter.testFieldMetroStation(metroStation); //поле "станция метро"
            pageOrderForWhomScooter.testFieldForPhone(phone); //поле "телефон"
            pageOrderForWhomScooter.pushButtonNext();//кнопка "Далее"

            PageOrderAboutRent pageOrderAboutRent = new PageOrderAboutRent(driver);
            pageOrderAboutRent.testPageAboutRentIsDisplayed(); //проверяем переход на страницу "про аренду"
            pageOrderAboutRent.testFieldWhenToBringScooter(date); //"дата доставки"
            pageOrderAboutRent.testFieldRentalPeriod(rentalPeriod); //поле "срок аренды"
            pageOrderAboutRent.testFieldColorScooter(colorScooter); //цвет самоката
            pageOrderAboutRent.testCommentFieldForCourier(comment); //поле "комментарий
            pageOrderAboutRent.testOrderButton(); //кнопку "Заказать"
            pageOrderAboutRent.testButtonOrderYes(); //кнопка "Да"

            //проверяем что заказ оформлен
            assertTrue(pageOrderAboutRent.toString().contains("Заказ оформлен"));
            assertTrue(pageOrderAboutRent.toString().contains("Номер заказа:"));
            assertTrue(pageOrderAboutRent.toString().contains("Запишите его:"));
            assertTrue(pageOrderAboutRent.toString().contains("пригодится, чтобы отслеживать статус"));

            driver.quit();
        }
        @After
        public void after() {
            driver.quit();
        }
    }