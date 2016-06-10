package Graphics;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class NotificationFramePanel extends JPanel {

	private JLabel nNotifications;
	/**
	 * Create the panel.
	 */
	public NotificationFramePanel()  {
		//super();
		this.setBounds(0, 0, 240, 100);
		this.setOpaque(false);
		setLayout(null);
		
		nNotifications = new JLabel("3 new notifications");
		nNotifications.setBounds(35, 50, 201, 14);
		add(nNotifications);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(new ImageIcon(getClass().getResource("/resources/notificationframe_back.png")).getImage(), 0,0,null);	
	}
	
	public void setNumberNotifications(int n)
	{
		nNotifications.setText("You have " + n + " new notifications");
	}

}
