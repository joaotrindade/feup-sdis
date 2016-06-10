
import java.io.BufferedReader;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;

import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Vector;



import org.json.JSONException;

class clientThread extends Thread {

  private PrintWriter out;
  private BufferedReader in; 
  private Socket clientSocket = null;
  private int receiveimage=0;
  private int sendimage=0;
  String nomeimg="";
  
  public clientThread(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  @SuppressWarnings("deprecation")
public void run() {
    try {
    	out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
    }
	while(true) 
	{
		String received = "";
		Vector<String> line = new Vector<String>();
		int cont = 0;
		try {
			while ((received = in.readLine()) != null) {
				System.out.println(received);
				line.add(received);
				cont++;
				if(cont==4)
					break;
			}
		} catch(IOException ex){
		    this.stop();
		}
		for(int x=0;x<line.size();x++)
		{
			System.out.println("line : " + line.elementAt(x));
		}
		if(line.size()==4)
		{	
			if(line.elementAt(0).contains("PUT"))
			{
				if(line.elementAt(line.size()-1).contains("PIC"))
				{
					receiveimage = 1;
					String ff[] = line.elementAt(line.size()-1).split("=");
					nomeimg = ff[1];
				}
				try {
					if(receiveimage==0) {
						out.println(Server.httpPut(line.elementAt(line.size()-1)));
						out.flush();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(line.elementAt(0).contains("GET"))
			{
				if(line.elementAt(line.size()-1).contains("GETPIC")) {
					sendimage=1;
				}
				try {
					if(sendimage==0) {
						out.println(Server.httpGet(line.elementAt(line.size()-1)));
						out.flush();
					}
					else
					{
						try {
							String nomeimage = Server.httpGet(line.elementAt(line.size()-1));
							File f = new File("images/" + nomeimage);
							ObjectOutputStream outimg = new ObjectOutputStream(clientSocket.getOutputStream());
					        byte[] content = Files.readAllBytes(f.toPath());
					        outimg.writeObject(content);
						}catch(IOException ex){
						    ex.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sendimage=0;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(line.elementAt(0).contains("POST"))
			{
				try {
					out.println(Server.httpPost(line.elementAt(line.size()-1)));
					out.flush();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(receiveimage==1) {
			try {
				ObjectInputStream inimg = new ObjectInputStream(clientSocket.getInputStream());
				byte[] content = null;
				content = (byte[]) inimg.readObject();
				//System.out.println(new String(content));
				FileOutputStream fw = new FileOutputStream("images/" + nomeimg);
				fw.write(content);
				fw.close();
			} catch(IOException ex){
			    ex.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receiveimage=0;
		}
	}
  }
}