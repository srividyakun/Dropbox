package com.dropbox.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.dropbox.base.Base;
import com.dropbox.pageObjects.HomePage;
import com.dropbox.pageObjects.SignupLoginPage;


public class CreateNewFolderTest extends Base {
	
	private static Logger log =LogManager.getLogger(Base.class.getName());
	
	@BeforeSuite
	public void BrowserNavigation() throws IOException {
		driver=InitializeWebdriver();
		
	}
	@BeforeMethod
	public void dropboxLogin() throws Exception {
		Properties prop = new Properties();
		FileInputStream fr= new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//files//data.properties");
		prop.load(fr);
		SignupLoginPage dblogin=PageFactory.initElements(driver,SignupLoginPage.class);
		dblogin.sgn_Link().click();
		 dblogin.dropBox_Login();
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		   
			Assert.assertTrue(dblogin.userLoggedIn(true));
			log.info("User logged in successfully");
	}
       @Test
       public void createNewFolder() throws Exception {
    	   Properties prop = new Properties();
   		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//files//data.properties");
   		prop.load(file);
    	   HomePage hpnf = PageFactory.initElements(driver,HomePage.class);
    	   hpnf.createFolder(prop.getProperty("foldername"));
    	  // Assert.assertTrue(loginpage.userLoggedIn(true));
			log.info("Folder Created successfully");
       }
       
       @AfterMethod
   	public void tearDown(ITestResult result) throws Exception {
   		if (result.getStatus() == ITestResult.FAILURE) {
   			try{
   				tearDown();
   			}catch(Exception e){
   				e.printStackTrace();
   			}
   		}
       }
   
   	
   	@AfterSuite
   	public void tearDown(){
   		log.info("closing webdriver");
   		driver.close();
   		if(driver != null){
   			driver = null;
   		}
   		log.info("webdriver closed.");
   	}
		
  
	
}


