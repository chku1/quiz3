import com.codeborne.selenide.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {

    @BeforeClass
    public void setUp() {
        configureSelenide();
    }

    private void configureSelenide() {
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        baseUrl = "http://the-internet.herokuapp.com";
        reportsFolder = "src/main/resources/Reports";
        downloadsFolder = "src/main/resources/images";
    }

    @Test
    public void dropdownTest() {
        open("/dropdown");

        $("#dropdown").selectOptionContainingText("Option 2");

        $("#dropdown").getSelectedOption().shouldHave(text("Option 2"));
    }

    @Test
    public void inputTest() {
        open("/inputs");

        $("[type='number']").setValue("100");

        $("[type='number']").shouldNotBe(empty);
    }

    @Test
    public void inputTestWithScreenshot() {
        open("/inputs");

        try {
            $("[type='number']").setValue("100");
            $("[type='number']").shouldNotBe(empty);
        } catch (Exception e) {
            captureScreenshotAndRethrow(e, "failed_test");
        }
    }

    private void captureScreenshotAndRethrow(Exception e, String screenshotName) {
        screenshot(screenshotName);
        throw e;
    }
}