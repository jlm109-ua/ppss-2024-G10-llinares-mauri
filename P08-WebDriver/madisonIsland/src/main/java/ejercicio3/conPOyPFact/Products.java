package ejercicio3.conPOyPFact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Products {
    WebDriver driver;
    WebElement closeWindowButton;
    String myHandleId;
    String myHandleIdFROM;

    public Products(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/catalog/product_compare/index/");
        closeWindowButton = driver.findElement(By.cssSelector("body > div > div.buttons-set > button"));
    }

    public void closeWindow(){
        closeWindowButton.click();
        driver.switchTo().window(myHandleIdFROM);
        return PageFactory.initElements(driver,ShoesPage.class);

    }

    public String getTitle(){
        return driver.getTitle();
    }
}
