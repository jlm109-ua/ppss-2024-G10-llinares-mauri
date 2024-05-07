package Ejercicio1.sinPageObject;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin {
    WebDriver driver;
    TestCreateAccount createAccount;

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        createAccount = new TestCreateAccount();
    }

    @Test
    public void S2_scenario_loginOK_should_login_with_success_when_user_account_exists(){
        // 1. Verificamos que el título de la página de inicio es el correcto ("Madison Island").
        String titulo = driver.getTitle();
        String tituloEsperado = "Madison Island";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 2. Seleccionamos Account, y a continuación seleccionamos el hiperenlace Login.
        driver.findElement(By.cssSelector("a[href=\"/account/login\"]")).click();

        // 3. Verificamos que el titulo de la página es el correcto ("Customer Login").
        titulo = driver.getTitle();
        tituloEsperado = "Customer Login";
        Assertions.assertEquals(tituloEsperado,titulo);

        // 4. Rellenamos el campo email con el email de la cuenta que hemos creado en el driver createAccount() y enviamos el formulario.

        // QUÉ DRIVER CREATEACCOUNT() HEMOS CREADO???

        // 5. Verificamos que nos aparece el mensaje "This is a required field" debajo del campo que hemos dejado vacío.
        // 6. Rellenamos el campo con la contraseña y volvemos a enviar los datos del formulario.
        // 7. Verificamos que estamos en la página correcta usando su título ("My Account").
    }

    @Test
    public void S3_scenario_loginFailed_should_fail_when_user_account_not_exists(){

    }
}
