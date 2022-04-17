package tests;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.launching;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class GetTemptest extends launching {

    ArrayList<String> brandName = new ArrayList<String>();
    ArrayList<String> sunscreensspf50 = new ArrayList<String>();
    ArrayList<String> sunscreensspf30 = new ArrayList<String>();
    ArrayList<String> aloe = new ArrayList<String>();
    ArrayList<String> almond = new ArrayList<String>();

    ArrayList<String> price = new ArrayList<String>();
    ArrayList<Integer> cost = new ArrayList<Integer>();
    ArrayList<Integer> almondCost = new ArrayList<Integer>();
    ArrayList<Integer> aloeCost = new ArrayList<Integer>();

    ArrayList<Integer> sunscreensspf50Cost = new ArrayList<Integer>();
    ArrayList<Integer> sunscreensspf30Cost = new ArrayList<Integer>();


    int num = 0;

    public GetTemptest() throws FileNotFoundException {
    }

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
        String instructions = driver.findElement(By.xpath("//*[@class=\"popover-body\"]")).getText();
        System.out.println(instructions);
    }
    @Test(priority = 4)
    public void countingNumberofItems() {
        for(int i = 1; i<4;i++) {
            brandName.add(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText());
            price.add(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[2]")).getText());
        }
        for(int i = 1; i<4;i++) {
            brandName.add(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/p[1]")).getText());
            price.add(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/p[2]")).getText());
        }
        System.out.println(price);
        for(int i = 0 ; i <6 ; i++) {
            String str;
            // Replacing every non-digit number
            // with a space(" ")
            str = (price.get(i).replaceAll("[^\\d]", " "));

            // Remove extra spaces from the beginning
            // and the ending of the string
            str = str.trim();

            // Replace all the consecutive white
            // spaces with a single space
            str = str.replaceAll(" +", " ");
            cost.add(Integer.parseInt(str));
        }
        System.out.println(cost);

        String headers = driver.findElement(By.xpath("/html/body/div[1]/div[1]")).getText();
        System.out.println(headers);

        Minimum min = new Minimum();
        String minpriceBrand;

        if(headers.equals("Sunscreens")) {
            for (int i = 0; i < 6; i++) {
//            System.out.println(brandName.get(i));
                if (brandName.get(i).contains("SPF-50") || brandName.get(i).contains("spf-50")) {
                    sunscreensspf50.add(brandName.get(i));
                    sunscreensspf50Cost.add(cost.get(i));
                }
                if (brandName.get(i).contains("SPF-30") || brandName.get(i).contains("spf-30")) {
                    sunscreensspf30.add(brandName.get(i));
                    sunscreensspf30Cost.add(cost.get(i));
                }
            }
            System.out.println(sunscreensspf50);
            System.out.println(sunscreensspf30);
            System.out.println(sunscreensspf30Cost);
            System.out.println(sunscreensspf50Cost);

            minpriceBrand = min.FindMinimum(sunscreensspf30Cost,sunscreensspf30);
            System.out.println(minpriceBrand);

            for(int i = 1; i<4;i++) {
                if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText().equals(minpriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/button")).click();
                }
            }
            for(int i = 1; i<4;i++) {
                if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[" + i + "]/p[1]")).getText().equals(minpriceBrand)) {
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/button")).click();

                }
            }
            minpriceBrand = min.FindMinimum(sunscreensspf50Cost,sunscreensspf50);
            System.out.println(minpriceBrand);

            for(int i = 1; i<4;i++) {
                if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText().equals(minpriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/button")).click();
                }
            }
            for(int i = 1; i<4;i++) {
                if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[" + i + "]/p[1]")).getText().equals(minpriceBrand)) {
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/button")).click();

                }
            }
        }
        else{
            for (int i = 0; i < brandName.size(); i++) {
//            System.out.println(brandName.get(i));
                if (brandName.get(i).contains("Aloe") || brandName.get(i).contains("aloe")) {
                    aloe.add(brandName.get(i));
                    aloeCost.add(cost.get(i));
                }
                if (brandName.get(i).contains("Almond") || brandName.get(i).contains("almond")) {
                    almond.add(brandName.get(i));
                    almondCost.add(cost.get(i));
                }
            }
            System.out.println(aloe);
            System.out.println(almond);
            System.out.println(aloeCost);
            System.out.println(aloeCost);

            minpriceBrand = min.FindMinimum(aloeCost,aloe);
            System.out.println(minpriceBrand);

            for(int i = 1; i<4;i++) {
                if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText().equals(minpriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/button")).click();
                }
            }
            for(int i = 1; i<4;i++) {
                if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[" + i + "]/p[1]")).getText().equals(minpriceBrand)) {
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/button")).click();

                }
            }
            minpriceBrand = min.FindMinimum(almondCost,almond);
            System.out.println(minpriceBrand);

            for(int i = 1; i<4;i++) {
                if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText().equals(minpriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/button")).click();
                }
            }
            for(int i = 1; i<4;i++) {
                if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[" + i + "]/p[1]")).getText().equals(minpriceBrand)) {
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/button")).click();

                }
            }

        }
    }
    @Test(priority = 5)
    public void verifyCart(){
        driver.findElement(By.xpath("/html/body/nav/ul/button")).click();
    }
    @Test (priority = 6)
    public void paymentGateway(){
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/button")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe")));
    }

    @Test(priority = 7)
    public void CardDetails() throws IOException {
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
        prop.load(ip);

        String email = prop.getProperty("email");

        String cardnumber = prop.getProperty("cardnumber");
        String cardnumber1 = prop.getProperty("cardnumber1");
        String cardnumber2 = prop.getProperty("cardnumber2");
        String cardnumber3 = prop.getProperty("cardnumber3");

        String expiry = prop.getProperty("expiry");
        String year = prop.getProperty("expiry1");
        String cvv = prop.getProperty("cvv");
        String zipcode = prop.getProperty("postalcode");

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardnumber);
        driver.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardnumber1);
        driver.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardnumber2);
        driver.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardnumber3);
        driver.findElement(By.xpath("//*[@id=\"cc-exp\"]")).sendKeys(expiry);
        driver.findElement(By.xpath("//*[@id=\"cc-exp\"]")).sendKeys(year);

        driver.findElement(By.xpath("//*[@id=\"cc-csc\"]")).sendKeys(cvv);
        driver.findElement(By.xpath("//*[@id=\"billing-zip\"]")).sendKeys(zipcode);

        driver.findElement(By.xpath("//*[@id=\"submitButton\"]")).click();
    }


}
