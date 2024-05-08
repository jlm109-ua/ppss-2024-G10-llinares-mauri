package ejercicio2.conPO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin2 {
    WebDriver driver;
    HomePage homePage;
    CustomerLoginPage customerLoginPage;
    MyAccountPage myAccountPage;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");
        homePage = new HomePage(driver);
        customerLoginPage = new CustomerLoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @Test
    public void S4_scenario_PO_loginOK_should_login_with_success_when_user_account_exists(){
        // 1. Verificamos que el título de la página de inicio es el correcto ("Madison Island").
        Assertions.assertEquals("Madison Island",homePage.getTitle());

        // 2. Seleccionamos Account, y a continuación seleccionamos el hiperenlace Login.
        homePage.logIn();

        // 3. Verificamos que el titulo de la página es el correcto ("Customer Login").
        Assertions.assertEquals("Customer Login",customerLoginPage.getTitle());

        // 4. Rellenamos el campo email con el email de la cuenta que hemos creado en el driver createAccount() y la contraseña (Paso 6.) y enviamos el formulario.
        String email = "juanjuanitojuan@gmail.com";
        String password = "juanito1234";

        customerLoginPage.logIn(email,password);

        // 7. Verificamos que estamos en la página correcta usando su título ("My Account").
        Assertions.assertEquals("My Account",myAccountPage.getTitle());

    }

    public void S5_scenario_PO_loginFailed_should_fail_when_user_account_not_exists(){
        // 1. Verificamos que el título de la página de inicio es el correcto ("Madison Island")
        Assertions.assertEquals("Madison Island",homePage.getTitle());

        // 2. Seleccionamos Account, y a continuación seleccionamos el hiperenlace Login.
        homePage.logIn();

        // 3. Verificamos que el título de la página es el correcto ("Customer Login")
        Assertions.assertEquals("Customer Login",customerLoginPage.getTitle());

        // 4. Rellenamos los campos con el email de la cuenta que hemos creado en el driver createAccount() y con un password incorrecto. Enviamos el formulario.
        String email = "juanjuanitojuan@gmail.com";
        String password = "estaConstraseñaNoEs";
        customerLoginPage.logIn(email,password);

        // 5. Verificamos que nos aparece el mensaje "Invalid login or password".
        Assertions.assertEquals("Invalid login or password.",customerLoginPage.getInvalidPasswordError());
    }
}
