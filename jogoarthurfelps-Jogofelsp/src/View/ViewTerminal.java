package View;

import java.util.Scanner;
import Controller.ServicosJogo;

public class ViewTerminal {
    private static final Scanner scanner = new Scanner(System.in);
    private final ServicosJogo servicosJogo;

    public ViewTerminal(ServicosJogo servicosJogo) {
        this.servicosJogo = servicosJogo;
    }

    public static void limparTela() {
        System.out.println("\n".repeat(50));
    }

    private int lerOpcao(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                String entrada = scanner.nextLine();

                if (entrada == null || entrada.isBlank()) {
                    System.out.println("Entrada vazia. Digite uma opção válida.");
                    continue;
                }

                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Use apenas números.");
            } catch (java.util.NoSuchElementException e) {
                System.out.println("\nEntrada encerrada. Encerrando o jogo.");
                System.exit(0);
                return -1;
            }
        }
    }

    private void aguardarEnter() {
        System.out.print("Pressione ENTER para continuar...");

        try {
            scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println();
        }
    }

    private void Saida(){
        limparTela();
        System.out.println("""
                        
                        ╔══════════════════════════════════════════════════════════════╗
                        ║                                                              ║
                        ║                  Obrigado por jogar!                         ║
                        ║                                                              ║
                        ║                       Até logo.                              ║
                        ║                                                              ║
                        ╚══════════════════════════════════════════════════════════════╝
                        
                        """);
    }

    public void inicio() {


        boolean executando = true;

        boolean noMenu = true;

        while (noMenu) {



            System.out.println("""
                ╔════════════════════════════════════════════════════╗
                ║                    BLACKGLASS                      ║
                ║               A MANSÃO DAS SOMBRAS                 ║
                ╚════════════════════════════════════════════════════╝
                ║                    [1] NOVO JOGO                   ║
                ║                                                    ║
                ║                    [2] INSTRUÇÕES                  ║
                ║                                                    ║
                ║                    [3] CRÉDITOS                    ║
                ║                                                    ║
                ║                    [4] SAIR                        ║
                """);

            int opcao = lerOpcao("\n> Opção: ");

            switch (opcao) {

                case 1 -> novoJogo();

                case 2 -> mostrarInstrucoes();

                case 3 -> mostrarCreditos();

                case 4 -> {
                    Saida();
                    noMenu = false;
                }

                default -> {
                    System.out.println("\nOpção inválida.");
                    pausar();
                }
            }
        }
    }

    public void novoJogo() {
        System.out.println("Antes de entrar na Mansão Ashcroft, antes de Greyhaven, antes de Blackglass mudar tudo... você tinha um nome.\n" +
                "Qual é o nome que carrega sua historia?");

        String nome = scanner.nextLine();
        if (nome == null || nome.isBlank()) {
            nome = "Aventureiro";
        }

        servicosJogo.iniciarNovaPartida(nome.trim());

        boolean jogando = true;

        while (jogando) {

            limparTela();

            if (servicosJogo.getCenaAtual() == null) {
                System.out.println("Cena não encontrada.");
                break;
            }

            System.out.print(
                    servicosJogo.cenaToString()
            );

            boolean executando = true;

            while (executando) {
                int opcao = lerOpcao("Insira opção: ");
                String resultado = servicosJogo.processarEscolha(opcao);

                if (resultado == null) {
                    System.out.println("Escolha inválida ou requisitos não atendidos.");
                    aguardarEnter();
                    continue;
                }

                if (resultado.equals("Sair")) {
                    jogando = false;
                    executando = false;
                    Saida();

                } else if (resultado.equals("Protagonista")) {
                    limparTela();
                    System.out.println(servicosJogo.getProta());
                    aguardarEnter();
                    limparTela();
                    System.out.print(servicosJogo.cenaToString());

                } else if (resultado.equals("Pausar")) {
                    pausar();
                    limparTela();
                    System.out.print(servicosJogo.cenaToString());

                } else {
                    limparTela();
                    System.out.print(servicosJogo.textoTransicao(opcao));
                    aguardarEnter();
                    servicosJogo.changeCena(resultado);
                    executando = false;
                }
            }
        }
    }

    public void pausar() {
        limparTela();
        System.out.println("""
                ╔══════════════════════════════════════════════════════════════════════╗
                ║                            JOGO PAUSADO                              ║
                ╠══════════════════════════════════════════════════════════════════════╣
                ║  [1] Continuar                                                       ║
                ║  [2] Sair da partida                                                 ║
                ╚══════════════════════════════════════════════════════════════════════╝
                """);

        while (true) {
            int opcao = lerOpcao("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> { return; }
                case 2 -> {
                    System.out.println("\nVocê saiu da partida.");
                    System.exit(0);
                    return;
                }
                default -> System.out.println("Opção de pausa inválida.");
            }
        }
    }

    public void mostrarCreditos() {

        limparTela();

        System.out.println("""
             
            ╔══════════════════════════════════════════════════════════════════════╗
            ║                             CRÉDITOS                                 ║
            ╠══════════════════════════════════════════════════════════════════════╣
            ║ Autores: Arthur Felipe Porto  e João Filipe Oliveira                 ║ 
            ║                                                                      ║ 
            ║  -Um projeto do EXA863 - MI - PROGRAMAÇÃO 2026.2                     ║ 
            ║                                                                      ║ 
            ║                                                                      ║ 
            ║                                                                      ║ 
            ║                                                                      ║ 
            ╚══════════════════════════════════════════════════════════════════════╝
            """);

        aguardarEnter();
    }

    public void mostrarInstrucoes() {

        limparTela();

        System.out.println("""
            
            ╔══════════════════════════════════════════════════════════════════════╗
            ║                            INSTRUÇÕES                                ║
            ╠══════════════════════════════════════════════════════════════════════╣
            ║                                                                      ║
            ║  Este é um jogo narrativo baseado em escolhas.                       ║
            ║                                                                      ║
            ║  Durante a história serão apresentadas diferentes opções.            ║
            ║  Digite o número correspondente à ação que deseja realizar.          ║
            ║                                                                      ║
            ║  Suas escolhas podem alterar:                                        ║
            ║                                                                      ║
            ║     • Dinheiro                                                       ║
            ║     • Charme                                                         ║
            ║     • Astúcia                                                        ║
            ║     • Furtividade                                                    ║
            ║     • Saúde                                                          ║
            ║     • Relação com os personagens                                     ║
            ║                                                                      ║
            ║  Algumas escolhas possuem requisitos mínimos de atributos.           ║
            ║                                                                      ║
            ║  Durante uma cena:                                                   ║
            ║                                                                      ║
            ║     [1], [2], [3]  → realizar uma escolha                            ║
            ║     [4]            → visualizar seus atributos                       ║
            ║     [5]            → sair da partida                                 ║
            ║                                                                      ║
            ╠══════════════════════════════════════════════════════════════════════╣
            ║                    Pressione ENTER para voltar                       ║
            ╚══════════════════════════════════════════════════════════════════════╝
            
            """);

        aguardarEnter();
    }
}
