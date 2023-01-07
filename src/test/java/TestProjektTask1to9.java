import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestProjektTask1to9 {
    static WebDriver driver;

    @BeforeAll
    static void PrepareBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
       driver.get("http://34.171.101.114/");
    }

    @AfterAll
    static void CloseBrowser() {
       // driver.quit();
    }

    @Test
    void task1LoginWithoutLogin() {

        // Przejście do zakładki logowania
        WebElement AccountPageLog = driver.findElement(By.id("menu-item-125"));
        AccountPageLog.click();

        // Wpisanie hasła
        WebElement passwordplace = driver.findElement(By.id("password"));
        passwordplace.sendKeys("test123");

        // Kliknięcie przycisku Logowania
        WebElement loginButton = driver.findElement(By.cssSelector("button[name='login']"));
        loginButton.click();

        // Asercja - logowania bez loginu
        Assertions.assertTrue(driver.findElement(By.className("woocommerce-error")).isDisplayed());

    }

    @Test
    void task2LoginWithoutPassword() {
        // Przejście do zakładki logowania
        WebElement accountPageLog = driver.findElement(By.id("menu-item-125"));
        accountPageLog.click();

        // Wpisanie loginu
        WebElement loginInput = driver.findElement(By.id("username"));
        loginInput.sendKeys("testowy321");


        // Kliknięcie przycisku logowania
        WebElement loginButton = driver.findElement(By.cssSelector("button[name='login']"));
        loginButton.click();


        // Aserja - logowania bez hasła
        List<WebElement> elements = driver.findElements(By.className("woocommerce-error"));
        Assertions.assertEquals(1, elements.size());
    }

    @Test
    void task3RegisterNewUser() {
        // Przejście do zakładki rejestracja
        WebElement accountPageLog = driver.findElement(By.linkText("register"));
        accountPageLog.click();

        //Faker faker = new Faker(new Locale("pl-PL"));
        // String userName = faker.name().firstName()+Math.random();
        // String userPassword = faker.name().firstName()+Math.random();
        // String userEmail = faker.name().lastName()+Math.random()+"@o13.pl";
        // WebElement userNameInput = driver.findElement(By.name("user_login"));
        // WebElement userPasswordInput = driver.findElement(By.name("user_pass"));
        // WebElement userPasswordConfirmInput = driver.findElement(By.name("user_confirm_password"));
        // WebElement userEmailInput = driver.findElement(By.name("user_email"));

        // Wpisanie danych- username
        WebElement username = driver.findElement(By.id("user_login"));
        username.sendKeys("joannamoz111895");

        // Wpisanie danych- user email
        WebElement usermail = driver.findElement(By.id("user_email"));
        usermail.sendKeys("test882755570@gmail.com");

        // Wpisanie danych- password
        WebElement password = driver.findElement(By.id("user_pass"));
        password.sendKeys("Testowe12");

        // Wpisanie ponownie hasła w celu jego potwierdzenia
        WebElement passwordconfirm = driver.findElement(By.id("user_confirm_password"));
        passwordconfirm.sendKeys("Testowe12");

        // Kilnięcie przycisku potwierdzającego rejestracje
        WebElement submitButton = driver.findElement(By.cssSelector("button[class='btn button ur-submit-button ']"));
        submitButton.click();

        Wait wait2 =new WebDriverWait(driver, Duration.ofSeconds(7));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("ur-submit-message-node")));

        //Asercja rejestracji
        WebElement confirmregister = driver.findElement(By.id("ur-submit-message-node"));
        Assertions.assertTrue(confirmregister.getText().contains("User successfully registered"));

    }

    @Test
    void task4LogoAndSearcherPresence(){
        // Asercja sprawdzająca widoczność logo i wyszukiwarki na stronie głównej
        Assertions.assertTrue(driver.findElement(By.linkText("Metal Shop")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.id("woocommerce-product-search-field-0")).isDisplayed());

        // Przejście do zakładki logowania
        WebElement accountPageLog = driver.findElement(By.id("menu-item-125"));
        accountPageLog.click();

        // Asercja sprawdzająca widoczność logo i wyszukiwarki na stronie logowania
        Assertions.assertTrue(driver.findElement(By.linkText("Metal Shop")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.id("woocommerce-product-search-field-0")).isDisplayed());

    }

    @Test
    void task5GoToTheContactTab(){
        //Klinięcie na stronie głównej zakładki "kontak"
        WebElement contactbutton = driver.findElement(By.linkText("Kontakt"));
        contactbutton.click();

        //Asercja czy znajduje się na stronie "kontakt"
        Assertions.assertTrue(driver.findElement(By.name("your-message")).isDisplayed());

    }

    @Test
    void task6BacktoDashboard(){
        // Przejście do zakładki logowania
        WebElement AccountPageLog = driver.findElement(By.id("menu-item-125"));
        AccountPageLog.click();

        // Asercja czy znajduje się na stronie logowania
        Assertions.assertTrue(driver.findElement(By.cssSelector("button[name='login']")).isDisplayed());

        // Powrót ze strony logowania na strone główną
        WebElement homebutton = driver.findElement(By.linkText("Home"));
       homebutton.click();

       // Asercja- czy nastąpił powrót  na strone główną
        Assertions.assertTrue(driver.findElement(By.className("orderby")).isDisplayed());
    }

    @Test
    void task7SendMessageThroughTheForm()  {
        // Klinięcie na stronie głównej zakładki "kontak"
        WebElement contactbutton = driver.findElement(By.linkText("Kontakt"));
        contactbutton.click();

        // Asercja czy znajduje się na stronie "kontakt"
        Assertions.assertTrue(driver.findElement(By.name("your-message")).isDisplayed());

        // Uzupełnienie danych- wpisanie imienia i nazwiska
        WebElement nameInput = driver.findElement
                (By.name("your-name"));
        nameInput.sendKeys("Joanna Mozolewska");

        // Uzupełnienie danych- wpisanie adresu email
        WebElement mailInput = driver.findElement
                (By.name("your-email"));
        mailInput.sendKeys("testmoz@gmail.com");

        // Uzupełnienie danych- wpisanie tematu wiadomości
        WebElement subjectInput = driver.findElement
                (By.name("your-subject"));
        subjectInput.sendKeys("test temat");

        // Uzupełnienie danych- wpisanie wiadomości
        WebElement areatext = driver.findElement
                (By.name("your-message"));
        areatext.sendKeys("test wiadomość do sklepu");


        // Klinięcie przycisku "wyślij"
        WebElement confirmButton = driver.findElement
                (By.cssSelector("input[class='wpcf7-form-control has-spinner wpcf7-submit']"));
        confirmButton.click();

       Wait wait1 =new WebDriverWait(driver, Duration.ofSeconds(7));
       wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("wpcf7-response-output")));

        // AssertJ potwierdzenie wysłania wiadomości
        WebElement confirmmessage = driver.findElement(By.cssSelector(".wpcf7-response-output"));
        String mesaggefromusers= "Twoja wiadomość została wysłana. Dziękujemy!";
        Assertions.assertEquals(mesaggefromusers, confirmmessage.getText());

    }

    @Test
    void task8AddPoductToCard()  {
        // Klinięcie na stronie głównej zakładki "koszyk"
        WebElement cardbutton = driver.findElement(By.linkText("Koszyk"));
        cardbutton.click();

        Wait wait2 =new WebDriverWait(driver, Duration.ofSeconds(15));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("return-to-shop")));

        // Sprawdzenie czy koszyk jest pusty
        Assertions.assertTrue(driver.findElement(By.className("return-to-shop")).isDisplayed());

        // Przejscie do strony sklepu
        WebElement dashbutton = driver.findElement(By.id("menu-item-124"));
        dashbutton.click();

        // Klinięcie w wybrany towar
        WebElement confirmbutton = driver.findElement(By.linkText("Add to cart"));
        confirmbutton.click();

        // Przejście do zakładki "koszyk"
        WebElement cardcheck = driver.findElement(By.id("site-header-cart"));
        cardcheck.click();

        //  Asercja - sprawdzenie czy wybrany towar znajduje się w koszyku
        Assertions.assertTrue(driver.findElement(By.className("wc-proceed-to-checkout")).isDisplayed());
    }

        @Test
    void task9AddAndRemovedProductToCard(){
            // Klinięcie w wybrany towar
            WebElement confirmbutton2 = driver.findElement(By.linkText("Add to cart"));
            confirmbutton2.click();

            // Przejście do zakładki "koszyk"
            WebElement cardcheck2 = driver.findElement(By.id("site-header-cart"));
            cardcheck2.click();

              Wait wait2 =new WebDriverWait(driver, Duration.ofSeconds(15));
              wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("coupon")));

            //  Asercja - sprawdzenie czy wybrany towar znajduje się w koszyku
            Assertions.assertTrue(driver.findElement(By.className("coupon")).isDisplayed());

            // Usunięcie towaru z koszyka
            WebElement remove = driver.findElement(By.className("remove"));
            remove.click();

            Wait wait3 =new WebDriverWait(driver, Duration.ofSeconds(15));
            wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("return-to-shop")));


           // Asercja - sprawdzająca czy koszyk jest pusty
            Assertions.assertTrue(driver.findElement(By.className("return-to-shop")).isDisplayed());

    }


    }



