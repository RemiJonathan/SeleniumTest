import io.github.bonigarcia.seljup.SeleniumExtension;
//import org.junit.Test;
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
        System.setProperty(BROWSER, PATH);
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

    /**
     * click on a tab
     *
     * @param tabId is the ID of the element in the nav bar
     */
    public void clickTab(int tabId) {
        WebElement tab = driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[" + tabId + "]/a"));
        tab.click();
    }

    public void fillCustomerTable() {

    }

    @Test
    public void testGuru99LoginAndFindElementOnPage() {
        login();

        //Verify the successful login
        String managerStringValue = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText();
        assertEquals(managerStringValue, "Manger Id : " + username);
    }

    @Test
    public void test_registerNewCustomer_selectPage() {
        login();
        clickTab(2);
        boolean addCustomerString = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")).isDisplayed();

        assertEquals(addCustomerString, is(true));
    }

    @Test
    public void test_registerNewCustomer_submitForm() {
        login();
        clickTab(2);

        // Building Customer
        String customerName;
        String gender;
        String dateOfBirth;
        String address;
        String city;
        String state;
        String personalIdentificationNumber;
        String mobileNumber;
        String email;
        String password;

        //Filling Customer info;
        customerName = "Dylan Pita";
        //gender = alpha male;
        dateOfBirth = "0020000527";
        address = "4997 Queens Bay";
        city = "Balfour";
        state = "BC";
        personalIdentificationNumber = "568133";
        mobileNumber = "5145552243";
        email = "dylan@remyjonathan.com";
        password = "Dylan224397";

        driver.findElement(By.name("name")).sendKeys(customerName);
        driver.findElements(By.name("rad1")).get(1).click();
        driver.findElement(By.name("dob")).sendKeys(dateOfBirth);
        driver.findElement(By.name("addr")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(personalIdentificationNumber);
        driver.findElement(By.name("telephoneno")).sendKeys(mobileNumber);
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("sub")).click();

        assertEquals(driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[1]/td/p")).getText(), "Customer Registered Successfully!!!");
    }

    @Test
    public void test_returnToMgrDashboardAfterRegNewCustSucccess(){

    }
}