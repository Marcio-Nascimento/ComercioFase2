package comercio2;

public class Categoria extends Produto {
    private String nomeCategoria;
    private String primeiroAtributo;
    private String segundoAtributo;

    public Categoria(String nome, int codigo, int categoria, int estoque, double custoCompra, double valorVenda,
                     String nomeCategoria, String primeiroAtributo, String segundoAtributo) {
        super(nome, codigo, categoria, estoque, custoCompra, valorVenda);
        this.nomeCategoria = nomeCategoria;
        this.primeiroAtributo = primeiroAtributo;
        this.segundoAtributo = segundoAtributo;
    }


	public String getSegundoAtributo() {
		return segundoAtributo;
	}

	public String getPrimeiroAtributo() {
		return primeiroAtributo;
	}

	public String getNomeCategoria() {
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
		        double valorVenda, String nomeCategoria, String sistemaOperacional, String quantidadeRam) {
		    super(nome, codigo, categoria, estoque, custoCompra, valorVenda, nomeCategoria, sistemaOperacional, quantidadeRam);
		    this.sistemaOperacional = sistemaOperacional;
		    this.quantidadeRam = quantidadeRam;
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
					" | Sistema operacional: %s | Quantidade RAM: %s | Custo de Compra: R$%.2f | Valor de Venda: R$%.2f | Nome categoria: %s",
					sistemaOperacional, quantidadeRam, getCustoCompra(), getValorVenda(), getNomeCategoria());

			return printar;
		}
	}

	public static class componentesHardware extends Categoria {
		private String paisOrigem;
		private String fabricante;

		public componentesHardware(String nome, int codigo, int categoria, int estoque, double custoCompra,
		        double valorVenda, String nomeCategoria, String paisOrigem, String fabricante) {
		    super(nome, codigo, categoria, estoque, custoCompra, valorVenda, nomeCategoria, paisOrigem, fabricante);
		    this.paisOrigem = paisOrigem;
		    this.fabricante = fabricante;
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
					" | Pais origem: %s| Fabricante: %s | Custo de Compra: R$%.2f | Valor de Venda: R$%.2f | Nome categoria: %s",
					paisOrigem, fabricante, getCustoCompra(), getValorVenda(), getNomeCategoria());
			return printar;
		}
	}

	public static class perifericosEAcessorios extends Categoria {
		private String tipo;
		private String semFio;

		public perifericosEAcessorios(String nome, int codigo, int categoria, int estoque, double custoCompra,
		        double valorVenda, String nomeCategoria, String tipo, String semFio) {
		    super(nome, codigo, categoria, estoque, custoCompra, valorVenda, nomeCategoria, tipo, semFio);
		    this.tipo = tipo;
		    this.semFio = semFio;
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
