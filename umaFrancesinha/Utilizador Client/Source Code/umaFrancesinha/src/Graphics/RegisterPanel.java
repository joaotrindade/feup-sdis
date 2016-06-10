package Graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import umaFrancesinha.umaFrancesinha;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RegisterPanel extends JPanel {

	private static Font DaxRegular=null;
	private static Font Magna=null;
	private JTextField userName_textbox;
	private JTextField FullName_textbox;
	private JPasswordField password_textbox;
	private JLabel registerButton;
	private JLabel goBack_label;
	
	private Boolean RegisterAuthorized = false;
	private Boolean RegisterRequest = false;
	
			
	/**
	 * Create the panel.
	 */
	public RegisterPanel() {
		
		
		this.setLayout(null);
		this.setBounds(0, 0, 750, 450);
		this.setOpaque(true);
		
		try {
			
			DaxRegular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Dax-Regular.ttf"));
			DaxRegular = DaxRegular.deriveFont(Font.PLAIN, 18);
			
			
			Magna = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Magna.ttf"));
			Magna = Magna.deriveFont(Font.PLAIN, 22);
			
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		
		userName_textbox = new JTextField();
		userName_textbox.setText("Username");
		userName_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		userName_textbox.setToolTipText("");
		userName_textbox.setBounds(276, 230, 201, 33);
		add(userName_textbox);
		userName_textbox.setColumns(10);
		userName_textbox.setBackground(new Color(24,25,30));
		userName_textbox.setBorder(null);
		userName_textbox.setForeground(Color.WHITE);
		userName_textbox.setFont(DaxRegular);
		userName_textbox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				userName_textbox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(userName_textbox.getText().equalsIgnoreCase(""))
				{
					userName_textbox.setText("Username");
				}
			}
		});
		
		
		password_textbox = new JPasswordField();
		password_textbox.setText("Password");
		password_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		password_textbox.setToolTipText("");
		password_textbox.setBounds(276, 280, 201, 33);
		add(password_textbox);
		password_textbox.setColumns(10);
		password_textbox.setBackground(new Color(24,25,30));
		password_textbox.setBorder(null);
		password_textbox.setForeground(Color.WHITE);
		password_textbox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				password_textbox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(new String(password_textbox.getPassword()).equalsIgnoreCase(""))
				{
					password_textbox.setText("Password");
				}
			}
		});
		
		
		FullName_textbox = new JTextField();
		FullName_textbox.setText("Full Name");
		FullName_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		FullName_textbox.setToolTipText("");
		FullName_textbox.setBounds(276, 180, 201, 33);
		add(FullName_textbox);
		FullName_textbox.setColumns(10);
		FullName_textbox.setBackground(new Color(24,25,30));
		FullName_textbox.setBorder(null);
		FullName_textbox.setForeground(Color.WHITE);
		FullName_textbox.setFont(DaxRegular);
		FullName_textbox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				FullName_textbox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(FullName_textbox.getText().equalsIgnoreCase(""))
				{
					FullName_textbox.setText("Full Name");
				}
			}
		});
		
		registerButton = new JLabel("Login");
		registerButton.setBounds(265, 330, 222, 45);
		registerButton.setIcon(new ImageIcon(getClass().getResource("/resources/LoginPanel_LoginButton.png")));
		registerButton_mouseActions();
		add(registerButton);
		
		goBack_label = new JLabel("< Go Back");
		goBack_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				umaFrancesinha.show_loginPanel();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				goBack_label.setCursor(new Cursor(Cursor.HAND_CURSOR));
				goBack_label.setForeground(Color.WHITE);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goBack_label.setForeground(Color.DARK_GRAY);
			}
		});
		goBack_label.setBounds(671, 10, 67, 16);
		//goBack_label.setFont(DaxRegular);
		add(goBack_label);
		
		

		
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g.create();
	    
		
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );  
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/topbar.png")).getImage(), 0,0,null);
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/login_background.png")).getImage(), 0,35,null);
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/BackLoginForm.png")).getImage(), 246,66,null);
		
		g2.setFont(Magna);
		g2.setColor(Color.WHITE);
		g2.drawString("umaFrancesinha", 20, 29);
		
		g2.setFont(DaxRegular.deriveFont(Font.PLAIN, 25));
		g2.drawString("Sign Up", 276, 130);
		
		g2.setFont(DaxRegular);
		g2.drawString("-  Sign Up", 120, 25);
		
		
		
		//g2.setColor(Color.BLACK);
		//g2.drawString("Username", 105, 88);
		
	}
	
	private void registerButton_mouseActions()
	{
		registerButton.addMouseListener(new MouseAdapter()  {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  if(RegisterRequest == false)
				  {
					  RegisterRequest = true;
					  try {
						  RegisterAuthorized = umaFrancesinha.client.inserirUtilizador(FullName_textbox.getText(),userName_textbox.getText(), new String(password_textbox.getPassword()));
						  if (RegisterAuthorized == true)
						  {
							  umaFrancesinha.show_loginPanel();
						  }
						  RegisterRequest = false;
					  } catch (IOException f) {
						f.printStackTrace();
					  }
				  }
			  }
			  
			  @Override
	          public void mouseEntered(MouseEvent e) {
				  registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				  repaint();
	          }

	          @Override
	          public void mouseExited(MouseEvent e) {
	        	  repaint();
	          }
		});
		
		registerButton.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
		  	public void mouseMoved(MouseEvent e) {
			 
          	}
			
		  	@Override
		  	public void mouseDragged(MouseEvent e) {
		  		if(RegisterRequest == false)
				  {
					  RegisterRequest = true;
					  try {
						  RegisterAuthorized = umaFrancesinha.client.inserirUtilizador(FullName_textbox.getText(),userName_textbox.getText(), new String(password_textbox.getPassword()));
						  if (RegisterAuthorized == true)
						  {
							  umaFrancesinha.show_loginPanel();
						  }
						  RegisterRequest = false;
					  } catch (IOException f) {
						f.printStackTrace();
					  }
				  }
			}
		});
	}
}
