import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import java.awt.Label;


public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JTextField tf_password;
	private JTextField tf_issue;

	/**
	 * Create the frame.
	 */
	public Gui() {
		
		
		final FileIO io = new FileIO();
		 User u = null;
		
		
		setTitle("Touch Papper Generator V0.6B");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAvalableTpTemplates = new JLabel("Avalable TP Templates:");
		lblAvalableTpTemplates.setBounds(37, 46, 136, 14);
		contentPane.add(lblAvalableTpTemplates);
		
		JLabel lblTpUsername = new JLabel("TP  Username:");
		lblTpUsername.setBounds(76, 82, 94, 14);
		contentPane.add(lblTpUsername);
		
		JLabel lblTppassword = new JLabel("TP Password:");
		lblTppassword.setBounds(76, 108, 97, 14);
		contentPane.add(lblTppassword);
		
		tf_username = new JTextField();
		tf_username.setBounds(192, 79, 121, 20);
		contentPane.add(tf_username);
		tf_username.setColumns(10);
		
		tf_password = new JPasswordField();
		tf_password.setBounds(192, 105, 121, 20);
		contentPane.add(tf_password);
		tf_password.setColumns(10);
		((JPasswordField) tf_password).setEchoChar('*');
		
		JLabel lblIssueNumber = new JLabel("Issue Number:");
		lblIssueNumber.setBounds(76, 143, 100, 14);
		contentPane.add(lblIssueNumber);
		
		tf_issue = new JTextField();
		tf_issue.setBounds(192, 136, 121, 20);
		contentPane.add(tf_issue);
		tf_issue.setColumns(10);
		
		
		final Choice choice = new Choice();
		choice.setBounds(192, 46, 121, 20);
		contentPane.add(choice);
		choice.add("STANDART_TEMPLAE");
		choice.add("EMERGENCY_TEMPLAE");
		choice.add("SRE_TEMPLAE");
		choice.add("RAM_TEMPLAE");
		
		
		
		JButton btnNewButton = new JButton("Load Template");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 //checking if all fields are filled
				if(tf_username.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane,"Please enter your username");
					return;
				}
				if(tf_password.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane,"Please enter your password");
					return;
				}
				System.out.println(tf_issue.getText());
				if(tf_issue.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane,"Please enter issue number");
					return;
				}
		
				
				//FIRST GETTING DETAILS IN BACKGROUND FROM INTRANET
				//Getting information from Omnipay Intrante from issue number.
				HtmlUnitClass uc = new HtmlUnitClass();
				IssueInfo ii = uc.getDetails(contentPane, tf_issue.getText());
				
				
				//logging in user::
				Login l = new Login();
				 	
				
				 	//saving object to the file to remember username and password
					String username = tf_username.getText();
					String password = tf_password.getText();
					
					
					//saving to the file username
					User u = new User(username,password);
					
					io.setValues(u);
					
				 	//logging in to the page
					WebDriver driver;
					
					
					driver =  l.run(username,password,contentPane);
					
					
			        
					//running template_SRE
					if(choice.getSelectedItem().equals("SRE_TEMPLAE")){
						
						
						template_SRE t = new template_SRE(username,tf_issue.getText(),ii.institution);
						//template_SRE t = new template_SRE(username,tf_issue.getText(),tf_inst.getText());
						
						t.applyTemplate(driver,contentPane);	
					}
					
					
					//running other template
					else if (choice.getSelectedItem().equals("STANDART_TEMPLAE")){
						
						template_Other t = new template_Other(username,tf_issue.getText(),ii.institution,ii.title);
						t.applyTemplate(driver,contentPane);
						
					}
					//running RAM template
					else if (choice.getSelectedItem().equals("RAM_TEMPLAE")){
						
						template_RAM t = new template_RAM(username,tf_issue.getText(),ii.institution,ii.title);
						t.applyTemplate(driver,contentPane);
						
					}
					
					else if (choice.getSelectedItem().equals("EMERGENCY_TEMPLAE")){
						
						template_Emergency t = new template_Emergency(username,tf_issue.getText(),ii.institution,ii.title);
						t.applyTemplate(driver,contentPane);
						
					}
					
				
				
			}
		});
		btnNewButton.setBounds(143, 210, 121, 23);
		contentPane.add(btnNewButton);
		
		Label label = new Label("Support: Deniss.Strods@firstdata.com");
		label.setBounds(100, 265, 228, 22);
		contentPane.add(label);
		
		
		
		
		//get values from existing file
		
		
		//getting value if file exists
		if(new File("H:\\user_indexing.ser").isFile()){
		u = io.getValues();
		tf_username.setText(u.username);
		tf_password.setText(u.password);
		}
		
		
	}
}
