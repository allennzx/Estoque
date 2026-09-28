package br.edu.aesa.model;

/**
 * Classe de modelo (entidade) que representa um Produto no estoque.
 * O atributo "codigo" atua como identificador unico do produto.
 */
public class Produto {

    private int codigo;
    private String nome;
    private String categoria;
    private double preco;
    private int quantidade;

    public Produto(int codigo, String nome, String categoria, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return String.format(
                "Codigo: %d | Nome: %s | Categoria: %s | Preco: R$ %.2f | Quantidade: %d",
                codigo, nome, categoria, preco, quantidade
        );
    }
}
