import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WaresPageTests {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp(){
        webDriver = new ChromeDriver();
    }

    @BeforeEach
    public void setUpBeforeEach(){
        openThePageAndMaximize();
    }

    @Test
    public void addingAVeggie(){
        openTheWaresPage();
        WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button"));
        addBtn.click();
        WebElement nameField = webDriver.findElement(By.id("name"));
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String veg = "капуста";
        nameField.sendKeys(veg);
        WebElement saveBtn = webDriver.findElement(By.id("save"));
        saveBtn.click();
        WebElement addedVeg = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[1]"));
        Assertions.assertEquals(veg, addedVeg.getText());
    }
    @Test
    public void addingAnExoticVeggie(){
        openTheWaresPage();
        WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button"));
        addBtn.click();
        WebElement nameField = webDriver.findElement(By.id("name"));
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String veg = "артишок";
        nameField.sendKeys(veg);
        WebElement exoticChechBox = webDriver.findElement(By.id("exotic"));
        exoticChechBox.click();
        WebElement saveBtn = webDriver.findElement(By.id("save"));
        saveBtn.click();
        WebElement addedVeg = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[1]"));
        WebElement exoCheckBox = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[3]"));
        Assertions.assertAll(
                () -> Assertions.assertEquals(veg, addedVeg.getText()),
                () -> Assertions.assertEquals("true", exoCheckBox.getText()));
    }




/*    @AfterAll
    public static void afterAll(){
        webDriver.quit();
    }*/
    public static void openThePageAndMaximize(){
        webDriver.get("http://localhost:8080");
        webDriver.manage().window().maximize();
    }

    public static void openTheWaresPage(){
        WebElement dropDownBar = webDriver.findElement(By.id("navbarDropdown"));
        dropDownBar.click();
        WebElement waresButton = webDriver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/div/a[1]"));
        waresButton.click();
    }
}
