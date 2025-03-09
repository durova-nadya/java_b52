import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
        if (!isElementPresent(By.name("selected[]"))) {
            driver.findElement(By.name("new")).click();
            driver.findElement(By.name("group_name")).click();
            driver.findElement(By.name("group_name")).sendKeys("Friends");
            driver.findElement(By.name("group_header")).click();
            driver.findElement(By.name("group_header")).sendKeys("Best friends");
            driver.findElement(By.name("group_footer")).click();
            driver.findElement(By.name("group_footer")).sendKeys("comment");
            driver.findElement(By.name("submit")).click();
            driver.findElement(By.linkText("group page")).click();
        }
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }
}
