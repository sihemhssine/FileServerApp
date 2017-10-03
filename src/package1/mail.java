 package package1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
 

public class  mail extends JFrame implements ActionListener {
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	  public  JTextField fromField  ;
	  public JTextField toField ;
	  public JTextField subjectField  ;
	  public JTextField usernameField  ; 
	  public JTextArea contentTextArea ; 
	  public  JPasswordField passwordField ; 
	  JButton sendMailButton  ; String email ; 
	 public mail(String email)  throws AddressException,
		MessagingException {
		 
		 this.email= email ; 
		 int a = email.indexOf("@");
		 String from = email.substring(0, a); 
         fromField  = new JTextField(from);
         fromField.setEditable(false);
		 toField = new JTextField();
		 subjectField   = new JTextField();
		 usernameField = new JTextField();
		 passwordField = new JPasswordField();
		 contentTextArea   = new JTextArea();
		    
		 setTitle("Send E-mail Client"); 
	        setSize(new Dimension(400, 280));   
	        getContentPane().setLayout(new BorderLayout());

	        // Header Panel
	        JPanel headerPanel = new JPanel();
	        headerPanel.setLayout(new GridLayout(6, 2));
	        headerPanel.add(new JLabel("From:"));
	        headerPanel.add(fromField);

	        headerPanel.add(new JLabel("To:"));
	        headerPanel.add(toField);

	        headerPanel.add(new JLabel("Subject:"));
	        headerPanel.add(subjectField);
          

	        headerPanel.add(new JLabel("Password:"));
	        headerPanel.add(passwordField);

	        // Body Panel
	        JPanel bodyPanel = new JPanel();
	        bodyPanel.setLayout(new BorderLayout());
	        bodyPanel.add(new JLabel("Message:"), BorderLayout.NORTH);
	        bodyPanel.add(contentTextArea, BorderLayout.CENTER);

	        JPanel footerPanel = new JPanel();
	        footerPanel.setLayout(new BorderLayout());
	          sendMailButton = new JButton("Send E-mail");
	        
	        footerPanel.add(sendMailButton, BorderLayout.SOUTH);

	        getContentPane().add(headerPanel, BorderLayout.NORTH);
	        getContentPane().add(bodyPanel, BorderLayout.CENTER);
	        getContentPane().add(footerPanel, BorderLayout.SOUTH);
	         sendMailButton.addActionListener( this );
	        
	     
	 }

  
		 @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//proprieties 
		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true"); 
		
		// create email 
		String[] toEmails = { toField.getText()};
		String emailSubject =   subjectField.getText();
		String emailBody = contentTextArea .getText();

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			try {
	    emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");//for a html email
		//emailMessage.setText(emailBody);// for a text email 
		} 
		catch ( Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  	} 
		
		// send email 
		
		String emailHost = "smtp.gmail.com";
		String fromUser =  fromField.getText();//just the id alone without @gmail.com
		String fromUserEmailPassword = passwordField.getText();

		Transport transport;
		try {
			transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close(); 
		JOptionPane.showMessageDialog(this, "Email sent successfully ");
		
	dispose(); 
		}  
		catch ( Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}