import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class WaresPageTests {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp(){
    }

    @BeforeEach
    public void setUpBeforeEach(){
        webDriver = new ChromeDriver();
        openThePageAndMaximize();
        webDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void addingAVeggie(){
        openTheWaresPage();
        WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button"));
        addBtn.click();
        WebElement nameField = webDriver.findElement(By.id("name"));
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String veg = "свекла";
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

    @Test
    public void addingAFruit(){
        openTheWaresPage();
        WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button"));
        addBtn.click();
        WebElement nameField = webDriver.findElement(By.id("name"));
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String fruit = "груша";
        nameField.sendKeys(fruit);
        Select typeSelect = new Select(webDriver.findElement(By.id("type")));
        typeSelect.selectByValue("FRUIT");
        WebElement saveBtn = webDriver.findElement(By.id("save"));
        saveBtn.click();
        WebElement addedVeg = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[1]"));
        Assertions.assertEquals(fruit, addedVeg.getText());
    }
    @Test
    public void addingAnExoticFruit(){
        openTheWaresPage();
        WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button"));
        addBtn.click();
        WebElement nameField = webDriver.findElement(By.id("name"));
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String fruit = "ананас";
        nameField.sendKeys(fruit);
        Select typeSelect = new Select(webDriver.findElement(By.id("type")));
        typeSelect.selectByValue("FRUIT");
        WebElement exoticChechBox = webDriver.findElement(By.id("exotic"));
        exoticChechBox.click();
        WebElement saveBtn = webDriver.findElement(By.id("save"));
        saveBtn.click();
        WebElement addedVeg = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[1]"));
        WebElement exoCheckBox = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[3]"));
        Assertions.assertAll(
                () -> Assertions.assertEquals(fruit, addedVeg.getText()),
                () -> Assertions.assertEquals("true", exoCheckBox.getText()));
    }

    @Test
    public void listIsEmptyAfterCleaning(){
        openTheWaresPage();
        WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button"));
        addBtn.click();
        WebElement nameField = webDriver.findElement(By.id("name"));
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String anyItem = "any veggie";
        nameField.sendKeys(anyItem);
        WebElement saveBtn = webDriver.findElement(By.id("save"));
        saveBtn.click();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement dropDownBar = webDriver.findElement(By.id("navbarDropdown"));
        dropDownBar.click();
        WebElement cleanBtn = webDriver.findElement(By.id("reset"));
        cleanBtn.click();
        Assertions.assertThrows(NoSuchElementException.class, () -> webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[1]")));
    }


    @AfterEach
    public void afterEach(){
        webDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        webDriver.close();
    }

    @AfterAll
    public static void afterAll(){
    }
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
