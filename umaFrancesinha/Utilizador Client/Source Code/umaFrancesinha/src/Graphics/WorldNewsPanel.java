package Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;






import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import umaFrancesinha.umaFrancesinha;


@SuppressWarnings("serial")
public class WorldNewsPanel extends JPanel {
	
	private JLabel tabSearch;
	private JLabel tabWorldNews;
	private JLabel tabNotifications;
	private JLabel tabSettings;
	private JLabel tabInfo;
	
	private String tabSearch_change;
	private String tabWorldNews_change;
	private String tabNotifications_change;
	private String tabSettings_change;
	private String tabInfo_change;
	
	//private int buttonIcon_change = 1;
	private static Font DaxRegular=null;
	private static Font Magna=null;
	
	private static int x_news;
	private static int y_news;
	
	private static boolean reloadNews;
	
	/**
	 * Create the panel.
	 */
	public WorldNewsPanel() {
		
		this.setLayout(null);
		this.setBounds(0, 0, 750, 450);
		AddTabs();

		try {
			
			DaxRegular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Dax-Regular.ttf"));
			DaxRegular = DaxRegular.deriveFont(Font.PLAIN, 17);
			
			Magna = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Magna.ttf"));
			Magna = Magna.deriveFont(Font.PLAIN, 22);
			
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		changeTabIcon();
	
		Graphics2D g2 = (Graphics2D) g.create();
	    
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON ); 
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/topbar.png")).getImage(), 0,0,null);
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/babyBlue_background.png")).getImage(), 0,35,null);

		g2.setFont(Magna);
		g2.setColor(Color.WHITE);
		g2.drawString("umaFrancesinha", 20, 29);
		
		g2.setFont(DaxRegular);
		g2.drawString("-  News", 120, 25);
		
		if(reloadNews)
		{
			repaintNews();
			reloadNews=false;
		}
		for(int i=0;i<umaFrancesinha.lastnews.size();i++)
		{
			if(i%2==0) x_news = 20;
			else x_news = 385;
			
			if(i==0 || i==1) y_news = 55;
			else if(i==2 || i==3) y_news = 186;
			else y_news = 317;
			
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/News_BackEval.png")).getImage(), x_news,y_news,null);
			
			g2.setFont(DaxRegular.deriveFont(Font.PLAIN, 20));
			g2.setColor(Color.DARK_GRAY);
			g2.drawString(umaFrancesinha.lastnews.get(i).getNome_restaurante(), x_news + 15, y_news + 25);
			
			g2.setFont(DaxRegular.deriveFont(Font.PLAIN, 16));
		
			if(umaFrancesinha.lastnews.get(i).getRanking_user() > (double) 2.5)
				g2.setColor(Color.RED);
			g2.drawString(umaFrancesinha.lastnews.get(i).getNome_utilizador(), x_news + 15, y_news + 50);
			
			
			g2.setColor(Color.DARK_GRAY);
			g2.drawString(umaFrancesinha.lastnews.get(i).getData(), x_news + 260, y_news + 50);
			
			g2.setFont(DaxRegular.deriveFont(Font.PLAIN, 12));
			g2.drawString(umaFrancesinha.lastnews.get(i).getComentario(), x_news + 15, y_news + 80);
			
			if(umaFrancesinha.lastnews.get(i).getPontuacao() == 0)
			{
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19+19,y_news+8,null);
			}
			else if(umaFrancesinha.lastnews.get(i).getPontuacao() == 1)
			{
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19+19,y_news+8,null);
			}
			else if(umaFrancesinha.lastnews.get(i).getPontuacao() == 2)
			{
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19+19,y_news+8,null);
			}
			else if(umaFrancesinha.lastnews.get(i).getPontuacao() == 3)
			{
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19+19,y_news+8,null);
			}
			else if(umaFrancesinha.lastnews.get(i).getPontuacao() == 4)
			{
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_news+246+19+19+19+19,y_news+8,null);
			}
			else if(umaFrancesinha.lastnews.get(i).getPontuacao() == 5)
			{
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19+19+19,y_news+8,null);
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_news+246+19+19+19+19,y_news+8,null);
			}
			
		}
		
		g2.dispose();
	}
	
	public void repaintNews()
	{
		repaint();
		try {
			umaFrancesinha.client.getWorldNews();
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	private void AddTabs()
	{
		/** ADD TABS **/
		tabWorldNews = new JLabel();
		tabWorldNews.setLocation(545, 0);
		tabWorldNews.setSize(31, 35);
		tabWorldNews.setIcon(new ImageIcon(getClass().getResource("/resources/tab_worldnews_h.png")));
		
		tabSearch = new JLabel();
		tabSearch.setLocation(586, 0);
		tabSearch.setSize(31, 35);
		tabSearch.setIcon(new ImageIcon(getClass().getResource("/resources/tab_search.png")));
		
		tabNotifications = new JLabel();
		tabNotifications.setLocation(627, 0);
		tabNotifications.setSize(31, 35);
		tabNotifications.setIcon(new ImageIcon(getClass().getResource("/resources/tab_notifications.png")));
		
		tabSettings = new JLabel();
		tabSettings.setLocation(668, 0);
		tabSettings.setSize(31, 35);
		tabSettings.setIcon(new ImageIcon(getClass().getResource("/resources/tab_settings.png")));
		
		tabInfo = new JLabel();
		tabInfo.setLocation(709, 0);
		tabInfo.setSize(31, 35);
		tabInfo.setIcon(new ImageIcon(getClass().getResource("/resources/tab_info.png")));
		/** END ADD TABS **/ 
		
		
		
		/** TAB WorldNews MOUSE LISTEN FUNCTION **/
		tabWorldNews.addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
			  umaFrancesinha.show_worldnewsPanel();
		  }
		  
		  @Override
          public void mouseEntered(MouseEvent e) {
			  tabWorldNews_change = "WorldNews_H";
			  tabWorldNews.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			  repaint();
          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  tabWorldNews_change = "WorldNews_H";
        	  repaint();
          }
		});
		/** END TAB WorldNews MOUSE LISTEN FUNCTION **/
		
		/** TAB SEARCH MOUSE LISTEN FUNCTION **/
		tabSearch.addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
			  umaFrancesinha.show_searchPanel();
		  }
		  
		  @Override
          public void mouseEntered(MouseEvent e) {
			  tabSearch_change = "Search_H";
			  tabSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
			  repaint();
          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  tabSearch_change = "Search";
        	  repaint();
          }
		});
		/** END TAB SEARCH MOUSE LISTEN FUNCTION **/
		
		/** TAB Notifications MOUSE LISTEN FUNCTION **/
		tabNotifications.addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
			  umaFrancesinha.show_notificationsPanel();
		  }
		  
		  @Override
          public void mouseEntered(MouseEvent e) {
			  tabNotifications_change = "Notifications_H";
			  tabNotifications.setCursor(new Cursor(Cursor.HAND_CURSOR));
			  repaint();
          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  tabNotifications_change = "Notifications";
        	  repaint();
          }
		});
		/** END TAB Notifications MOUSE LISTEN FUNCTION **/
		
		/** TAB Settings MOUSE LISTEN FUNCTION **/
		tabSettings.addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
			  umaFrancesinha.show_settingsPanel();
		  }
		  
		  @Override
          public void mouseEntered(MouseEvent e) {
			  tabSettings_change = "Settings_H";
			  tabSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
			  repaint();
          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  tabSettings_change = "Settings";
        	  repaint();
          }
		});
		/** END TAB Settings MOUSE LISTEN FUNCTION **/
		
		/** TAB Info MOUSE LISTEN FUNCTION **/
		tabInfo.addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
			  umaFrancesinha.show_infoPanel();
		  }
		  
		  @Override
          public void mouseEntered(MouseEvent e) {
			  tabInfo_change = "Info_H";
			  tabInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			  repaint();
          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  tabInfo_change = "Info";
        	  repaint();
          }
		});
		/** END TAB Info MOUSE LISTEN FUNCTION **/
		
		add(tabSearch);
		add(tabWorldNews);
		add(tabNotifications);
		add(tabSettings);
		add(tabInfo);
	}
	
	private void changeTabIcon() {
		
		if(tabWorldNews_change == "WorldNews")
		{
			tabWorldNews.setIcon(new ImageIcon(getClass().getResource("/resources/tab_worldnews.png")));
		}
		else if(tabWorldNews_change == "WorldNews_H")
		{
			tabWorldNews.setIcon(new ImageIcon(getClass().getResource("/resources/tab_worldnews_h.png")));
		}
		
		if(tabSearch_change == "Search")
		{
			tabSearch.setIcon(new ImageIcon(getClass().getResource("/resources/tab_search.png")));
		}
		else if(tabSearch_change == "Search_H")
		{
			tabSearch.setIcon(new ImageIcon(getClass().getResource("/resources/tab_search_h.png")));
		}
		
		if(tabNotifications_change == "Notifications")
		{
			tabNotifications.setIcon(new ImageIcon(getClass().getResource("/resources/tab_notifications.png")));
		}
		else if(tabNotifications_change == "Notifications_H")
		{
			tabNotifications.setIcon(new ImageIcon(getClass().getResource("/resources/tab_notifications_h.png")));
		}
		
		if(tabSettings_change == "Settings")
		{
			tabSettings.setIcon(new ImageIcon(getClass().getResource("/resources/tab_settings.png")));
		}
		else if(tabSettings_change == "Settings_H")
		{
			tabSettings.setIcon(new ImageIcon(getClass().getResource("/resources/tab_settings_h.png")));
		}
		
		if(tabInfo_change == "Info")
		{
			tabInfo.setIcon(new ImageIcon(getClass().getResource("/resources/tab_info.png")));
		}
		else if(tabInfo_change == "Info_H")
		{
			tabInfo.setIcon(new ImageIcon(getClass().getResource("/resources/tab_info_h.png")));
		}
		
		tabWorldNews_change=null;
		tabInfo_change=null;
		tabSettings_change=null;
		tabNotifications_change=null;
		tabSearch_change=null;
		
	}

	public static boolean getReloadNews() {
		return reloadNews;
	}

	public static void setReloadNews(boolean _reloadNews) {
		reloadNews = _reloadNews;
	}
}