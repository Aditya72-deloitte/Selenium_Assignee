package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.launching;

public class GetTemptest extends launching {
    int num = 0;
    @Test(priority = 1)
    public void getString() {
        String temp = driver.findElement(By.xpath("//*[@id=\"temperature\"]")).getText();
        System.out.println(temp);
        int i = 0;
        boolean isNeg = false;
// Check for negative sign; if it's there, set the isNeg flag
        if (temp.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }
// Process each character of the string;
        while( i < 2) {
            num *= 10;
            num += temp.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
        }
        if (isNeg)
            num = -num;
        System.out.println(num);
    }
    @Test(priority = 2)
    public void click(){
        if(num>18){
            driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/a/button")).click();
        }
        else{
            driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/a/button")).click();
        }
    }
    @Test(priority = 3)
    public void readinInstruction(){
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/span")).click();
        String instructions = driver.findElement(By.xpath("//*[@id=\"popover58884\"]/div[2]/text()")).getText();
        System.out.println(instructions);

    }
}
