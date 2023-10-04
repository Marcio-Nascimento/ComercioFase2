package comercio4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Loja {
	private double totalComprasSessaoAtual = 0.0;
	private double totalVendasSessaoAtual = 0.0;

	private double totalComprasHistorico = 0.0;
	private double totalVendasHistorico = 0.0;

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
				if (produto instanceof Categoria) {
					Categoria categoria = (Categoria) produto;
					if (categoria.getCategoria() == categoriaEscolhida) {
						produtoEncontrado = true;
						System.out.print(produto.getNome() + " (Código: " + produto.getCodigo() + " | Estoque: "
								+ produto.getEstoque());
						System.out.print(" | Nome Categoria: " + categoria.getNomeCategoria());
						System.out.print(" | Primeiro Atributo: " + categoria.getPrimeiroAtributo());
						System.out.print(" | Segundo Atributo: " + categoria.getSegundoAtributo());
						System.out.print(" | Custo de Compra: R$" + produto.getCustoCompra());
						System.out.println(" | Valor de Venda: R$" + produto.getValorVenda());
					}
				}
			}

			if (!produtoEncontrado) {
				System.out.println("Nenhum produto encontrado para a categoria escolhida.");
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

			System.out.printf("%s cadastrado com sucesso! | Código: %d | Estoque: %d", nome, codigo, estoque);

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
		entrada.nextLine();

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
					totalComprasSessaoAtual += custoTotal;
					registrarCompra(custoTotal);
					System.out.println("Estoque adicionado com sucesso!");

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
		entrada.nextLine();

		boolean produtoEncontrado = false;

		for (Produto produto : listaProdutos) {
			if (produto.getCodigo() == codigo) {
				produtoEncontrado = true;
				System.out.print("Deseja realmente remover o produto " + produto.getNome() + " (Cód.: "
						+ produto.getCodigo() + ")? \n1)Sim \n2)Não \nDigite: ");
				int confirmacao = entrada.nextInt();
				entrada.nextLine();

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
		entrada.nextLine();

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
					totalVendasSessaoAtual += valorVenda;
					registrarVenda(valorVenda);

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

	public void carregarDados() {
		try (BufferedReader br = new BufferedReader(new FileReader("Loja.txt"))) {
			String linha;
			listaProdutos.clear();

			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");

				if (dados.length >= 6) {
					int codigo = Integer.parseInt(dados[1]);
					int categoria = Integer.parseInt(dados[2]);
					int estoque = Integer.parseInt(dados[5]);
					double custoCompra = Double.parseDouble(dados[3]);
					double valorVenda = Double.parseDouble(dados[4]);

					if (categoria == 1) {

						String sistemaOperacional = dados[6];
						String quantidadeRAM = dados[7];
						Categoria categoriaProduto = new Categoria(dados[0], codigo, categoria, estoque, custoCompra,
								valorVenda, "Computadores e Laptops", sistemaOperacional, quantidadeRAM);
						listaProdutos.add(categoriaProduto);
					} else if (categoria == 2) {

						String primeiroAtributo = dados[6];
						String segundoAtributo = dados[7];
						Categoria categoriaProduto = new Categoria(dados[0], codigo, categoria, estoque, custoCompra,
								valorVenda, "Componentes de Hardware", primeiroAtributo, segundoAtributo);
						listaProdutos.add(categoriaProduto);
					} else if (categoria == 3) {

						String primeiroAtributo = dados[6];
						String segundoAtributo = dados[7];
						Categoria categoriaProduto = new Categoria(dados[0], codigo, categoria, estoque, custoCompra,
								valorVenda, "Periféricos e Acessórios", primeiroAtributo, segundoAtributo);
						listaProdutos.add(categoriaProduto);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao carregar os dados");
		}
	}

	public void salvarDados() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Loja.txt"))) {
			for (Produto produto : listaProdutos) {
				if (produto instanceof Categoria) {
					Categoria categoria = (Categoria) produto;
					bw.write(categoria.getNome() + "," + categoria.getCodigo() + "," + categoria.getCategoria() + ","
							+ categoria.getCustoCompra() + "," + categoria.getValorVenda() + ","
							+ categoria.getEstoque() + "," + categoria.getNomeCategoria() + ","
							+ categoria.getPrimeiroAtributo() + "," + categoria.getSegundoAtributo());
				} else {
					bw.write(produto.getNome() + "," + produto.getCodigo() + "," + produto.getCategoria() + ","
							+ produto.getCustoCompra() + "," + produto.getValorVenda() + "," + produto.getEstoque());
				}
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
		File arquivoSaldo = new File("saldo.txt");

		if (arquivoSaldo.exists() && arquivoSaldo.isFile()) {
			try (BufferedReader br = new BufferedReader(new FileReader(arquivoSaldo))) {
				String linha = br.readLine();

				if (linha != null && !linha.trim().isEmpty()) {
					saldoComercio = Double.parseDouble(linha);
					System.out.println("Saldo carregado com sucesso: R$" + saldoComercio);
				} else {
					System.out.println("O arquivo de saldo está vazio ou em um formato inválido.");
				}
			} catch (IOException e) {
				System.out.println("Erro ao carregar o saldo: " + e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("Formato inválido no arquivo de saldo.");
			}
		} else {
			System.out.println("O arquivo de saldo não existe.");
		}
	}

	void gerarRelatorioSessaoAtual() {
		System.out.println("Relatório da Sessão Atual:");
		System.out.println("Total Compras na Sessão Atual: R$" + totalComprasSessaoAtual);
		System.out.println("Total Vendas na Sessão Atual: R$" + totalVendasSessaoAtual);
		System.out.println("Saldo Arrecadado na Sessão Atual: R$" + (totalVendasSessaoAtual - totalComprasSessaoAtual));
	}

	void gerarRelatorioGeral() {
		System.out.println("Relatório Geral:");
		System.out
				.println("Total Compras no Histórico Completo: R$" + (totalComprasHistorico + totalComprasSessaoAtual));
		System.out.println("Total Vendas no Histórico Completo: R$" + (totalVendasHistorico + totalVendasSessaoAtual));
		System.out
				.println("Saldo Arrecadado no Histórico Completo: R$" + ((totalVendasHistorico + totalVendasSessaoAtual)
						- (totalComprasHistorico + totalComprasSessaoAtual)));
	}

	public void salvarDadosRelatorio() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Caixa_loja.txt"))) {

			bw.write("TotalComprasSessaoAtual: " + totalComprasSessaoAtual);
			bw.newLine();
			bw.write("TotalVendasSessaoAtual: " + totalVendasSessaoAtual);
			bw.newLine();

		} catch (IOException e) {
			System.out.println("Erro ao salvar os dados");
		}
	}

	public void carregarDadosRelatorio() {
		try (BufferedReader br = new BufferedReader(new FileReader("Caixa_loja.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("TotalComprasSessaoAtual: ")) {
					totalComprasSessaoAtual = Double.parseDouble(line.substring("TotalComprasSessaoAtual: ".length()));
				} else if (line.startsWith("TotalVendasSessaoAtual: ")) {
					totalVendasSessaoAtual = Double.parseDouble(line.substring("TotalVendasSessaoAtual: ".length()));
				} else {
					System.out.println("O arquivo de saldo está vazio ou em um formato inválido.");

				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao carregar os dados");
		}
	}

	private void registrarCompra(double valor) {
		totalComprasSessaoAtual += valor;
	}

	private void registrarVenda(double valor) {
		totalVendasSessaoAtual += valor;
	}

}
