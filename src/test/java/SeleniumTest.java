import io.github.bonigarcia.seljup.SeleniumExtension;
//import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
class SeleniumTest {
    //For School Computer use me
    //private final String PATH = "C:\\Users\\conta\\Documents\\School\\SOFTWARE TESTING AND QUALITY ASSURANCE\\ChromeDriver";

    //For Home Computer use me
    private final String PATH ="C:\\Users\\Remi\\Documents\\School\\SOFTWARE TESTING AND QUALITY ASSURANCE\\ChromeDriver";

    private final String BROWSER = "webdriver.chrome.driver";

    //For School Computer use me
    //private final String SCREENSHOTS = "C:\\Users\\conta\\Documents\\School\\SOFTWARE TESTING AND QUALITY ASSURANCE\\Screenshots";

    //For Home Computer use me
    private final String SCREENSHOTS = "C:\\Users\\Remi\\Documents\\School\\SOFTWARE TESTING AND QUALITY ASSURANCE\\Screenshots";

    ChromeDriver driver;

    public SeleniumTest(ChromeDriver driver){
        this.driver = driver;
        System.setProperty(BROWSER, PATH);
    }

    @Test
    public void test_facebook_maximize(){
        //Open up the webpage
        driver.get("https://www.facebook.com");

        //Maximize the webpage
        driver.manage().window().maximize();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();

    }

    @Test
    public void test_facebook_find_postbar(){
        //Open up the webpage
        driver.get("https://www.facebook.com");

        //Maximize the webpage
        driver.manage().window().maximize();

        WebElement fbLogo = driver.findElement(By.className("fb_logo"));

        assertThat(fbLogo.isDisplayed(), is(true));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();

    }
}