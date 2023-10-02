package comercio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
	private static ArrayList<Produto> listaProdutos = new ArrayList<>();
	private static double saldoComercio = 1000.0;
	static Scanner entrada = new Scanner(System.in);

	void listarProdutos() {
		if (listaProdutos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado no sistema.");
		} else {
			System.out.println("Lista de Produtos:");
			for (Produto produto : listaProdutos) {
				produto.exibirProduto();
			}
		}
	}

	void listarProdutosPorCategoria() {
		if (listaProdutos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado no sistema.");
		} else {
			System.out.println("Escolha a categoria:");
			System.out.println("1. Computadores e Laptops");
			System.out.println("2. Componentes de Hardware");
			System.out.println("3. Periféricos e Acessórios");

			int categoriaEscolhida = entrada.nextInt();

			System.out.println("Produtos da categoria escolhida:");
			for (Produto produto : listaProdutos) {
				if (produto.getCategoria() == categoriaEscolhida) {
					produto.exibirProduto();
				}
			}
		}
	}

	void cadastrarProduto() {
		System.out.print("Digite o nome do produto: ");
		String nome = entrada.nextLine();

		System.out.println("Escolha a categoria do produto:");
		System.out.println("1. Computadores e Laptops");
		System.out.println("2. Componentes de Hardware");
		System.out.println("3. Periféricos e Acessórios");

		int categoria = 0;

		try {
			String categoriaInput = entrada.next();
			categoria = Integer.parseInt(categoriaInput);
		} catch (NumberFormatException e) {
			System.out.println("Categoria inválida. Escolha um número entre 1 e 3.");
			return;
		}

		if (categoria < 1 || categoria > 3) {
			System.out.println("Categoria inválida. Escolha um número entre 1 e 3.");
			return;
		}

		System.out.print("Digite o custo de compra do produto: ");
		double custoCompra = entrada.nextDouble();

		System.out.print("Digite o valor de venda do produto: ");
		double valorVenda = entrada.nextDouble();

		entrada.nextLine();

		int codigo = listaProdutos.size() + 1;
		listaProdutos.add(new Produto(nome, codigo, categoria, custoCompra, valorVenda));

		System.out.println(nome + " cadastrado com sucesso. Código: " + codigo + ", Estoque: 0");
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

				double custoTotal = quantidade * produto.getCustoCompra();
				if (quantidade > 0 && custoTotal <= saldoComercio) {
					produto.adicionarEstoque(quantidade);
					saldoComercio -= custoTotal;
					System.out.println("Estoque atualizado com sucesso!");
				}else if (quantidade <= 0) {
					System.out.println("Quantidade invalída");
					
				}else {System.out.println("Saldo insuficiente no caixa da loja");}

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
						+ produto.getCodigo() + ")? (1 - Sim, 2 - Não): ");
				int confirmacao = entrada.nextInt();
				if (confirmacao == 1) {
					saldoComercio += produto.getEstoque() * produto.getCustoCompra();
					listaProdutos.remove(produto);
					System.out.println("Produto removido com sucesso!");
					return;
				} else {
					System.out.println("Operação cancelada.");
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

		for (Produto produto : listaProdutos) {
			if (produto.getCodigo() == codigo) {
				System.out.print("Quantidade a ser vendida: ");
				int quantidadeVendida = entrada.nextInt();

				if (quantidadeVendida <= produto.getEstoque()) {
					produto.vender(quantidadeVendida);
					saldoComercio += quantidadeVendida * produto.getValorVenda();
					System.out.println("Venda realizada com sucesso! Estoque atualizado.");
					return;
				} else {
					System.out.println("Quantidade insuficiente em estoque!");
					return;
				}
			}		System.out.println("Código inválido!");}

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
				listaProdutos.add(new Produto(nome, codigo, categoria, custoCompra, valorVenda));
				listaProdutos.get(listaProdutos.size() - 1).adicionarEstoque(estoque);
			}
		} catch (IOException e) {
			System.out.println("Erro ao carregar os dados.");
		}
	}

	void salvarDados() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Loja.txt"))) {
			for (Produto produto : listaProdutos) {
				bw.write(produto.getNome() + "," + produto.getCodigo() + "," + produto.getCategoria() + ","
						+ produto.getCustoCompra() + "," + produto.getValorVenda() + "," + produto.getEstoque());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao salvar os dados.");
		}
	}

	public void salvarSaldo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("saldo.txt"))) {
			bw.write(String.valueOf(saldoComercio));
		} catch (IOException e) {
			System.out.println("Erro ao salvar o saldo.");
		}
	}

	public void carregarSaldo() {
		try (BufferedReader br = new BufferedReader(new FileReader("saldo.txt"))) {
			String linha = br.readLine();
			saldoComercio = Double.parseDouble(linha);
		} catch (IOException e) {
			System.out.println("Erro ao carregar o saldo.");
		}
	}

}
