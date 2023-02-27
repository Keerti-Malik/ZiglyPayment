import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CustomerMapping {

	@Test
	public void readdata() throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = null;
		driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// login functionality
		driver.get("https://www.zigly.com/admin_1ep93n/admin");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("pooja.sharma");
		driver.findElement(By.id("login")).sendKeys("Cosmo@123");
		driver.findElement(By.xpath("//div[@class='actions']/button")).click();
		// click on Customer Mapping
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='menu-zigly-customermapping-parent']/a")).click();
		js.executeScript("window.scrollTo(300,0)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"menu-zigly-customermapping-parent\"]/div/ul/li/a/span")).click();
		Thread.sleep(2000);
		List<WebElement> mappingList = driver.findElements(By.xpath("//table[@id='postGrid_table']/tbody/tr/td[4]"));
		if (!mappingList.isEmpty()) {
			for (int i = 0; i <= mappingList.size(); i++) {
				mappingList = driver.findElements(By.xpath("//table[@id='postGrid_table']/tbody/tr/td[4]"));
				mappingList.get(i).click();
				driver.findElement(By.id("save")).click();
				Thread.sleep(2000);
			}
		} else {
			System.out.println("no order is present, so quiting the driver...");
			driver.quit();
		}

	}
}
