package ejercicio3.conPOyPFact;

import org.openqa.selenium.*;

import java.util.Set;

public class ShoesPage {
    WebDriver driver;
    WebElement wingtip;
    WebElement suede;
    WebElement compareButton;
    WebElement clearAllButton;
    String myHandleId;
    Set<String> setIds;
    Alert alert;

    public ShoesPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://demo-store.seleniumacademy.com/accessories/shoes.html");
        wingtip = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(5) > div > div.actions > ul > li:nth-child(2) > a"));
        suede = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(6) > div > div.actions > ul > li:nth-child(2) > a"));
        myHandleId = driver.getWindowHandle();
    }

    public void selectShoes(int number){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch(number) {
            case 5:
                jse.executeScript("arguments[0].scrollIntoView();", wingtip);
                wingtip.click();
                break;
            case 6:
                jse.executeScript("arguments[0].scrollIntoView();", suede);
                suede.click();
                break;
        };
    }

    public void selectCompare(){
        compareButton = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-right.sidebar > div > div.block-content > div > button"));
        compareButton.click();

        // El handleID de la nueva ventana se añade al conjunto de manejadores del navegador
        setIds = driver.getWindowHandles();
        String[] handleIds = setIds.toArray(new String[setIds.size()]);
        System.out.println("ID 0: "+handleIds[0]); // Manejador de la ventana ShoesPage
        System.out.println("ID 1: "+handleIds[1]); // Manejador de la ventana ProductComparisonPage
        driver.switchTo().window(handleIds[1]); // Nos movemos a la ProductComparisonPage
    }

    public void selectClearAll(){
        clearAllButton = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-right.sidebar > div > div.block-content > div > a"));
        clearAllButton.click();
    }

    public void switchToAlert(){
        // Operaciones sobre ventanas de alerta
        // Cambiamos el foco a la ventana de alerta
        alert = driver.switchTo().alert();
        // Podemos obtener el mensaje de la ventana
        // String mensaje = alert.getText();
        // Podemos pulsar sobre el botón OK (si lo hubiese)
        // alert.accept();
        // Podemos pulsar sobre el botón Cancel (si lo hubiese)
        // alert.dismiss();
        // Podemos teclear algún texto (si procede)
        // alert.sendKeys("user");
    }

    public void acceptAlert(){
        alert.accept();
    }

    public String getAlertText(){
        return alert.getText();
    }

    public String getClearMessage(){
        return driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > ul > li > ul > li > span")).getText();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
