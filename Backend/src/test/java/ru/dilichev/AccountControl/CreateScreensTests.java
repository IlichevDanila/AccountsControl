package ru.dilichev.AccountControl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.TestPropertySource;
import ru.dilichev.AccountControl.Models.AccountType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:application.properties")
public class CreateScreensTests {

    private SessionFactory sessionFactory;

    private FirefoxDriver driver;

    @Autowired
    void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean)
    {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    @AfterEach
    @BeforeAll
    void empty_tables()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE Offices RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Transactions RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Physical_Clients RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Legal_Clients RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Clients RESTART IDENTITY CASCADE").executeUpdate();
        session.createSQLQuery("TRUNCATE Accounts RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Account_Types RESTART IDENTITY CASCADE").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @BeforeEach
    public void Setup()
    {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Asimus\\Desktop\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1680, 1024));
        driver.get("localhost:8080/");
    }

    @AfterEach
    public void End()
    {
        driver.close();
    }

    @Test
    public void ClientCreateScreenTest()
    {
        WebElement ClientEntity = driver.findElement(By.cssSelector("[data-entityid=Clients]"));
        ClientEntity.click();
        WebElement CreateButton = driver.findElement(By.id("CreateButton"));
        CreateButton.click();
        WebElement tinInput = driver.findElement(By.id("TIN_input"));
        tinInput.sendKeys("123456789012");
        WebElement phoneInput = driver.findElement(By.id("Phone_input"));
        phoneInput.sendKeys("79156751234");
        WebElement addressInput = driver.findElement(By.id("Address_input"));
        addressInput.sendKeys("Some address");
        Select type = new Select(driver.findElement(By.id("ClientType_select")));
        type.selectByValue("physical");
        WebElement FIOInput = driver.findElement(By.id("FIO_input"));
        FIOInput.sendKeys("Some FIO");
        WebElement PassportInput = driver.findElement(By.id("Passport_input"));
        PassportInput.sendKeys("4515607890");
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();

        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewFIO = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewFIO.getText(), "Some FIO");
        WebElement NewAddress = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(3);
        assertEquals(NewAddress.getText(), "Some address");

        ClientEntity = driver.findElement(By.cssSelector("[data-entityid=Clients]"));
        ClientEntity.click();
        CreateButton = driver.findElement(By.id("CreateButton"));
        CreateButton.click();
        tinInput = driver.findElement(By.id("TIN_input"));
        tinInput.sendKeys("1234567890");
        phoneInput = driver.findElement(By.id("Phone_input"));
        phoneInput.sendKeys("79156751235");
        addressInput = driver.findElement(By.id("Address_input"));
        addressInput.sendKeys("Another address");
        type = new Select(driver.findElement(By.id("ClientType_select")));
        type.selectByValue("legal");
        WebElement NameInput = driver.findElement(By.id("OrgName_input"));
        NameInput.sendKeys("Some name");
        WebElement FormInput = driver.findElement(By.id("OrgForm_input"));
        FormInput.sendKeys("OOO");
        OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();

        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewName = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewName.getText(), "Some name");
        WebElement NewForm = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(2);
        assertEquals(NewForm.getText(), "OOO");
    }

    @Test
    public void OfficeCreateScreenTest()
    {
        WebElement ClientEntity = driver.findElement(By.cssSelector("[data-entityid=Offices]"));
        ClientEntity.click();
        WebElement CreateButton = driver.findElement(By.id("CreateButton"));
        CreateButton.click();
        WebElement idInput = driver.findElement(By.id("ID_input"));
        idInput.sendKeys("123456789012");
        WebElement phoneInput = driver.findElement(By.id("Phone_input"));
        phoneInput.sendKeys("79156751234");
        WebElement addressInput = driver.findElement(By.id("Address_input"));
        addressInput.sendKeys("Some address");
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();

        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewAddress = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewAddress.getText(), "Some address");
        WebElement NewPhone = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(2);
        assertEquals(NewPhone.getText(), "79156751234");
    }

    @Test
    public void AccountCreateScreenTest()
    {
        WebElement ClientEntity = driver.findElement(By.cssSelector("[data-entityid=Accounts]"));
        ClientEntity.click();
        WebElement CreateButton = driver.findElement(By.id("CreateButton"));
        CreateButton.click();
        WebElement idInput = driver.findElement(By.id("ID_input"));
        idInput.sendKeys("11245678900987654321");
        WebElement timeInput = driver.findElement(By.id("Date_input"));
        timeInput.sendKeys("2022-05-27 18:00:00");
        Select typeInput = new Select(driver.findElement(By.id("AccountType_input")));
        typeInput.selectByIndex(1);
        WebElement loanInput = driver.findElement(By.id("Loan_input"));
        loanInput.sendKeys("09876543211111111111");
        WebElement clientInput = driver.findElement(By.id("Client_input"));
        clientInput.sendKeys("2");
        WebElement balanceInput = driver.findElement(By.id("Balance_input"));
        balanceInput.sendKeys("10.0");
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();

        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewStatus = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewStatus.getText(), "Opened");
        WebElement NewLoan = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(5);
        assertEquals(NewLoan.getText(), "09876543211111111111");
        WebElement NewClient = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(6);
        assertEquals(NewClient.getText(), "2");
        WebElement NewBalance = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(7);
        assertEquals(NewBalance.getText(), "10.0");
    }

    @Test
    public void AccountTypeCreateScreenTest()
    {
        WebElement ClientEntity = driver.findElement(By.cssSelector("[data-entityid=AccountTypes]"));
        ClientEntity.click();
        WebElement CreateButton = driver.findElement(By.id("CreateButton"));
        CreateButton.click();
        WebElement idInput = driver.findElement(By.id("ID_input"));
        idInput.sendKeys("123");
        WebElement NameInput = driver.findElement(By.id("Name_input"));
        NameInput.sendKeys("Some name");
        WebElement profitInput = driver.findElement(By.id("Profitability"));
        profitInput.sendKeys("10");
        WebElement percentInput = driver.findElement(By.id("Percent_input"));
        percentInput.click();
        WebElement accrualInput = driver.findElement(By.id("Accrual_input"));
        accrualInput.click();
        Select periodInput = new Select(driver.findElement(By.id("Period_input")));
        periodInput.selectByValue("Annually");
        WebElement validInput = driver.findElement(By.id("Valid_input"));
        validInput.click();
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();

        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewName = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewName.getText(), "Some name");
        WebElement NewProfit = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(3);
        assertEquals(NewProfit.getText(), "10.0");
        WebElement NewPeriod = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(6);
        assertEquals(NewPeriod.getText(), "Annually");
        WebElement NewValid = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(7);
        assertEquals(NewValid.getText(), "Да");
    }

    @Test
    public void TransactionCreateScreenTest()
    {
        WebElement ClientEntity = driver.findElement(By.cssSelector("[data-entityid=Transactions]"));
        ClientEntity.click();
        WebElement CreateButton = driver.findElement(By.id("CreateButton"));
        CreateButton.click();
        WebElement idInput = driver.findElement(By.id("ID_input"));
        idInput.sendKeys("123");
        WebElement DebitInput = driver.findElement(By.id("Debit_input"));
        DebitInput.sendKeys("40701959800049107299");
        WebElement CreditInput = driver.findElement(By.id("Credit_input"));
        CreditInput.sendKeys("40804959200027413046");
        WebElement timeInput = driver.findElement(By.id("Date_input"));
        timeInput.sendKeys("2022-05-27 18:00:00");
        WebElement BalanceInput = driver.findElement(By.id("Sum_input"));
        BalanceInput.sendKeys("12300.0");
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();

        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewDebit = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewDebit.getText(), "40701959800049107299");
        WebElement NewCredit = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(2);
        assertEquals(NewCredit.getText(), "40804959200027413046");
        WebElement NewTime = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(3);
        assertEquals(NewTime.getText(), "2022-05-27 18:00:00.0");
        WebElement NewBalance = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(4);
        assertEquals(NewBalance.getText(), "12300.0");
    }
}
