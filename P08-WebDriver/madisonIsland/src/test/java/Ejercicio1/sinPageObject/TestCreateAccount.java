package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCreateAccount {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    @Test
    @Tag("OnlyOnce")
    public void S1_scenario_createAccount_should_create_new_account_in_the_demo_store_when_this_account_does_not_exist() {
        driver.get("https://madison-island.com/");
        // 1. Verificamos que el título de la página de inicio es el correcto ("Madison Island")
        String titulo = driver.getTitle();
        String tituloEsperado = "Madison Island";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 2. Seleccionamos Account, y a continuación seleccionamos el hiperenlace Login
        driver.findElement(By.cssSelector("site-header__icon site-header__account")).click();

        // 3. Verificamos que el titulo de la página es el correcto ("Customer Login")
        titulo = driver.getTitle();
        tituloEsperado = "Customer Login";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 4. Seleccionamos el botón "Create Account"
        driver.findElement(By.cssSelector("#customer_register_link")).click();

        // 5. Verificamos que estamos en la página correcta usando el título de la misma ("Create new Customer Account")
        titulo = driver.getTitle();
        tituloEsperado = "Create new Customer Account";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 6. Rellenamos los campos con los datos de la cuenta excepto el campo "Confirmation" (cada uno de vosotros elegirá unos valores diferentes), y enviamos los datos del formulario. Nota: el valor del password debe tener 6 o más caracteres.
        String firstName = "Juan";
        String lastName = "Juanito";
        String email = "juanjuanitojuan@gmail.com";
        String password = "juanito1234";
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("CreatePassword")).sendKeys(password);

        // ¿QUÉ CAMPO CONFIRMATION?

        // 7. Verificamos que nos aparece el mensaje "This is a required field." debajo del campo que hemos dejado vacío
        // 8. Rellenamos el campo que nos falta y volvemos a enviar los datos del formulario.
        // 9. Volvemos a la página anterior (ver observaciones)
        // 10.Verificamos que estamos en la página correcta usando su título ("My Account").


    }
}