package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;
    WebElement account;
    WebElement logInHyper;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.driver.get("http://demo-store.seleniumacademy.com/");
        this.account = driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a"));
    }

    public void logIn() {
        account.click();
        this.logInHyper = driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a"));
        logInHyper.click();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
