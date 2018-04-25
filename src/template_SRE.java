import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class template_SRE {
	
	Template t = new Template();
	String requestor,issue;
	
	
	public template_SRE(String requestor,String issue,String inst){
		
		this.requestor = requestor;
		this.issue = issue;
		//adding all necesary info to the template
		t.requestor = requestor.toLowerCase();
		t.implementor = "DBA";
		t.summary  = "Issue "+ issue + " - Adding SREs for inst "+inst;
		//get current date::
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat( "dd MMMMMM yyyy", Locale.getDefault() );
		SimpleDateFormat dateFormatBranch = new SimpleDateFormat( "yyyyMMdd", Locale.getDefault() );
		t.requested_imp = dateFormat.format(cal.getTime());
		t.actual_imp = dateFormat.format(cal.getTime());
		t.category = "Database";
		t.reason_for_change = "Production Fix";
		t.related_issue_trackers = issue;
		
		
		String emergency_branch = dateFormatBranch.format(cal.getTime());
		t.release_ref = "GIT RELEASE: "+emergency_branch+"E"+ "\n"+
						"GIT DEV BRANCH: "+issue+"_"+inst+
						"\n\nSinret:";
		
		t.envoirment  = "Production";
		t.details = "Adding SREs for inst "+inst+" via SQL inserts based on BW3 error logging.";
		t.impact_assesment = "if not applied settlement will be missing for institution.";
		t.backout_plan = "delete newly inserted records";
		t.testing_details = "not required as value added would be limited";
		t.verification_plan = "verification by Sysimp PIR by SD - will confirm that the process loads successfully.";
	
	}
	//passing driver from gui class
	public void applyTemplate(WebDriver driver,JPanel contentPane){
		
		
		//this value sets timeout before each drop down list after inputig value is worked on
		int sleepValue = 500;
		
        //Going to change managment page - shortGroup0
		try{
        driver.findElement(By.id("shortGroup0")).click();
        //Emergency TP shortcuItem_d2ca9b23-9574-4895-bc97-9ebcaf1df90c
        driver.findElement(By.id("shortcuItem_d2ca9b23-9574-4895-bc97-9ebcaf1df90c")).click();
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			JOptionPane.showMessageDialog(contentPane,"Login To the touchpaper failed!");
			return;
		}

        
        //Filling fields in the template
        driver.findElement(By.id("mainForm-RaiseUser4Display")).sendKeys(t.requestor);//Keys.ARROW_DOWN)
        try {
            Thread.sleep(sleepValue);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.id("mainForm-RaiseUser4Display")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-RaiseUser4Display")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-RaiseUser4Display")).sendKeys(Keys.ENTER);
        

        
        
        driver.findElement(By.id("mainForm-_UserDisplay")).sendKeys(t.implementor);
        try {
            Thread.sleep(sleepValue);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.id("mainForm-_UserDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-_UserDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-_UserDisplay")).sendKeys(Keys.ENTER);

        
        
        driver.findElement(By.id("mainForm-Title")).sendKeys(t.summary);
        
        
        
        //date - time
        driver.findElement(By.id("mainForm-_ImplementationDate-date")).click();
        driver.findElement(By.id("mainForm-_ImplementationDate-date")).sendKeys(Keys.ENTER);
        
        
        
        driver.findElement(By.id("mainForm-_ImplementationDate-time")).sendKeys("16");

        
        //service impact
        driver.findElement(By.id("mainForm-Severity210Display")).sendKeys("3");//Keys.ARROW_DOWN)
        try {
            Thread.sleep(600);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.id("mainForm-Severity210Display")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-Severity210Display")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-Severity210Display")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-Severity210Display")).sendKeys(Keys.ENTER);
        
        
        
        
        driver.findElement(By.id("mainForm-CategoryNameDisplay")).click();
        try {
            Thread.sleep(sleepValue);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.id("mainForm-CategoryNameDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-CategoryNameDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-CategoryNameDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-CategoryNameDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-CategoryNameDisplay")).sendKeys(Keys.ENTER);
        
        
        
        //date and time
        driver.findElement(By.id("mainForm-_ActualImplementationDate5-date")).click();
        driver.findElement(By.id("mainForm-_ActualImplementationDate5-date")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("mainForm-_ActualImplementationDate5-time")).sendKeys("16");
        
        
        
        
        driver.findElement(By.id("mainForm-_ReasonforChangeDisplay")).sendKeys(t.reason_for_change);
        try {
            Thread.sleep(sleepValue);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.id("mainForm-_ReasonforChangeDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-_ReasonforChangeDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-_ReasonforChangeDisplay")).sendKeys(Keys.ENTER);
        
        
        
        //envoirment mainForm-_EnvironmentDisplay
        driver.findElement(By.id("mainForm-_EnvironmentDisplay")).sendKeys(t.envoirment);
        try {
            Thread.sleep(sleepValue);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.id("mainForm-_EnvironmentDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-_EnvironmentDisplay")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("mainForm-_EnvironmentDisplay")).sendKeys(Keys.ENTER);
        
        
        //Filling Fields
        
        driver.findElement(By.id("mainForm-_RelatedIssueTrackers")).sendKeys(t.related_issue_trackers);
        //mainForm-_ReleaseRef
        driver.findElement(By.id("mainForm-_ReleaseRef")).sendKeys(t.release_ref);
        driver.findElement(By.id("mainForm-Description")).sendKeys(t.details);
        
        driver.findElement(By.id("mainForm-_ImpactAssessment")).sendKeys(t.impact_assesment);
        driver.findElement(By.id("mainForm-_BackoutPlan")).sendKeys(t.backout_plan);
        driver.findElement(By.id("mainForm-_TestingDetails")).sendKeys(t.testing_details);
        driver.findElement(By.id("mainForm-_VerificationPlan")).sendKeys(t.verification_plan);
        
        
	}

}
