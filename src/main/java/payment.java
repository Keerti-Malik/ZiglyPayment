
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class payment {

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
		// click on sales
		Thread.sleep(4000);
		js.executeScript("window.scrollTo(0,300)");
		// click on marketing
		List<WebElement> sidebarlabel = driver.findElements(By.xpath("//nav[@class='admin__menu']/ul/li/a"));
		for (int j = 0; j < sidebarlabel.size(); j++) {
			if (sidebarlabel.get(j).getText().contains("SALES")) {
				sidebarlabel.get(j).click();
				break;
			}
		}			
		Thread.sleep(2000);
		// click on Order
		driver.findElement(By.xpath("//li[@class='item-sales-order    level-2']")).click();
		Thread.sleep(2000);
		// Input file location
		FileInputStream fs = new FileInputStream("E://test.xlsx");
		// Creating a workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalRow = sheet.getLastRowNum();
		int colcount = sheet.getRow(totalRow).getLastCellNum();
		System.out.println(totalRow);
		System.out.println(colcount);
		for (int i = 0; i <= totalRow; i++) {
			// search order id
			WebElement search = driver
					.findElement(By.xpath("/html/body/div[2]/main/div[3]/div/div/div/div[2]/div[1]/div[2]/input"));
			search.clear();
			String cellOrderId = "00000" + sheet.getRow(i).getCell(0).getRawValue();
			System.out.println(cellOrderId);
			search.sendKeys(cellOrderId);
			Thread.sleep(3000);
			search.sendKeys(Keys.ENTER);
			Thread.sleep(4000);
			List<WebElement> orderid = driver
					.findElements(By.xpath("//*[@id=\"container\"]/div/div[4]/table/tbody/tr/td[2]"));
			System.out.println(orderid.get(0).getText());
			if (orderid.get(0).getText().equals(cellOrderId)) {
				
				driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[4]/table/tbody/tr/td[2]")).click();
				Thread.sleep(2000);
				List<WebElement> we = driver.findElements(By.xpath("//span[@id='order_status']"));
				System.out.println(we.get(0).getText());
				// orderid

				if (we.get(0).getText().equalsIgnoreCase("Inprogress")) {
					// click on ship
					driver.findElement(By.xpath("//button[@id='order_ship']")).click();
					Thread.sleep(4000);
					// click on submit button
					driver.findElement(By.xpath("//div[@class='order-history-comments-actions actions']/button"))
							.click();
					
				}
			// click on back
			Thread.sleep(3000);
			//driver.navigate().back();
			driver.findElement(By.xpath("//div[@class='page-actions-buttons']/button[1]")).click();
			Thread.sleep(5000);

			}
		}

	}
}
