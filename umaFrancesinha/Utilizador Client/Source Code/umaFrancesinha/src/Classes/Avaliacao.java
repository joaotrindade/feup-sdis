package Classes;

public class Avaliacao {
	
	private String nome_restaurante;
	private String nome_utilizador;
	private String foto;
	private String comentario;
	private String data;
	private double ranking_user;
	
	private int pontuacao;
	
	public Avaliacao()
	{
		setNome_restaurante("");
		setNome_utilizador("");
		setFoto("");
		setComentario("");
		setData("");
		setPontuacao(-1);
	}
	
	public Avaliacao(String _nome_restaurante, String _nome_utilizador, String _foto, String _comentario, String _data, int _pontuacao,double _ranking_user)
	{
		setNome_restaurante(_nome_restaurante);
		setNome_utilizador(_nome_utilizador);
		setFoto(_foto);
		setComentario(_comentario);
		setData(_data);
		setPontuacao(_pontuacao);
		setRanking_user(_ranking_user);
	}

	public String getNome_restaurante() {
		return nome_restaurante;
	}

	public void setNome_restaurante(String nome_restaurante) {
		this.nome_restaurante = nome_restaurante;
	}

	public String getNome_utilizador() {
		return nome_utilizador;
	}

	public void setNome_utilizador(String nome_utilizador) {
		this.nome_utilizador = nome_utilizador;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public double getRanking_user() {
		return ranking_user;
	}

	public void setRanking_user(double _ranking_user) {
		this.ranking_user = _ranking_user;
	}
	
	

}
