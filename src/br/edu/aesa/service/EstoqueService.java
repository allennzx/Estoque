package br.edu.aesa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.aesa.model.Produto;

/**
 * Classe de servico/repositorio responsavel por encapsular a estrutura de
 * dados escolhida (HashMap) e fornecer as operacoes de CRUD sobre os
 * produtos do estoque.
 *
 * Estrutura escolhida: HashMap<Integer, Produto>
 *
 * Justificativa:
 * - O codigo do produto e unico, entao ele funciona perfeitamente como
 *   chave do mapa.
 * - Busca, insercao, atualizacao e remocao por codigo tem complexidade
 *   media O(1), o que e ideal para um sistema de estoque onde a operacao
 *   mais comum e localizar um produto especifico pelo seu codigo.
 * - Nao ha necessidade de manter os produtos ordenados por codigo (nao e
 *   um requisito do dominio), entao o custo extra de uma TreeMap
 *   (O(log n)) nao se justifica aqui.
 * - Desvantagem: o HashMap nao garante nenhuma ordem de iteracao. Para
 *   este cenario isso nao e um problema, mas seria um ponto a considerar
 *   caso o requisito fosse listar sempre em ordem de cadastro ou de
 *   codigo.
 */
public class EstoqueService {

    private final Map<Integer, Produto> estoque = new HashMap<>();

    /**
     * Create: cadastra um novo produto, validando que o codigo (ID) ainda
     * nao existe no estoque.
     *
     * @return true se o produto foi cadastrado, false se ja existe um
     *         produto com o mesmo codigo.
     */
    public boolean cadastrar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto nao pode ser nulo.");
        }
        if (estoque.containsKey(produto.getCodigo())) {
            return false;
        }
        estoque.put(produto.getCodigo(), produto);
        return true;
    }

    /**
     * Read: busca um unico produto pelo codigo.
     *
     * @return o produto encontrado ou null se nao existir.
     */
    public Produto buscarPorId(int codigo) {
        return estoque.get(codigo);
    }

    /**
     * Read: lista todos os produtos cadastrados no estoque.
     *
     * @return lista com todos os produtos (vazia se o estoque estiver vazio).
     */
    public List<Produto> listarTodos() {
        return new ArrayList<>(estoque.values());
    }

    /**
     * Update: localiza o produto pelo codigo e atualiza seus dados.
     *
     * @return true se o produto foi encontrado e atualizado, false se o
     *         codigo informado nao existe no estoque.
     */
    public boolean atualizar(int codigo, Produto novosDados) {
        if (!estoque.containsKey(codigo)) {
            return false;
        }
        if (novosDados == null) {
            throw new IllegalArgumentException("Novos dados nao podem ser nulos.");
        }
        // Garante que o codigo do produto atualizado permaneca o mesmo
        // que foi usado como chave de busca.
        novosDados.setCodigo(codigo);
        estoque.put(codigo, novosDados);
        return true;
    }

    /**
     * Delete: remove o produto do estoque pelo codigo.
     *
     * @return true se o produto foi removido, false se o codigo informado
     *         nao existe no estoque.
     */
    public boolean remover(int codigo) {
        if (!estoque.containsKey(codigo)) {
            return false;
        }
        estoque.remove(codigo);
        return true;
    }

    /**
     * Metodo auxiliar para tratar o caso de borda de estoque vazio.
     */
    public boolean estaVazio() {
        return estoque.isEmpty();
    }

    public int total() {
        return estoque.size();
    }
}
