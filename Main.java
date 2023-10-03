package comercio2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        criarArquivo();

        Loja loja = new Loja();
        loja.carregarDados();
        loja.carregarSaldo();
        Scanner entrada = new Scanner(System.in);

        int opcao = 1;

        while (opcao != 7) {
            exibirMenu();
            opcao = entrada.nextInt();
            entrada.nextLine();

            if (opcao == 1) {
                
                System.out.println("1) Listar todos os produtos");
                System.out.println("2) Listar produtos por categoria");
                System.out.print("Escolha a opÃ§Ã£o:");

                int listarOpcao = entrada.nextInt();

                if (listarOpcao == 1) {
                    loja.listarProdutos();
                } else if (listarOpcao == 2) {
                    loja.listarProdutosPorCategoria();
                } else {
                    System.out.println("OpÃ§Ã£o invÃ¡lida.");
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
                loja.relatorioSessaoAtual();
                loja.relatorioHistoricoCompleto();
            } else if (opcao == 7) {
                loja.salvarDados();
                loja.salvarSaldo();
                System.out.println("Obrigado por usar nosso sistema!");
            } else {
                System.out.println("OpÃ§Ã£o invÃ¡lida, escolha uma opÃ§Ã£o vÃ¡lida.");
            }
        }
        entrada.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=============== Loja SI =================:");
        System.out.println("1) Listar produtos");
        System.out.println("2) Cadastrar novo produto");
        System.out.println("3) Adicionar estoque de um produto");
        System.out.println("4) Remover produto");
        System.out.println("5) Vender produto");
        System.out.println("6) RelatÃ³rio de compra e vendas");
        System.out.println("7) Sair do programa");
        System.out.print("\nEscolha uma opÃ§Ã£o: ");
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
