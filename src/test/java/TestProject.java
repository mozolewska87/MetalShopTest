import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
//update
public class TestProject {
    static WebDriver driver;

    @BeforeEach
    void PrepareBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://34.171.101.114/");
    }

    @AfterEach
    void CloseBrowser() {
        driver.quit();
    }

    @Test
    void CheckVerifyLoginWithoutLogin() {

        WebElement AccountPageLog = driver.findElement(By.id("menu-item-125"));
        AccountPageLog.click();

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("test123");

        WebElement loginButton = driver.findElement(By.cssSelector("button[name='login']"));
        loginButton.click();

        WebElement errorMessageFromUser = driver.findElement(By.className("woocommerce-error"));
        Assertions.assertTrue(errorMessageFromUser.isDisplayed());

    }

    @Test
    void CheckVerifyLoginWithoutPassword() {

        WebElement accountLoginPage = driver.findElement(By.id("menu-item-125"));
        accountLoginPage.click();

        WebElement loginInput = driver.findElement(By.id("username"));
        loginInput.sendKeys("testowy321");

        WebElement loginButton = driver.findElement(By.cssSelector("button[name='login']"));
        loginButton.click();

        List<WebElement> elements = driver.findElements(By.className("woocommerce-error"));
        Assertions.assertEquals(1, elements.size());
    }

    @Test
    void checkVerifyRegisterNewUser() {

        WebElement accountLogPage = driver.findElement(By.linkText("register"));
        accountLogPage.click();

        Faker faker = new Faker(new Locale("pl-PL"));
        String userName = faker.name().firstName() + Math.random();
        String userPassword = faker.name().firstName() + Math.random();
        String userEmail = faker.name().lastName() + Math.random() + "@o13.pl";

        WebElement userNameInput = driver.findElement(By.name("user_login"));
        WebElement userPasswordInput = driver.findElement(By.name("user_pass"));
        WebElement userPasswordConfirmInput = driver.findElement(By.name("user_confirm_password"));
        WebElement userEmailInput = driver.findElement(By.name("user_email"));

        userNameInput.sendKeys(userName);
        userEmailInput.sendKeys(userEmail);
        userPasswordInput.sendKeys(userPassword);

        userPasswordConfirmInput.sendKeys(userPassword);
        userPasswordInput.sendKeys("1");
        userPasswordConfirmInput.sendKeys("1");
        userPasswordInput.sendKeys(Keys.ENTER);

        WebElement submitButton= driver.findElement(By.cssSelector("div.ur-button-container>button"));
        submitButton.click();

        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ur-submit-message-node")));

        WebElement confirmregister = driver.findElement(By.id("ur-submit-message-node"));
        Assertions.assertTrue(confirmregister.getText().contains("User successfully registered"));

    }

    @Test
    void CheckVerifyLogoAndSearcherPresenceOnHomeWeb() {

        Assertions.assertTrue(driver.findElement(By.linkText("Metal Shop")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.id("woocommerce-product-search-field-0")).isDisplayed());

        WebElement accountPageLog = driver.findElement(By.id("menu-item-125"));
        accountPageLog.click();

        Assertions.assertTrue(driver.findElement(By.linkText("Metal Shop")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.id("woocommerce-product-search-field-0")).isDisplayed());

    }

    @Test
    void CheckVerifyGoToTheContactTab() {

        WebElement contactButton = driver.findElement(By.linkText("Kontakt"));
        contactButton.click();

        WebElement visibleMessageArea = driver.findElement(By.name("your-message"));
        Assertions.assertTrue(visibleMessageArea.isDisplayed());

    }

    @Test
    void CheckVerifyBackToDashboardFromLoginPage() {

        WebElement AccountLoginPage = driver.findElement(By.id("menu-item-125"));
        AccountLoginPage.click();

        Assertions.assertTrue(driver.findElement(By.cssSelector("button[name='login']")).isDisplayed());

        WebElement homeButton = driver.findElement(By.linkText("Strona główna"));
        homeButton.click();

        WebElement returnHomeWeb = driver.findElement(By.className("orderby"));
        Assertions.assertTrue(returnHomeWeb.isDisplayed());
    }

    @Test
    void CheckVerifySendMessageThroughTheForm() {

        WebElement contactButton = driver.findElement(By.linkText("Kontakt"));
        contactButton.click();

        Assertions.assertTrue(driver.findElement(By.name("your-message")).isDisplayed());

        WebElement nameInput = driver.findElement
                (By.name("your-name"));
        nameInput.sendKeys("Joanna Mozolewska");

        WebElement mailInput = driver.findElement
                (By.name("your-email"));
        mailInput.sendKeys("testmoz@gmail.com");

        WebElement subjectInput = driver.findElement
                (By.name("your-subject"));
        subjectInput.sendKeys("test temat");

        WebElement areaText = driver.findElement
                (By.name("your-message"));
        areaText.sendKeys("test wiadomość do sklepu");

        WebElement confirmButton = driver.findElement
                (By.cssSelector("input[type='submit']"));
        confirmButton.click();

        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("wpcf7-response-output")));

        WebElement confirmMessage = driver.findElement(By.cssSelector(".wpcf7-response-output"));
        String messageFromUsers = "Twoja wiadomość została wysłana. Dziękujemy!";
        Assertions.assertEquals(messageFromUsers, confirmMessage.getText());

    }

    @Test
    void CheckVerifyAddProductToCard() {

        WebElement cardButton = driver.findElement(By.linkText("Koszyk"));
        cardButton.click();

        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(18));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("return-to-shop")));
        Assertions.assertTrue(driver.findElement(By.className("return-to-shop")).isDisplayed());

        WebElement menuButton = driver.findElement(By.id("menu-item-124"));
        menuButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-product_id='24']")));
        WebElement confirmButton = driver.findElement(By.cssSelector("a[data-product_id='24']"));
        confirmButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-header-cart")));
        WebElement cardCheck = driver.findElement(By.id("site-header-cart"));
        cardCheck.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("coupon")));
        WebElement couponVisible= driver.findElement(By.className("coupon"));
        Assertions.assertTrue(driver.findElement(By.className("coupon")).isDisplayed());

    }

    @Test
    void CheckVerifyAddAndRemovedProductToCard() {

        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement confirmButton = driver.findElement(By.cssSelector("a[data-product_id='24']"));
        confirmButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-product_id='24']")));

        WebElement cardCheck = driver.findElement(By.id("site-header-cart"));
        cardCheck.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("coupon")));
        Assertions.assertTrue(driver.findElement(By.className("coupon")).isDisplayed());

        WebElement removeProductFromCard = driver.findElement(By.className("remove"));
        removeProductFromCard.click();

       wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("return-to-shop")));
        WebElement returnToShop = driver.findElement(By.className("return-to-shop"));
        Assertions.assertTrue(returnToShop.isDisplayed());

    }


}



