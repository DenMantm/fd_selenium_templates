import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Login {
	//taking user name and password
	WebDriver driver = null;
	public WebDriver run(String username, String password,JPanel contentPane){
	try{
	System.setProperty("webdriver.chrome.driver", "X:/dstrods/tp_template_tool/silenium_driver/chromedriver.exe");
	driver = new ChromeDriver();
    driver.get("http://ntserv05.omnipay.ie/WebDesk/Logon/Logon.rails");             
    driver.findElement(By.id("Ecom_User_ID")).sendKeys(username);     
    driver.findElement(By.id("Ecom_User_Password")).sendKeys(password);     
    driver.findElement(By.id("logonButton")).submit();
	
	}
	catch(java.lang.IllegalStateException e){
		JOptionPane.showMessageDialog(contentPane,"Silenium chrome driver is missing from: X:/dstrods/tp_template_tool/silenium_driver/chromedriver.exe, please contacd Deniss.Strods@firstdata.com");
	}

    //return page after login
    return driver;
	}
}
