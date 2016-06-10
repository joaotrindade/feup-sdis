
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;



public class Client {
	static Socket socket;
	static InetAddress  addr;
	static int port;
	static String path = "/restful/";
	static BufferedWriter out = null;
	static BufferedReader in = null;
	public static void main(String args[]) throws NumberFormatException, UnknownHostException, IOException {
		addr = InetAddress.getByName(args[0]);
		port = Integer.parseInt(args[1]);
		socket = new Socket(addr,port);
		System.out.println("O que deseja fazer?");
		System.out.println("1 - INSERIR UTILIZADOR");
		System.out.println("2 - INSERIR RESTAURANTE");
		System.out.println("3 - INSERIR AVALIA�AO");
		System.out.println("4 - LOGIN");
		System.out.println("5 - VER RESTAURANTE POR ID");
		@SuppressWarnings("resource")
		Scanner p = new Scanner(System.in);
		int op = p.nextInt();
		if(op==1)
		{
			inserirUtilizador();
		}
		else if(op==2)
		{
			inserirRestaurante();
		}
		else if(op==3)
		{
			inserirAvaliacao();
		}
		else if(op==4)
		{
			login();
		}
		else if(op==5)
		{
			listarRestaurante();
		}
		else if(op==6)
		{
			sendPic();
		}
		else
			System.exit(1);
	}
	
	@SuppressWarnings("resource")
	public static boolean inserirUtilizador() throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("NAME:");
		Scanner s = new Scanner(System.in);
		String nome = s.nextLine();
		System.out.println("PASSWORD:");
		Scanner s1 = new Scanner(System.in);
		String password = s1.nextLine();
		System.out.println("USERNAME:");
		Scanner s2 = new Scanner(System.in);
		String username = s2.nextLine();
		String pedido = "{utilizador:[{'nome':'" + nome + "','username':'" + username + "','pw':'" + password + "'}]}";
		httpPut(pedido);
		
		//Ler resposta
		System.out.println(in.readLine());
		out.close();
		in.close();
		socket.close();
		return true;
	}
	
	@SuppressWarnings("resource")
	public static boolean inserirRestaurante() throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("NAME:");
		Scanner s = new Scanner(System.in);
		String nome = s.nextLine();
		System.out.println("PASSWORD:");
		Scanner s1 = new Scanner(System.in);
		String password = s1.nextLine();
		System.out.println("RUA:");
		Scanner s2 = new Scanner(System.in);
		String rua = s2.nextLine();
		System.out.println("CIDADE:");
		Scanner s3 = new Scanner(System.in);
		String cidade = s3.nextLine();
		/*System.out.println("FOTO:");
		Scanner s4 = new Scanner(System.in);
		String foto = s4.nextLine();
		System.out.println("RANKING:");
		Scanner s5 = new Scanner(System.in);
		float ranking = s4.nextFloat();*/
		String pedido = "{restaurante:[{'nome':'" + nome + "','pw':'" + password + "','rua':'" + rua + "','cidade':'" + cidade + "'}]}";
		httpPut(pedido);
		
		//Ler resposta
		System.out.println(in.readLine());
		out.close();
		in.close();
		socket.close();
		return true;
	}
	
	@SuppressWarnings("resource")
	public static boolean inserirAvaliacao() throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("NAME OF RESTAURANT:");
		Scanner s = new Scanner(System.in);
		String nome = s.nextLine();
		System.out.println("SCORE:");
		Scanner s1 = new Scanner(System.in);
		int score = s1.nextInt();
		System.out.println("PHOTO:");
		Scanner s2 = new Scanner(System.in);
		String foto = s2.nextLine();
		System.out.println("COMMENT:");
		Scanner s3 = new Scanner(System.in);
		String comment = s3.nextLine();
		System.out.println("DATA:");
		Scanner s4 = new Scanner(System.in);
		String data = s4.nextLine();
		String pedido = "{avaliacao:[{'nome':'" + nome + "','score':'" + score + "','photo':'" + foto + "','comment':'" + comment + "','data':'" + data + "'}]}";
		httpPut(pedido);

		//Ler resposta
		System.out.println(in.readLine());
		out.close();
		in.close();
		socket.close();
		return true;
	}
	
	@SuppressWarnings("resource")
	public static boolean login() throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("NAME:");
		Scanner s = new Scanner(System.in);
		@SuppressWarnings("unused")
		String username = s.nextLine();
		System.out.println("PASSWORD:");
		Scanner s1 = new Scanner(System.in);
		String password = s1.nextLine();
		String pedido = "username=" + 1 + "&password=" + password;
		httpGet(pedido);
		
		//Ler resposta
		System.out.println(in.readLine());
		out.close();
		in.close();
		socket.close();
		return true;
	}
	
	public static boolean sendPic() throws IOException {
        Vector<byte[]> vec = readByteBlock("putin.jpg",64000);
        OutputStream out = socket.getOutputStream(); 
        DataOutputStream dos = new DataOutputStream(out);
        dos.write(("PIC\r\n").getBytes());
        dos.write(vec.elementAt(0));
        dos.write(("PICEND \r\n").getBytes());
		return true;
	}
	
	public static boolean listarRestaurante() throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("NAME:");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		int id = s.nextInt();
		String pedido = "restaurante_id=" + id;
		httpGet(pedido);
		
		//Ler resposta
		System.out.println(in.readLine());
		out.close();
		in.close();
		socket.close();
		return true;
	}
	
	public static void httpGet(String pedido) throws IOException {
		path += "GET?";
		//Mandar para o output
		out.write("GET " + path + " HTTP/1.0 \r\n");
		System.out.println("GET " + path + " HTTP/1.0 \r\n");
		out.write("Content-Length: " + pedido.length() + "\r\n");
		out.write("Content-Type: application/json\r\n");
		out.write("\r\n");
		out.write(pedido);
		out.write("\r\n");
		out.flush();
	}
	
	public static void httpPost(String pedido) throws IOException {
			path += "POST?";
			//Mandar para o output
			out.write("POST " + path + " HTTP/1.0 \r\n");
			out.write("Content-Length: " + pedido.length() + "\r\n");
			out.write("Content-Type: application/json\r\n");
			out.write("\r\n");
			out.write(pedido);
			out.write("\r\n");
			out.flush();
	}
	
	public static void httpPut(String pedido) throws IOException {
		path += "PUT?";
		//Mandar para o output
		out.write("PUT " + path + " HTTP/1.0 \r\n");
		out.write("Content-Length: " + pedido.length() + "\r\n");
		out.write("Content-Type: application/json\r\n");
		out.write(pedido);
		out.write("\r\n");
		out.flush();
	}
	
	@SuppressWarnings("resource")
	public static void httpDelete() throws IOException {
		path += "REGISTER?";
		System.out.println("INSERT THE PLATE NUMBER:");
		Scanner s = new Scanner(System.in);
		String plate = s.nextLine();
		System.out.println(plate);
		//String do pedido
		String pedido = "plate=" + URLEncoder.encode(plate, "UTF-8") + "&ResponseType=" + URLEncoder.encode("json", "UTF-8");
		//Mandar para o output
		out.write("DELETE " + path + " HTTP/1.0 \r\n");
		out.write("Content-Length: " + pedido.length() + "\r\n");
		out.write("Content-Type: application/json\r\n");
		out.write("\r\n");
		out.write(pedido);
		out.write("\r\n");
		out.flush();
	}
	
	static Vector<byte[]> readByteBlock(String nomeFicheiro, int noBytes) throws IOException {
		@SuppressWarnings("unused")
		int last;
		Vector<byte[]> file = new Vector<byte[]>(1000);
		System.out.println(nomeFicheiro);
		File f = new File(nomeFicheiro);
		if(f.exists() && !f.isDirectory()) {
		 	@SuppressWarnings("resource")
			InputStream in = new FileInputStream(nomeFicheiro);
		    byte[] result = new byte[noBytes];
		    while((last = in.read(result,0,noBytes))!=-1)
		    {
		    	file.add(result);
		    	result = new byte[noBytes];
		    }
		}
		else
		{
			return null;
		}
	    return file;
	}
}
