package ejercicio3.conPOyPFact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MyAccountPage {
    WebDriver driver;
    WebElement accessories;
    WebElement shoes;
    Actions builder;


    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/customer/account/index/");
        accessories = driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-3.parent > a"));
        shoes = driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-3.parent > ul > li.level1.nav-3-3 > a"));
        builder = new Actions(driver);
    }

    public void goToShoes(){
        // Movemos el ratón sobre el elemento "Accessories"
        builder.moveToElement(accessories);
        builder.perform();
        // Ahora serán visibles los hiperenlaces y podremos clickar sobre "shoes"
        shoes.click();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
