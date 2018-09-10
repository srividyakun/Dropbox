package com.dropbox.pageObjects;

import java.io.FileInputStream;



import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.dropbox.base.DropboxAPI;


public class SignupLoginPage {
	
	WebDriver driver;
	
	// --------------------------- CONSTRUCTORS ---------------------------

		public SignupLoginPage(WebDriver driver) {
			// TODO Auto-generated constructor stub
		
			this.driver = driver;
		}

	//login webelements
	@FindBy(id ="sign-up-in")
	WebElement sgnin_link;
	
	@FindBy(className ="text-input-input")
	WebElement sgnin_email;
	
	@FindBy(xpath =".//*[@name='login_password']")
	WebElement sgnin_password;
	
	@FindBy(xpath =".//*[@id='component1484361347655279581']/div/div/div/form/div[3]/div/label")
	WebElement sgnin_rememberme;
	
	@FindBy(xpath =".//*[@class='login-button signin-button button-primary']")
	WebElement sgnin_login;
	
	//HomePage
	
	@FindBy(xpath = ".//*[@class='account-menu-user-summary__display-name-and-link']")
	WebElement account_menu_text;
	@FindBy(xpath = ".//*[@id='home']")
	WebElement home_link;
	@FindBy(xpath = ".//*[@class='primary-action-menu__button-wrapper']/button")
	WebElement Upload_Files;
	
	
    
	
	//Logout
	@FindBy(xpath = ".//*[@class='mc-popover-trigger']")
	WebElement account_menu;
	@FindBy(linkText = "Sign out")
	WebElement signout_link;
	@FindBy(xpath = ".//*[@class='login-register-header']")
	WebElement validatelogout;
	
	
	
	//Signin Methods
	
	public WebElement sgn_Link()
	   {
		 return sgnin_link;
	   }
	   
	public WebElement setEmail() {
		//log.info("entering user Name email: " + SigninEmail);
		return sgnin_email;
		
	}

	public WebElement setPassword() {
		//log.info("entering password: " + SignInPassword);
		return sgnin_password;
	}
	
	public WebElement clickLogin() {
		//log.info("clicking login button");
		return sgnin_login;
	}
	public WebElement validateLogout() {
		//log.info("clicking login button");
		return validatelogout;
	}
	
	
	public boolean userLoggedIn(boolean expectedCondition) throws Exception {
		Properties prop = new Properties();
		FileInputStream fr= new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//files//data.properties");
		prop.load(fr);
   		if(expectedCondition){
   			DropboxAPI db = new DropboxAPI();
   			Assert.assertEquals(prop.getProperty("loginemail"), db.dropBoxAuth().users().getCurrentAccount().getEmail());
   			expectedCondition = true;
   		}else {
   			expectedCondition = false;
   		}
   		return expectedCondition;
   	}


   public void dropBox_Login() throws IOException
   {
	   Properties prop = new Properties();
		FileInputStream fr= new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//files//data.properties");
		prop.load(fr);
	  
	   sgnin_email.sendKeys(prop.getProperty("loginemail"));
	   sgnin_password.sendKeys(prop.getProperty("loginpassword"));
	   sgnin_login.click();	
	
   }
   
   public void logout() {
	   
	   WebDriverWait expwait = new WebDriverWait(driver,30);
       expwait.until(ExpectedConditions.elementToBeClickable(account_menu));
	   account_menu.click();
       expwait.until(ExpectedConditions.elementToBeClickable(signout_link));
	   signout_link.click();
	   
   }
   
  

}
