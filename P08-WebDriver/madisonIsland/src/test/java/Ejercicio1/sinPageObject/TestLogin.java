package Ejercicio1.sinPageObject;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestLogin {
    WebDriver driver;
    TestCreateAccount createAccount;

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");
        createAccount = new TestCreateAccount();
    }

    @AfterEach
    void tearDown(){
        driver.close();
    }

    @Test
    public void S2_scenario_loginOK_should_login_with_success_when_user_account_exists(){
        // 1. Verificamos que el título de la página de inicio es el correcto ("Madison Island").
        String titulo = driver.getTitle();
        String tituloEsperado = "Madison Island";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 2. Seleccionamos Account, y a continuación seleccionamos el hiperenlace Login.
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();

        // 3. Verificamos que el titulo de la página es el correcto ("Customer Login").
        titulo = driver.getTitle();
        tituloEsperado = "Customer Login";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 4. Rellenamos el campo email con el email de la cuenta que hemos creado en el driver createAccount() y enviamos el formulario.
        String email = "juanjuanitojuan@gmail.com";
        driver.findElement(By.id("email")).sendKeys(email);
        // driver.findElement(By.id("send2")).click(); NO, REALIZARLO CON SUBMIT()
        driver.findElement(By.id("send2")).submit();

        // 5. Verificamos que nos aparece el mensaje "This is a required field" debajo del campo que hemos dejado vacío.
        String required = driver.findElement(By.id("advice-required-entry-pass")).getText();
        Assertions.assertEquals(required,"This is a required field."); // Confirmamos que se haya mostrado el elemento.

        // 6. Rellenamos el campo con la contraseña y volvemos a enviar los datos del formulario.
        String password = "juanito1234";
        driver.findElement(By.id("pass")).sendKeys(password);

        driver.findElement(By.id("send2")).submit();

        // 7. Verificamos que estamos en la página correcta usando su título ("My Account").
        titulo = driver.getTitle();
        tituloEsperado = "My Account";
        Assertions.assertEquals(tituloEsperado,titulo);
    }

    @Test
    public void S3_scenario_loginFailed_should_fail_when_user_account_not_exists(){
        // 1. Verificamos que el título de la página de inicio es el correcto ("Madison Island")
        String titulo = driver.getTitle();
        String tituloEsperado = "Madison Island";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 2. Seleccionamos Account, y a continuación seleccionamos el hiperenlace Login.
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();

        // 3. Verificamos que el título de la página es el correcto ("Customer Login")
        titulo = driver.getTitle();
        tituloEsperado = "Customer Login";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 4. Rellenamos los campos con el email de la cuenta que hemos creado en el driver createAccount() y con un password incorrecto. Enviamos el formulario.
        String email = "juanjuanitojuan@gmail.com";
        String password = "estaConstraseñaNoEs";
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("send2")).submit();

        // 5. Verificamos que nos aparece el mensaje "Invalid login or password".
        String invalidPassword = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span")).getText();
        Assertions.assertEquals(invalidPassword,"Invalid login or password.");
    }
}
