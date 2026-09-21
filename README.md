# Blackglass: A Mansão das Sombras

Um jogo de escolhas em Java, com estilo de visual novel/aventura, desenvolvido para a disciplina de programação. O projeto combina narrativa interativa, decisões estratégicas e progressão por cenas, com atributos do protagonista, relacionamentos e itens que influenciam o rumo da história.

## Visão geral

Você assume o papel de um protagonista anônimo que entra em uma missão arriscada na mansão Ashcroft, em Londres. O objetivo principal é alcançar o artefato conhecido como Blackglass, uma joia negra rara e extremamente valiosa. No entanto, a operação não é simples: câmeras, guardas, armadilhas e segredos do passado começam a se interligar a cada decisão.

O jogo apresenta:

- menu principal com opções de início, instruções, créditos e saída;
- criação de personagem com nome personalizado;
- cenas narrativas com textos e descrições imersivas;
- escolhas com impactos em atributos como astúcia, charme e furtividade;
- relacionamento com personagens importantes;
- aquisição e uso de itens durante a trajetória;
- múltiplos caminhos e finais dependendo das escolhas do jogador.

## Estrutura do projeto

```text
Jogo-VisualNovel/
├── README.md
├── .gitignore
├── jogoarthurfelps-Jogofelsp/
│   ├── src/
│   ├── jogoarthurfelps.iml
│   └── .idea/
└── ...
```

O código-fonte principal está na pasta `jogoarthurfelps-Jogofelsp/src`.

## Etapa atual de desenvolvimento

O projeto está em uma etapa funcional de desenvolvimento, com uma primeira versão jogável da narrativa. O fluxo principal já está implementado: o jogador acessa o menu, informa o nome do protagonista, percorre as cenas, realiza escolhas e pode pausar ou encerrar a partida.

Atualmente, o foco está na consolidação da Fase 1 e na expansão da experiência. A base de cenas, personagens, itens, atributos e consequências já existe, mas o projeto ainda pode evoluir com:

- mais capítulos, cenas e finais alternativos;
- revisão e balanceamento dos requisitos das escolhas;
- aprimoramento do sistema de morte, derrota e encerramento da história;
- persistência de partidas salvas;
- melhorias na interface do terminal e na acessibilidade;
- ampliação da cobertura de testes automatizados.

Em resumo, o jogo já funciona como um protótipo jogável de visual novel textual, mas ainda está em desenvolvimento para se tornar uma experiência narrativa mais completa.

## Ferramentas e tecnologias utilizadas

- **Java**: linguagem principal do jogo;
- **Java JDK 17 ou superior**: compilação e execução;
- **IntelliJ IDEA**: organização do projeto, edição e execução durante o desenvolvimento;
- **JUnit**: criação de testes para classes como protagonista, cenas e escolhas;
- **Git**: controle de versão do código;
- **GitHub**: hospedagem do repositório e colaboração;
- **Terminal/console**: interface utilizada pelo jogador para navegar pelos menus e selecionar as opções.

A implementação segue uma organização por responsabilidades, separando modelo, serviços de controle, carregamento de dados, repositórios e visualização no terminal.

## História em destaque

A trama gira em torno da mansão Ashcroft, uma residência antiga e extremamente protegida, que guarda um objeto misterioso chamado Blackglass. A pedra, em formato de olho, é descrita como uma relíquia rara, envolta em lendas, ambição e perigos.

O protagonista é convocado para uma missão de infiltração ao lado de Dante, um parceiro que parece saber muito mais sobre o objeto do que revela. A operação começa como uma simples aventura de roubo, mas rapidamente se transforma em uma disputa de estratégia, memória, confiança e sobrevivência.

À medida que o jogador atravessa os corredores, salas e galeria da mansão, descobre que a estrutura foi preparada para esconder mais do que uma peça valiosa. Há mecanismos ocultos, sensores sofisticados, passagens pouco conhecidas e uma sensação constante de que alguém queria que a missão falhasse.

O enredo vai além do roubo: há tensão entre a confiança no parceiro e a necessidade de agir sozinho; há mistério sobre o que Blackglass realmente representa; e há o peso de cada escolha, que pode levar o protagonista a um sucesso audacioso ou a uma derrota humilhante.

Cada cena pede uma decisão: correr, manipular, agir com inteligência, seduzir, se esconder ou arriscar tudo. A narrativa é construída para que o jogador sinta que a história não é apenas um conjunto de textos, mas um caminho vivo, em que cada ação altera o curso dos acontecimentos.

## Personagens principais

- Protagonista: o jogador, nomeado ao iniciar o jogo.
- Dante: parceiro e guia da operação, estratégico e descontraído, mas com segredos.
- Victor: figura ligada aos conflitos e à história da mansão.
- Julian: personagem envolvido em dinâmicas de tensão e pressão.
- Miller e Ward: nomes ligados ao ambiente de ameaça e ação.

## Mecânicas do jogo

### Atributos

O protagonista possui atributos que influenciam as decisões:

- astúcia;
- charme;
- furtividade;
- afinidade com personagens.

### Itens

O jogo também apresenta itens como:

- sinal de Dante;
- mapa de manutenção;
- chave improvisada;
- cartão de acesso;
- fragmentos e artefatos relacionados a Blackglass.

Esses itens podem ser necessários para desbloquear caminhos e respostas em cenas específicas.

## Como executar

A partir da raiz do repositório, abra a pasta do projeto Java:

```bash
cd jogoarthurfelps-Jogofelsp
```

Compile os arquivos Java:

```bash
javac -d out $(find src -name "*.java")
```

Execute o jogo:

```bash
java -cp out Main
```

Se estiver usando IntelliJ IDEA ou outra IDE Java, basta abrir a pasta `jogoarthurfelps-Jogofelsp` como projeto e rodar a classe `Main`.

## Requisitos

- Java JDK 17 ou superior
- IDE Java opcional (IntelliJ IDEA, Eclipse, VS Code com suporte Java)

## Créditos

### Autores

- Arthur Felipe Porto
- João Filipe Oliveira

Os autores são responsáveis pela criação e desenvolvimento do projeto, incluindo a programação em Java, a estrutura da narrativa, a implementação das cenas e escolhas, os personagens, os itens e os testes iniciais.

### Quem está fazendo o projeto

O projeto está sendo desenvolvido pelos mesmos autores listados acima, em colaboração durante a implementação e evolução do jogo. As contribuições futuras podem ser incorporadas por meio de issues e pull requests no GitHub.

## Como você pode ajudar

Há várias formas de contribuir com o projeto:

1. **Narrativa**: sugerir novos capítulos, personagens, diálogos, escolhas e finais.
2. **Programação**: implementar novas cenas, itens, atributos, mecânicas e sistemas de salvamento.
3. **Testes**: criar testes para validar escolhas, requisitos, inventário, afinidades e transições entre cenas.
4. **Revisão**: identificar erros de texto, inconsistências na história ou problemas na experiência do jogador.
5. **Interface**: melhorar a apresentação do terminal, a navegação dos menus e as mensagens de erro.
6. **Documentação**: atualizar o README e registrar novas mecânicas ou instruções.

Para contribuir, crie uma branch para sua alteração, faça uma mudança específica, teste o comportamento e abra um pull request descrevendo o que foi implementado.

## Observação

Este jogo representa uma experiência de narrativa interativa em fase inicial, com foco em escolhas, cenários e estrutura de RPG textual. O visual é terminal-based, com forte ênfase em história e decisões do jogador.
