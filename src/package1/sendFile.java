 package package1; 

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.Serializable;
import java.net.Socket;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
 

public   class sendFile extends Thread  implements Serializable {

	String path ; String file_name; JList<String> list;String espace_name ; int index   ; String user_name;
	DefaultListModel listModel1 ; Socket s ; File[] listOfFiles ; FileInputStream fis; 

	
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	
	File dir = new File("/home/sihem/workspaceRMI/ProjectDev");
	DataOutputStream dos ;
	String pseud ; Boolean testMail ; 
	Date  datefile ; 
	sendFile(String path , String user_name, String file_name, String espace_name, JList<String> list, Socket s , Boolean testMail )
	{    
		this.path= path ;
		this.user_name= user_name ; 
		this.file_name = file_name ; 
		this.list=list ; this.s= s ; this.espace_name= espace_name; 
		this.testMail = this.testMail ; 
		if(testMail == true  )
		{
			System.out.println("yeeeeeeeeesss");


		}
	}
	public synchronized void run(){

		//recupération nom dufichier + id + nom d  espace et inserrtion ds la base 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		try{  
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql-sihem.alwaysdata.net/sihem_mydrive","sihem","sob7anaALLAH");
			Statement st = con.createStatement(); 
			ResultSet rs = st.executeQuery("select    MAX(indexF) from fichier"  );
			if(rs.next()){
				index = rs.getInt(1);
			} 

			PreparedStatement ps = con.prepareStatement("insert into fichier(email,name_file,dateFile,space_file) values(?,?,?,?)");
			ps.setString(1,  user_name);
			index= index+1; 
			ps.setString(2,  file_name +"*"+espace_name+index  ); 

			// (2) create a java sql date object we want to insert
			Calendar calendar = Calendar.getInstance();
			java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());

			ps.setDate( 3,    ourJavaDateObject);
			ps.setString(4, espace_name );
			ps.executeUpdate();


			dos = new DataOutputStream(s.getOutputStream());
			fis = new FileInputStream(path);
			byte[] buffer = new byte[4096];

			//  envoir info via socket 
			dos.writeUTF(  file_name  +"*" + espace_name +index ); 
			while (fis.read(buffer) > 0) {
				dos.write(buffer);
			}

			fis.close();
			dos.close(); 

			sendMail( file_name, espace_name); 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, " File added successfully");


		listModel1 = new DefaultListModel();

		// affichage des fichiers 
		listOfFiles =  dir.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().charAt(0)!='.' && listOfFiles[i].getName().contains( espace_name ) ) {


				int a = listOfFiles[i].getName().indexOf("*") ; 



				try 
				{    		     
					Connection con = DriverManager.getConnection("jdbc:mysql://mysql-sihem.alwaysdata.net/sihem_mydrive","sihem","sob7anaALLAH");


					PreparedStatement ps ; 
					ps = con.prepareStatement("select   `dateFile`  from  fichier  where name_file = ?");
					ps.setString(1, listOfFiles[i].getName());
					ResultSet result1 = ps.executeQuery();
					if(result1.next())
					{    
						datefile = result1.getDate("datefile");
						 
					}     
					String fileName  = listOfFiles[i].getName().substring(0 ,  a  ) ;   
					listModel1.addElement( fileName + "Created "+ datefile   );
				} 
				catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  

			} 
		}


		list.setModel(listModel1);


	} 




	public void sendMail(String file_n ,String esp_n ) throws Exception
	{


		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try{  
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql-mydrive.alwaysdata.net/mydrive_db","mydrive","1234");
			Statement st = con.createStatement(); 
			ResultSet rs = st.executeQuery("select email from membre"  );
			while(rs.next()){
				String s= rs.getString("email") ; 
		  		
				// proprieties
				String emailPort = "587"; 
				emailProperties = System.getProperties();
				emailProperties.put("mail.smtp.port", emailPort);
				emailProperties.put("mail.smtp.auth", "true");
				emailProperties.put("mail.smtp.starttls.enable", "true");
				
				String[] toEmails = { s };
				String emailSubject = "Developers workspace";
				String emailBody = " Hi, We want inform you that a new file named  "+file_n +" is addeddin your favorite working space"+esp_n;

				mailSession = Session.getDefaultInstance(emailProperties, null);
				emailMessage = new MimeMessage(mailSession);

				for (int i = 0; i < toEmails.length; i++) {
					emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
				}

				emailMessage.setSubject(emailSubject);
				emailMessage.setContent(emailBody, "text/html");//for a html email
				//emailMessage.setText(emailBody);// for a text email

				String emailHost = "smtp.gmail.com";
				String fromUser = "hcinesihem14";//just the id alone without @gmail.com
				String fromUserEmailPassword = "thankuALLAH";

				Transport transport = mailSession.getTransport("smtp");

				transport.connect(emailHost, fromUser, fromUserEmailPassword);
				transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
				transport.close();
				System.out.println("Email sent successfully.");

				
				
			}
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
        
        
		
		
		

	 
	 
 
 
		



	}



}   
