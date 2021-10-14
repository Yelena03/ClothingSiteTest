import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AsosTest {

    private WebDriver driver;
    String url = "https://www.asos.com/us/";
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }
        @AfterMethod
        public void setDown(){
        driver.quit();
    }

    @Test
    public void urlVerify() throws InterruptedException {

        String expectedResult = "https://www.asos.com/us/";

        Thread.sleep(3000);

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void searchInput(){
        WebElement searchBar = driver.findElement(By.xpath("//input[@type='search']"));

        searchBar.sendKeys("skirt\n");

        WebElement confirmText = driver.findElement(By.xpath("//p[contains(text(),'skirt')]"));
        String confirmTextText = confirmText.getText();
        Assert.assertEquals(confirmTextText, "\"Skirt\"");
    }
}
