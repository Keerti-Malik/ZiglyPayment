import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagentoWriteReview {

	WebDriver driver;

	@BeforeTest()
	public void login() throws Exception {

		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(
				"https://preprod.zigly.com/admin/admin/index/index/key/8eb97891d87c711a051991a9fc5183a2d064798ae927b9539364986540b1121b/");
		driver.manage().window().maximize();
	}

	@Test
	public void writereview() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.id("username")).sendKeys("pooja.sharma");
		driver.findElement(By.id("login")).sendKeys("Cosmo@123");
		driver.findElement(By.xpath("//div[@class='actions']/button")).click();
		Thread.sleep(4000);
		js.executeScript("window.scrollTo(0,300)");
		// click on marketing
		List<WebElement> sidebarlabel = driver.findElements(By.xpath("//nav[@class='admin__menu']/ul/li/a"));
		for (int j = 0; j < sidebarlabel.size(); j++) {
			if (sidebarlabel.get(j).getText().contains("MARKETING")) {
				sidebarlabel.get(j).click();
				break;
			}
		}

		Thread.sleep(2000);
		// click on All Review
		driver.findElement(By.xpath("//li[@class='item-catalog-reviews-ratings-reviews-all    level-2']/a")).click();
		Thread.sleep(1000);

		for (int i =0; i <= 0; i++) {
			// click on new review
			driver.navigate().refresh();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='page-actions-buttons']/button"))).click();
			Thread.sleep(1000);
			// click on next page
			if (i ==0) {				
				WebElement digit=driver.findElement(By.id("productGrid_page-current"));
				digit.clear();
						digit.sendKeys("8");
						digit.sendKeys(Keys.ENTER);
						Thread.sleep(2000);
			}
			Thread.sleep(2000);
			// driver.navigate().refresh();
			// select per page limit
			Select list = new Select(driver.findElement(By.id("productGrid_page-limit")));
			list.selectByValue("50");
			driver.navigate().refresh();
			Thread.sleep(2000);
			// click on ID
			try {
				List<WebElement> review = driver.findElements(By.xpath(
						"//table[@id='productGrid_table']/tbody/tr/td[@class=' col-id col-entity_id                                        ']"));
				Thread.sleep(2000);

				review.get(i).click();
			} catch (StaleElementReferenceException ex) {
				System.out.println(ex.toString());
			}

			Thread.sleep(2000);
			  Select visibility = new Select(driver.findElement(By.id("select_stores")));
			 visibility.selectByValue("1"); 	
			Thread.sleep(3000);
			js.executeScript("window.scrollTo(70,0)");
			Thread.sleep(2000);
			// click on feedback star
			driver.findElement(By.xpath("//div[@class='admin__field-control']/label[2]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nickname"))).sendKeys("Dahiya");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title"))).sendKeys("Good");
			driver.findElement(By.xpath("//textarea[@id='detail']"))
					.sendKeys("Good");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='page-actions-buttons']/button[@id='save_button']"))).click();
		}
	}
}
