package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerLoginPage {
    WebDriver driver;
    WebElement email;
    WebElement password;
    WebElement logInForm;
    WebElement error;
    public CustomerLoginPage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/customer/account/login/");
    }

    public void logIn(String user, String password) {
        this.email = driver.findElement(By.id("email"));
        this.password = driver.findElement(By.id("pass"));
        this.logInForm = driver.findElement(By.id("send2"));

        this.email.sendKeys(user);
        this.password.sendKeys(password);
        this.logInForm.submit();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getInvalidPasswordError(){
        return driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span")).getText();
    }
}
