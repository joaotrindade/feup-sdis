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
import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import umaFrancesinha.umaFrancesinha;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class InsertRestaurantePanel extends JPanel {

	private static Font DaxRegular=null;
	private static Font Magna=null;
	private JTextField name_textbox;
	private JTextField cidade_textbox;
	private JTextField rua_textbox;
	private JTextField contacto_textbox;
	private JTextField preco_textbox;
	private JLabel registerButton;
	
			
	/**
	 * Create the panel.
	 */
	public InsertRestaurantePanel() {
		
		
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
		
		
		name_textbox = new JTextField();
		name_textbox.setText("Name");
		name_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		name_textbox.setToolTipText("");
		name_textbox.setBounds(188, 180, 170, 33);
		add(name_textbox);
		name_textbox.setColumns(10);
		name_textbox.setBackground(new Color(24,25,30));
		name_textbox.setBorder(null);
		name_textbox.setForeground(Color.WHITE);
		name_textbox.setFont(DaxRegular);
		name_textbox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				name_textbox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(name_textbox.getText().equalsIgnoreCase(""))
				{
					name_textbox.setText("Name");
				}
			}
		});
		
		
		cidade_textbox = new JTextField();
		cidade_textbox.setText("City");
		cidade_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		cidade_textbox.setToolTipText("");
		cidade_textbox.setBounds(375, 180, 170, 33);
		add(cidade_textbox);
		cidade_textbox.setColumns(10);
		cidade_textbox.setBackground(new Color(24,25,30));
		cidade_textbox.setBorder(null);
		cidade_textbox.setForeground(Color.WHITE);
		cidade_textbox.setFont(DaxRegular);
		cidade_textbox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				cidade_textbox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(new String(cidade_textbox.getText()).equalsIgnoreCase(""))
				{
					cidade_textbox.setText("City");
				}
			}
		});
		
		
		rua_textbox = new JTextField();
		rua_textbox.setText("Street");
		rua_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		rua_textbox.setToolTipText("");
		rua_textbox.setBounds(188, 284, 357, 33);
		add(rua_textbox);
		rua_textbox.setColumns(10);
		rua_textbox.setBackground(new Color(24,25,30));
		rua_textbox.setBorder(null);
		rua_textbox.setForeground(Color.WHITE);
		rua_textbox.setFont(DaxRegular);
		rua_textbox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				rua_textbox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(rua_textbox.getText().equalsIgnoreCase(""))
				{
					rua_textbox.setText("Street");
				}
			}
		});
	
		
		registerButton = new JLabel("Login");
		registerButton.setBounds(265, 330, 222, 45);
		registerButton.setIcon(new ImageIcon(getClass().getResource("/resources/LoginPanel_LoginButton.png")));
		registerButton_mouseActions();
		add(registerButton);
		
		contacto_textbox = new JTextField();
		contacto_textbox.setText("Contact");
		contacto_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		contacto_textbox.setToolTipText("");
		contacto_textbox.setBounds(188, 232, 170, 33);
		add(contacto_textbox);
		contacto_textbox.setColumns(10);
		contacto_textbox.setBackground(new Color(24,25,30));
		contacto_textbox.setBorder(null);
		contacto_textbox.setForeground(Color.WHITE);
		contacto_textbox.setFont(DaxRegular);
		contacto_textbox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				contacto_textbox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(contacto_textbox.getText().equalsIgnoreCase(""))
				{
					contacto_textbox.setText("Contact");
				}
			}
		});
		
		
		preco_textbox = new JTextField();
		preco_textbox.setText("Price");
		preco_textbox.setHorizontalAlignment(SwingConstants.CENTER);
		preco_textbox.setToolTipText("");
		preco_textbox.setBounds(375, 232, 170, 33);
		add(preco_textbox);
		preco_textbox.setColumns(10);
		preco_textbox.setBackground(new Color(24,25,30));
		preco_textbox.setBorder(null);
		preco_textbox.setForeground(Color.WHITE);
		preco_textbox.setFont(DaxRegular);
		preco_textbox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				preco_textbox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(preco_textbox.getText().equalsIgnoreCase(""))
				{
					preco_textbox.setText("Price");
				}
			}
		});
		
		

		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g.create();
	    
		
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );  
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/topbar.png")).getImage(), 0,0,null);
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/insertRestaurante_background.png")).getImage(), 0,35,null);
		
		g2.setFont(Magna);
		g2.setColor(Color.WHITE);
		g2.drawString("umaFrancesinha", 20, 29);
		
		g2.setFont(DaxRegular.deriveFont(Font.PLAIN, 25));
		g2.drawString("Insert Restaurant", 198, 130);
		
		g2.setFont(DaxRegular);
		g2.drawString("-  Insert Restaurant", 120, 25);
		
		
		
		//g2.setColor(Color.BLACK);
		//g2.drawString("Username", 105, 88);
		
	}
	
	private void registerButton_mouseActions()
	{
		registerButton.addMouseListener(new MouseAdapter()  {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				 if(name_textbox.getText().equals("Name")==false && name_textbox.getText().equals("")==false 
						 && cidade_textbox.getText().equals("City")==false && cidade_textbox.getText().equals("")==false
						 && rua_textbox.getText().equals("Street")==false && rua_textbox.getText().equals("")==false
						 && preco_textbox.getText().equals("Price")==false && preco_textbox.getText().equals("")==false
						 && contacto_textbox.getText().equals("Contact")==false && contacto_textbox.getText().equals("")==false
					)
				 {
						 try {
							if(umaFrancesinha.client.inserirRestaurante(name_textbox.getText(),rua_textbox.getText(),cidade_textbox.getText(),"nada.png",Float.parseFloat(preco_textbox.getText()),contacto_textbox.getText()) == true)
							{
								JOptionPane.showMessageDialog(null, "Restaurant Insert Successfully, make a search for it", "Success", JOptionPane.WARNING_MESSAGE); 
								umaFrancesinha.show_searchPanel();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "There was some problems inserting the new restaurant. Try again.", "Error", JOptionPane.ERROR_MESSAGE); 
							}
							
						
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "You have to fill all the parameters", "#Error", JOptionPane.ERROR_MESSAGE); 
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
		  		 if(name_textbox.getText().equals("Name")==false && name_textbox.getText().equals("")==false 
						 && cidade_textbox.getText().equals("City")==false && cidade_textbox.getText().equals("")==false
						 && rua_textbox.getText().equals("Street")==false && rua_textbox.getText().equals("")==false
						 && preco_textbox.getText().equals("Price")==false && preco_textbox.getText().equals("")==false
						 && contacto_textbox.getText().equals("Contact")==false && contacto_textbox.getText().equals("")==false
					)
				 {
						 try {
							if(umaFrancesinha.client.inserirRestaurante(name_textbox.getText(),rua_textbox.getText(),cidade_textbox.getText(),"nada.png",Float.parseFloat(preco_textbox.getText()),contacto_textbox.getText()) == true)
							{
								JOptionPane.showMessageDialog(null, "Restaurant Insert Successfully, make a search for it", "Success", JOptionPane.WARNING_MESSAGE); 
								umaFrancesinha.show_searchPanel();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "There was some problems inserting the new restaurant. Try again.", "Error", JOptionPane.ERROR_MESSAGE); 
							}
							
						
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "You have to fill all the parameters", "#Error", JOptionPane.ERROR_MESSAGE); 
				 }
		  		
				  
			}
		});
	}
}
