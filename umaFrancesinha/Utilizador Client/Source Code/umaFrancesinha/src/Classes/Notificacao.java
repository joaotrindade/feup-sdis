package Classes;

public class Notificacao {
	private int id;
	private String nome_restaurante;
	private String descricao;
	private int id_restaurante;
	
	public Notificacao()
	{
		id=-1;
		nome_restaurante=null;
		descricao=null;
		id_restaurante = -1;
	}
	
	public Notificacao(int _id,String _nr,String _desc, int _idrest)
	{
		id=_id;
		nome_restaurante=_nr;
		descricao=_desc;
		id_restaurante = _idrest;
	}
	
	
	public int getId_restaurante() {
		return id_restaurante;
	}
	public void setId_restaurante(int id_restaurante) {
		this.id_restaurante = id_restaurante;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome_restaurante() {
		return nome_restaurante;
	}
	public void setNome_restaurante(String nome_restaurante) {
		this.nome_restaurante = nome_restaurante;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
