package conPOyPFact;

import ejercicio3.conPOyPFact.Cookies;
import ejercicio3.conPOyPFact.MyAccountPage;
import ejercicio3.conPOyPFact.Products;
import ejercicio3.conPOyPFact.ShoesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestShoes {
    WebDriver driver;
    MyAccountPage myAccountPage;
    ShoesPage shoesPage;
    Products products;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        Cookies.loadCookiesFromFile(driver);
        driver.get("http://demo-store.seleniumacademy.com/customer/account/");
        myAccountPage = new MyAccountPage(driver);
        shoesPage = new ShoesPage(driver);
        products = new Products(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @Test
    public void compareShoes(){
        // 1. Verificamos que el título de la página es el correcto ("My Account")
        Assertions.assertEquals("My Account",myAccountPage.getTitle());

        // 2. Seleccionamos Accessories → Shoes
        myAccountPage.goToShoes();

        // 3. Verificamos que el titulo de la página es el correcto ("Shoes - Accessories")
        Assertions.assertEquals(shoesPage.getTitle(),"Shoes - Accessories");

        // 4. Seleccionamos dos zapatos para compararlos (pulsando sobre "Add to Compare". En concreto queremos seleccionar los dos últimos.
        shoesPage.selectShoes(5);
        shoesPage.selectShoes(6);

        // 5. Seleccionamos el botón "COMPARE"
        shoesPage.selectCompare();

        // 6. Verificamos que estamos en la página correcta usando el título de la misma ("Products Comparison List ...")
        Assertions.assertTrue(products.getTitle().contains("Products Comparison List"));

        // 7. Cerramos la ventana con la comparativa de productos
        products.closeWindow();

        // 8. Verificamos que estamos de nuevo en la ventana ("Shoes - Accessories")
        Assertions.assertEquals(shoesPage.getTitle(),"Shoes - Accessories");

        // 9. Borramos la comparativa (hiperenlace "Clear All"), y verificamos que nos aparece una ventana de alerta con el mensaje "Are you sure you would like to remove all products from your comparison?"
        shoesPage.selectClearAll();
        shoesPage.switchToAlert();
        String mensaje = shoesPage.getAlertText();
        Assertions.assertEquals("Are you sure you would like to remove all products from your comparison?",mensaje);
        shoesPage.acceptAlert();

        // 10.Verificamos que en la página aparece el mensaje: "the comparison list was cleared"
        mensaje = shoesPage.getClearMessage();
        Assertions.assertEquals("The comparison list was cleared.",mensaje);
    }
}
