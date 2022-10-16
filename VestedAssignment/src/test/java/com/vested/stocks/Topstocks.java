package com.vested.stocks;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topstocks {

	WebDriver driver;
	String Browser="chrome";
@BeforeClass
public void setUp()
	{
	if(Browser.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		//Open Chrome Browser
		driver =new ChromeDriver();
	}else if(Browser.equalsIgnoreCase("mozilla")){
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		//Open Mozilla Browser
		 driver=new FirefoxDriver();	
	}
	else System.out.println("Please specify correct browser name");
//Open URL	
driver.get("https://app.vested.co.in/login");
//Browser maximize
driver.manage().window().maximize();
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//click on Login with Email
driver.findElement(By.xpath("(//div[contains(text(),'Login with Email')])[1]")).click();
// Enter email ID
driver.findElement(By.xpath("(//input[@name='email'])[1]")).click();
driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("admin@gmail.com");
//Enter password
driver.findElement(By.xpath("(//input[@name='password'])[1]")).click();
driver.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys("admin");
//Login button 
driver.findElement(By.xpath("(//button[@class='ant-btn'])[1]")).click();
//Clicking on Show All under top movers
driver.findElement(By.xpath("//p[contains(text(), 'Top Movers')]/following-sibling::p")).click();
}

//Test case to count total number of rows present in Top 20 movers webtable
@Test
public void totalrows() {
// putting total count of rows in webtable 	into list
List<WebElement> rows = driver.findElements(By.xpath("(//table)[1]/tbody/tr"));
int totalrows = rows.size();
if(totalrows==20)
{
	System.out.println("20 records are displayed in the table");
	Assert.assertTrue(true);
}
else{
	System.out.println("total records displayed in the table are" + totalrows);
	Assert.assertTrue(false);
}
}
//Test case to verify sorting of names column in ascending & descending order
@Test
public void nameSort() throws InterruptedException{
//putting names column data into list	
List<WebElement> names = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[2]"));
List<String> beforesort = new ArrayList<String>();
for(WebElement ele : names)
{
	String str = ele.getText();
	System.out.println(str);//printing names column data before sorting
	beforesort.add(str);
}
//click first time on names to sort column data into ascending order
driver.findElement(By.xpath("(//table)[1]/thead/tr/th[2]")).click();
//putting names column data into list after sorting in ascending order
List<WebElement> names2 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[2]"));
List<String> aftersort = new ArrayList<String>();
for(WebElement ele : names2)
{
	String str = ele.getText();
	System.out.println(str); //printing names column data after sorting in ascending
	aftersort.add(str);
}
//sorting using collections class
Collections.sort(beforesort); 
// comparing using assert class
Assert.assertEquals(beforesort, aftersort);
Thread.sleep(2000);
//click second time on names to sort column data into descending order
driver.findElement(By.xpath("(//table)[1]/thead/tr/th[2]")).click();
//putting names column data into list after sorting in descending order
List<WebElement> names3 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[2]"));
List<String> aftersort2 = new ArrayList<String>();
for(WebElement ele : names3)
{
	String str = ele.getText();
	System.out.println(str); //printing names column data after sorting in descending
	aftersort2.add(str);
}
//sorting using collections class in descending order
Collections.sort(beforesort, Collections.reverseOrder());
Assert.assertEquals(beforesort, aftersort2);
Thread.sleep(2000);
}
//Test case to verify sorting of Symbol column in ascending & descending order
@Test
public void symbolSort() throws InterruptedException{
List<WebElement> symbols = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[3]"));
List<String> beforesort = new ArrayList<String>();
for(WebElement ele : symbols)
{
	String str = ele.getText();
	System.out.println(str);
	beforesort.add(str);
}
driver.findElement(By.xpath("(//table)[1]/thead/tr/th[3]")).click();
List<WebElement> symbols2 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[3]"));
List<String> aftersort = new ArrayList<String>();
for(WebElement ele : symbols2)
{
	String str = ele.getText();
	System.out.println(str);
	aftersort.add(str);
}
Collections.sort(beforesort);
Assert.assertEquals(beforesort, aftersort);
Thread.sleep(2000);

driver.findElement(By.xpath("(//table)[1]/thead/tr/th[3]")).click();
List<WebElement> symbols3 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[3]"));
List<String> aftersort2 = new ArrayList<String>();
for(WebElement ele : symbols3)
{
	String str = ele.getText();
	System.out.println(str);
	aftersort2.add(str);
}
Collections.sort(beforesort, Collections.reverseOrder());
Assert.assertEquals(beforesort, aftersort2);
Thread.sleep(2000);
}
//Test case to verify sorting of Price column in ascending & descending order
@Test
public void priceSort() throws InterruptedException{
List<WebElement> prices = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[4]"));
List<Double> beforesort = new ArrayList<Double>();
for(WebElement ele : prices)
{
	//removing $ & converting string value to double
	Double d = Double.valueOf(ele.getText().replace("$", ""));
	System.out.println(d);
	beforesort.add(d);
}
driver.findElement(By.xpath("(//table)[1]/thead/tr/th[4]")).click();
List<WebElement> prices2 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[4]"));
List<Double> aftersort = new ArrayList<Double>();
for(WebElement ele : prices2)
{
	Double d = Double.valueOf(ele.getText().replace("$", ""));
	System.out.println(d);
	aftersort.add(d);
}
Collections.sort(beforesort);
Assert.assertEquals(beforesort, aftersort);
Thread.sleep(2000);

driver.findElement(By.xpath("(//table)[1]/thead/tr/th[4]")).click();
List<WebElement> prices3 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[4]"));
List<Double> aftersort2 = new ArrayList<Double>();
for(WebElement ele : prices3)
{
	Double d = Double.valueOf(ele.getText().replace("$", ""));
	System.out.println(d);
	aftersort2.add(d);
}
Collections.sort(beforesort, Collections.reverseOrder());
Assert.assertEquals(beforesort, aftersort2);
Thread.sleep(2000);
}
//Test case to verify sorting of DailyChanges column in ascending & descending order
@Test
public void dailychangeSort() throws InterruptedException{
List<WebElement> dailychanges = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[5]"));
List<Double> beforesort = new ArrayList<Double>();
for(WebElement ele : dailychanges)
{
	Double d = Double.valueOf(ele.getText().replace("%", ""));
	System.out.println(d);
	beforesort.add(d);
}
driver.findElement(By.xpath("(//table)[1]/thead/tr/th[5]")).click();
List<WebElement> dailychanges2 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[5]"));
List<Double> aftersort = new ArrayList<Double>();
for(WebElement ele : dailychanges2)
{
	Double d = Double.valueOf(ele.getText().replace("%", ""));
	System.out.println(d);
	aftersort.add(d);
}
Collections.sort(beforesort);
Assert.assertEquals(beforesort, aftersort);
Thread.sleep(2000);

driver.findElement(By.xpath("(//table)[1]/thead/tr/th[5]")).click();
List<WebElement> dailychanges3 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[5]"));
List<Double> aftersort2 = new ArrayList<Double>();
for(WebElement ele : dailychanges3)
{
	Double d = Double.valueOf(ele.getText().replace("%", ""));
	System.out.println(d);
	aftersort2.add(d);
}
Collections.sort(beforesort, Collections.reverseOrder());
Assert.assertEquals(beforesort, aftersort2);
Thread.sleep(2000);
}
//Test case to verify sorting of MarketCaps column in ascending & descending order
@Test
public void marketcapSort() throws InterruptedException{
List<WebElement> marketcaps = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[6]"));
List<Double> beforesort = new ArrayList<Double>();
for(WebElement ele : marketcaps)
{
	Double d = Double.valueOf(ele.getText());
	System.out.println(d);
	beforesort.add(d);
}
driver.findElement(By.xpath("(//table)[1]/thead/tr/th[6]")).click();
List<WebElement> marketcaps2 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[6]"));
List<Double> aftersort = new ArrayList<Double>();
for(WebElement ele : marketcaps2)
{
	Double d = Double.valueOf(ele.getText());
	System.out.println(d);
	aftersort.add(d);
}
Collections.sort(beforesort);
Assert.assertEquals(beforesort, aftersort);
Thread.sleep(2000);

driver.findElement(By.xpath("(//table)[1]/thead/tr/th[6]")).click();
List<WebElement> marketcaps3 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[6]"));
List<Double> aftersort2 = new ArrayList<Double>();
for(WebElement ele : marketcaps3)
{
	Double d = Double.valueOf(ele.getText());
	System.out.println(d);
	aftersort2.add(d);
}
Collections.sort(beforesort, Collections.reverseOrder());
Assert.assertEquals(beforesort, aftersort2);
Thread.sleep(2000);
}
//Test case to verify sorting of PERatio column in ascending & descending order
@Test
public void peratioSort() throws InterruptedException{
List<WebElement> peratios = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[7]"));
List<Double> beforesort = new ArrayList<Double>();
for(WebElement ele : peratios)
{
	Double d = Double.valueOf(ele.getText());
	System.out.println(d);
	beforesort.add(d);
}
driver.findElement(By.xpath("(//table)[1]/thead/tr/th[7]")).click();
List<WebElement> peratios2 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[7]"));
List<Double> aftersort = new ArrayList<Double>();
for(WebElement ele : peratios2)
{
	Double d = Double.valueOf(ele.getText());
	System.out.println(d);
	aftersort.add(d);
}
Collections.sort(beforesort);
Assert.assertEquals(beforesort, aftersort);
Thread.sleep(2000);

driver.findElement(By.xpath("(//table)[1]/thead/tr/th[7]")).click();
List<WebElement> peratios3 = driver.findElements(By.xpath("(//table)[1]/tbody/tr/td[7]"));
List<Double> aftersort2 = new ArrayList<Double>();
for(WebElement ele : peratios3)
{
	Double d = Double.valueOf(ele.getText());
	System.out.println(d);
	aftersort2.add(d);
}
Collections.sort(beforesort, Collections.reverseOrder());
Assert.assertEquals(beforesort, aftersort2);
Thread.sleep(2000);
}

//Closing Current open browser window
@AfterClass
public void teardown(){
	driver.quit();
}


}
