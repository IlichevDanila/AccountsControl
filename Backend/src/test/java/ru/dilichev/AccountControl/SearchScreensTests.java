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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SearchScreensTests {

    private FirefoxDriver driver;


    @BeforeEach
    public void Setup()
    {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Asimus\\Desktop\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1680, 1024));
    }

    @AfterEach
    public void End()
    {
        driver.close();
    }

    @Test
    public void MainScreenTest()
    {
        driver.get("localhost:8080/");
    }

    @Test
    public void ClientSearchScreenTest()
    {
        driver.get("localhost:8080/");

        WebElement ClientEntity = driver.findElement(By.cssSelector("[data-entityid=Clients]"));
        ClientEntity.click();
        WebElement SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 11);

        ClientEntity = driver.findElement(By.cssSelector("[data-entityid=Clients]"));
        ClientEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        Select typeSelect = new Select(driver.findElement(By.id("ClientType_select")));
        typeSelect.selectByValue("Legal");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 3);

        ClientEntity = driver.findElement(By.cssSelector("[data-entityid=Clients]"));
        ClientEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement passportInput = driver.findElement(By.id("Passport_input"));
        passportInput.sendKeys("4504549236");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
    }

    @Test
    public void OfficeSearchScreenTest()
    {
        driver.get("localhost:8080/");

        WebElement OfficeEntity = driver.findElement(By.cssSelector("[data-entityid=Offices]"));
        OfficeEntity.click();
        WebElement SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 11);

        OfficeEntity = driver.findElement(By.cssSelector("[data-entityid=Offices]"));
        OfficeEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement idInput = driver.findElement(By.id("ID_input"));
        idInput.sendKeys("7");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);

        OfficeEntity = driver.findElement(By.cssSelector("[data-entityid=Offices]"));
        OfficeEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement phoneInput = driver.findElement(By.id("Phone_input"));
        phoneInput.sendKeys("89046156200");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
    }

    @Test
    public void AccountSearchScreenTest()
    {
        driver.get("localhost:8080/");

        WebElement AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=Accounts]"));
        AccountsEntity.click();
        WebElement SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 22);

        AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=Accounts]"));
        AccountsEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement idInput = driver.findElement(By.id("ID_input"));
        idInput.sendKeys("40501978500035688960");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);

        AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=Accounts]"));
        AccountsEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement clientInput = driver.findElement(By.id("Client_input"));
        clientInput.sendKeys("6");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 4);
    }

    @Test
    public void AccountTypeSearchScreenTest()
    {
        driver.get("localhost:8080/");

        WebElement AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=AccountTypes]"));
        AccountsEntity.click();
        WebElement SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 9);

        AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=AccountTypes]"));
        AccountsEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        Select validSelect = new Select(driver.findElement(By.id("Valid_input")));
        validSelect.selectByValue("False");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 3);

        AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=AccountTypes]"));
        AccountsEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement phighInput = driver.findElement(By.id("Profitability_high_input"));
        phighInput.sendKeys("0");
        WebElement percentInput = driver.findElement(By.id("Percent_input"));
        percentInput.click();
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 5);
    }

    @Test
    public void TransactionSearchScreenTest()
    {
        driver.get("localhost:8080/");

        WebElement AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=Transactions]"));
        AccountsEntity.click();
        WebElement SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 11);

        AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=Transactions]"));
        AccountsEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement idInput = driver.findElement(By.id("ID_input"));
        idInput.sendKeys("4");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);

        AccountsEntity = driver.findElement(By.cssSelector("[data-entityid=Transactions]"));
        AccountsEntity.click();
        SearchButton = driver.findElement(By.id("SearchButton"));
        SearchButton.click();
        WebElement slowInput = driver.findElement(By.id("Sum_low_input"));
        slowInput.sendKeys("1000");
        WebElement shighInput = driver.findElement(By.id("Sum_high_input"));
        shighInput.sendKeys("5000");
        ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 5);
    }
}
