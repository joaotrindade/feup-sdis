

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.lang.String;

import org.json.*;

public class Server {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static HashMap <String, String> data = new HashMap(); 
	static SqlServer sqlS;
	static Vector<clientThread> threads = new Vector<clientThread>();
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws IOException, SQLException, JSONException { 
		sqlS = new SqlServer();
		int port_number=Integer.parseInt(args[0]);
		@SuppressWarnings("resource")
		ServerSocket sckt = new ServerSocket(port_number);
		System.out.println("Servidor Iniciado");
		while(true) 
		{
			try {
		        Socket datasckt = sckt.accept();
		        threads.add(new clientThread(datasckt));
		        threads.elementAt(threads.size()-1).start();
		      } catch (IOException e) {
		        System.out.println(e);
		      }
		}
	}
	
	public static String httpPost(String received) throws JSONException, SQLException {
		SqlServer sqlS2 = new SqlServer();
		String toOut="";
		
		System.out.println("RECEBEU POST: " + received);
		if(received.contains("alterar_utilizador"))
		{
			String nome="";
			String pass="";
			int id=0;
			JSONObject obj = new JSONObject(received);
		
			id = obj.getInt("alterar_utilizador");
			nome = obj.getString("nome");
			pass = obj.getString("password");
			
			System.out.println("ID: " + id);
			System.out.println("NOVO NOME: " + nome);
			System.out.println("NOVA PW: " + pass);
			
			if(nome.equals(""))
			{
				if(sqlS2.executeQuery("UPDATE Utilizador set pw='"+pass+"' where id="+id)==true)
				{
					toOut =("YES");
				}
				else
				{
					toOut=("NO");
				}
			}
			else if(pass.equals(""))
			{
				if(sqlS2.executeQuery("UPDATE Utilizador set nome='"+nome+"' where id="+id)==true)
				{
					toOut =("YES");
				}
				else
				{
					toOut =("NO");
				}
			}
			else
			{
				if(sqlS2.executeQuery("UPDATE Utilizador set nome='"+nome+"', pw='"+pass+"' where id="+id)==true)
				{
					toOut =("YES");
				}
				else
				{
					toOut =("NO");
				}
			}
			sqlS2.closes();
		}
		else if(received.contains("restaurante"))
		{
			String nome="";
			String pass="";
			int id=0;
			JSONObject obj = new JSONObject(received);
			
			id = obj.getInt("restaurante");
			nome = obj.getString("nome");
			pass = obj.getString("password");
			
			System.out.println("ID: " + id);
			System.out.println("NOVO NOME: " + nome);
			System.out.println("NOVA PW: " + pass);
			
			if(nome.equals(""))
			{
				if(sqlS2.executeQuery("UPDATE Restaurante set pw='"+pass+"' where id="+id)==true)
				{
					toOut =("YES");
				}
				else
				{
					toOut=("NO");
				}
			}
			else if(pass.equals(""))
			{
				if(sqlS2.executeQuery("UPDATE Restaurante set username='"+nome+"' where id="+id)==true)
				{
					toOut =("YES");
				}
				else
				{
					toOut =("NO");
				}
			}
			else
			{
				if(sqlS2.executeQuery("UPDATE Restaurante set username='"+nome+"', pw='"+pass+"' where id="+id)==true)
				{
					toOut =("YES");
				}
				else
				{
					toOut =("NO");
				}
			}
			sqlS2.closes();
		}
		else if(received.contains("notificacao_confirmada")) // MARCAR UMA NOTIFICACAO COMO VISTA
		{
			JSONObject obj = new JSONObject(received);
			int id_notificacao = obj.getInt("notificacao_confirmada");
			
			if(sqlS2.executeQuery("UPDATE Notificacao SET visto=1 where id_notificacao=" + id_notificacao)==true)
			{
				toOut =("UPDATED NOTIFICATION");
			}
			else
			{
				toOut=("NOT UPDATED NOTIFICATION");
			}
		}
		return toOut;
		
	}
	
	public static String httpPut(String received) throws SQLException, JSONException {
		SqlServer sqlS2 = new SqlServer();
		String toOut="";
		if(received.contains("{utilizador"))
		{
			String nome="";
			String username="";
			String password="";
			JSONObject obj = new JSONObject(received);

			JSONArray array = obj.getJSONArray("utilizador");
			for(int i = 0 ; i < array.length() ; i++){
			    nome = (array.getJSONObject(i).getString("nome"));
			    username = (array.getJSONObject(i).getString("username"));
			    password = (array.getJSONObject(i).getString("pw"));
			}
			System.out.println(nome);
			System.out.println(username);
			System.out.println(password);
			if(sqlS2.executeQuery("INSERT INTO Utilizador VALUES (NULL,'" + nome + "','" + username + "','" + password + "',0)")==true)
			{
				toOut =("USER CREATED");
			}
			else
			{
				toOut =("COULD NOT INSERT USER");
			}
			sqlS2.closes();
		}
		else if(received.contains("{restaurante"))
		{
			String nome="";
			String password="";
			String rua="";
			String cidade="";
			String username="";
			String fotografia="";
			String ranking="NULL";
			double preco=0;
			String contacto="";
			JSONObject obj = new JSONObject(received);

			JSONArray array = obj.getJSONArray("restaurante");
			for(int i = 0 ; i < array.length() ; i++){
			    nome = (array.getJSONObject(i).getString("nome"));
			    username = "admin";
			    password = "12345";
			    rua = (array.getJSONObject(i).getString("rua"));
			    cidade = (array.getJSONObject(i).getString("cidade"));
			    fotografia = (array.getJSONObject(i).getString("foto"));
			    preco = (array.getJSONObject(i).getDouble("preco"));
			    contacto = (array.getJSONObject(i).getString("contacto"));
			}
			if(sqlS2.executeQuery("INSERT INTO Restaurante VALUES (NULL,'" + nome + "','" + username + "','" + password + "','" + rua + "','" + cidade + "','" + fotografia + "','" + ranking + "'," + preco + ",'" + contacto + "')")==true)
			{
				toOut = "RESTAURANT INSERTED SUCCESSFULLY";
			}
			else
			{
				toOut = "COULD NOT INSERT RESTAURANT";
			}
			sqlS2.closes();
		}
		else if(received.contains("{avaliacao"))
		{
			int id_restaurante=0;
			int id_utilizador=0;
			int score=0;
			String photo="";
			String comment="";
			String data="";
			String hms="";
			JSONObject obj = new JSONObject(received);

			JSONArray array = obj.getJSONArray("avaliacao");
			for(int i = 0 ; i < array.length() ; i++){
			    id_restaurante = (array.getJSONObject(i).getInt("id_restaurante"));
			    id_utilizador = (array.getJSONObject(i).getInt("id_utilizador"));
			    score = array.getJSONObject(i).getInt("pontuacao");
			    photo = (array.getJSONObject(i).getString("foto"));
			    comment = (array.getJSONObject(i).getString("comentario"));
			    data = (array.getJSONObject(i).getString("data_avaliacao"));
			    hms = (array.getJSONObject(i).getString("hms"));
			}
			
			System.out.println("INSERT INTO Avaliacao VALUES (NULL," + id_restaurante + "," + id_utilizador + "," + score + ",'" + photo + "','" + comment + "','" + data + "','" + hms + "')");
			if(sqlS2.executeQuery("INSERT INTO Avaliacao VALUES (NULL," + id_restaurante + "," + id_utilizador + "," + score + ",'" + photo + "','" + comment + "','" + data + "','" + hms + "')")==true)
			{
				toOut =("AVALIATION INSERTED SUCCESSFULLY");
			}
			else
			{
				toOut =("COULD NOT INSERT AVALIATION");
			}
	        sqlS2.closes();
		}
		else if(received.contains("descricao_notificacao")) // INSERIR NOTIFICACOES A TODOS OS UTILIZADORES QUE TENHAM DADO FEEDBACK POSITIVO
		{
			System.out.println("ENTROU AQUI ACPFAPSF");
			boolean inserted = false;
			JSONObject obj = new JSONObject(received);
			int id_restaurante = obj.getInt("id_restaurante");
			String descricao = obj.getString("descricao_notificacao");
			
			ResultSet rs = sqlS2.executeQueryRS("SELECT id_utilizador FROM Avaliacao WHERE id_restaurante=" + id_restaurante + " and pontuacao>2 group by id_utilizador" );
			while (rs.next())
			{
				
				int id_utilizador = rs.getInt("id_utilizador");
				//System.out.println("Entrou, utilizador: " + id_utilizador);
				//System.out.println("INSERT INTO Notificacao VALUES(NULL," + id_restaurante + "," + id_utilizador + "," + 0 + "," + descricao +")" );
				if (sqlS2.executeQuery("INSERT INTO Notificacao VALUES(NULL," + id_restaurante + "," + id_utilizador + "," + 0 + ",'" + descricao +"')")) inserted = true;
			}
			if(inserted==true)
			{
				toOut =("NOTIFICATION(S) INSERTED");
			}
			else
			{
				toOut=("ERROR: NOTIFICATION(S) NOT INSERTED");
			}
		}
		return toOut;
	}
	
	public static String httpGet(String received) throws JSONException, SQLException {
		SqlServer sqlS2 = new SqlServer();
		String toOut="";
		if(received.contains("restaurante_id="))
		{
			int restaurante_id=0;
			String sides[] = received.split("=");
			restaurante_id = Integer.parseInt(sides[1]);
			System.out.println(restaurante_id);
			ResultSet rs = sqlS2.executeQueryRS("SELECT * FROM Restaurante WHERE id=" + restaurante_id );
			if (rs.next())
			{
				ResultSet rs2 = sqlS2.executeQueryRS("SELECT id_utilizador,Avaliacao.id as id_aval,pontuacao,comentario,data_avaliacao,nome FROM Avaliacao INNER JOIN Utilizador On Utilizador.id = Avaliacao.id_utilizador WHERE Avaliacao.id_restaurante=" + restaurante_id + " ORDER BY Avaliacao.id DESC LIMIT 2" );
				JSONArray JSONavaliacoes = new JSONArray();
				while (rs2.next())
				{
					int id_user = rs2.getInt("id_utilizador");
					ResultSet rs3 = sqlS2.executeQueryRS("SELECT * FROM Utilizador WHERE id='" + id_user + "'");
					Float ranking = rs3.getFloat("ranking");
					
					ResultSet rs4 = sqlS2.executeQueryRS("SELECT count(id_utilizador) as nraval FROM Avaliacao WHERE id_utilizador="+id_user);
					int nraval = rs4.getInt("nraval");
					double ranking_user = ranking/nraval;
					ranking_user = Math.sqrt(ranking_user);
					
					
					int id_aval = rs2.getInt("id_aval");
					int pontuacao = rs2.getInt("pontuacao");
					String comentario = rs2.getString("comentario");
					String data_avaliacao = rs2.getString("data_avaliacao");
					String nome_user = rs2.getString("nome");
					
					JSONObject JSONaval = new JSONObject();
					JSONaval.put("id", id_aval);
					JSONaval.put("nome_user", nome_user);
					JSONaval.put("pontuacao", pontuacao);
					JSONaval.put("comentario", comentario);
					JSONaval.put("data_avaliacao", data_avaliacao);
					JSONaval.put("ranking_user", ranking_user);
					JSONavaliacoes.put(JSONaval);
					rs3.close();
					rs4.close();
				}
				
				ResultSet rs3 = sqlS2.executeQueryRS("SELECT count(*) as contador from Avaliacao Where id_restaurante= " + restaurante_id );
				int numeroAvaliacoes = 0;
				if (rs3.next()) numeroAvaliacoes = rs3.getInt("contador");
				
				System.out.println();
				System.out.println("Entrou");
				int id = rs.getInt("id");
		        String  name = rs.getString("nome");
		        String  rua = rs.getString("rua");
		        String  cidade = rs.getString("cidade");
		        String contacto = rs.getString("contacto");
		        Float preco = rs.getFloat("preco");
		        int ranking  = rs.getInt("rua");
		        
		        JSONObject obj = new JSONObject();
		        
		        obj.put("restaurante_id",id );
		        obj.put("nome",name);
		        obj.put("ranking",ranking );
		        obj.put("rua", rua);
		        obj.put("cidade", cidade);
		        obj.put("preco", preco);
		        obj.put("contacto", contacto);
		        obj.put("numero_avaliacoes", numeroAvaliacoes);
		        obj.put("avaliacoes",JSONavaliacoes);

		        JSONObject n = new JSONObject();
		        
		        n.put("selected_restaurante", obj);
		        
		        toOut = n.toString();
		        System.out.println("PEDIDO DE RESTAURANTE: "+ n.toString());
		        
				rs2.close();
				rs3.close();
			}
			else
			{
				System.out.println("Vazio");
			}
			
			rs.close();
			sqlS2.closes();
		}
		else if(received.contains("username="))
		{
			System.out.println("entrei");
			String fields[] = received.split("&");
			String sidesusername[] = fields[0].split("=");
			String username = sidesusername[1];
			String sidespassword[] = fields[1].split("=");
			String password = sidespassword[1];
			ResultSet rs = sqlS2.executeQueryRS("SELECT * FROM Utilizador WHERE username='" + username + "' AND pw='" + password + "'");
			if(rs.next())
			{
				toOut = "FOUND USER="+rs.getInt("id");
			}
			else
				toOut = "USER NOT FOUND";
			rs.close();
			sqlS2.closes();
		}
		else if(received.contains("restaurante="))
		{
			String fields[] = received.split("&");
			String sidesusername[] = fields[0].split("=");
			String username = sidesusername[1];
			String sidespassword[] = fields[1].split("=");
			String password = sidespassword[1];
			ResultSet rs = sqlS2.executeQueryRS("SELECT * FROM Restaurante WHERE username='" + username + "' AND pw='" + password + "'");
			if(rs.next())
			{
				toOut = "FOUND RESTAURANT="+rs.getInt("id");
			}
			else
				toOut = "RESTAURANT NOT FOUND";
			rs.close();
			sqlS2.closes();
		}
		else if(received.contains("last6"))
		{
			ResultSet rs = sqlS2.executeQueryRS("SELECT * FROM Avaliacao ORDER BY id DESC LIMIT 6");
			if(rs.next())
			{
					JSONObject top6 = new JSONObject();
					System.out.println("Entrou");
					int id_restaurante = rs.getInt("id_restaurante");
					int id_utilizador = rs.getInt("id_utilizador");
					int pont = rs.getInt("pontuacao");
					String foto = rs.getString("foto");
					String comentario = rs.getString("comentario");
					String data_avaliacao = rs.getString("data_avaliacao");
					
					ResultSet rs2 = sqlS2.executeQueryRS("SELECT * FROM Restaurante WHERE id='" + id_restaurante + "'");
					String nome_restaurante = rs2.getString("nome");
					
					
					ResultSet rs3 = sqlS2.executeQueryRS("SELECT * FROM Utilizador WHERE id='" + id_utilizador + "'");
					int id_user = rs3.getInt("id");
					String nome_utilizador = rs3.getString("nome");
					Float ranking = rs3.getFloat("ranking");
					
					ResultSet rs4 = sqlS2.executeQueryRS("SELECT count(id_utilizador) as nraval FROM Avaliacao WHERE id_utilizador="+id_user);
					int nraval = rs4.getInt("nraval");
					double ranking_user = ranking/nraval;
					ranking_user = Math.sqrt(ranking_user);
					
					
					 JSONObject obj = new JSONObject();
				        
				     obj.put("nome_restaurante",nome_restaurante );
				     obj.put("nome_utilizador",nome_utilizador);
				     obj.put("pontuacao",pont );
				     obj.put("foto", foto);
				     obj.put("comentario", comentario);
				     obj.put("data_aval", data_avaliacao);
				     obj.put("ranking", ranking_user);
				     
				     top6.put("top6", obj);
				     
				     rs2.close();
				     rs3.close();
				        
					while(rs.next())
					{
						id_restaurante = rs.getInt("id_restaurante");
						id_utilizador = rs.getInt("id_utilizador");
						pont = rs.getInt("pontuacao");
						foto = rs.getString("foto");
						comentario = rs.getString("comentario");
						data_avaliacao = rs.getString("data_avaliacao");
						
						ResultSet rs10 = sqlS2.executeQueryRS("SELECT * FROM Restaurante WHERE id='" + id_restaurante + "'");
						nome_restaurante = rs10.getString("nome");
						
						ResultSet rs5= sqlS2.executeQueryRS("SELECT * FROM Utilizador WHERE id='" + id_utilizador + "'");
						nome_utilizador = rs5.getString("nome");
						ranking = rs5.getFloat("ranking");
						
						ResultSet rs11 = sqlS2.executeQueryRS("SELECT count(id_utilizador) as nraval FROM Avaliacao WHERE id_utilizador="+id_user);
						nraval = rs11.getInt("nraval");
						ranking_user = ranking/nraval;
						ranking_user = Math.sqrt(ranking_user);
						
						
						JSONObject obj1 = new JSONObject();
				        
					     obj1.put("nome_restaurante",nome_restaurante );
					     obj1.put("nome_utilizador",nome_utilizador);
					     obj1.put("pontuacao",pont );
					     obj1.put("foto", foto);
					     obj1.put("comentario", comentario);
					     obj1.put("data_aval", data_avaliacao);
					     obj1.put("ranking", ranking_user);
						
						top6.accumulate("top6", obj1);
						
						rs4.close();
						rs5.close();
						rs11.close();
						rs10.close();
					}

			        toOut = top6.toString();
			        //rs2.close();
			        //System.out.println("Aqui: "+ top6.toString());
			        
			        
			}
			else
				toOut = "NO EVALUATIONS";
			rs.close();
			sqlS2.closes();
		}
		else if(received.contains("notificacao_user="))
		{
			System.out.println("#Notificao");
			System.out.println(received);
			int utilizador_id=0;
			String sides[] = received.split("=");
			utilizador_id = Integer.parseInt(sides[1]);
			ResultSet rs = sqlS2.executeQueryRS("SELECT * FROM Notificacao INNER JOIN Restaurante ON Notificacao.id_restaurante=Restaurante.id WHERE Notificacao.id_utilizador=" + utilizador_id + " AND visto=0" );
			JSONArray JSONAvaliacoes = new JSONArray();
			JSONObject JSONNotificacao = new JSONObject();
			while (rs.next())
			{
				int id_notificacao = rs.getInt("id_notificacao");
				int id_restaurante = rs.getInt("id_restaurante");
				String nome_restaurante = rs.getString("nome");
				String descricao_notificacao = rs.getString("descricao_notificacao");
				JSONObject JSONRestaurante = new JSONObject();
				JSONRestaurante.put("id_notificacao", id_notificacao);
				JSONRestaurante.put("id_restaurante", id_restaurante);
				JSONRestaurante.put("nome", nome_restaurante);
				JSONRestaurante.put("descricao_notificacao", descricao_notificacao);
				JSONAvaliacoes.put(JSONRestaurante);
			}
			JSONNotificacao.put("notificacao", JSONAvaliacoes);
			
			toOut = JSONNotificacao.toString();
	        System.out.println("PEDIDO DE Notificacao: "+ JSONNotificacao.toString());
			
		}
		else if(received.contains("#SEARCH#"))
		{
			System.out.println("#SEARCH REQUEST");
			System.out.println(received);
			
			String toQuery = "";
			
			String fields[] = received.split("&");
			if(received.contains("name") == true && received.contains("location") == false)
			{
				String searchfieldside[] = fields[1].split("=");
				String searchfield = searchfieldside[1];
			
				String words[] = searchfield.split(" ");
				toQuery = "";
				
				if(words.length >= 1)
				{
					toQuery = "nome LIKE '%"+words[0]+"%'";
					for(int i=1;i<words.length;i++)
					{
						toQuery+= " OR nome LIKE '%"+words[i]+"%'";
					}
				}
			}
			else if(received.contains("name") == false && received.contains("location") == true)
			{
				String searchfieldside[] = fields[1].split("=");
				String searchfield = searchfieldside[1];
			
				String words[] = searchfield.split(" ");
				toQuery = "";
				
				if(words.length >= 1)
				{
					toQuery = "cidade LIKE '%"+words[0]+"%'";
					for(int i=1;i<words.length;i++)
					{
						toQuery+= " OR cidade LIKE '%"+words[i]+"%'";
					}
				}
			}
			else if(received.contains("name") == true && received.contains("location") == true)
			{
				String searchfieldside[] = fields[1].split("=");
				String names = searchfieldside[1];
				
				String searchfieldside2[] = fields[2].split("=");
				String locais = searchfieldside2[1];
			
				String words1[] = names.split(" ");
				String words2[] = locais.split(" ");
				toQuery = "";
				
				if(words1.length >= 1)
				{
					toQuery = "(nome LIKE '%"+words1[0]+"%'";
					for(int i=1;i<words1.length;i++)
					{
						toQuery+= " OR nome LIKE '%"+words1[i]+"%'";
					}
					toQuery+=")";
				}
				toQuery+=" AND ";
				if(words2.length >= 1)
				{
					toQuery += "(cidade LIKE '%"+words2[0]+"%'";
					for(int i=1;i<words2.length;i++)
					{
						toQuery+= " OR cidade LIKE '%"+words2[i]+"%'";
					}
					toQuery+=")";
				}
				
			}
				
			
			ResultSet rs = sqlS2.executeQueryRS("SELECT * FROM Restaurante WHERE "+toQuery);
			if(rs.next())
			{
					JSONObject search = new JSONObject();
					System.out.println("Entrou");
					
					int id = rs.getInt("id");
					int ranking = rs.getInt("ranking");
					String nome = rs.getString("nome");
					String cidade = rs.getString("cidade");
					String rua = rs.getString("rua");
					
					 JSONObject obj = new JSONObject();

					 obj.put("id",id);
				     obj.put("nome", nome);
				     obj.put("ranking", ranking);
				     obj.put("cidade", cidade);
				     obj.put("rua", rua);
				     
				     search.put("#SEARCH#", obj);
				        
					while(rs.next())
					{
						id = rs.getInt("id");
						ranking = rs.getInt("ranking");
						nome = rs.getString("nome");
						cidade = rs.getString("cidade");
						rua = rs.getString("rua");
						
						JSONObject obj1 = new JSONObject();
				        
						 obj1.put("id",id);
					     obj1.put("nome", nome);
					     obj1.put("ranking", ranking);
					     obj1.put("cidade", cidade);
					     obj1.put("rua", rua);
						
						search.accumulate("#SEARCH#", obj1);
					}
					
					toOut=search.toString();
			}
			else
				toOut = "NO RESTAURANTS";
			rs.close();
			sqlS2.closes();
		}
		else if(received.contains("GETPIC"))
		{
			String sides[] = received.split("=");
			int id_avaliacao = Integer.parseInt(sides[1]);
			ResultSet rs = sqlS2.executeQueryRS("SELECT * FROM Avaliacao WHERE id=" + id_avaliacao);
			if(rs.next()) {
				String nomeimg = rs.getString("foto");
				toOut = nomeimg;
			}
			else
			{
				toOut="NO SUCH ID";
			}
			rs.close();
		}
		return toOut;
	}
}
