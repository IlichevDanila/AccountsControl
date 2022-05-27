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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:application.properties")
public class EditScreensTests {

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
    public void ClientEditScreenTest()
    {
        driver.get("http://localhost:8080/ClientsSearch?type=Physical&");
        WebElement ID_1 = driver.findElement(By.id("SearchResult")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(0);
        ID_1.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement addressInput = driver.findElement(By.id("Address_input"));
        addressInput.clear();
        addressInput.sendKeys("Some new address");
        WebElement FIOInput = driver.findElement(By.id("FIO_input"));
        FIOInput.clear();
        FIOInput.sendKeys("Some new FIO");
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewFIO = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewFIO.getText(), "Some new FIO");
        WebElement NewAddress = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(3);
        assertEquals(NewAddress.getText(), "Some new address");
    }

    @Test
    public void OfficeEditScreenTest()
    {
        driver.get("http://localhost:8080/OfficesSearch?");
        WebElement ID_1 = driver.findElement(By.id("SearchResult")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(0);
        ID_1.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement addressInput = driver.findElement(By.id("Address_input"));
        addressInput.clear();
        addressInput.sendKeys("Some new address");
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewFIO = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewFIO.getText(), "Some new address");
    }

    @Test
    public void AccountEditScreenTest()
    {
        driver.get("http://localhost:8080/AccountsSearch?%20status=null&type=null&");
        WebElement ID_1 = driver.findElement(By.id("SearchResult")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(0);
        ID_1.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        Select statusInput = new Select(driver.findElement(By.id("Status_input")));
        statusInput.selectByValue("Frozen");
        WebElement loanInput = driver.findElement(By.id("Loan_input"));
        loanInput.clear();
        loanInput.sendKeys("99999959826343099999");
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewStatus = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewStatus.getText(), "Frozen");
        WebElement NewLoan = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(5);
        assertEquals(NewLoan.getText(), "99999959826343099999");
    }

    @Test
    public void AccountTypeScreenTest()
    {
        driver.get("http://localhost:8080/AccountTypesSearch?debiting=null&accrual=null&valid=null&period=null");
        WebElement ID_1 = driver.findElement(By.id("SearchResult")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(0);
        ID_1.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement nameInput = driver.findElement(By.id("Name_input"));
        nameInput.clear();
        nameInput.sendKeys("Some new name");
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
        WebElement NewName = Table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
        assertEquals(NewName.getText(), "Some new name");
    }

    @Test
    public void TransactionEditScreenTest()
    {
        driver.get("http://localhost:8080/TransactionsSearch?");
        WebElement ID_1 = driver.findElement(By.id("SearchResult")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(0);
        ID_1.click();
        WebElement ChangeViewButton = driver.findElement(By.id("ChangeViewButton"));
        ChangeViewButton.click();
        WebElement OKButton = driver.findElement(By.id("OKButton"));
        OKButton.click();
        WebElement Table = driver.findElement(By.id("SearchResult"));
        assertEquals(Table.findElements(By.tagName("tr")).size(), 2);
    }
}
