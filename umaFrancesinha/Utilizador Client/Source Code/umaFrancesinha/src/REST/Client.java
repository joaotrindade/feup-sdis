package REST;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import umaFrancesinha.umaFrancesinha;

import org.json.*;

import Classes.Avaliacao;
import Classes.Notificacao;
import Classes.Restaurante;

public class Client {
	static Socket socket;
	static InetAddress  addr;
	static int port;
	static String path = "/restful/";
	static BufferedWriter out = null;
	static BufferedReader in = null;
	

	public Client(String IPADD, int PORTA) throws NumberFormatException, UnknownHostException, IOException {
		addr = InetAddress.getByName(IPADD);
		port = PORTA;
		try{
			socket = new Socket(addr,port);
			umaFrancesinha.hasconnection = true;
		}catch(IOException e){
			System.out.println("SEM CONEXÃO SERVIDOR");
		}
	}
	
	

    public boolean inserirUtilizador(String nome,String username, String password) throws IOException {
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String pedido = "{utilizador:[{'nome':'" + nome + "','username':'" + username + "','pw':'" + password + "'}]}";
        httpPut(pedido);
   
        //Ler resposta
        String answer = in.readLine();
        //socket.shutdownInput();
		//socket.shutdownOutput();
        //out.close();
        //in.close();
        //socket.close();
        
        if(answer.equalsIgnoreCase("USER CREATED"))
		{
			return true;
		}
		else
		{
			System.out.println(answer);
			return false;
		}
     }


	
    public boolean inserirRestaurante(String nome,String rua,String cidade,String foto,float preco,String contacto) throws IOException {
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String pedido = "{restaurante:[{'nome':'" + nome + "','pw':'" + "12345" + "','rua':'" + rua + "','cidade':'" + cidade + "','foto':'" + foto + "','preco':'" + preco + "','contacto':'" + contacto + "'}]}";
        String answer;
        httpPut(pedido);
       
        //Ler resposta
        answer=in.readLine();
      
        if(answer.contains("INSERTED SUCCESSFULLY"))
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }
	
	
	public boolean login(String username,String password) throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String pedido = "username=" + username + "&password=" + password;
		String answer="JA";
		httpGet(pedido);
		
		//Ler resposta
		answer = in.readLine();
		//System.out.println(answer);
		if(answer.contains("FOUND USER"))
		{
			//System.out.println(answer);
			String sides[] = answer.split("=");
			umaFrancesinha.idUser = Integer.parseInt(sides[1]);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean getNotifications() throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String pedido = "notificacao_user=" + umaFrancesinha.idUser;
		String answer="";
		httpGet(pedido);
		
		//Ler resposta
		answer = in.readLine();

		if(answer.contains("notificacao"))
		{
			try {
				JSONObject obj2 = new JSONObject(answer);
	        	
	        	JSONArray array = obj2.getJSONArray("notificacao");
	        
	        	for(int i = 0 ; i < array.length() ; i++)
	        	{
	            	umaFrancesinha.notificationlist.add(new Notificacao(
	            			array.getJSONObject(i).getInt("id_notificacao"),
	            			array.getJSONObject(i).getString("nome"),
	            			array.getJSONObject(i).getString("descricao_notificacao"),
	            			array.getJSONObject(i).getInt("id_restaurante")
	            			));
	            }
	        	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkNotification(int id_notification) throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("notificacao_confirmada",id_notification);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String pedido = obj.toString();
		String answer="";
		httpPost(pedido);
		
		//Ler resposta
		answer = in.readLine();
		
		if(answer.contains("UPDATED NOTIFICATION"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean alterarDadosUser(int id,String nome,String password) throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("alterar_utilizador",id);
			obj.put("nome", nome);
			obj.put("password",password);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String pedido = obj.toString();
		String answer="";
		httpPost(pedido);
		
		//Ler resposta
		answer = in.readLine();
		
		if(answer.contains("YES"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean inserirAvaliacao(int id_restaurante,int id_utilizador,int score,String foto,String comment,String data,String hms) throws IOException {
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String pedido = "{avaliacao:[{'id_restaurante':'" + id_restaurante + "','id_utilizador':'" + id_utilizador + "','pontuacao':'" + score + "','foto':'" + foto + "','comentario':'" + comment + "','data_avaliacao':'" + data + "','hms':'" + hms + "'}]}";
        httpPut(pedido);

        //Ler resposta
        System.out.println(in.readLine());
       
        return true;
	}
	
	public boolean getRestaurante(int id) throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String pedido = "restaurante_id=" + id;
		String answer;
		httpGet(pedido);
		
		//Ler resposta
		answer = in.readLine();
		
		System.out.println(answer);
		if(answer.contains("selected_restaurante"))
		{
			try {
				JSONObject obj = new JSONObject(answer);
				JSONObject array = obj.getJSONObject("selected_restaurante");

				umaFrancesinha.selectedRestaurante.setId(array.getInt("restaurante_id"));
				umaFrancesinha.selectedRestaurante.setCidade(array.getString("cidade"));
	        	umaFrancesinha.selectedRestaurante.setNome(array.getString("nome"));
	        	//umaFrancesinha.selectedRestaurante.setRanking((float) array.getDouble("ranking"));
	        	umaFrancesinha.selectedRestaurante.setRua(array.getString("rua"));
	        	umaFrancesinha.selectedRestaurante.setPreco((float)array.getDouble("preco"));
	        	umaFrancesinha.selectedRestaurante.setContacto(array.getString("contacto"));
	        	umaFrancesinha.restaurantePanel.setNavals(array.getInt("numero_avaliacoes"));
	        	
	        	JSONArray array_aval = array.getJSONArray("avaliacoes");
	        	
	        	umaFrancesinha.restaurantePanel.setNomeuser1("");
	        	umaFrancesinha.restaurantePanel.setNomeuser2("");
	        	umaFrancesinha.restaurantePanel.setComentario1("");
	        	umaFrancesinha.restaurantePanel.setComentario2("");
	        	for(int i = 0 ; i < array_aval.length() ; i++){
	            	
	        		if(i==0)
	        		{
	        			umaFrancesinha.restaurantePanel.setNomeuser1(array_aval.getJSONObject(i).getString("nome_user"));
	        			umaFrancesinha.restaurantePanel.setComentario1(array_aval.getJSONObject(i).getString("comentario"));
	        			umaFrancesinha.restaurantePanel.setUser1avalid(array_aval.getJSONObject(i).getInt("id"));
	        			umaFrancesinha.restaurantePanel.setUser1rank(array_aval.getJSONObject(i).getInt("ranking_user"));
	        		}
	        		else
	        		{
	        			umaFrancesinha.restaurantePanel.setNomeuser2(array_aval.getJSONObject(i).getString("nome_user"));
	        			umaFrancesinha.restaurantePanel.setComentario2(array_aval.getJSONObject(i).getString("comentario"));
	        			umaFrancesinha.restaurantePanel.setUser2avalid(array_aval.getJSONObject(i).getInt("id"));
	        			umaFrancesinha.restaurantePanel.setUser2rank(array_aval.getJSONObject(i).getInt("ranking_user"));
	        		}
	            		
	            }
	        	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	

			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getSearch(String name,String location) throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		if(name.equals("") && location.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Search: One of the fields must have text to make a search", "#Error", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		else 
		{
			String pedido;
			if(name.equals(""))
			{
				pedido = "#SEARCH#&location=" + location;
			}
			else if(location.equals(""))
			{
				pedido = "#SEARCH#&name=" + name;
			}
			else
			{
				pedido = "#SEARCH#&name=" + name + "&location=" + location;
			}
			
			String answer;
			httpGet(pedido);
			
			//Ler resposta
			answer = in.readLine();
			//System.out.println(answer);
			
			if(answer.contains("#SEARCH#"))
			{
				umaFrancesinha.searchResult.clear();
				JSONObject obj;
				
				try {
					obj = new JSONObject(answer);
					//yourObject instanceof JSONObject
					
						if(obj.get("#SEARCH#") instanceof JSONArray)
						{
							JSONArray array = obj.getJSONArray("#SEARCH#");
						
				            for(int i = 0 ; i < array.length() ; i++){
				            	
				            	umaFrancesinha.searchResult.add(new Restaurante(
				            			array.getJSONObject(i).getInt("id"),
				            			array.getJSONObject(i).getString("cidade"),
				            			array.getJSONObject(i).getString("nome"),
				            			array.getJSONObject(i).getInt("ranking"),
				            			array.getJSONObject(i).getString("rua")
				            			));
				            }
			            }
			            else
			            {
			            	JSONObject array = obj.getJSONObject("#SEARCH#");
			            	
			            	umaFrancesinha.searchResult.add(new Restaurante(
			            			array.getInt("id"),
			            			array.getString("cidade"),
			            			array.getString("nome"),
			            			array.getInt("ranking"),
			            			array.getString("rua")
			            	));
			            }

					
				} catch (JSONException e) {
					//System.out.println("ERROR PARSING JSON");
					e.printStackTrace();
				}
				return 1;
			}
			else
			{
				return 0;
			}
		}
			
		
	}
	
	public boolean getWorldNews() throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String pedido = "last6";
		String answer;
		httpGet(pedido);
		
		//Ler resposta
		answer = in.readLine();

		if(answer.contains("top6"))
		{
			umaFrancesinha.lastnews.clear();
		    JSONObject obj;
			
		    try {
				obj = new JSONObject(answer);
		     
	            JSONArray array = obj.getJSONArray("top6");
	            for(int i = 0 ; i < array.length() ; i++){
	            	
	            	umaFrancesinha.lastnews.add(new Avaliacao(
	            			array.getJSONObject(i).getString("nome_restaurante"),
	            			array.getJSONObject(i).getString("nome_utilizador"),
	            			array.getJSONObject(i).getString("foto"),
	            			array.getJSONObject(i).getString("comentario"),
	            			array.getJSONObject(i).getString("data_aval"),
	            			array.getJSONObject(i).getInt("pontuacao"),
	            			array.getJSONObject(i).getDouble("ranking")
	            			));
	            }
			} catch (JSONException e) {
				//System.out.println("ERROR PARSING JSON");
				e.printStackTrace();
			}


			return true;
		}
		else
		{
			return false;
		}

	}
	
	public boolean sendPic(String name,String path) throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		httpPut("PIC=" + name);
		
		System.out.println("PIC=" + name);
        File f = new File(path);
        
        ObjectOutputStream outimg = new ObjectOutputStream(socket.getOutputStream());
        
        byte[] content = Files.readAllBytes(f.toPath());
        outimg.writeObject(content);
		
        return true;
		 
	}
	
	public boolean receivePic(int id) throws IOException {
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		httpGet("GETPIC=" + id);
		
		try {
                ObjectInputStream inimg = new ObjectInputStream(socket.getInputStream());
               // byte[] content2 = null;
                byte[] content = (byte[]) inimg.readObject();
                
                umaFrancesinha.restaurantePanel.setImagem_get(new ImageIcon(content));
                
                return true;
                
        } catch(IOException ex) {
        	ex.printStackTrace();
        	return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static void httpGet(String pedido) throws IOException {
		path = "GET?";
		//Mandar para o output
		out.write("GET " + path + " HTTP/1.0 " + "\r\n");
		//System.out.println("GET " + path + " HTTP/1.0\r\n");
		out.write("Content-Length: " + pedido.length() + "\r\n");
		out.write("Content-Type: application/json\r\n");
		out.write(pedido);
		out.write("\r\n");
		out.flush();
	}
	
	public static void httpPost(String pedido) throws IOException {
			path = "POST?";
			//Mandar para o output
			out.write("POST " + path + " HTTP/1.0 " + "\r\n");
			out.write("Content-Length: " + pedido.length() + "\r\n");
			out.write("Content-Type: application/json\r\n");
			out.write(pedido);
			out.write("\r\n");
			out.flush();
	}
	
	public static void httpPut(String pedido) throws IOException {
		path = "PUT?";
		//Mandar para o output
		out.write("PUT " + path + " HTTP/1.0 " + "\r\n");
		out.write("Content-Length: " + pedido.length() + "\r\n");
		out.write("Content-Type: application/json\r\n");
		out.write(pedido);
		out.write("\r\n");
		out.flush();
	}
	
	public static void httpDelete() throws IOException {
		path = "REGISTER?";
		System.out.println("INSERT THE PLATE NUMBER:");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		String plate = s.nextLine();
		System.out.println(plate);
		//String do pedido
		String pedido = "plate=" + URLEncoder.encode(plate, "UTF-8") + "&ResponseType=" + URLEncoder.encode("json", "UTF-8");
		//Mandar para o output
		out.write("DELETE " + path + " HTTP/1.0 " + "\r\n");
		out.write("Content-Length: " + pedido.length() + "\r\n");
		out.write("Content-Type: application/json\r\n");
		out.write(pedido);
		out.write("\r\n");
		out.flush();
	}
}
