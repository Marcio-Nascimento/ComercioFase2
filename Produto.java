package comercio;

public class Produto {
	private String nome;
	private int codigo;
	private int categoria; 
	private double custoCompra;
	private double valorVenda;
	private int estoque;

	public Produto(String nome, int codigo, int categoria, double custoCompra, double valorVenda) {
		this.nome = nome;
		this.codigo = codigo;
		this.categoria = categoria;
		this.custoCompra = custoCompra;
		this.valorVenda = valorVenda;
		this.estoque = 0; 
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public double getCustoCompra() {
		return custoCompra;
	}

	public void setCustoCompra(double custoCompra) {
		this.custoCompra = custoCompra;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public String getCategoriaString() {
		if (categoria == 1) {
			return "Computadores e Laptops";
		} else if (categoria == 2) {
			return "Componentes de Hardware";
		} else if (categoria == 3) {
			return "Periféricos e Acessórios";
		} else {
			return "Categoria não definida";
		}
	}

	public void adicionarEstoque(int quantidade) {
		estoque += quantidade;
	}

	public void vender(int quantidade) {
		if (quantidade <= estoque) {
			estoque -= quantidade;
		} else {
			System.out.println("Estoque insuficiente.");
		}
	}

	public void exibirProduto() {
		System.out.println(nome + " ( Cód.: " + codigo + " | Estoque: " + estoque + " | Categoria: " + categoria
				+ " | Custo de Compra: " + custoCompra + " | Valor de Venda: " + valorVenda + " )");
	}

}
