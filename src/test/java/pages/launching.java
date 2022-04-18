package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import tests.takeScreenShots;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class launching {
    public static WebDriver driver;
    @BeforeTest
    public void setup() throws IOException {
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
        prop.load(ip);
        String browserName = prop.getProperty("browser");
        String url = prop.getProperty("url");

        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\Users\\adityakumar72\\Documents\\chromedriver.exe");
            driver= new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();
            driver.navigate().to(url);
            takeScreenShots.screenshots(driver,"UrlNavigated");
        }
        else{
            System.out.println("No Value found in properties file for the Provided key");
        }
        //driver.close();
    }
    @AfterTest
    public static void closeBrowser() throws InterruptedException {
        takeScreenShots.screenshots(driver,"paymentSuccessfulll");
        System.out.println("You are in closing zone");
        Thread.sleep(3000);
        driver.close();
    }


}
