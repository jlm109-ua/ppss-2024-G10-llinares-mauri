package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestCreateAccount {
    WebDriver driver;

    @BeforeEach
    /**
     * Creamos el driver antes de cada test.
     */
    public void setup(){
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @AfterEach
    /**
     * Cerramos el navegador después de cada test.
     */
    public void tearDown() { driver.close(); }

    @Test
    @Tag("OnlyOnce")
    public void S1_scenario_createAccount_should_create_new_account_in_the_demo_store_when_this_account_does_not_exist() {
        // 1. Verificamos que el título de la página de inicio es el correcto ("Madison Island")
        String titulo = driver.getTitle();
        String tituloEsperado = "Madison Island";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 2. Seleccionamos Account, y a continuación seleccionamos el hiperenlace Login
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();

        // 3. Verificamos que el titulo de la página es el correcto ("Customer Login")
        titulo = driver.getTitle();
        tituloEsperado = "Customer Login";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 4. Seleccionamos el botón "Create Account"
        driver.findElement(By.cssSelector("#login-form > div > div.col-1.new-users > div.buttons-set > a")).click();

        // 5. Verificamos que estamos en la página correcta usando el título de la misma ("Create new Customer Account")
        titulo = driver.getTitle();
        tituloEsperado = "Create New Customer Account";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 6. Rellenamos los campos con los datos de la cuenta excepto el campo "Confirmation" (cada uno de vosotros elegirá unos valores diferentes), y enviamos los datos del formulario. Nota: el valor del password debe tener 6 o más caracteres.
        String firstName = "Juan";
        String middleName = "Juanito";
        String lastName = "Juan";
        String email = "juanjuanitojuan@gmail.com";
        String password = "juanito1234";
        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("middlename")).sendKeys(middleName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);

        // Enviamos el formulario.
        // driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).submit();

        // 7. Verificamos que nos aparece el mensaje "This is a required field." debajo del campo que hemos dejado vacío
        String required = driver.findElement(By.cssSelector("#advice-required-entry-confirmation")).getText(); // Guardamos el elemento de la web
        Assertions.assertEquals(required,"This is a required field."); // Confirmamos que se haya mostrado el elemento.

        // 8. Rellenamos el campo que nos falta y volvemos a enviar los datos del formulario.
        driver.findElement(By.id("confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).submit();

        // 9. Volvemos a la página anterior (ver observaciones)
        driver.navigate().back();

        // 10.Verificamos que estamos en la página correcta usando su título ("My Account").
        titulo = driver.getTitle();
        tituloEsperado = "My Account";
        Assertions.assertEquals(tituloEsperado,titulo);
    }
}