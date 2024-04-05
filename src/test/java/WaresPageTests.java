import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WaresPageTests {

    private static WebDriver webDriver;

/*    @BeforeAll
    public static void setUp(){
        webDriver = new ChromeDriver();
        openThePageAndMaximize();
    }*/

    @BeforeEach
    public void setUpp(){
        webDriver = new ChromeDriver();
        openThePageAndMaximize();
    }

    @Test
    public void addingExoticVeggie(){
        openTheWaresPage();
        WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button"));
        addBtn.click();
        WebElement nameField = webDriver.findElement(By.id("name"));
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String veg = "артишок";
        nameField.sendKeys(veg);
        WebElement saveBtn = webDriver.findElement(By.id("save"));
        saveBtn.click();
        WebElement addedVeg = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[1]"));
        WebElement exoCheckBox = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[5]/td[3]"));
        Assertions.assertAll(
                () -> Assertions.assertEquals(veg, addedVeg.getText()),
                () -> Assertions.assertEquals("false", exoCheckBox.getText()));
    }

    @Test
    public void addingAVeggie(){
        openTheWaresPage();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement addBtn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button"));
        addBtn.click();
/*        WebElement modalWindowHeader = webDriver.findElement(By.id("editModalLabel"));
        if(!modalWindowHeader.isDisplayed()){
            Assertions.fail("Option window wasn't opened");
        }*/
        WebElement nameField = webDriver.findElement(By.id("name"));
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String veg = "капуста";
        nameField.sendKeys(veg);
/*        WebElement typeField = webDriver.findElement(By.id("type"));
        System.out.println(typeField.getText());*/
        WebElement saveBtn = webDriver.findElement(By.id("save"));
        saveBtn.click();
        WebElement addedVeg = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr[6]/td[1]"));
        Assertions.assertEquals(veg, addedVeg.getText());
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
