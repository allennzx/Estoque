package br.edu.aesa.app;

import java.util.List;
import java.util.Scanner;

import br.edu.aesa.model.Produto;
import br.edu.aesa.service.EstoqueService;

/**
 * Classe principal com o menu interativo em console para manipular o
 * estoque de produtos atraves da classe EstoqueService.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EstoqueService estoqueService = new EstoqueService();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    removerProduto();
                    break;
                case 0:
                    System.out.println("\nEncerrando o programa. Ate mais!");
                    break;
                default:
                    System.out.println("\nOpcao invalida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n==========================================");
        System.out.println("       CONTROLE DE ESTOQUE - MENU");
        System.out.println("==========================================");
        System.out.println("[1] Cadastrar novo produto");
        System.out.println("[2] Listar todos os produtos");
        System.out.println("[3] Buscar produto por codigo");
        System.out.println("[4] Atualizar produto");
        System.out.println("[5] Remover produto");
        System.out.println("[0] Sair do programa");
        System.out.print("Escolha uma opcao: ");
    }

    private static int lerOpcaoMenu() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            // Entrada invalida (nao numerica); retorna um valor que cai
            // no "default" do switch, sem derrubar o programa.
            return -1;
        }
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- Cadastro de Produto ---");
        try {
            int codigo = lerInteiro("Codigo: ");
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine().trim();
            double preco = lerDouble("Preco: ");
            int quantidade = lerInteiro("Quantidade: ");

            if (nome.isEmpty()) {
                System.out.println("Erro: o nome do produto nao pode ser vazio.");
                return;
            }

            Produto produto = new Produto(codigo, nome, categoria, preco, quantidade);
            boolean sucesso = estoqueService.cadastrar(produto);

            if (sucesso) {
                System.out.println("Produto cadastrado com sucesso!");
            } else {
                System.out.println("Erro: ja existe um produto cadastrado com o codigo " + codigo + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: valor numerico invalido. Cadastro cancelado.");
        }
    }

    private static void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        if (estoqueService.estaVazio()) {
            System.out.println("Nenhum produto cadastrado no momento.");
            return;
        }

        List<Produto> produtos = estoqueService.listarTodos();
        System.out.println("Total de produtos: " + produtos.size());
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    private static void buscarProduto() {
        System.out.println("\n--- Buscar Produto por Codigo ---");
        try {
            int codigo = lerInteiro("Informe o codigo do produto: ");
            Produto produto = estoqueService.buscarPorId(codigo);

            if (produto != null) {
                System.out.println("Produto encontrado:");
                System.out.println(produto);
            } else {
                System.out.println("Nenhum item encontrado com o codigo informado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: codigo invalido.");
        }
    }

    private static void atualizarProduto() {
        System.out.println("\n--- Atualizar Produto ---");
        try {
            int codigo = lerInteiro("Informe o codigo do produto que deseja atualizar: ");

            if (estoqueService.buscarPorId(codigo) == null) {
                System.out.println("Nenhum item encontrado com o codigo informado.");
                return;
            }

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Nova categoria: ");
            String categoria = scanner.nextLine().trim();
            double preco = lerDouble("Novo preco: ");
            int quantidade = lerInteiro("Nova quantidade: ");

            Produto novosDados = new Produto(codigo, nome, categoria, preco, quantidade);
            boolean sucesso = estoqueService.atualizar(codigo, novosDados);

            if (sucesso) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum item encontrado com o codigo informado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: valor numerico invalido. Atualizacao cancelada.");
        }
    }

    private static void removerProduto() {
        System.out.println("\n--- Remover Produto ---");
        try {
            int codigo = lerInteiro("Informe o codigo do produto que deseja remover: ");
            boolean sucesso = estoqueService.remover(codigo);

            if (sucesso) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum item encontrado com o codigo informado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: codigo invalido.");
        }
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
    }
}
