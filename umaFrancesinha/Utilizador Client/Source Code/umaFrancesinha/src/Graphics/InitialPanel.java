package Graphics;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;





import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import umaFrancesinha.umaFrancesinha;


@SuppressWarnings("serial")
public class InitialPanel extends JPanel {
	
	JLabel loginButton;
	String buttonIcon_url = "/resources/initial_Login.png";
	ImageIcon buttonIcon;
	
	private int buttonIcon_change = 1;
	
	
	/**
	 * Create the panel.
	 */
	public InitialPanel() {
		
		this.setLayout(null);
		this.setBounds(0, 0, 750, 450);

		loginButton = new JLabel();
		loginButton.setLocation(0, 405);
		loginButton.setSize(750, 45);

		loginButton.addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
			  if(umaFrancesinha.hasconnection==true)
			  {
				  umaFrancesinha.show_loginPanel();
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null, "No connection to server, please restart the application or check your internet connection.", "Problems", JOptionPane.WARNING_MESSAGE); 
			  }
			  //umaFrancesinha.show_restaurantePanel();
		  }
		  
		  @Override
          public void mouseEntered(MouseEvent e) {
			  buttonIcon_url = "/resources/initial_Login_h.png";
			  loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			  buttonIcon_change = 1;
			  repaint();
          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  buttonIcon_url = "/resources/initial_Login.png";
        	  buttonIcon_change = 1;
        	  repaint();
          }
		});
	
		add(loginButton);
		
	
		
	}
	
	public void paintComponent(Graphics g)
	{
	
		if(buttonIcon_change == 1)
		{
			buttonIcon = new ImageIcon(getClass().getResource(buttonIcon_url));
			loginButton.setIcon(buttonIcon);
			buttonIcon_change = 0;
		}
		
		g.drawImage(new ImageIcon(getClass().getResource("/resources/umaFrancesinha_InitialScreen.png")).getImage(), 0,0,null);	
	}
}
