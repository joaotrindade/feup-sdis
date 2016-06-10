package Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;






import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import umaFrancesinha.umaFrancesinha;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class SettingsPanel extends JPanel {
	
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	
	/**
	 * Create the panel.
	 */
	public SettingsPanel() {
		
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

		g2.setFont(Magna);
		g2.setColor(Color.WHITE);
		g2.drawString("umaFrancesinha", 20, 29);
		
		g2.setFont(DaxRegular);
		g2.drawString("-  Settings", 120, 25);
		
		g2.dispose();
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
		tabSettings.setIcon(new ImageIcon(getClass().getResource("/resources/tab_settings_h.png")));
		
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
        	  tabSettings_change = "Settings_H";
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
		
		final JCheckBox chckbxNotificationWarnings = new JCheckBox("Notification Warnings");
		chckbxNotificationWarnings.setSelected(true);
		chckbxNotificationWarnings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNotificationWarnings.isSelected())
				{
					umaFrancesinha.wantNotifications = true;
				}
				else
				{
					umaFrancesinha.wantNotifications = false;
				}
			}
		});
		chckbxNotificationWarnings.setBounds(30, 69, 185, 25);
		add(chckbxNotificationWarnings);
		
		textField = new JTextField();
		textField.setBounds(30, 150, 185, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(292, 150, 185, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNovoNome = new JLabel("Alterar Nome Completo");
		lblNovoNome.setBounds(30, 124, 185, 16);
		add(lblNovoNome);
		
		JLabel lblNewLabel = new JLabel("Alterar Password");
		lblNewLabel.setBounds(292, 124, 185, 16);
		add(lblNewLabel);
		
		JButton btnSubmeter = new JButton("Submeter Altera\u00E7\u00F5es");
		btnSubmeter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().equals("") && textField_1.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "No Data to Change", "Problems", JOptionPane.WARNING_MESSAGE); 
					}
					else
					{
						if(umaFrancesinha.client.alterarDadosUser(umaFrancesinha.idUser, textField.getText(), textField_1.getText())==true)
						{
							JOptionPane.showMessageDialog(null, "Data changed Successfully", "Yey!", JOptionPane.WARNING_MESSAGE); 
						}
						else
						{
							JOptionPane.showMessageDialog(null, "There was some problems changing yourd data", "Problems", JOptionPane.WARNING_MESSAGE); 
						}
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSubmeter.setBounds(526, 149, 173, 25);
		add(btnSubmeter);
		
		JLabel lblNotaNa = new JLabel("Nota : Na altera\u00E7\u00E3o de campos, escreva s\u00F3 aqueles que quiser alterar. Campos a vazio n\u00E3o ser\u00E3o alterados.");
		lblNotaNa.setBounds(30, 185, 669, 20);
		add(lblNotaNa);
		
		textField_2 = new JTextField();
		textField_2.setBounds(30, 269, 185, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton = new JButton("Gravar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try{
				umaFrancesinha.notificationTime = Integer.parseInt(textField_2.getText());
				}catch(NumberFormatException ee){
					JOptionPane.showMessageDialog(null, "Needs to be an Integer Value", "Problems", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(244, 268, 97, 25);
		add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Alterar Tempo (Minutos) Notifica\u00E7\u00F5es");
		lblNewLabel_1.setBounds(30, 240, 311, 16);
		add(lblNewLabel_1);
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
}