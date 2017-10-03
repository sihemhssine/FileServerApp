 package package1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class accountManagment  extends JFrame implements ActionListener {
Container c ;
String email ; JPanel  p1,p2 ; 
JLabel l1,l2,l3  ,l4  ; 
JTextField textField_2,textField_1 ; String[]  n ; 
//static JLabel l ;    
 JButton btnNewButton; 
public accountManagment(String email) {
	// TODO Auto-generated constructor stub
	this.email=email; 
	
	this.setResizable(true);
	this.setBounds(100, 100, 450, 300);
	this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE) ; 
	c= this.getContentPane(); 

	c.setBackground(new Color(192, 192, 192));
	c.setLayout(null);
	
	
	JLabel lblNewLabel = new JLabel("Account Managment");
	lblNewLabel.setForeground(new Color(46, 139, 87));
	lblNewLabel.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
	lblNewLabel.setBounds(10, 0, 400, 42);
	c.add(lblNewLabel);
	
	JTextField textField_1 = new JTextField(15);
	textField_1.setBounds(250, 122, 150, 31);
	c.add(textField_1);
	
	btnNewButton = new JButton("Save changes");
	btnNewButton.setBackground(new Color(143, 188, 143));
	btnNewButton.setForeground(new Color(72, 61, 139));
	btnNewButton.setFont(new Font("Urdu Typesetting", Font.BOLD, 16));
	btnNewButton.setBounds(250, 180, 150, 42);
	c.add(btnNewButton);
	

	
	JTextField textField_2 = new JTextField(15);
	textField_2.setBounds(250, 69, 150, 31);
	c.add(textField_2);
	
	JLabel lblNewLabel_1 = new JLabel("New Email");
	lblNewLabel_1.setForeground(new Color(72, 61, 139));
	lblNewLabel_1.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 25));
	lblNewLabel_1.setBounds(102, 63, 116, 37);
	c.add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("New Password");
	lblNewLabel_2.setForeground(new Color(72, 61, 139));
	lblNewLabel_2.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 25));
	lblNewLabel_2.setBounds(102, 122, 138, 31);
	c.add(lblNewLabel_2);

	
  
 /*l2= new JLabel("change pw "); 
 t1= new JTextField(15);
 l3= new JLabel("change email ");
 t2= new JTextField(15); 
 l4= new JLabel("Shared files ") ; 
 
	b1= new JButton("save changes"); 
	c.add(l1); 
	c.add(l2); 
	c.add(t1); 
	c.add( b1 ) ; 
	c.add(t2); 
	c.add(l4) ; 
	 */
	  //headers for the table
    String[] columns = new String[] {
        "Id", "Name", "Hourly Rate", "Part Time"
    };
     
    //actual data for the table in a 2d array
    Object[][] data = new Object[][] {
        {1, "John", 40.0, false },
        {2, "Rambo", 70.0, false },
        {3, "Zorro", 60.0, true }
    };
    //create table with data
  
	// list of  files 
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	int i= 1 ; 
	try 
	{
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql-mydrive.alwaysdata.net/mydrive_db","mydrive","1234");
        Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select  name_file  from  fichier where email='"+email+"'");
      while (rs.next()){ 
  String a = rs.getString( "name_file") ; 
   System.out.println(a );
   JLabel l = new JLabel( rs.getString( "name_file")); 
   
     	}
      
 
	    while (rs.next()){
    	 
     JPanel  p= new JPanel(); 
     p.setLayout( new FlowLayout());
     JLabel  l = new  JLabel(rs.getString(i )) ; 
  //    b = new JButton("delete") ; 
     /* b.addActionListener ( new ActionListener()
      {
    	    @Override
    	    public void actionPerformed(ActionEvent e)
    	    {
    	      System.out.println(l.getText());
    	    }
    	});*/
    
       }
    
    
}catch(Exception ex){
    ex.printStackTrace();
}

	
	
	
	//btnNewButton.addActionListener(this);
 
   
	
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	/*if(e.getSource()== b )
	{ 
		
		
	}*/
	if(e.getSource() == btnNewButton)
 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

       if(textField_2.getText()!= "")
       {
		try{ 
		 
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql-mydrive.alwaysdata.net/mydrive_db","mydrive","1234");
			PreparedStatement ps = con.prepareStatement("update membre set   pw  = ? where email ='"+email+"'");
			
			textField_2.setText( getMD5Hash( textField_2.getText()) );
			ps.setString(1, textField_2.getText());
			ps.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
       }
       if(textField_2.getText() !="")
       {
    	   
    	   try {
   			Class.forName("com.mysql.jdbc.Driver");
   		} catch (ClassNotFoundException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		} 

          if(textField_1.getText()!= "")
          {
   		try{  
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql-sihem.alwaysdata.net/sihem_mydrive","sihem","sob7anaALLAH");
   			PreparedStatement ps = con.prepareStatement("update membre set    email  = ? where email ='"+email+"'");
   			ps.setString(1, textField_1.getText());
   			ps.executeUpdate(); 
   		}catch(Exception ex){
   			ex.printStackTrace();
   		}
        } 
          }
       
       JOptionPane.showMessageDialog(null, "Data  updated  with success"); 
	}
}

public   String getMD5Hash(String s) throws NoSuchAlgorithmException {
	String result = s;
	if (s != null) {
		MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
		md.update(s.getBytes());
		BigInteger hash = new BigInteger(1, md.digest());
		result = hash.toString(16);
		while (result.length() < 32) { // 40 for SHA-1
			result = "0" + result;
		}
	}
	return result; }   
}