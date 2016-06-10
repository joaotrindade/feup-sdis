package Classes;

public class Restaurante {
	
	private int id;
	private String nome;
	private String pw;
	private String cidade;
	private String rua;
	private String fotografia;
	private String username;
	private float preco;
	private String contacto;
	private float ranking;
	
	public Restaurante()
	{
		id = -1;
		nome = "";
		pw ="";
		rua ="";
		fotografia="";
		ranking=(float) 0.0;
	}
	
	public Restaurante(int _id,String _cidade,String _nome,float _rank,String _rua)
	{
		id = _id;
		nome = _nome;
		cidade = _cidade;
		ranking=_rank;
		rua=_rua;
	}
	
	public Restaurante(int _id,String _cidade,String _nome,float _rank,String _rua,float _preco,String _contacto)
	{
		id = _id;
		nome = _nome;
		cidade = _cidade;
		ranking=_rank;
		rua=_rua;
		preco=_preco;
		contacto=_contacto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getFotografia() {
		return fotografia;
	}
	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}
	public float getRanking() {
		return ranking;
	}
	public void setRanking(float ranking) {
		this.ranking = ranking;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
}
