import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;
    WebElement account;
    WebElement logInHyper;
    WebElement logInForm;
    WebElement email;
    WebElement password;
    WebElement title;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.driver.get("http://demo-store.seleniumacademy.com/");
        this.account = driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a"));
    }

    public void userLogIn(String user, String password) {
        account.click();
        this.logInHyper = driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a"));
        logInHyper.click();
        this.email = driver.findElement(By.id("email"));
        this.password = driver.findElement(By.id("pass"));
        this.logInForm = driver.findElement(By.id("send2"));

        this.email.sendKeys(user);
        this.password.sendKeys(password);
        this.logInForm.submit();
    }
}
