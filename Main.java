package comercio2;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/*Pedro Henrique
- Márcio Vitor de S. Nascimento
- Luiz Neto
- Ramon Gonçalves
- Sairo Soares
- Vinicius Cândido Firmino
- Carlos Daniel
*/
public class Main {
	public static void main(String[] args) {
		criarArquivo();

		Loja loja = new Loja();
		loja.carregarDados();
		loja.carregarSaldo();
		loja.carregarDadosRelatorio();
		Scanner entrada = new Scanner(System.in);

		int opcao = 1;

		while (opcao != 7) {
			exibirMenu();
			try {
				opcao = entrada.nextInt();
				entrada.nextLine();

				if (opcao == 1) {

					System.out.println("1) Listar todos os produtos");
					System.out.println("2) Listar produtos por categoria");
					System.out.print("Escolha a opção: ");

					int listarOpcao = entrada.nextInt();

					if (listarOpcao == 1) {
						loja.listarProdutos();
					} else if (listarOpcao == 2) {
						loja.listarProdutosPorCategoria();
					} else {
						System.out.println("Opção invalída.");
					}
				} else if (opcao == 2) {
					loja.cadastrarProduto();
				} else if (opcao == 3) {
					loja.adicionarEstoque();
				} else if (opcao == 4) {
					loja.removerProduto();
				} else if (opcao == 5) {
					loja.venderProduto();
				} else if (opcao == 6) {
					loja.gerarRelatorioSessaoAtual();
					loja.gerarRelatorioGeral();
				} else if (opcao == 7) {
					loja.salvarDados();
					loja.salvarSaldo();
					loja.salvarDadosRelatorio();
					System.out.println("Obrigado por usar nosso sistema!");
				} else {
					System.out.println("Opção inválida, escolha uma opção válida.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida, escolha uma opção válida.");
				entrada.nextLine();
			}
		} entrada.close();

	}

	private static void exibirMenu() {
		System.out.println("\n=============== Loja SI =================:");
		System.out.println("1) Listar produtos");
		System.out.println("2) Cadastrar novo produto");
		System.out.println("3) Adicionar estoque de um produto");
		System.out.println("4) Remover produto");
		System.out.println("5) Vender produto");
		System.out.println("6) Relatório de compra e vendas");
		System.out.println("7) Sair do programa");
		System.out.print("\nEscolha uma opção: ");
	}

	private static void criarArquivo() {
		String nomeArquivo = "Loja.txt";
		File arquivo = new File(nomeArquivo);

		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
				System.out.println("Arquivo " + nomeArquivo + " criado com sucesso.");
			} catch (IOException e) {
				System.out.println("Erro ao criar o arquivo " + nomeArquivo);
			}
		}
	}
}
