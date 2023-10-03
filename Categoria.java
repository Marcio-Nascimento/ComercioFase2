package comercio2;

public class Categoria extends Produto {
	private String nomeCategoria;

	public Categoria(String nome, int codigo, int categoria, double custoCompra, double valorVenda,
			String nomeCategoria) {
		super(nome, codigo, categoria, custoCompra, valorVenda);
		this.nomeCategoria = nomeCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public static class computadorELaptop extends Categoria {
		private String sistemaOperacional;
		private int quantidadeRam;

		public computadorELaptop(String nome, int codigo, int categoria, double custoCompra, double valorVenda,
				String nomeCategoria, String sistemaOperacional, int quantidadeRam) {
			super(nome, codigo, categoria, custoCompra, valorVenda, "Computador e Laptop");
			this.sistemaOperacional = sistemaOperacional;
			this.quantidadeRam = quantidadeRam;
		}

		public String getSistemaOperacional() {
			return sistemaOperacional;
		}

		public void setSistemaOperacional(String sistemaOperacional) {
			this.sistemaOperacional = sistemaOperacional;
		}

		public int getQuantidadeRam() {
			return quantidadeRam;
		}

		public void setQuantidadeRam(int quantidadeRam) {
			this.quantidadeRam = quantidadeRam;
		}
		
		@Override
		public String toString() {           
			String printar = super.toString() + String.format(" | Sistema operacional: %s | Quantidade RAM: %d)", sistemaOperacional, quantidadeRam);
			return printar;
            }
	}

	public static class componentesHardware extends Categoria {
		private String paisOrigem;
		private String fabricante;

		public componentesHardware(String nome, int codigo, int categoria, double custoCompra, double valorVenda,
				String nomeCategoria, String paisOrigem, String fabricante) {
			super(nome, codigo, categoria, custoCompra, valorVenda, "Componentes de hardware");
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
		public String toString() {           
			String printar = super.toString() + String.format(" | País de origem: %s | Fabricante: %s)", paisOrigem, fabricante);
			return printar;
            }
	}

	public static class perifericosEAcessorios extends Categoria {
		private String tipo;
		private String semFio;

		public perifericosEAcessorios(String nome, int codigo, int categoria, double custoCompra, double valorVenda,
				String nomeCategoria, String tipo, String semFio) {
			super(nome, codigo, categoria, custoCompra, valorVenda, "PerifÃ©ricos e AcessÃ³rios");
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
		public String toString() {           
			String printar = super.toString() + String.format(" | Tipo: %s | Sem fio: %s)", tipo, semFio);
			return printar;
            }
	}

}
