package com.platinum;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;
public class LoginStepDefinitions {
    private WebDriver driver;

    @Given("Estoy en la página de inicio de sesión")
    public void navigateToLoginPage() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/57322/Desktop/agua/static/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8086/login");
    }

    @When("Ingreso el nombre de usuario {string} y la contraseña {string}")
    public void enterUsernameAndPassword(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Then("Debería ver el panel de control del usuario")
    public void verifyUserDashboard() {
        String dashboardText = driver.findElement(By.id("dashboard-text")).getText();
        assertEquals("Welcome to the user dashboard", dashboardText);
    }

    @Then("Debería ver un mensaje de error de inicio de sesión")
    public void verifyLoginErrorMessage() {
        String errorMessage = driver.findElement(By.id("login-error-message")).getText();
        assertEquals("Invalid username or password", errorMessage);
    }

    @Then("Debería ver un mensaje de error de campos vacíos")
    public void verifyEmptyFieldsErrorMessage() {
        String errorMessage = driver.findElement(By.id("empty-fields-error-message")).getText();
        assertEquals("Please enter both username and password", errorMessage);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
