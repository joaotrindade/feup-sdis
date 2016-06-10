
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JPasswordField;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField textField;
	private JPasswordField pwdTxtpassword;
	public static boolean hasConnection;
	public static int idUser;
	private static Client clientConnection;
	private JPasswordField confirmPasswordField;
	private JButton btnLogout;
	private JButton btnEnviar;
	private JButton btnChangePassword;
	private JButton btnLogin;
	private JTextField txtNewUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final String ip = args[0];
		final int porta = Integer.parseInt(args[1]);
		System.out.println(ip);
		System.out.println(porta);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hasConnection = false;
					clientConnection = new Client(ip,porta);
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel jPanel_main = new JPanel();
		jPanel_main.setBounds(12, 12, 924, 247);
		contentPane.add(jPanel_main);
		jPanel_main.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(137, 26, 114, 19);
		jPanel_main.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdTxtpassword = new JPasswordField();
		pwdTxtpassword.setBounds(137, 57, 114, 19);
		jPanel_main.add(pwdTxtpassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(30, 28, 106, 15);
		jPanel_main.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(30, 55, 89, 19);
		jPanel_main.add(lblPassword);
		
		
		
		
		
		JSeparator separatorHor = new JSeparator();
		separatorHor.setBounds(12, 91, 900, 2);
		jPanel_main.add(separatorHor);
		
		JLabel lblNewPassword = new JLabel("New Username:");
		lblNewPassword.setBounds(522, 28, 123, 15);
		jPanel_main.add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(521, 57, 147, 15);
		jPanel_main.add(lblConfirmPassword);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(669, 55, 107, 19);
		confirmPasswordField.setEnabled(false);
		jPanel_main.add(confirmPasswordField);
		
		
		JSeparator separatorVert = new JSeparator();
		separatorVert.setOrientation(SwingConstants.VERTICAL);
		separatorVert.setBounds(460, 91, 2, 156);
		jPanel_main.add(separatorVert);
		
		JLabel lblCriarNotificao = new JLabel("Criar Notificação");
		lblCriarNotificao.setBounds(137, 101, 152, 15);
		jPanel_main.add(lblCriarNotificao);
		
		textField = new JTextField();
		textField.setBounds(12, 137, 239, 98);
		textField.setEnabled(false);
		jPanel_main.add(textField);
		textField.setColumns(10);
		
		txtNewUsername = new JTextField();
		txtNewUsername.setBounds(669, 26, 107, 19);
		txtNewUsername.setEnabled(false);
		jPanel_main.add(txtNewUsername);
		txtNewUsername.setColumns(10);
		
		btnChangePassword = new JButton("Save Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newUsername = txtNewUsername.getText();
				String newPassword = new String(confirmPasswordField.getPassword());
				if (hasConnection && (!newUsername.equals("") || !newPassword.equals("")) )
				{
					try {
						clientConnection.alterarDadosUser(idUser, newUsername, newPassword);
						System.out.println();
					} catch (IOException e1) {
						System.out.println("Error changing data");
						//e1.printStackTrace();
					}
				}
				
			}
		});
		btnChangePassword.setBounds(783, 26, 129, 48);
		btnChangePassword.setEnabled(false);
		jPanel_main.add(btnChangePassword);
		
		btnEnviar = new JButton("Send");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descricao = textField.getText();
				if (hasConnection && descricao.length() < 190)
				{
					try {
						clientConnection.enviarNotificacao(idUser, descricao);
					} catch (IOException e1) {
						System.out.println("Connection Error");
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnEnviar.setBounds(263, 161, 185, 48);
		btnEnviar.setEnabled(false);
		jPanel_main.add(btnEnviar);
		
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String(pwdTxtpassword.getPassword());
				if (hasConnection && !username.equals("") && !password.equals(""))
				{
					try {
						if (clientConnection.login(username,password))
						{
							System.out.println("Login Done");
							btnLogout.setEnabled(true);
							btnEnviar.setEnabled(true);
							btnChangePassword.setEnabled(true);
							btnLogin.setEnabled(false);
							txtUsername.setEnabled(false);
							pwdTxtpassword.setEnabled(false);
							txtNewUsername.setEnabled(true);
							textField.setEnabled(true);
							confirmPasswordField.setEnabled(true);
							
						}
						else
						{
							System.out.println("Error");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnLogin.setBounds(263, 23, 114, 56);
		jPanel_main.add(btnLogin);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idUser = -1;
				btnLogin.setEnabled(true);
				btnEnviar.setEnabled(false);
				btnChangePassword.setEnabled(false);
				btnChangePassword.setText("");
				txtUsername.setEnabled(true);
				txtUsername.setText("");
				pwdTxtpassword.setEnabled(true);
				pwdTxtpassword.setText("");
				txtNewUsername.setEnabled(false);
				txtNewUsername.setText("");
				textField.setEnabled(false);
				textField.setText("");
				confirmPasswordField.setEnabled(false);
				confirmPasswordField.setText("");
			}
		});
		btnLogout.setBounds(389, 23, 114, 56);
		btnLogout.setEnabled(false);
		jPanel_main.add(btnLogout);
		
		
		
		
		
		
	}
}
