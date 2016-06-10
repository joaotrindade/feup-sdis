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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import umaFrancesinha.umaFrancesinha;

import javax.swing.SwingConstants;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;



@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	private static Font DaxRegular=null;
	private static Font Magna=null;
	private JTextField userName_textbox;
	private JPasswordField password_textbox;
	private JLabel loginButton;
	private JLabel lblRegistese;
	
	private Boolean LoginAuthorized = false;
	private Boolean LoginRequest = false;
	
			
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		
		
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
		userName_textbox.setText("Username");
		userName_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		userName_textbox.setToolTipText("");
		userName_textbox.setBounds(276, 180, 201, 33);
		add(userName_textbox);
		userName_textbox.setColumns(10);
		userName_textbox.setBackground(new Color(24,25,30));
		userName_textbox.setBorder(null);
		userName_textbox.setForeground(Color.WHITE);
		userName_textbox.setFont(DaxRegular);
		
		
		password_textbox = new JPasswordField();
		password_textbox.setText("Password");
		password_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		password_textbox.setToolTipText("");
		password_textbox.setBounds(276, 250, 201, 33);
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
					password_textbox.setText("Username");
				}
			}
		});

		
		loginButton = new JLabel("Login");
		loginButton.setBounds(265, 330, 222, 45);
		loginButton.setIcon(new ImageIcon(getClass().getResource("/resources/LoginPanel_LoginButton.png")));
		loginButton_mouseActions();
		add(loginButton);
		
		lblRegistese = new JLabel("Registe-se");
		lblRegistese.setFont(DaxRegular.deriveFont(Font.PLAIN, 14));
		lblRegistese.setBounds(346, 381, 64, 22);
		lblRegistese.addMouseListener(new MouseAdapter()  {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  umaFrancesinha.show_registerPanel();
			  }
			  
			  @Override
	          public void mouseEntered(MouseEvent e) {
				  lblRegistese.setCursor(new Cursor(Cursor.HAND_CURSOR));
				  lblRegistese.setForeground(Color.WHITE);
				  repaint();
			  }
			  
	          @Override
	          public void mouseExited(MouseEvent e) {
	        	  lblRegistese.setForeground(new Color(50,50,50));
	        	  repaint();
	          }
		});
		add(lblRegistese);

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
		g2.drawString("Login", 276, 130);
		
		g2.setFont(DaxRegular);
		g2.drawString("-  Login", 120, 25);
		
		
		
		//g2.setColor(Color.BLACK);
		//g2.drawString("Username", 105, 88);
		
	}
	
	private void loginButton_mouseActions()
	{
		loginButton.addMouseListener(new MouseAdapter()  {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  
				  if(LoginRequest == false)
				  {
					  if(userName_textbox.getText().equals("") || new String(password_textbox.getPassword()).equals(""))
					  {
						  JOptionPane.showMessageDialog(null, "You need to fill all the fields", "Problems", JOptionPane.WARNING_MESSAGE);
					  }
					  else
					  {
						  LoginRequest = true;
						  try {
							  LoginAuthorized = umaFrancesinha.client.login(userName_textbox.getText(), new String(password_textbox.getPassword()));
							  if (LoginAuthorized == true)
							  {
								  umaFrancesinha.show_worldnewsPanel();
							  }
							  else
							  {
								  JOptionPane.showMessageDialog(null, "#LoginProblem: Username and Password may not match", "#Error", JOptionPane.ERROR_MESSAGE); 
							  }
							  LoginRequest = false;
							 
						  } catch (IOException f) {
							f.printStackTrace();
						  }
					  }
				  }
			  }
			  
			  @Override
	          public void mouseEntered(MouseEvent e) {
				  loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				  repaint();
	          }

	          @Override
	          public void mouseExited(MouseEvent e) {
	        	  repaint();
	          }
		});
		
		loginButton.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
		  	public void mouseMoved(MouseEvent e) {
			 
          	}

		  	@Override
		  	public void mouseDragged(MouseEvent e) {
		  		if(LoginRequest == false)
				  {
					  LoginRequest = true;
					  try {
						  LoginAuthorized = umaFrancesinha.client.login(userName_textbox.getText(), new String(password_textbox.getPassword()));
						  if (LoginAuthorized == true)
						  {
							  umaFrancesinha.show_worldnewsPanel();
						  }
						  else
						  {
							  JOptionPane.showMessageDialog(null, "#LoginProblem: Username and Password may not match", "#Error", JOptionPane.ERROR_MESSAGE); 
						  }
						  LoginRequest = false;
					  } catch (IOException f) {
						f.printStackTrace();
					  }
				  }
			}
		});
	}
}
