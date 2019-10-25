import io.github.bonigarcia.seljup.SeleniumExtension;
//import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
class SeleniumTest {
    //For School Computer use me
    private final String PATH = "C:\\Users\\conta\\Documents\\School\\SOFTWARE TESTING AND QUALITY ASSURANCE\\ChromeDriver";

    //For Home Computer use me
    //private final String PATH ="C:\\Users\\Remi\\Documents\\School\\SOFTWARE TESTING AND QUALITY ASSURANCE\\ChromeDriver";

    private final String BROWSER = "webdriver.chrome.driver";

    //For School Computer use me
    private final String SCREENSHOTS = "C:\\Users\\conta\\Documents\\School\\SOFTWARE TESTING AND QUALITY ASSURANCE\\Screenshots";

    //For Home Computer use me
    //private final String SCREENSHOTS = "C:\\Users\\Remi\\Documents\\School\\SOFTWARE TESTING AND QUALITY ASSURANCE\\Screenshots";

    ChromeDriver driver;

    //User ID: mngr229914
    String username = "mngr229914";
    //Password: EpUjAzu
    String password = "EpUjAzu";

    public SeleniumTest(ChromeDriver driver) {
        this.driver = driver;
        //System.setProperty(BROWSER, PATH);
        System.setProperty("sel.jup.screenshot.at.the.end.of.tests", "true");
        System.setProperty("sel.jup.screenshot.format", "png");
        System.setProperty("sel.jup.output.folder", "./src/test/testDoneScreenshots");
    }

    public void login() {
        //Open the webpage
        driver.get("http://demo.guru99.com/V4/");

        //Find and fill the username field
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='uid']"));
        usernameField.sendKeys(username);

        //Find and fill the password field
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(password);

        //Find and click the login button
        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();
    }

    public void clickTab(int tabId) {
        WebElement tab = driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[" + tabId + "]/a"));
        tab.click();
    }

    public void fillCustomerTable(String customerName, String gender, String dateOfBirth, String address, String city, String state, String personalIdentificationNumber, String mobileNumber, String email, String password) {
        driver.findElement(By.name("name")).sendKeys(customerName);
        if (gender.toUpperCase().equals("MALE")) {
            driver.findElements(By.name("rad1")).get(1).click();
        } else {
            driver.findElements(By.name("rad1")).get(2).click();
        }
        driver.findElement(By.name("dob")).sendKeys(dateOfBirth);
        driver.findElement(By.name("addr")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(personalIdentificationNumber);
        driver.findElement(By.name("telephoneno")).sendKeys(mobileNumber);
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public void fillAddAccountTable(int customerId, String accountType, int initialDeposit) {
        driver.findElement(By.name("cusid")).sendKeys(Integer.toString(customerId));
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td[2]/select")).click();
        if (accountType.toUpperCase().equals("Current")) {
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td[2]/select/option[2]")).click();
        } else {
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td[2]/select/option[1]")).click();
        }
        driver.findElement(By.name("inideposit")).sendKeys(Integer.toString(initialDeposit));

    }

    @Test
    @Order(1)
    public void testGuru99LoginAndFindElementOnPage() {
        login();

        //Verify the successful login
        String managerStringValue = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText();
        assertEquals("Manger Id : " + username, managerStringValue);

    }

    @Test
    @Order(2)
    public void test_registerNewCustomer_selectPage() {
        login();
        clickTab(2);
        boolean addCustomerString = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")).isDisplayed();

        assertEquals(true, addCustomerString);
    }

    @Test
    @Order(3)
    public void test_registerNewCustomer_submitForm() {
        login();
        clickTab(2);
        //Use of randomness to generate original emails every time
        fillCustomerTable("REMI JONATHAN", "male", "0019950612", "1230 Maple", "New York", "NY", "809828",
                "5645552352", Math.round(Math.random() * 100000) + "user@email.com", Math.round(Math.random() * 100000) + "");

        driver.findElement(By.name("sub")).click();

        assertEquals(driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[1]/td/p")).getText(), "Customer Registered Successfully!!!");
    }

    @Test
    @Order(4)
    public void test_returnToMgrDashboardAfterRegNewCustSucccess() {
        login();
        clickTab(2);
        //Use of randomness to generate original emails every time
        fillCustomerTable("REMI JONATHAN", "male", "0019950612", "1230 Maple", "New York", "NY", "809828",
                "5645552352", "user" + Math.round(Math.random() * 10000) + "@email.com", Math.round(Math.random() * 100000) + "");

        driver.findElement(By.name("sub")).click();
        clickTab(1);
        String managerStringValue = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText();
        assertEquals("Manger Id : " + username, managerStringValue);
    }

    @Test
    @Order(5)
    public void test_newAccount_SelectPageSuccess() {
        login();
        clickTab(5);

        String addAccountStringValue = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")).getText();
        assertEquals("Add new account form", addAccountStringValue);
    }

    @Test
    @Order(6)
    public void test_newAccount_AddFormSuccess() {
        login();
        clickTab(5);

        fillAddAccountTable(31540, "Current", 199325);
        driver.findElement(By.name("button2")).click();

        String accountGeneratedString = driver.findElement(By.xpath("//*[@id=\"account\"]/tbody/tr[1]/td/p")).getText();

        assertEquals("Account Generated Successfully!!!", accountGeneratedString);
    }

    @Test
    @Order(7)
    public void test_balanceInquiry(){

    }

    @Test
    @Order(8)
    public void test_balanceInquiry(){

    }

    @Test
    @Order(9)
    public void test_balanceInquiry(){

    }

    @Test
    @Order(10)
    public void test_balanceInquiry(){

    }
}