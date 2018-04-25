
//creating object so that it would be easier to exchange data between classes
public class IssueInfo {
	public String title,institution;
	public IssueInfo(String title, String institution){
		this.institution = institution;
		this.title = title;
		
		//getting last 2 digits 
		this.institution = institution.substring(Math.max(institution.length() - 2, 0));
		//System.out.println(institution);
	}
}
