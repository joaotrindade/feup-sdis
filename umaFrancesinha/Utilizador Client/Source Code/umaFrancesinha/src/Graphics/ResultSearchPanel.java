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
import javax.swing.SwingConstants;

import Classes.Restaurante;
import umaFrancesinha.umaFrancesinha;


@SuppressWarnings("serial")
public class ResultSearchPanel extends JPanel{
	
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
	
	private static Font DaxRegular=null;
	private static Font Magna=null;
	
	@SuppressWarnings("unused")
	private int pages;
	private int page;
	
	private static int x_result;
	private static int y_result;
	private JLabel nextPage_button;
	private JLabel previousPage_button;
	private JLabel pageIndicator;
	private JLabel addnewLabel;
	
	private JLabel _1stResult_page;
	private JLabel _2ndResult_page;
	private JLabel _3rdResult_page;

	
	
	
	/**
	 * Create the panel.
	 */
	public ResultSearchPanel() {
		
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
		
		nextPage_button = new JLabel("nextPage");
		nextPage_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				page++;
				
				if(umaFrancesinha.searchResult.size()>(page*3))
					nextPage_button.setVisible(true);
				else
					nextPage_button.setVisible(false);
				
				previousPage_button.setVisible(true);
				
				_1stResult_page.setVisible(true);
				_2ndResult_page.setVisible(true);
				_3rdResult_page.setVisible(true);
				
				if(umaFrancesinha.searchResult.size()<(page*3))
					_3rdResult_page.setVisible(false);
				if(umaFrancesinha.searchResult.size()<((page*3)-1))
					_2ndResult_page.setVisible(false);
				if(umaFrancesinha.searchResult.size()<((page*3)-2))
					_1stResult_page.setVisible(false);
				
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				nextPage_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		nextPage_button.setBounds(700, 403, 34, 34);
		nextPage_button.setIcon(new ImageIcon(getClass().getResource("/resources/next_arrow.png")));
		add(nextPage_button);
		
		previousPage_button = new JLabel("previousPage");
		previousPage_button.setBounds(668, 403, 34, 34);
		previousPage_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(page>1)
					page--;
				
				if(page==1)
					previousPage_button.setVisible(false);
				
				nextPage_button.setVisible(true);
				
				_1stResult_page.setVisible(true);
				_2ndResult_page.setVisible(true);
				_3rdResult_page.setVisible(true);
				
				if(umaFrancesinha.searchResult.size()<(page*3))
					_3rdResult_page.setVisible(false);
				if(umaFrancesinha.searchResult.size()<((page*3)-1))
					_2ndResult_page.setVisible(false);
				if(umaFrancesinha.searchResult.size()<((page*3)-2))
					_1stResult_page.setVisible(false);
				
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				previousPage_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		previousPage_button.setIcon(new ImageIcon(getClass().getResource("/resources/previous_arrow.png")));
		add(previousPage_button);
		
		pageIndicator = new JLabel("1");
		pageIndicator.setHorizontalAlignment(SwingConstants.CENTER);
		pageIndicator.setBounds(668, 391, 62, 16);
		add(pageIndicator);
		
		_1stResult_page = new JLabel("1stResult");
		_1stResult_page.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int sel = ((page*3)-3);
				umaFrancesinha.selectedRestaurante = new Restaurante();
				umaFrancesinha.selectedRestaurante.setId(umaFrancesinha.searchResult.get(sel).getId());
				umaFrancesinha.selectedRestaurante.setCidade(umaFrancesinha.searchResult.get(sel).getCidade());
				umaFrancesinha.selectedRestaurante.setNome(umaFrancesinha.searchResult.get(sel).getNome());
				umaFrancesinha.selectedRestaurante.setRua(umaFrancesinha.searchResult.get(sel).getRua());
				umaFrancesinha.selectedRestaurante.setRanking(umaFrancesinha.searchResult.get(sel).getRanking());
				
				umaFrancesinha.show_restaurantePanel();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_1stResult_page.setCursor(new Cursor(Cursor.HAND_CURSOR));
				_1stResult_page.setForeground(new Color(253,139,13));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				_1stResult_page.setForeground(Color.DARK_GRAY);
			}
		});
		_1stResult_page.setBounds(217, 63, 226, 25);
		_1stResult_page.setText("KAPANEGRA");
		_1stResult_page.setFont(DaxRegular.deriveFont(Font.PLAIN, 20));
		add(_1stResult_page);
		
		_2ndResult_page = new JLabel("2ndResult");
		_2ndResult_page.setBounds(217, 194, 226, 25);
		_2ndResult_page.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int sel = ((page*3)-2);
				umaFrancesinha.selectedRestaurante = new Restaurante();
				umaFrancesinha.selectedRestaurante.setId(umaFrancesinha.searchResult.get(sel).getId());
				umaFrancesinha.selectedRestaurante.setCidade(umaFrancesinha.searchResult.get(sel).getCidade());
				umaFrancesinha.selectedRestaurante.setNome(umaFrancesinha.searchResult.get(sel).getNome());
				umaFrancesinha.selectedRestaurante.setRua(umaFrancesinha.searchResult.get(sel).getRua());
				umaFrancesinha.selectedRestaurante.setRanking(umaFrancesinha.searchResult.get(sel).getRanking());
				
				umaFrancesinha.show_restaurantePanel();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_2ndResult_page.setCursor(new Cursor(Cursor.HAND_CURSOR));
				_2ndResult_page.setForeground(new Color(253,139,13));
			}
			public void mouseExited(MouseEvent e) {
				_2ndResult_page.setForeground(Color.DARK_GRAY);
			}
		});
		_2ndResult_page.setFont(DaxRegular.deriveFont(Font.PLAIN, 20));
		add(_2ndResult_page);
		
		_3rdResult_page = new JLabel("3rdResult");
		_3rdResult_page.setBounds(217, 326, 226, 25);
		_3rdResult_page.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int sel = ((page*3)-1);
				umaFrancesinha.selectedRestaurante = new Restaurante();
				umaFrancesinha.selectedRestaurante.setId(umaFrancesinha.searchResult.get(sel).getId());
				umaFrancesinha.selectedRestaurante.setCidade(umaFrancesinha.searchResult.get(sel).getCidade());
				umaFrancesinha.selectedRestaurante.setNome(umaFrancesinha.searchResult.get(sel).getNome());
				umaFrancesinha.selectedRestaurante.setRua(umaFrancesinha.searchResult.get(sel).getRua());
				umaFrancesinha.selectedRestaurante.setRanking(umaFrancesinha.searchResult.get(sel).getRanking());
				
				umaFrancesinha.show_restaurantePanel();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_3rdResult_page.setCursor(new Cursor(Cursor.HAND_CURSOR));
				_3rdResult_page.setForeground(new Color(253,139,13));
			}
			public void mouseExited(MouseEvent e) {
				_3rdResult_page.setForeground(Color.DARK_GRAY);
			}
		});
		_3rdResult_page.setFont(DaxRegular.deriveFont(Font.PLAIN, 20));
		add(_3rdResult_page);
		
		addnewLabel = new JLabel("addNew");
		addnewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				umaFrancesinha.show_insertrestaurantePanel();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				addnewLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		addnewLabel.setBounds(690, 48, 34, 34);
		addnewLabel.setIcon(new ImageIcon(getClass().getResource("/resources/add_icon.png")));
		add(addnewLabel);
		
		
		

	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		pageIndicator.setText(Integer.toString(page));
		
		changeTabIcon();
		
		Graphics2D g2 = (Graphics2D) g.create();
	    
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON ); 
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/topbar.png")).getImage(), 0,0,null);

		g2.setFont(Magna);
		g2.setColor(Color.WHITE);
		g2.drawString("umaFrancesinha", 20, 29);
	
		
		g2.setFont(DaxRegular);
		g2.drawString("-  Search Result", 120, 25);
		
		int temp=1;
		x_result= 203;
		for(int i=((page*3)-3);i<(page*3);i++)
		{
			if(i<umaFrancesinha.searchResult.size())
			{
				if(temp==1)
				{
					_1stResult_page.setText(umaFrancesinha.searchResult.get(i).getNome());
					_1stResult_page.setVisible(true);
					y_result = 55;
				}
				else if(temp==2)
				{
					_2ndResult_page.setText(umaFrancesinha.searchResult.get(i).getNome());
					_2ndResult_page.setVisible(true);
					y_result = 186;
				}
				else if(temp==3)
				{
					_3rdResult_page.setText(umaFrancesinha.searchResult.get(i).getNome());
					_3rdResult_page.setVisible(true);
					y_result = 317;
				}
				
				g2.drawImage(new ImageIcon(getClass().getResource("/resources/News_BackEval.png")).getImage(), x_result,y_result,null);
				
				g2.setFont(DaxRegular.deriveFont(Font.PLAIN, 20));
				g2.setColor(Color.DARK_GRAY);
				//g2.drawString(umaFrancesinha.searchResult.get(i).getNome(), x_result+ 14, y_result + 25);
				
				g2.setFont(DaxRegular.deriveFont(Font.PLAIN, 16));
				g2.drawString(umaFrancesinha.searchResult.get(i).getCidade(), x_result + 15, y_result + 50);
				//g2.drawString(umaFrancesinha.lastnews.get(i).getData(), x_result + 260, y_result + 50);
				
				g2.setFont(DaxRegular.deriveFont(Font.PLAIN, 12));
				g2.drawString(umaFrancesinha.searchResult.get(i).getRua(), x_result + 15, y_result + 80);
				
				if(umaFrancesinha.searchResult.get(i).getRanking() == 0)
				{
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19+19,y_result+8,null);
				}
				else if(umaFrancesinha.searchResult.get(i).getRanking() == 1)
				{
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19+19,y_result+8,null);
				}
				else if(umaFrancesinha.searchResult.get(i).getRanking() == 2)
				{
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19+19,y_result+8,null);
				}
				else if(umaFrancesinha.searchResult.get(i).getRanking() == 3)
				{
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19+19,y_result+8,null);
				}
				else if(umaFrancesinha.searchResult.get(i).getRanking() == 4)
				{
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_result+246+19+19+19+19,y_result+8,null);
				}
				else if(umaFrancesinha.searchResult.get(i).getRanking() == 5)
				{
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19+19+19,y_result+8,null);
					g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_result+246+19+19+19+19,y_result+8,null);
				}

			}
			temp++;
		}
			
		//g2.dispose();
	}

	private void AddTabs()
	{
		/** ADD TABS **/
		tabWorldNews = new JLabel();
		tabWorldNews.setLocation(545, 0);
		tabWorldNews.setSize(31, 35);
		tabWorldNews.setIcon(new ImageIcon(getClass().getResource("/resources/tab_worldnews.png")));
		
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
        	  tabWorldNews_change = "WorldNews";
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
	
	public void printResults()
	{
		pages =(int) Math.ceil((float) umaFrancesinha.searchResult.size()/ (float) 3);
		page = 1;
		
		if(umaFrancesinha.searchResult.size()>3)
		{
			nextPage_button.setVisible(true);
		}
		else
		{
			nextPage_button.setVisible(false);
		}
		
		_1stResult_page.setVisible(true);
		_2ndResult_page.setVisible(true);
		_3rdResult_page.setVisible(true);
		
		if(umaFrancesinha.searchResult.size()<(page*3))
			_3rdResult_page.setVisible(false);
		if(umaFrancesinha.searchResult.size()<((page*3)-1))
			_2ndResult_page.setVisible(false);
		if(umaFrancesinha.searchResult.size()<((page*3)-2))
			_1stResult_page.setVisible(false);
		
		previousPage_button.setVisible(false);
		
		
	}
}
