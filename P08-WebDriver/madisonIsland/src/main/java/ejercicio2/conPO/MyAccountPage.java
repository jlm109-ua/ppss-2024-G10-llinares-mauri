package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/customer/account/index/");
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
