package com.dropbox.pageObjects;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.dropbox.base.DropboxAPI;
import com.dropbox.base.WebDriverUtilities;



public class HomePage extends DropboxAPI {
	
	WebDriver driver;
	
	// --------------------------- CONSTRUCTORS ---------------------------

		public HomePage(WebDriver driver) {
			// TODO Auto-generated constructor stub
		
			this.driver = driver;
		}
	

	
	public static String PATH_TO_FILE_FROM_ROOTFOLDER = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator;

		
	// -------------------------- WEB ELEMENTS --------------------------
	
	
	@FindBy(xpath = ".//*[@class='mc-popover-trigger']")
	WebElement account_menu;
	@FindBy(xpath = ".//*[@class='account-menu-user-summary__display-name-and-link']")
	WebElement account_menu_text;
	@FindBy(xpath = ".//*[@id='home']")
	WebElement home_link;
	@FindBy(xpath = ".//*[@class='primary-action-menu__button-wrapper']/div/button")
	WebElement upload_button;
	
	@FindBy(xpath = ".//*[@class='mc-popover-content-menu']/div/button[1]")
	WebElement upload_files;
	
	@FindBy(xpath = ".//*[@class='primary-action-menu__button-wrapper']/button")
	WebElement ffupload_files;
	
	//Link File
	
	@FindBy(xpath = ".//*[@class='maestro-nav__product']")
	WebElement file_link;
	
	@FindBy(xpath = ".//*[@class='appactions-menu--buttons']/ul/li[2]")
	WebElement new_folder;
	
	//deleteFile

	@FindBy(xpath = ".//*[@class='brws-files-view']/div[1]/table/tbody")
	WebElement filesandfolders_view;
		
	@FindBy(xpath = ".//*[@class='brws-file-name-cell-filename']")
	WebElement folderorfile_name;
	
	
	
	

	// -------------------------- OTHER METHODS --------------------------

	public void openAccountMenu() {
		account_menu.click();
	}

	
	public void waitForHomePage() throws Exception{
		WebDriverUtilities.waitForElement(driver, account_menu_text);
	}
	
	public String getUserName() throws Exception{
		WebDriverUtilities.waitForElement(driver, account_menu_text);
		return account_menu_text.getText();
	}
	
	//FileLink methods
	
	public void fileLink() {
		file_link.click();
	}

	public void newfolder() {
		new_folder.click();
	}
	
	//deletefiles
	public void allFilesView() {
		filesandfolders_view.click();
	}
	
	public void linksView() {
		folderorfile_name.click();
	}
	

	

	// -------------------------- FILE  --------------------------
	
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	
    
    public void uploadFile(String fileName) throws Exception {
		File file = new File("");
		PATH_TO_FILE_FROM_ROOTFOLDER = file.getCanonicalPath()+PATH_TO_FILE_FROM_ROOTFOLDER+fileName;
		PATH_TO_FILE_FROM_ROOTFOLDER = new File(PATH_TO_FILE_FROM_ROOTFOLDER).getCanonicalPath();
		
		
			 WebDriverWait expwait = new WebDriverWait(driver,30);
		        expwait.until(ExpectedConditions.elementToBeClickable(upload_button));
		        upload_button.click();
		        
		       	setClipboardData(PATH_TO_FILE_FROM_ROOTFOLDER);
		       	
		        expwait.until(ExpectedConditions.elementToBeClickable(upload_files));
			    upload_files.click();
		    	pasteFileLocationInUploadWindow();
		    	
	}
    
   		
	public void createFolder(String fileName) throws Exception	{
		
		
			 WebDriverWait expwait = new WebDriverWait(driver,30);
		        expwait.until(ExpectedConditions.elementToBeClickable(file_link));
		        file_link.click();
		        
		       	setClipboardData(fileName);
		       	
		        expwait.until(ExpectedConditions.elementToBeClickable(new_folder));
			    new_folder.click();
		    	pasteFileLocationInUploadWindow();
	}
	
	 public void pasteFileLocationInUploadWindow() throws Exception {		
			
			Robot robot = new Robot();
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
		    	}
	
		
	
	}






	



