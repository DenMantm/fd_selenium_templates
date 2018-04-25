import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;


public class HtmlUnitClass {
	
	 IssueInfo iinfo = null;
	 
	 
	 //passing issue number to centre message in the window, passing issue number so we would know which issue to search for
	 
	public IssueInfo getDetails(JPanel contentPane, String issue_number){
		 final WebClient webClient = new WebClient();
		try{ 
 	    HtmlPage page = webClient.getPage("http://intranet.omnipay.ie:8080/omnipay/");
 	    HtmlElement usrname = page.getElementByName("UNAME");
 	    usrname.click();
 	    usrname.type("omnipay-sysimp@firstdata.com");
 	    HtmlElement psswrd = page.getElementByName("PWORD");
 	    psswrd.click();
 	    psswrd.type("sysimp");
 	    
 	   // HtmlSelect select = (HtmlSelect) page.getElementById("cmbProducts");
 	   // HtmlOption option = select.getOptionByValue("ITDirect");
 	   // select.setSelectedAttribute(option, true);
 	   // page.getByXPath("//input[@class = 'button']");
 	    
 	    
 	    //this is workaround for getting where we need
 	    List<HtmlElement> l = (List<HtmlElement>) page.getByXPath("//input[@class = 'button']");
 	    HtmlElement element = l.get(0);
 	    page =   element.click();
 	    
 	    
 	    //AFTER LOGIN
 	    
 	   //JOptionPane.showMessageDialog(contentPane, "working!");
 	   
 	    element = page.getElementByName("ISSUEID");
 	    //entering manually for now
 	    
 	    //issue_number = "200055";
 	    element.type(issue_number);
 	    
 	    //xpath //input[@name = 'ISSUEID']/../td[1]/input
 	    
 	    //getting button to press by navigating trough DOM tree
 	    
 	    l = (List<HtmlElement>) page.getByXPath("(//input[@name = 'ISSUEID']/../../td)[2]/input");
 	    element = l.get(0);
 	    page = element.click();
 	   
 	    
 	    
 	    
 	    //getting title for the field
 	    element =  page.getElementByName("TITLE");
 	    System.out.println(element.getAttribute("value"));
 	        
 	    
 	   // HtmlSelect select = page.getElementByName("INSTNO");
 	    HtmlSelect select = (HtmlSelect)page.getElementByName("INSTNO");
 	    HtmlOption option =  select.getSelectedOptions().get(0);
 	    
 	   System.out.println(option.getAttribute("value"));
 	    
 	    //setting values from intranet
 	   
 	   iinfo = new IssueInfo(element.getAttribute("value"),option.getAttribute("value"));
 	    
 	   
 	    
 	    //System.out.println(select.getSelectedOptions());
 	    //System.out.println(select);
 	    //getting institution number
		}
		catch(Exception e){
			
			JOptionPane.showMessageDialog(contentPane, "Something is wrong with issue number or intranet password");
			
		}
		finally{
 	    webClient.closeAllWindows();
		}

		//TestMethod t = new TestMethod();
		//t.run();
 	    return iinfo;
	}
	
}
