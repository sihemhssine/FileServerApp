 package package1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JMenuItem;
import java.awt.Dimension;

public class Espace extends JFrame implements ActionListener, MouseListener {

	private Container frame;
	private static  Socket s;
	static String filename    ; 
	static String  name  ; 
	static String  host ="localhost";
	static  int port = 1987 ; 
   static  String[] aux  ;   JLabel lblNewLabel_1 ; JLabel  lblName ; 
	JSplitPane js ;  JCheckBox j ;
	Container c ; 
	JLabel l1 , l2 , l3 ; 
	String  st  ,  espace_name  , user_name , file_name;  
	JButton  browse ; 
	JPanel p ; 
	static JFileChooser fileChooser ; 
	static  DefaultListModel<String> model = new DefaultListModel<>();
	DefaultListModel<String> model1 = new DefaultListModel<>();
	static JList<String> list = new JList<>( model ); 
	static JList<String> list1 = new JList<>( model ); 
	static DefaultListModel listModel ; 
	static DefaultListModel listModel1 ; 
	File[] listOfFiles ; 
	File dir = new File("/home/sihem/workspaceRMI/ProjectDev");
    Date  datefile;  String pseud ; 	JCheckBox chckbxNewCheckBox ; 
 
	 
	public Espace(String  espace_name   ,  String user_name) {
		frame = this.getContentPane();
		this.setBounds(100, 100, 870,520); 
		frame.setBackground(new Color(192, 192, 192));
		frame.setLayout(null);
		this.user_name = user_name ;
		this.file_name = file_name ;
		this.espace_name   =  espace_name   ; 
		   aux= new String[100]; 
		browse = new JButton( "Browse ") ; 
		browse.setForeground(new Color(0, 128, 128));
		browse.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 28));
		browse.setBounds(10, 251, 125, 35);
		
		listModel = new DefaultListModel();
 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		listOfFiles =  dir.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().charAt(0)!='.' && listOfFiles[i].getName().contains( espace_name) ) {

		 		//date , owner 
			
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
			 	int a = listOfFiles[i].getName().indexOf("*") ;  
			    aux[i] = 	listOfFiles[i].getName()  ;  
				String fileName  = listOfFiles[i].getName().substring(0 ,  a  ) ;   
				listModel.addElement( fileName + " Created "+ datefile    );
			
		  } 
			catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			
		}
			
			list  = new JList(listModel );  }
		list.setBounds(38, 203, 213, -167);
 list.addMouseListener(this);
   
 
 
 
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 883, 61);
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel(espace_name);
		lblNewLabel_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_2.setFont(new Font("Urdu Typesetting", Font.BOLD, 30));
		lblNewLabel_2.setBounds(150, 0, 300, 61);
		
		panel.add(lblNewLabel_2);
		 chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(412, 20, 21, 21);
		panel.add(chckbxNewCheckBox);
		
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
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(21, 89, 810, 369);
		frame.add(splitPane);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		p2.setPreferredSize(new Dimension(300, 500));
		splitPane.setRightComponent(p1);
		 
		
	//	p1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Chat room");
		lblNewLabel_4.setForeground(new Color(0, 139, 139));
		lblNewLabel_4.setFont(new Font("Urdu Typesetting", Font.BOLD, 40));
		//lblNewLabel_4.setBounds(126, 50, 250, 250);
		p1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		//lblNewLabel_5.setBounds(126, 117, 250, 250);
		lblNewLabel_5.setIcon(new ImageIcon("images/mes.png"));
		p1.add(lblNewLabel_5);
		
		splitPane.setLeftComponent(p2);
		JLabel lblNewLabel_3 = new JLabel("");
		p2.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon("images/2.png"));
		lblNewLabel_3.setBounds(334, 35, 256, 193);
		p2.add(  list ) ; 
		p2.add( browse) ; 
		browse.addActionListener(this );
		
		
		
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
	ResultSet rs = st.executeQuery("select * from  membre where email='"+user_name+"'");
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
	
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()== browse)
		{

			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(fileChooser);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();

				try {

					file_name  = fileChooser.getSelectedFile().getName()  ;
					/* int a = name.indexOf(".") ; 
		    filename = name.substring(0 ,  a  ) ; */
					s = new Socket(host, port);
      	sendFile f = new sendFile( selectedFile.getAbsolutePath() , user_name ,  file_name, espace_name,  list , s , chckbxNewCheckBox.isSelected()   );
					
                       f.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
			} 
	 }
	}
	

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		listOfFiles =  dir.listFiles(); 
			
		for(int i=1 ; i<aux.length; i++)
		{System.out.println(aux[i]);
			String s = list.getSelectedValue(); 
			int a = s.indexOf(" ") ; 
			s= s.substring(0 , a); 
			if(aux[i]!= null)
			{
			 	if (aux[i].contains(s)){
			 		try {
 			 File f = new File("/home/sihem/workspaceRMI/ProjectDev/"+ aux[i] ) ;
			 
			 
				  Desktop.getDesktop().open(f );
			} 
			 catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			 	
			 	
			 	
			 	
			 	} }
			/*
		
			System.out.println(s);
			System.out.println(s+aux );
			File f = new File("/home/sihem/new version/"+s  ) ; 
		*/
			
		}
	
			/*try {
			  Desktop.getDesktop().open(f );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  */ 
		
		
		
 
	}

	 

		 
	private static void addPopup(Component component, final JPopupMenu popup) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}