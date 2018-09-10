package com.dropbox.base;

import com.dropbox.core.DbxException;
	   import com.dropbox.core.DbxRequestConfig;
	   import com.dropbox.core.v2.DbxClientV2;
	   import com.dropbox.core.v2.files.FileMetadata;
	   import com.dropbox.core.v2.files.ListFolderResult;
	   import com.dropbox.core.v2.files.Metadata;
	   import com.dropbox.core.v2.users.FullAccount;

	   import java.io.FileInputStream;
	   import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

	   public class DropboxAPI {
		   
			protected static WebDriver driver;

			private static DbxClientV2 client;

	       public DbxClientV2 dropBoxAuth()  throws DbxException, IOException {
	    	   
	    	   Properties prop = new Properties();
				FileInputStream fr= new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//files//data.properties");
				prop.load(fr);
	           // Create Dropbox client
	           DbxRequestConfig config = new DbxRequestConfig("dropbox/java-selenium");
	           DbxClientV2 client = new DbxClientV2(config, prop.getProperty("accesstocken"));
	           
	          // System.out.println("Linked account: " + client.users().getCurrentAccount().getName().getDisplayName());

	   		

	           // Get current account info
	           FullAccount account = client.users().getCurrentAccount();
	           System.out.println(account.getName().getDisplayName());
	           
	      	          
	     	return client;
	       }
	       
	       
	      

	   }