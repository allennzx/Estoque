# Desafio Prático: CRUD com Estruturas de Dados em Java

Projeto desenvolvido para o desafio prático de Estrutura de Dados, com um
sistema de **Controle de Estoque de Produtos** implementado em Java.

> 🔗 **Link da Apresentação em Vídeo:** [...]

---

## Sumário

- [Tema escolhido](#tema-escolhido)
- [Estrutura de dados escolhida e justificativa](#estrutura-de-dados-escolhida-e-justificativa)
- [Estrutura de pastas](#estrutura-de-pastas)
- [Como compilar e executar](#como-compilar-e-executar)
- [Funcionalidades do menu](#funcionalidades-do-menu)
- [Tratamento de casos de borda](#tratamento-de-casos-de-borda)

---

## Tema escolhido

**Controle de Estoque de Produtos.** Cada produto possui código (ID único),
nome, categoria, preço e quantidade em estoque.

## Estrutura de dados escolhida e justificativa

Foi utilizado um **`HashMap<Integer, Produto>`**, do pacote `java.util`,
onde a chave é o código do produto e o valor é o próprio objeto `Produto`.

**Por que HashMap?**

- O código do produto é único, então ele se encaixa naturalmente como
  chave de um mapa.
- As operações de busca, inserção, atualização e remoção por código têm
  complexidade média de **O(1)**, o que é ideal para um sistema em que a
  operação mais frequente é localizar um produto específico pelo código.
- O domínio não exige que os produtos fiquem ordenados automaticamente,
  então o custo extra de uma `TreeMap` (O(log n)) não se justificaria.

**Desvantagem:** o `HashMap` não garante nenhuma ordem de iteração. Isso
não é um problema para este cenário, mas seria um ponto de atenção caso o
requisito fosse sempre listar os produtos em ordem de cadastro ou de
código.

## Estrutura de pastas

```
CRUD-Estoque/
├── README.md
└── src/
    └── br/edu/aesa/
        ├── model/
        │   └── Produto.java        # Classe de entidade
        ├── service/
        │   └── EstoqueService.java # Classe do CRUD (encapsula o HashMap)
        └── app/
            └── Main.java           # Classe principal com o menu
```

## Como compilar e executar

Pelo terminal, na raiz do projeto (`CRUD-Estoque/`):

```bash
# Compilar
javac -d bin $(find src -name "*.java")

# Executar
java -cp bin br.edu.aesa.app.Main
```

## Funcionalidades do menu

```
[1] Cadastrar novo produto
[2] Listar todos os produtos
[3] Buscar produto por código
[4] Atualizar produto
[5] Remover produto
[0] Sair do programa
```

## Tratamento de casos de borda

- **Estoque vazio:** ao listar, buscar ou remover em um estoque vazio, o
  sistema informa que não há produtos cadastrados/encontrados, em vez de
  gerar erro.
- **ID duplicado:** o cadastro é bloqueado se já existir um produto com o
  mesmo código, com mensagem explicativa.
- **ID inexistente:** busca, atualização e remoção informam claramente
  quando o código informado não é encontrado.
- **Entradas inválidas:** valores não numéricos digitados onde se espera
  número (código, preço, quantidade) são tratados com `try/catch`, sem
  derrubar o programa.
