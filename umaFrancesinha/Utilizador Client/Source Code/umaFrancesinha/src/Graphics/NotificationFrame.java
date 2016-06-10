package Graphics;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;


@SuppressWarnings("serial")
/**
 * NotificationFrame Class.
 */
public class NotificationFrame extends JFrame {
	
	private int notification_frame_width = 240;
	private int notification_frame_height = 100;
	private int notification_time = 5000;
	private int screenWidth = 0;
	private int screenHeight = 0;
	private NotificationFramePanel panel;

	
	/**
	 * Create the frame.
	 */
	public NotificationFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, notification_frame_width, notification_frame_height);
		
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		
		this.setLocation(screenWidth-notification_frame_width,screenHeight);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		getContentPane().setLayout(null);
		
		panel = new NotificationFramePanel();
		getContentPane().add(panel);
	}
	
	
	/**
	 * Show the frame in Notification Style.
	 */
	public void showNotification(int n)
	{
		try
	    {
			panel.setNumberNotifications(n);
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/resources/notification_sound.wav")));
	        clip.start();
	        
	        panel.repaint();
	        
	        
	        this.setVisible(true);
	        
			for(int i=0; i<notification_frame_height+35 ; i+=2)
			{
			
				Thread.sleep(5);
				this.setLocation(screenWidth-notification_frame_width,screenHeight-i);
			
			}
			
			Thread.sleep(notification_time);
			
			for(int i=notification_frame_height+35; i>0 ; i-=2)
			{
				Thread.sleep(5);
				this.setLocation(screenWidth-notification_frame_width,screenHeight-i);
			}
			
			this.setVisible(false);
	        
	        
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace(System.out);
	    }

	}
}
