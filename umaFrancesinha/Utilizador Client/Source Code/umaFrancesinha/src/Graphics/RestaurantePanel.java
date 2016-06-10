package Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;






import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import umaFrancesinha.umaFrancesinha;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class RestaurantePanel extends JPanel {
	
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
	
	private String nomeuser1;
	private String nomeuser2;
	
	private String comentario1;
	private String comentario2;
	
	private JLabel rankLabel;
	
	private int navals;
	
	//private int buttonIcon_change = 1;
	private static Font DaxRegular=null;
	private static Font Magna=null;
	private JLabel locationLabel;
	private JLabel priceLabel;
	private JLabel nratingsLabel;
	private JLabel nomeLabel;
	
	private int x_stars = 92;
	private int y_stars = 247;
	
	private int nstars = 0;
	private JTextField commentField;
	private JLabel user1nameLabel;
	private JLabel user1commentLabel;
	private int user1avalid;
	private JLabel user2nameLabel;
	private JLabel user2commentLabel;
	private int user2avalid;
	private JLabel lblInserir;
	
	private JLabel _1star;
	private JLabel _2star;
	private JLabel _3star;
	private JLabel _4star;
	private JLabel _5star;
	
	private int selectedstars = 0;
	
	private JFileChooser selectfoto;
	private JLabel uploadFoto;
	private String fotopath;
	private String uploadFoto_change;
	private JLabel user1photo;
	private JLabel user2photo;
	
	private double user1rank;
	private double user2rank;
	
	private String photo_extension;
	private String new_file_name;
	
	private ImageIcon imagem_get;
	/**
	 * Create the panel.
	 */
	public RestaurantePanel() {
		
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
		
		rankLabel = new JLabel("0.0");
		rankLabel.setHorizontalAlignment(SwingConstants.LEFT);
		rankLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 30));
		rankLabel.setBounds(41, 245, 46, 23);
		rankLabel.setForeground(Color.DARK_GRAY);
		add(rankLabel);
		
		locationLabel = new JLabel("Rua X da Y , Porto");
		locationLabel.setBounds(66, 331, 264, 20);
		locationLabel.setHorizontalAlignment(SwingConstants.LEFT);
		locationLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 18));
		locationLabel.setForeground(new Color(169,169,169));
		add(locationLabel);
		
		priceLabel = new JLabel(" 7.50 Euros");
		priceLabel.setBounds(358, 329, 114, 21);
		priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		priceLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 24));
		priceLabel.setForeground(Color.DARK_GRAY);
		add(priceLabel);
		
		nratingsLabel = new JLabel("125 ratings");
		nratingsLabel.setBounds(214, 252, 85, 15);
		nratingsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nratingsLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 14));
		nratingsLabel.setForeground(new Color(169,169,169));
		add(nratingsLabel);
		
		nomeLabel = new JLabel("NOMERESTAURANTE");
		nomeLabel.setBounds(41, 50, 502, 41);
		nomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nomeLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 36));
		nomeLabel.setForeground(new Color(169,169,169));
		add(nomeLabel);
		
		commentField = new JTextField();
		commentField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(commentField.getText().length()>100)
				{
					commentField.setForeground(Color.RED);
				}
				else
				{
					commentField.setForeground(Color.DARK_GRAY);
				}
			}
		});
		commentField.setBounds(41, 405, 359, 20);
		add(commentField);
		commentField.setColumns(10);
		
		user1nameLabel = new JLabel("User1");
		user1nameLabel.setBounds(517, 260, 195, 16);
		user1nameLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 16));
		user1nameLabel.setForeground(new Color(169,169,169));
		add(user1nameLabel);
		
		user1commentLabel = new JLabel("User1");
		user1commentLabel.setBounds(517, 280, 206, 16);
		user1commentLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 14));
		user1commentLabel.setForeground(new Color(169,169,169));
		add(user1commentLabel);
		
		user2nameLabel = new JLabel("User1");
		user2nameLabel.setBounds(517, 330, 195, 16);
		user2nameLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 16));
		user2nameLabel.setForeground(new Color(169,169,169));
		add(user2nameLabel);
		
		user2commentLabel = new JLabel("User1");
		user2commentLabel.setBounds(517, 350, 206, 16);
		user2commentLabel.setForeground(new Color(169,169,169));
		user2commentLabel.setFont(DaxRegular.deriveFont(Font.PLAIN, 14));
		add(user2commentLabel);
		
		lblInserir = new JLabel("Inserir");
		lblInserir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				Calendar now = Calendar.getInstance();
				String hms = now.get(Calendar.HOUR_OF_DAY)+ "_" + now.get(Calendar.MINUTE) + "_" + now.get(Calendar.SECOND) + "_" + now.get(Calendar.MILLISECOND);
				String datefoto = now.get(Calendar.DAY_OF_MONTH)+ "-" + (now.get(Calendar.MONTH)+1) + "-" + now.get(Calendar.YEAR);
				String serverpath = null;
				
				if(photo_extension!=null)
				{
					new_file_name = umaFrancesinha.idUser + "_" + umaFrancesinha.selectedRestaurante.getId() + "_" + datefoto + "_" + hms + "." + photo_extension;
					serverpath = "/images/"+new_file_name;
				}
				
				if(selectedstars>0)
				{
					if(commentField.getText().length()<=100 && commentField.getText().length()>0)
					{
						try {
							if(umaFrancesinha.client.inserirAvaliacao(umaFrancesinha.selectedRestaurante.getId(), umaFrancesinha.idUser, selectedstars, new_file_name, commentField.getText(), dateFormat.format(date),hms)==true)
							{
								if(serverpath!=null)
								{
									umaFrancesinha.client.sendPic(new_file_name,fotopath);
								}
								umaFrancesinha.show_restaurantePanel();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "There was some problems uploading your score", "Problems", JOptionPane.WARNING_MESSAGE); 
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You need to write a comment with more than 0 chars and less then 100", "Missing Field", JOptionPane.WARNING_MESSAGE); 
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You need to give 1 to 5 stars", "Missing Field", JOptionPane.WARNING_MESSAGE); 
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblInserir.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			}
		});
		lblInserir.setBounds(400, 405, 69, 19);
		lblInserir.setIcon(new ImageIcon(getClass().getResource("/resources/submit_eval.png")));
		add(lblInserir);
		
		_1star = new JLabel("0star");
		_1star.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedstars = 1;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_1star.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			}
		});
		_1star.setBounds(268, 150, 32, 32);
		_1star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		add(_1star);
		
		_2star = new JLabel("0star");
		_2star.setBounds(312, 150, 32, 32);
		_2star.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedstars = 2;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_2star.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			}
		});
		_2star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		add(_2star);
		
		_3star = new JLabel("0star");
		_3star.setBounds(355, 150, 32, 32);
		_3star.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedstars = 3;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_3star.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			}
		});
		_3star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		add(_3star);
		
		_4star = new JLabel("0star");
		_4star.setBounds(399, 150, 32, 32);
		_4star.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedstars = 4;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_4star.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			}
		});
		_4star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		add(_4star);
		
		_5star = new JLabel("0star");
		_5star.setBounds(443, 150, 32, 32);
		_5star.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedstars = 5;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				_5star.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			}
		});
		_5star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		add(_5star);
		
		selectfoto = new JFileChooser();
		selectfoto.setAcceptAllFileFilterUsed(false);
		FileFilter jpgFilter = new FileNameExtensionFilter("JPEG FILES","jpg","jpeg");
		FileFilter pngFilter = new FileNameExtensionFilter("PNG FILES", "png");
		selectfoto.setFileFilter(jpgFilter);
		selectfoto.setFileFilter(pngFilter);
		
		uploadFoto = new JLabel("Foto");
		uploadFoto.setIcon(new ImageIcon(getClass().getResource("/resources/camera_icon.png")));
		uploadFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int option = selectfoto.showOpenDialog(RestaurantePanel.this);
				if(option == JFileChooser.APPROVE_OPTION)
				{
					fotopath = selectfoto.getSelectedFile().toString();
					String filename =  selectfoto.getSelectedFile().getName();
					String sides[] = filename.split("\\.");

					photo_extension = sides[sides.length-1];

					JOptionPane.showMessageDialog(null, filename + " successfully selected", "Foto", JOptionPane.DEFAULT_OPTION); 
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The file open operation was cancelled", "Upload File", JOptionPane.WARNING_MESSAGE); 
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				uploadFoto_change = "UploadFoto_H";
				uploadFoto.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				uploadFoto_change = "UploadFoto";
				repaint();

			}
		});
		uploadFoto.setBounds(395, 242, 24, 24);
		add(uploadFoto);
		
		user1photo = new JLabel("New label");
		user1photo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					umaFrancesinha.client.receivePic(user1avalid);
					
					if(imagem_get.getIconWidth()>1 && imagem_get.getIconHeight()>1)
					{
						JFrame newFrame = new JFrame();
						newFrame.setBounds(umaFrancesinha.wscreen/2 - 600/2,umaFrancesinha.hscreen/2 - 500/2, 600, 500);
						newFrame.setVisible(true);
						JPanel newPanel = new JPanel();
						newPanel.setBounds(0,0,582,453);
						newPanel.setLayout(null);
						newFrame.getContentPane().add(newPanel);
						JLabel newLabel = new JLabel();
						
						
						Dimension newDimension = getScaledDimension(new Dimension(imagem_get.getIconWidth(),imagem_get.getIconHeight()),new Dimension(582,453));
						newLabel.setIcon(new ImageIcon(imagem_get.getImage().getScaledInstance(newDimension.width, newDimension.height, Image.SCALE_AREA_AVERAGING)));
						newLabel.setBounds(582/2 - newDimension.width/2,453/2 - newDimension.height/2,newDimension.width,newDimension.height);
						newPanel.add(newLabel);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No imagem in this evaluation", "Warning", JOptionPane.WARNING_MESSAGE); 
					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				user1photo.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			}
		});
		user1photo.setBounds(720, 260, 16, 16);
		user1photo.setIcon(new ImageIcon(getClass().getResource("/resources/portrait_icon.png")));
		add(user1photo);
		
		user2photo = new JLabel("New label");
		user2photo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					umaFrancesinha.client.receivePic(user2avalid);
					
					if(imagem_get.getIconWidth()>1 && imagem_get.getIconHeight()>1)
					{
						JFrame newFrame = new JFrame();
						newFrame.setBounds(umaFrancesinha.wscreen/2 - 600/2,umaFrancesinha.hscreen/2 - 500/2, 600, 500);
						newFrame.setVisible(true);
						JPanel newPanel = new JPanel();
						newPanel.setBounds(0,0,582,453);
						newPanel.setLayout(null);
						newFrame.getContentPane().add(newPanel);
						JLabel newLabel = new JLabel();
						
						
						Dimension newDimension = getScaledDimension(new Dimension(imagem_get.getIconWidth(),imagem_get.getIconHeight()),new Dimension(582,453));
						newLabel.setIcon(new ImageIcon(imagem_get.getImage().getScaledInstance(newDimension.width, newDimension.height, Image.SCALE_AREA_AVERAGING)));
						newLabel.setBounds(582/2 - newDimension.width/2,453/2 - newDimension.height/2,newDimension.width,newDimension.height);
						newPanel.add(newLabel);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No imagem in this evaluation", "Warning", JOptionPane.WARNING_MESSAGE); 
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				user2photo.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
			}
		});
		user2photo.setBounds(720, 330, 16, 16);
		user2photo.setIcon(new ImageIcon(getClass().getResource("/resources/portrait_icon.png")));
		add(user2photo);
		
		
		

	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		changeTabIcon();
		
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON ); 
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/topbar.png")).getImage(), 0,0,null);
		g2.drawImage(new ImageIcon(getClass().getResource("/resources/restaurante_background.png")).getImage(), 0,35,null);

		g2.setFont(Magna);
		g2.setColor(Color.WHITE);
		g2.drawString("umaFrancesinha", 20, 29);
		
		g2.setFont(DaxRegular);
		if(umaFrancesinha.selectedRestaurante.getNome() != null)
			g2.drawString("- " + umaFrancesinha.selectedRestaurante.getNome(), 120, 25);
		
		if(selectedstars == 0)
		{
			_1star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_2star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_3star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_4star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_5star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		}
		else if(selectedstars == 1)
		{
			_1star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_2star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_3star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_4star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_5star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		}
		else if(selectedstars == 2)
		{
			_1star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_2star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_3star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_4star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_5star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		}
		else if(selectedstars == 3)
		{
			_1star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_2star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_3star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_4star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
			_5star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		}
		else if(selectedstars == 4)
		{
			_1star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_2star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_3star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_4star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_5star.setIcon(new ImageIcon(getClass().getResource("/resources/star_unselected.png")));
		}
		else if(selectedstars == 5)
		{
			_1star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_2star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_3star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_4star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
			_5star.setIcon(new ImageIcon(getClass().getResource("/resources/star_selected.png")));
		}
		
		if(nstars == 0)
		{
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19+19,y_stars,null);
		}
		else if(nstars == 1)
		{
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19+19,y_stars,null);
		}
		else if(nstars == 2)
		{
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19+19,y_stars,null);
		}
		else if(nstars == 3)
		{
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19+19,y_stars,null);
		}
		else if(nstars == 4)
		{
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_unselected_2.png")).getImage(), x_stars+19+19+19+19,y_stars,null);
		}
		else if(nstars == 5)
		{
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19+19+19,y_stars,null);
			g2.drawImage(new ImageIcon(getClass().getResource("/resources/star_selected_2.png")).getImage(), x_stars+19+19+19+19,y_stars,null);
		}
		
		g2.dispose();
	}
	
	public void makeLabelChanges()
	{
		commentField.setText("");
		
		photo_extension = null;
		new_file_name="null.png";
		
		rankLabel.setText(String.format("%.1f", umaFrancesinha.selectedRestaurante.getRanking()));
		locationLabel.setText(umaFrancesinha.selectedRestaurante.getRua() + " , " + umaFrancesinha.selectedRestaurante.getCidade());
		nomeLabel.setText(umaFrancesinha.selectedRestaurante.getNome());
		
		System.out.println(umaFrancesinha.selectedRestaurante.getRanking());
		nstars = Math.round(umaFrancesinha.selectedRestaurante.getRanking()); 
		
		priceLabel.setText(umaFrancesinha.selectedRestaurante.getPreco() + " Euros");
		
		selectedstars=0;
		
		user1nameLabel.setText(nomeuser1);
		user1commentLabel.setText(comentario1);
		user1nameLabel.setForeground(new Color(169,169,169));
		
		user2nameLabel.setText(nomeuser2);
		user2commentLabel.setText(comentario2);
		user2nameLabel.setForeground(new Color(169,169,169));
		
		user1photo.setVisible(true);
		user2photo.setVisible(true);
		
		if(nomeuser1.equals(""))
			user1photo.setVisible(false);
		
		if(nomeuser2.equals(""))
			user2photo.setVisible(false);
		
		if(user1rank > 2.5)
			user1nameLabel.setForeground(Color.RED);
		
		if(user2rank > 2.5)
			user2nameLabel.setForeground(Color.RED);
	
		nratingsLabel.setText(navals + " ratings");
	
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
		
		if(uploadFoto_change == "UploadFoto")
		{
			uploadFoto.setIcon(new ImageIcon(getClass().getResource("/resources/camera_icon.png")));
		}
		else if(uploadFoto_change == "UploadFoto_H")
		{
			uploadFoto.setIcon(new ImageIcon(getClass().getResource("/resources/camera_icon_h.png")));
		}
		
		tabWorldNews_change=null;
		tabInfo_change=null;
		tabSettings_change=null;
		tabNotifications_change=null;
		tabSearch_change=null;
		uploadFoto_change = null;
		
	}

	public String getNomeuser1() {
		return nomeuser1;
	}

	public void setNomeuser1(String nomeuser1) {
		this.nomeuser1 = nomeuser1;
	}

	public String getNomeuser2() {
		return nomeuser2;
	}

	public void setNomeuser2(String nomeuser2) {
		this.nomeuser2 = nomeuser2;
	}

	public String getComentario1() {
		return comentario1;
	}

	public void setComentario1(String comentario1) {
		this.comentario1 = comentario1;
	}

	public String getComentario2() {
		return comentario2;
	}

	public void setComentario2(String comentario2) {
		this.comentario2 = comentario2;
	}

	public int getNavals() {
		return navals;
	}

	public void setNavals(int navals) {
		this.navals = navals;
	}

	public ImageIcon getImagem_get() {
		return imagem_get;
	}

	public void setImagem_get(ImageIcon imagem_get) {
		this.imagem_get = imagem_get;
	}

	public int getUser1avalid() {
		return user1avalid;
	}

	public void setUser1avalid(int user1avalid) {
		this.user1avalid = user1avalid;
	}

	public int getUser2avalid() {
		return user2avalid;
	}

	public void setUser2avalid(int user2avalid) {
		this.user2avalid = user2avalid;
	}
	
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}

	public double getUser1rank() {
		return user1rank;
	}

	public void setUser1rank(double user1rank) {
		this.user1rank = user1rank;
	}

	public double getUser2rank() {
		return user2rank;
	}

	public void setUser2rank(double user2rank) {
		this.user2rank = user2rank;
	}
}