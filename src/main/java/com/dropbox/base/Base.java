package com.dropbox.base;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
public WebDriver driver;
private static Logger log =LogManager.getLogger(Base.class.getName());

	
	public WebDriver InitializeWebdriver() throws IOException 
	{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//files//data.properties");
		prop.load(file);
		String browsername = prop.getProperty("browser");
		
		if(browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//main//resources//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browsername.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//src//main//resources//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			
		}else if(browsername.equals("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//src//main//resources//drivers//IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		log.info("Driver is initialized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String urlname = prop.getProperty("url");
		driver.get(urlname);
		log.info("URL is Launched");
		return driver;
		
	}
	
	
	
}
