package umaFrancesinha;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import Classes.Avaliacao;
import Classes.Notificacao;
import Classes.NotifyThread;
import Classes.Restaurante;
import Graphics.InfoPanel;
import Graphics.InitialPanel;
import Graphics.InsertRestaurantePanel;
import Graphics.LoginPanel;
import Graphics.NotificationFrame;
import Graphics.NotificationsPanel;
import Graphics.RegisterPanel;
import Graphics.RestaurantePanel;
import Graphics.ResultSearchPanel;
import Graphics.SearchPanel;
import Graphics.SettingsPanel;
import Graphics.WorldNewsPanel;
import REST.Client;

public class umaFrancesinha {
	
	public static boolean hasconnection;
	
	public static JFrame mainFrame;
	public static NotificationFrame notification_frame;
	
	private static InitialPanel initialPanel;
	private static LoginPanel loginPanel;
	private static SearchPanel searchPanel;
	private static WorldNewsPanel worldnewsPanel;
	private static SettingsPanel settingsPanel;
	private static InfoPanel infoPanel;
	public static NotificationsPanel notificationsPanel;
	private static RegisterPanel registerPanel;
	private static ResultSearchPanel resultsearchPanel;
	public static RestaurantePanel restaurantePanel;
	private static InsertRestaurantePanel insertrestaurantePanel;
	private static NotifyThread notificationThread;
	
	public static Client client;
	
	private static int mainFrame_width = 756;
	private static int mainFrame_height = 479;
	
	public static int showPanel_no = 1;
	public static int exit = 0;
	
	public static int idUser = -1;
	
	public static ArrayList<Avaliacao> lastnews = new ArrayList<Avaliacao>();
	public static ArrayList<Restaurante> searchResult = new ArrayList<Restaurante>();
	public static ArrayList<Notificacao> notificationlist = new ArrayList<Notificacao>();
	
	public static Restaurante selectedRestaurante = new Restaurante();
	
	public static int wscreen;
	public static int hscreen;
	
	public static boolean wantNotifications;
	
	public static int notificationTime = 3;
	
	private static String IPADD;
	private static int Porta;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		IPADD = args[0];
		Porta = Integer.parseInt(args[1]);
		
		initialize();
		
		start_communication_server();
		
		Code();
		
	}

	
	/**
	 * Create the application.
	 */
	public umaFrancesinha() {
		//initialize();
	}

	
	/**
	 * Code the application.
	 */
	private static void Code() {

		//GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		//int width = gd.getDisplayMode().getWidth();
		//int height = gd.getDisplayMode().getHeight();
		
		//notification_frame = new NotificationFrame();
		//notification_frame.showNotification();

	}
	
	
	private static void start_communication_server()
	{
		try {
			client = new Client(IPADD,Porta);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		wscreen = width;
		hscreen = height;
		
		hasconnection = false;
		
		wantNotifications = true;
		
		mainFrame = new JFrame();
		mainFrame.setBounds(width/2 - mainFrame_width/2, height/2 - mainFrame_height/2, mainFrame_width, mainFrame_height);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		
		initialPanel = new InitialPanel();
		initialPanel.setVisible(true);
		mainFrame.getContentPane().add(initialPanel);
		
		loginPanel = new LoginPanel();
		loginPanel.setVisible(false);
		mainFrame.getContentPane().add(loginPanel);
		
		searchPanel = new SearchPanel();
		searchPanel.setVisible(false);
		mainFrame.getContentPane().add(searchPanel);
		
		worldnewsPanel = new WorldNewsPanel();
		worldnewsPanel.setVisible(false);
		mainFrame.getContentPane().add(worldnewsPanel);
		
		settingsPanel = new SettingsPanel();
		settingsPanel.setVisible(false);
		mainFrame.getContentPane().add(settingsPanel);
		
		infoPanel = new InfoPanel();
		infoPanel.setVisible(false);
		mainFrame.getContentPane().add(infoPanel);
		
		notificationsPanel = new NotificationsPanel();
		notificationsPanel.setVisible(false);
		mainFrame.getContentPane().add(notificationsPanel);
		
		registerPanel = new RegisterPanel();
		registerPanel.setVisible(false);
		mainFrame.getContentPane().add(registerPanel);
		
		resultsearchPanel = new ResultSearchPanel();
		resultsearchPanel.setVisible(false);
		mainFrame.getContentPane().add(resultsearchPanel);
		
		restaurantePanel = new RestaurantePanel();
		restaurantePanel.setVisible(false);
		mainFrame.getContentPane().add(restaurantePanel);
		
		insertrestaurantePanel = new InsertRestaurantePanel();
		insertrestaurantePanel.setVisible(false);
		mainFrame.getContentPane().add(insertrestaurantePanel);
		
		notificationThread = new NotifyThread();
		notificationThread.start();
		
		
	}
	
	
	public static void show_initialPanel()
	{
		initialPanel.setVisible(true);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
	}
	
	public static void show_loginPanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(true);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
	}
	
	public static void show_searchPanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(true);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
	}
	
	@SuppressWarnings("static-access")
	public static void show_worldnewsPanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(true);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
		
		worldnewsPanel.setReloadNews(true);
		
	}
	
	public static void show_settingsPanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(true);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
	}
	
	public static void show_infoPanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(true);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
	}
	
	public static void show_notificationsPanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(true);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
		
		notificationsPanel.printResults();
	}
	
	public static void show_registerPanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(true);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
	}
	
	public static void show_resultsearchPanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(true);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(false);
		
		resultsearchPanel.printResults();
	}
	
	public static void show_restaurantePanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(true);
		insertrestaurantePanel.setVisible(false);
		
		try {
			client.getRestaurante(selectedRestaurante.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		restaurantePanel.makeLabelChanges();

	}
	
	public static void show_insertrestaurantePanel()
	{
		initialPanel.setVisible(false);
		loginPanel.setVisible(false);
		searchPanel.setVisible(false);
		worldnewsPanel.setVisible(false);
		settingsPanel.setVisible(false);
		infoPanel.setVisible(false);
		notificationsPanel.setVisible(false);
		registerPanel.setVisible(false);
		resultsearchPanel.setVisible(false);
		restaurantePanel.setVisible(false);
		insertrestaurantePanel.setVisible(true);

	}
}
