package ejercicio2.conPO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin2 {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    @Test
    public void S4_scenario_PO_loginOK_should_login_with_success_when_user_account_exists(){

    }

    public void S5_scenario_PO_loginFailed_should_fail_when_user_account_not_exists(){
        
    }
}
