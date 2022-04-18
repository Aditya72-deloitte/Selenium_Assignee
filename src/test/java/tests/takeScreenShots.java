package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;

import static java.sql.DriverManager.getDriver;

public class takeScreenShots {
    public static String screenshots(WebDriver driver, String filename) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+ "\\SS\\" + filename + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source,finalDestination);
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;
    }
}