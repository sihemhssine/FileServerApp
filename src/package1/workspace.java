 package package1;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


public class workspace extends JFrame implements MouseListener, ActionListener{
	JPopupMenu menu;
	JMenuItem  m1,m2;
	JButton config;
private Container frame;
String email ; 
JLabel lblNewLabel_3,   lblNewLabel_12 , lblNewLabel_4,lblNewLabel_5, lblNewLabel_6, lblNewLabel_7, lblNewLabel_8, lblNewLabel_9, lblNewLabel_10;
Border border = LineBorder.createGrayLineBorder();
	public workspace(String email ) {
	 this.email= email ;
		frame = this.getContentPane();
		this.setBounds(100, 100, 900,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(192, 192, 192));
		frame.setLayout(null);
	
		initialize();	
	}
 
	private void initialize() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 883, 61);
		frame.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel_2 = new JLabel("Welcome");
		lblNewLabel_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_2.setFont(new Font("Urdu Typesetting", Font.BOLD, 30));
		lblNewLabel_2.setBounds(200, 0, 300, 61);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(169, 169, 169));
		panel_1.setBounds(448, 0, 453, 61);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("profileImage");
		lblNewLabel_1.setBounds(10, 11, 103, 44);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(123, 11, 79, 44);
		panel_1.add(lblName);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(227, 0, 58, 61);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("images/bell-1096280_640.png"));
		
		JLabel lblNewLabel_11 = new JLabel("0");
		lblNewLabel_11.setForeground(new Color(255, 0, 0));
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_11.setBounds(267, 41, 20, 20);
		panel_1.add(lblNewLabel_11);
		
	
		
		JButton config = new JButton("");
		config.setIcon(new ImageIcon("images/reglageWP-350x350.png"));
		config.setBackground(new Color(169, 169, 169));
		config.setBounds(340, 0, 65, 61);
		panel_1.add(config);
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon("images/email.png"));
		lblNewLabel_12.setBounds(287, 0, 54, 61);
		panel_1.add(lblNewLabel_12);
		lblNewLabel_12.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//  sending email 
				try {
					mail m = new mail(email);
					m.setVisible(true);
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} }
		});
	   
            // Display profile photo and  pseudo 
			 try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			try 
			{
				Connection con = DriverManager.getConnection("jdbc:mysql://mysql-sihem.alwaysdata.net/sihem_mydrive","sihem","sob7anaALLAH");
	            Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from  membre where email='"+email+"'");
	        if(rs.next()){
	        	System.out.println("pp"+rs.getBytes("profilephoto"));
	            byte[] img = rs.getBytes("profilephoto");
	           //Resize The ImageIcon
	            ImageIcon image = new ImageIcon(img);
	            Image im = image.getImage();
	            Image myImg = im.getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(),Image.SCALE_SMOOTH);
	            ImageIcon newImage = new ImageIcon(myImg);
	            lblNewLabel_1.setIcon(newImage);
	            String s =rs.getString( "name") ; 
	            lblName.setText( s);
	           }
	        
	       
	    }catch(Exception ex){
	        ex.printStackTrace();
	    }
	    
			
			 menu=new JPopupMenu();
			  m1=new JMenuItem("Account Managment");
		      m2=new JMenuItem("Log Out");
		       m2.addActionListener(this);
		       m1.addActionListener(this);
		        menu.add(m1);
		        menu.add(m2);
		      
		        
		      
		        ActionListener a1=new ActionListener(){
		            public void actionPerformed(ActionEvent ae)
		            {
		               
		              showPopup(ae);
		            }
		        };
		         config.addActionListener(a1);
		      
			JLabel lblChooseYourSpace = new JLabel("Choose your space");
			lblChooseYourSpace.setBounds(10, 58, 500, 42);
			lblChooseYourSpace.setFont(new Font("Urdu Typesetting", Font.BOLD, 19));
			frame.add(lblChooseYourSpace);

			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setBounds(42, 128, 236, 143);
			lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
			lblNewLabel_3.setIcon(new ImageIcon("images/design2.png"));
			frame.add(lblNewLabel_3);
			lblNewLabel_3.addMouseListener(this);
			
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setBackground(new Color(169, 169, 169));
			lblNewLabel_4.setBounds(297, 128, 236, 143);
			lblNewLabel_4.setIcon(new ImageIcon("images/web2.png"));
			frame.add(lblNewLabel_4);
			lblNewLabel_4.addMouseListener(this);
			
			lblNewLabel_5 = new JLabel("Design");
			lblNewLabel_5.setBounds(106, 264, 72, 30);
			lblNewLabel_5.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 16));
			frame.add(lblNewLabel_5);
			lblNewLabel_5.addMouseListener(this);
			
			lblNewLabel_6 = new JLabel("Web Dev");
			lblNewLabel_6.setBounds(361, 268, 135, 22);
			lblNewLabel_6.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 16));
			frame.add(lblNewLabel_6);
			
			lblNewLabel_7 = new JLabel("");
			lblNewLabel_7.setBackground(new Color(169, 169, 169));
			lblNewLabel_7.setBounds(571, 128, 212, 143);
			lblNewLabel_7.setIcon(new ImageIcon("images/mobile2.png"));
			frame.add(lblNewLabel_7);
			lblNewLabel_7.addMouseListener(this);
			
			lblNewLabel_8 = new JLabel("");
			lblNewLabel_8.setBackground(new Color(169, 169, 169));
			lblNewLabel_8.setBounds(42, 305, 236, 138);
			lblNewLabel_8.setIcon(new ImageIcon("images/security2.png"));
			frame.add(lblNewLabel_8);
			lblNewLabel_8.addMouseListener(this);
			
			lblNewLabel_9 = new JLabel("");
			lblNewLabel_9.setBackground(new Color(169, 169, 169));
			lblNewLabel_9.setBounds(296, 301, 212, 143);
			lblNewLabel_9.setIcon(new ImageIcon("images/system2.png"));
			frame.add(lblNewLabel_9);
			lblNewLabel_9.addMouseListener(this);
			
			lblNewLabel_10 = new JLabel("");
			lblNewLabel_10.setBackground(new Color(199, 21, 133));
			lblNewLabel_10.setBounds(571, 305, 212, 138);
			lblNewLabel_10.setIcon(new ImageIcon("images/database2.png"));
			frame.add(lblNewLabel_10);
			lblNewLabel_10.addMouseListener(this);
			
			JLabel lblSequrity = new JLabel("Security");
			lblSequrity.setBackground(new Color(192, 192, 192));
			lblSequrity.setBounds(106, 440, 300, 30);
			lblSequrity.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 16));
			frame.add(lblSequrity);
			
			JLabel lblOperatingSystem = new JLabel("System");
			lblOperatingSystem.setBounds(360, 440, 300, 30);
			lblOperatingSystem.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 16));
			frame.add(lblOperatingSystem);
			
			JLabel lblMobileDev = new JLabel("Mobile Dev");
			lblMobileDev.setBackground(new Color(192, 192, 192));
			lblMobileDev.setBounds(627, 264, 102, 30);
			lblMobileDev.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 16));
			frame.add(lblMobileDev);
			
			JLabel lblDataBases = new JLabel("Data Bases");
			lblDataBases.setBounds(643,440, 300, 30);
			lblDataBases.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 16));
			frame.add(lblDataBases);
		
			
	}
	
	private void showPopup(ActionEvent ae)
    {
        
        Component b=(Component)ae.getSource();
        
    
        Point p=b.getLocationOnScreen();
        
       
        this.menu.show(this,0,0);
     
       this. menu.setLocation(p.x,p.y+b.getHeight());
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	 
		if(e.getSource() == lblNewLabel_10)
		{
			Espace s1 = new Espace("Database" ,  email ); 
			 s1.setVisible(true); 
		}
		if(e.getSource()==lblNewLabel_3)
		{
			
	 	Espace s1 = new Espace("Design" , email ); 
			 s1.setVisible(true);  
		}
	 	if(e.getSource()==lblNewLabel_4)
		{Espace s1 = new Espace("Web" , email ); 
		 s1.setVisible(true); }
		if(e.getSource()== lblNewLabel_7 )
		{ 
			Espace s1 = new Espace("Mobile" , email ); 
			 s1.setVisible(true); 
		}
		if(e.getSource()== lblNewLabel_8)
		{Espace s1 = new Espace("Security" , email ); 
		 s1.setVisible(true);  } 
			
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object source =e.getSource();
		if(source==lblNewLabel_3){
			lblNewLabel_3.setIcon(new ImageIcon("images/design1.png"));
		}
		if(source==lblNewLabel_4){
			lblNewLabel_4.setIcon(new ImageIcon("images/web1.png"));
		}
		if(source==lblNewLabel_7){
			lblNewLabel_7.setIcon(new ImageIcon("images/mobile1.png"));
		}
		if(source==lblNewLabel_8){
			lblNewLabel_8.setIcon(new ImageIcon("images/security1.png"));
		}
		if(source==lblNewLabel_9){
			lblNewLabel_9.setIcon(new ImageIcon("images/system1.png"));
		}
		if(source==lblNewLabel_10){
			lblNewLabel_10.setIcon(new ImageIcon("images/database1.png"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source =e.getSource();
		if(source==lblNewLabel_3){
		
			lblNewLabel_3.setIcon(new ImageIcon("images/design2.png"));
		}
		if(source==lblNewLabel_4){
			lblNewLabel_4.setIcon(new ImageIcon("images/web2.png"));
		}
		if(source==lblNewLabel_7){
			lblNewLabel_7.setIcon(new ImageIcon("images/mobile2.png"));
		}
		if(source==lblNewLabel_8){
			lblNewLabel_8.setIcon(new ImageIcon("images/security2.png"));
		}
		if(source==lblNewLabel_9){
			lblNewLabel_9.setIcon(new ImageIcon("images/system2.png"));
		}
		if(source==lblNewLabel_10){
			lblNewLabel_10.setIcon(new ImageIcon("images/database2.png"));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==m2){
			dispose(); 
	    SignIn s = new SignIn() ;
	    s.setVisible(true );
		}
		if(e.getSource()==m1)
		{
		  accountManagment c = new accountManagment(email); 
		  c.setVisible(true );
		}
	}
	}