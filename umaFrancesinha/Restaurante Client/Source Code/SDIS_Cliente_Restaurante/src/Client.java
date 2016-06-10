 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Scanner;
 
 

import org.json.*;

 
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
                        MainWindow.hasConnection = true;
                }catch(IOException e){
                	e.printStackTrace();
                        System.out.println("SEM CONEXÃO SERVIDOR");
                }
        }
        
        public boolean login(String username,String password) throws IOException {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String pedido = "restaurante=" + username + "&password=" + password;
            String answer="";
            httpGet(pedido);
           
            //Ler resposta
            answer = in.readLine();
            //System.out.println(answer);
            if(answer.contains("FOUND RESTAURANT"))
            {
                    //System.out.println(answer);
                    String sides[] = answer.split("=");
                    MainWindow.idUser = Integer.parseInt(sides[1]);
                    return true;
            }
            else
            {
                    return false;
            }
        }
        
        public boolean enviarNotificacao(int id, String descricao) throws IOException
        {
        	out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            JSONObject obj = new JSONObject();
        	try {
        		obj.put("id_restaurante", id);
            	obj.put("descricao_notificacao", descricao);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	httpPut(obj.toString());
        	String answer = in.readLine();
        	
        	if (answer.contains("ERROR"))
        	{
        		return false;
        	}
        	else
        	{
        		return true;
        	}
        }
        
    	

        public boolean alterarDadosUser(int id,String nome,String password) throws IOException {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           
            JSONObject obj = new JSONObject();
            try {
                    obj.put("restaurante",id);
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
           
            if(answer.contains("UPDATED"))
            {
                    return true;
            }
            else
            {
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
