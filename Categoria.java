package comercio2;

public class Categoria extends Produto {
	public static String nomeCategoria;
	public static String primeiroAtributo;
	public static String segundoAtributo;

	public Categoria(String nome, int codigo, int categoria, int estoque, double custoCompra, double valorVenda,
			String nomeCategoria, String primeiroAtributo, String segundoAtributo) {
		super(nome, codigo, categoria, estoque, custoCompra, valorVenda);
		Categoria.nomeCategoria = nomeCategoria;
		Categoria.primeiroAtributo = primeiroAtributo;
		Categoria.segundoAtributo = segundoAtributo;

	}

	public static String getSegundoAtributo() {
		return segundoAtributo;
	}
	
	public static String getPrimeiroAtributo() {
		return primeiroAtributo;
	}

	public static String getNomeCategoria() {
		return nomeCategoria;
	}

	@Override
	public String toString() {
		return getNome() + " (Código: " + getCodigo() + " | Estoque: " + getEstoque() + " | Categoria: "
				+ getCategoria() + " | Custo de Compra: R$" + getCustoCompra() + " | Valor de Venda: R$"
				+ getValorVenda();
	}

	public static class computadorELaptop extends Categoria {
		private String sistemaOperacional;
		private String quantidadeRam;

		public computadorELaptop(String nome, int codigo, int categoria, int estoque, double custoCompra,
				double valorVenda, String nomeCategoria, String primeiroAtributo, String segundoAtributo) {
			super(nome, codigo, categoria, estoque, custoCompra, valorVenda, "Computador e Laptop", primeiroAtributo,
					segundoAtributo);
			this.sistemaOperacional = primeiroAtributo;
			this.quantidadeRam = segundoAtributo;
		}

		public String getSistemaOperacional() {
			return sistemaOperacional;
		}

		public void setSistemaOperacional(String sistemaOperacional) {
			this.sistemaOperacional = sistemaOperacional;
		}

		public String getQuantidadeRam() {
			return quantidadeRam;
		}

		public void setQuantidadeRam(String quantidadeRam) {
			this.quantidadeRam = quantidadeRam;
		}

		@Override
		public String toStringCategoria() {
			String printar = super.toStringCategoria() + String.format(
					" | Sistema operacional: %s| Quantidade RAM: %d | Custo de Compra: R$%.2f | Valor de Venda: R$%.2f",
					sistemaOperacional, quantidadeRam, getCustoCompra(), getValorVenda());
			return printar;
		}
	}

	public static class componentesHardware extends Categoria {
		private String paisOrigem;
		private String fabricante;

		public componentesHardware(String nome, int codigo, int categoria, int estoque, double custoCompra,
				double valorVenda, String nomeCategoria, String primeiroAtributo, String segundoAtributo) {
			super(nome, codigo, categoria, estoque, custoCompra, valorVenda, "Componentes de hardware",
					primeiroAtributo, segundoAtributo);
			this.paisOrigem = primeiroAtributo;
			this.fabricante = segundoAtributo;
		}

		public String getPaisOrigem() {
			return paisOrigem;
		}

		public void setPaisOrigem(String paisOrigem) {
			this.paisOrigem = paisOrigem;
		}

		public String getFabricante() {
			return fabricante;
		}

		public void setFabricante(String fabricante) {
			this.fabricante = fabricante;
		}

		@Override
		public String toStringCategoria() {
			String printar = super.toStringCategoria() + String.format(
					" | Pais origem: %s| Fabricante: %d | Custo de Compra: R$%.2f | Valor de Venda: R$%.2f", paisOrigem,
					fabricante, getCustoCompra(), getValorVenda());
			return printar;
		}
	}

	public static class perifericosEAcessorios extends Categoria {
		private String tipo;
		private String semFio;

		public perifericosEAcessorios(String nome, int codigo, int categoria, int estoque, double custoCompra,
				double valorVenda, String nomeCategoria, String primeiroAtributo, String segundoAtributo) {
			super(nome, codigo, categoria, estoque, custoCompra, valorVenda, "Periféricos e Acessórios",
					primeiroAtributo, segundoAtributo);
			this.tipo = primeiroAtributo;
			this.semFio = segundoAtributo;

		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getSemFio() {
			return semFio;
		}

		public void setSemFio(String semFio) {
			this.semFio = semFio;
		}

		@Override
		public String toStringCategoria() {
			String printar = super.toStringCategoria()
					+ String.format(" | Tipo: %s| Sem fio: %s | Custo de Compra: R$%.2f | Valor de Venda: R$%.2f", tipo,
							semFio, getCustoCompra(), getValorVenda());
			return printar;
		}

	}

}
