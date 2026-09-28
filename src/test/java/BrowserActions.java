import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class BrowserActions {
    WebDriver driver;

    @Test
    public void testCase() {

        driver = new EdgeDriver();
    }
}
