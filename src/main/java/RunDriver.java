import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import static org.testng.AssertJUnit.assertEquals;

public class RunDriver {

    public static final String AUTOMATION_SITE = "http://automationpractice.com/";

    @Test
    public static void testAssertEquals() {
        assertEquals("failure - strings are not equal", "text", "text");
        System.out.println("test runs");
    }

    public static void main(String[] params) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/dmitrijsulga/Documents/Tools/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(AUTOMATION_SITE);
        driver.findElement(By.xpath("//div[@class='header_user_info']/a[@class='login']")).click();

        WebElement email = driver.findElement(By.xpath("//input[@name='email_create']"));
        Random rand = new Random();
        int int_random = rand.nextInt();
        String generatedEmail = "bob" + int_random + "@gmail.com";
        email.sendKeys(generatedEmail);
        driver.findElement(By.xpath("//button[@name='SubmitCreate']")).click();

        // sign in data
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//label[@for='id_gender1']")).click();
        driver.findElement(By.xpath("//input[@name='customer_firstname']")).sendKeys("Bob");
        driver.findElement(By.xpath("//input[@name='customer_lastname']")).sendKeys("Jones");
        driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("sdsf4r23fd");

        Select birthdayDay = new Select(driver.findElement(By.xpath("//select[@name='days']")));
        Select birthdayMonth = new Select(driver.findElement(By.xpath("//select[@name='months']")));
        Select birthdayYear = new Select(driver.findElement(By.xpath("//select[@name='years']")));
        birthdayDay.selectByValue("5");
        birthdayMonth.selectByValue("11");
        birthdayYear.selectByValue("1986");
        driver.findElement(By.xpath("//label[@for='newsletter']")).click();

        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Bob");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Jones");
        driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Bob INC");
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("46 Birch Hill St. Ormond Beach, FL 32174");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Kiev");
        Select country = new Select(driver.findElement(By.xpath("//select[@name='id_country']")));
        country.selectByValue("21");
        Select state = new Select(driver.findElement(By.xpath("//select[@name='id_state']")));
        state.selectByValue("11");
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("32174");
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("+15854380072");
        driver.findElement(By.xpath("//input[@name='phone_mobile']")).sendKeys("+15854383472");
        driver.findElement(By.xpath("//input[@name='alias']")).sendKeys("Bob@gmail.com");
        driver.findElement(By.xpath("//button[@name='submitAccount']")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String myAccontText = driver.findElement(By.xpath("//*[@class='page-heading'][text() = 'My account']")).getText();
        System.out.println(myAccontText);
        assertEquals("failure - didn't visit my-account page", "MY ACCOUNT", myAccontText);

        Thread.sleep(3000);
        driver.quit();
    }
}