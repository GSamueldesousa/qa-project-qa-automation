package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "welcome-message")
    private WebElement welcomeMessage;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("/dashboard"))
               && wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();
    }

    public String getWelcomeText() {
        return welcomeMessage.getText();
    }
}