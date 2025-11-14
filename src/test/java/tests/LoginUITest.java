package tests;

import config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DashboardPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginUITest {

    private static WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @BeforeEach
    void setupPages() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        loginPage.open();
    }

    @AfterEach
    void cleanup() {
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    void TC1_loginValido_USER_redirecionaParaDashboard() {
        long start = System.currentTimeMillis();
        loginPage.login(TestConfig.getUserEmail(), TestConfig.getUserPassword());

        assertTrue(dashboardPage.isLoaded(), "Dashboard não carregou");
        assertTrue(dashboardPage.getWelcomeText().contains("Bem-vindo"), "Mensagem de boas-vindas incorreta");
        long loadTime = System.currentTimeMillis() - start;
        assertTrue(loadTime <= 5000, "Tempo de carregamento: " + loadTime + "ms > 5s");
    }

    @Test
    @Order(2)
    void TC2_loginVisitor_semPermissao_exibeMensagemAcessoNegado() {
        loginPage.login(TestConfig.getVisitorEmail(), TestConfig.getUserPassword());

        assertTrue(loginPage.isErrorMessageDisplayed(), "Mensagem de erro não exibida");
        String msg = loginPage.getErrorMessage().toLowerCase();
        assertTrue(msg.contains("acesso") && msg.contains("negado") || msg.contains("não autorizado"),
                "Mensagem inesperada: " + msg);
        assertFalse(driver.getCurrentUrl().contains("/dashboard"), "Redirecionamento indevido");
    }

    @Test
    @Order(3)
    void TC3_bloqueioAposTresTentativasInvalidas() {
        String email = TestConfig.getUserEmail();

        for (int i = 1; i <= 3; i++) {
            loginPage.open();
            loginPage.login(email, TestConfig.getInvalidPassword() + i);
            assertTrue(loginPage.isErrorMessageDisplayed(), "Erro não exibido na tentativa " + i);
        }

        loginPage.open();
        loginPage.login(email, TestConfig.getUserPassword());

        assertTrue(loginPage.isErrorMessageDisplayed(), "Erro não exibido após bloqueio");
        String msg = loginPage.getErrorMessage().toLowerCase();
        assertTrue(msg.contains("bloqueado") || msg.contains("tente novamente mais tarde"),
                "Mensagem de bloqueio ausente: " + msg);
    }
}