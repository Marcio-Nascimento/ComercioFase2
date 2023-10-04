package comercio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Loja {
	private ArrayList<Produto> listaProdutos = new ArrayList<>();
	private double saldoComercio = 1000.0;
	
	static Scanner entrada = new Scanner(System.in);

	void listarProdutos() {
		if (listaProdutos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado no sistema.");
		} else {
			System.out.println("Lista de Produtos:");
			for (Produto produto : listaProdutos) {
				System.out.println(produto.toString());
			}
		}
	}

	void listarProdutosPorCategoria() {
		if (listaProdutos.isEmpty()) {
			System.out.println("Não existem produtos cadastrados!");
		} else {

			System.out.println("\n1. Computadores e Laptops");
			System.out.println("2. Componentes de Hardware");
			System.out.println("3. Periféricos e Acessórios");
			System.out.print("Escolha a categoria:");

			int categoriaEscolhida = entrada.nextInt();
			entrada.nextLine();

			boolean produtoEncontrado = false;

			System.out.println("Produtos da categoria escolhida:");
			for (Produto produto : listaProdutos) {

				if (produto.getCategoria() == categoriaEscolhida) {
					produtoEncontrado = true;
					System.out.println(produto.toStringCategoria());
				}

			}
			if (!produtoEncontrado) {
				System.out.println("Não existem produtos cadastrados para essa categoria!");
			}
		}
	}

	void cadastrarProduto() {
		System.out.print("Digite o nome do produto: ");
		String nome = entrada.nextLine();

		System.out.print("Digite o custo de compra do produto: ");
		double custoCompra = entrada.nextDouble();

		System.out.print("Digite o valor de venda do produto: ");
		double valorVenda = entrada.nextDouble();

		int codigo = listaProdutos.size() + 1;

		int estoque = 0;

		System.out.print("Deseja adicionar estoque? \n1)Sim \n2)Não \nDigite: ");
		int desejaEstoque = entrada.nextInt();

		if (desejaEstoque == 1) {
			System.out.print("Digite o estoque: ");
			int quantidade = entrada.nextInt();
			entrada.nextLine();

			if (quantidade > 0) {
				estoque = quantidade;

			} else {
				System.out.println("Digite um estoque válido!");
				return;
			}

		} else if (desejaEstoque == 2) {
			estoque = 0;

		} else {
			System.out.println("Opção inválida!");
		}

		System.out.println("1. Computadores e Laptops");
		System.out.println("2. Componentes de Hardware");
		System.out.println("3. Periféricos e Acessórios");
		System.out.print("Escolha a categoria do produto: ");

		int categoria = 0;

		try {
			categoria = entrada.nextInt();
			entrada.nextLine();
			if (categoria < 1 || categoria > 3) {
				System.out.println("Categoria inválida. Escolha um número entre 1 e 3");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("Categoria inválida. Escolha um número entre 1 e 3");
			return;
		}

		if (categoria == 1) {

			System.out.print("Descreva o sistema operacional do dispositivo: ");
			String sistemaOperacional = entrada.nextLine();

			System.out.print("Digite a quantidade de RAM do dispositivo: ");
			String quantidadeRam = entrada.nextLine();
			
			listaProdutos.add(new Categoria.computadorELaptop(nome, listaProdutos.size() + 1, categoria, estoque,
					custoCompra, valorVenda, "Computadores e Laptops", sistemaOperacional, quantidadeRam));

			System.out.printf("<%s> cadastrado com sucesso! | Código: <%d> | Estoque: <%d>", nome, codigo, estoque);

		} else if (categoria == 2) {
			System.out.print("Descreva o país de origem do componente: ");
			String paisOrigem = entrada.nextLine();

			System.out.print("Descreva o fabricante do componente: ");
			String fabricante = entrada.nextLine();

			listaProdutos.add(new Categoria.componentesHardware(nome, listaProdutos.size() + 1, categoria, estoque,
					custoCompra, valorVenda, "Componentes de Hardware", paisOrigem, fabricante));

			System.out.printf("<%s> cadastrado com sucesso! | Código: <%d> | Estoque: <%d>", nome, codigo, estoque);

		} else if (categoria == 3) {
			System.out.print("Descreva o tipo do equipamento: ");
			String tipo = entrada.nextLine();

			System.out.print("Equipamento sem fio? (Sim/Não): ");
			String semFio = entrada.nextLine();

			listaProdutos.add(new Categoria.perifericosEAcessorios(nome, listaProdutos.size() + 1, categoria, estoque,
					custoCompra, valorVenda, "Periféricos e Acessórios", tipo, semFio));

			System.out.printf("<%s> cadastrado com sucesso! | Código: <%d> | Estoque: <%d>", nome, codigo, estoque);

		}

	}

	void adicionarEstoque() {
		if (listaProdutos.isEmpty()) {
			System.out.println("Não existem produtos cadastrados!");
			return;
		}

		listarProdutos();

		System.out.print("Digite o código do produto que deseja adicionar estoque: ");
		int codigo = entrada.nextInt();

		boolean produtoEncontrado = false;

		for (Produto produto : listaProdutos) {
			if (produto.getCodigo() == codigo) {
				produtoEncontrado = true;
				System.out.print("Quantidade a ser adicionada: ");
				int quantidade = entrada.nextInt();
				entrada.nextLine();

				double custoTotal = quantidade * produto.getCustoCompra();
				if (quantidade > 0 && custoTotal <= saldoComercio) {
					produto.adicionarEstoque(quantidade);
					saldoComercio -= custoTotal;
					historicoTransacoes.add(new Transacao(custoTotal, "compra"));
					escreverHistoricoTransacoesNoArquivo();
				} else if (quantidade <= 0) {
					System.out.println("Quantidade inválida!");

				} else {
					System.out.println("Saldo insuficiente no caixa da loja!");
				}

			}

		}
		if (!produtoEncontrado)
			System.out.println("Código inválido!");
	}

	void removerProduto() {
		if (listaProdutos.isEmpty()) {
			System.out.println("Não existem produtos cadastrados para remover!");
			return;
		}

		listarProdutos();

		System.out.print("Digite o código do produto que deseja remover: ");
		int codigo = entrada.nextInt();

		boolean produtoEncontrado = false;

		for (Produto produto : listaProdutos) {
			if (produto.getCodigo() == codigo) {
				produtoEncontrado = true;
				System.out.print("Deseja realmente remover o produto " + produto.getNome() + " (Cód.: "
						+ produto.getCodigo() + ")? \n1)Sim \n2)Não \nDigite: ");
				int confirmacao = entrada.nextInt();
				if (confirmacao == 1) {
					saldoComercio += produto.getEstoque() * produto.getCustoCompra();
					listaProdutos.remove(produto);
					System.out.println("Produto removido com sucesso!");
					return;
				} else {
					System.out.println("Operação cancelada!");
					return;
				}
			}
		}
		if (!produtoEncontrado)
			System.out.println("Código inválido!");
	}

	void venderProduto() {

		if (listaProdutos.isEmpty()) {
			System.out.println("Não existem produtos cadastrados para vender!");
			return;
		}

		listarProdutos();

		System.out.print("Digite o código do produto que deseja vender: ");
		int codigo = entrada.nextInt();

		boolean produtoEncontrado = false;

		for (Produto produto : listaProdutos) {
			if (produto.getCodigo() == codigo) {
				produtoEncontrado = true;
				System.out.print("Quantidade a ser vendida: ");
				int quantidadeVendida = entrada.nextInt();
				entrada.nextLine();

				if (quantidadeVendida <= produto.getEstoque()) {
					produto.vender(quantidadeVendida);
					double valorVenda = quantidadeVendida * produto.getValorVenda();
					saldoComercio += valorVenda;

					historicoTransacoes.add(new Transacao(valorVenda, "venda"));
					escreverHistoricoTransacoesNoArquivo();

					System.out.println("Venda realizada com sucesso! Estoque atualizado!");
					return;
				} else {
					System.out.println("Quantidade insuficiente em estoque!");
					return;
				}
			}

		}

		if (!produtoEncontrado) {
			System.out.println("Código inválido!");
		}
	}

	void gerarRelatorio() {
		System.out.println("\nRelatório:");
		System.out.println("Saldo em caixa: R$" + saldoComercio);

	}

	void carregarDados() {
		try (BufferedReader br = new BufferedReader(new FileReader("Loja.txt"))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(",");
				String nome = partes[0];
				int codigo = Integer.parseInt(partes[1]);
				int categoria = Integer.parseInt(partes[2]);
				double custoCompra = Double.parseDouble(partes[3]);
				double valorVenda = Double.parseDouble(partes[4]);
				int estoque = Integer.parseInt(partes[5]);
				String nomeCategoria = partes[6];
				String primeiroAtributo = partes[7];
				String segundoAtributo = partes[8];
				listaProdutos.add(new Categoria(nome, codigo, categoria, estoque, custoCompra, valorVenda, nomeCategoria, primeiroAtributo, segundoAtributo));
				listaProdutos.get(listaProdutos.size() - 1).setEstoque(estoque);
			}
		} catch (IOException e) {
			System.out.println("Erro ao carregar os dados");
		}
	}

	void salvarDados() {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Loja.txt"))) {
	        for (Produto produto : listaProdutos) {
	            bw.write(produto.getNome() + "," + produto.getCodigo() + "," + produto.getCategoria() + ","
	                    + produto.getCustoCompra() + "," + produto.getValorVenda() + "," + produto.getEstoque() + ","
	                    + Categoria.nomeCategoria + "," + Categoria.primeiroAtributo + ","
	                    + Categoria.segundoAtributo);
	            bw.newLine();
	        }
	    } catch (IOException e) {
	        System.out.println("Erro ao salvar os dados");
	    }
	}


	public void salvarSaldo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("saldo.txt"))) {
			bw.write(String.valueOf(saldoComercio));
		} catch (IOException e) {
			System.out.println("Erro ao salvar o saldo");
		}
	}

	public void carregarSaldo() {
		try (BufferedReader br = new BufferedReader(new FileReader("saldo.txt"))) {
			String linha = br.readLine();
			saldoComercio = Double.parseDouble(linha);
		} catch (IOException e) {
			System.out.println("Erro ao carregar o saldo");
		}
	}

	void relatorioSessaoAtual() {
		System.out.println("Relatório da sessão atual: ");

		double totalComprasSessaoAtual = 0.0;
		double totalVendasSessaoAtual = 0.0;

		for (Transacao transacao : historicoTransacoes) {
			if (transacao.getTipo().equals("compra")) {
				totalComprasSessaoAtual += transacao.getValor();
			} else if (transacao.getTipo().equals("venda")) {
				totalVendasSessaoAtual += transacao.getValor();
			}
		}

		double saldoArrecadadoSessao = totalVendasSessaoAtual - totalComprasSessaoAtual;
		System.out.println("Total Compras na Sessão Atual: R$" + (-totalComprasSessaoAtual));
		System.out.println("Total Vendas na Sessão Atual: R$" + totalVendasSessaoAtual);
		System.out.println("Saldo Arrecadado na Sessão Atual: R$" + saldoArrecadadoSessao);
	}

	void relatorioHistoricoCompleto() {
		System.out.println("Relatório geral: ");

		double totalComprasHistoricoCompleto = 0.0;
		double totalVendasHistoricoCompleto = 0.0;

		for (Transacao transacao : historicoTransacoes) {
			if (transacao.getTipo().equals("compra")) {
				totalComprasHistoricoCompleto += transacao.getValor();
			} else if (transacao.getTipo().equals("venda")) {
				totalVendasHistoricoCompleto += transacao.getValor();
			}
		}

		double saldoArrecadadoHistoricoCompleto = totalVendasHistoricoCompleto - totalComprasHistoricoCompleto;

		System.out.println("Total Compras no Histórico Completo: R$" + totalComprasHistoricoCompleto);
		System.out.println("Total Vendas no Histórico Completo: R$" + totalVendasHistoricoCompleto);
		System.out.println("Saldo Arrecadado no Histórico Completo: R$" + saldoArrecadadoHistoricoCompleto);
	}

	public void iniciarSessao() {
		historicoTransacoes.clear();

	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Transacao> lerHistoricoTransacoesDoArquivo() {
	    ArrayList<Transacao> historico = new ArrayList<>();

	    try {
	        FileInputStream fileInputStream = new FileInputStream("historico.txt");
	        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

	        historico = (ArrayList<Transacao>) objectInputStream.readObject();

	        objectInputStream.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("Arquivo 'historico.txt' não encontrado.");
	    } catch (IOException e) {
	        System.out.println("Erro ao ler o arquivo 'historico.txt'.");
	    } catch (ClassNotFoundException e) {
	        System.out.println("Classe não encontrada ao ler o arquivo 'historico.txt'.");
	    }

	    return historico;
	}
	void escreverHistoricoTransacoesNoArquivo() {
	    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("historico.txt"))) {
	        objectOutputStream.writeObject(historicoTransacoes);
	    } catch (IOException e) {
	        System.out.println("Erro ao escrever o arquivo 'historico.txt'.");
	    }
	}


	class Transacao {
		private double valor;
		private String tipo;

		public Transacao(double valor, String tipo) {
			this.valor = valor;
			this.tipo = tipo;
		}

		public double getValor() {
			return valor;
		}

		public String getTipo() {
			return tipo;
		}

	}

	ArrayList<Loja.Transacao> historicoTransacoes = (ArrayList<Loja.Transacao>) lerHistoricoTransacoesDoArquivo();

}
