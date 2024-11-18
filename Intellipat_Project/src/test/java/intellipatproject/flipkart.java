package intellipatproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class flipkart {

	public static void main(String[] args) throws IOException, InterruptedException  {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		WebElement electronics = driver.findElement(By.xpath("//span[text()='Electronics']"));
		
		Actions action = new Actions(driver);
		action.clickAndHold(electronics).build().perform();
		Thread.sleep(2000);
		
		driver.findElement(By.partialLinkText("Mob")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys("mobile phone");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElements(By.xpath("//div[@class='yKfJKb row']"));
	Thread.sleep(2000);
	
		int mobile_count = driver.findElements(By.xpath("//div[@class='yKfJKb row']")).size();
		System.out.println("Mobile_count=" +mobile_count);
	
		XSSFWorkbook workbook = new XSSFWorkbook();
		 XSSFSheet sheet = workbook.createSheet("Phones");
		 
		 int rowNum = 0;
		 
		 Row row = null;
		 
		 List<WebElement> name;
		 List<WebElement> price;
		 
     	 row = sheet.createRow(rowNum++);
		 row.createCell(0).setCellValue("NAME");
		 row.createCell(1).setCellValue("PRICE");   
		 
		 for(int i=0; i< mobile_count; i++)
		 {
		  name = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
		price = driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']")); 

         String Mobile_Name = name.get(i).getText();
		 String Mobile_Price= price.get(i).getText();
		
		 
		  
		 row = sheet.createRow(rowNum++);
		 row.createCell(0).setCellValue(Mobile_Name);
		 row.createCell(1).setCellValue(Mobile_Price);
		 
		 System.out.println(Mobile_Name+ "     " +Mobile_Price);
		 }
		 Thread.sleep(5000);
		 FileOutputStream os = new FileOutputStream("Phones.xlsx");
		 workbook.write(os);
	

	}

}
