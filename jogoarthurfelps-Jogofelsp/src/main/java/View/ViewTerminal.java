package View;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import Controller.ServicosJogo;
import DTOs.ConquistaDTO;
import DTOs.EstadoJogoDTO;
import DTOs.PreferenciaDTO;

public class ViewTerminal {
    private static final Scanner scanner = new Scanner(System.in);
    private final ServicosJogo servicosJogo;
    private boolean encerrarAplicacao;


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
                System.out.println("\nEntrada encerrada. Fechando o jogo.");
                encerrarAplicacao = true;
                return -1;
            }
        }
    }

    private void aguardarEnter() {
        System.out.print("Pressione ENTER para continuar...");

        try {
            scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("\nEntrada encerrada. Fechando o jogo.");
            encerrarAplicacao = true;
        }
    }

    private int lerSlot(String mensagem) {
        while (!encerrarAplicacao) {
            int slot = lerOpcao(mensagem);
            if (encerrarAplicacao) {
                return -1;
            }
            if (slot >= 1 && slot <= 3) {
                return slot;
            }
            System.out.println("Slot inválido. Escolha um número de 1 a 3.");
        }
        return -1;
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

    public void inicio() throws Exception {
        while (!encerrarAplicacao) {

            System.out.println("""
                ╔════════════════════════════════════════════════════╗
                ║                    BLACKGLASS                      ║
                ║               A MANSÃO DAS SOMBRAS                 ║
                ╚════════════════════════════════════════════════════╝
                ║                    [1] JOGAR                       ║
                ║                                                    ║
                ║                    [2] INSTRUÇÕES                  ║
                ║                                                    ║
                ║                    [3] CRÉDITOS                    ║
                ║                                                    ║
                ║                    [4] CONFIGURAÇÕES               ║
                ║                                                    ║
                ║                    [5] CONQUISTAS                  ║
                ║                                                    ║
                ║                    [6] SAIR                        ║
                """);

            int opcao = lerOpcao("\n> Opção: ");
            if (encerrarAplicacao) {
                return;
            }

            switch (opcao) {

                case 1 -> menuSaves();

                case 2 -> mostrarInstrucoes();

                case 3 -> mostrarCreditos();
                case 4 -> menuPreferencias();
                case 5 -> mostrarConquistas();
                case 6 -> {
                    encerrarAplicacao();
                }

                default -> {
                    System.out.println("\nOpção inválida.");
                }
            }
        }
    }


    public boolean pausar() throws Exception {
        limparTela();
        System.out.println("""
                ╔══════════════════════════════════════════════════════════════════════╗
                ║                            JOGO PAUSADO                              ║
                ╠══════════════════════════════════════════════════════════════════════╣
                ║  [1] Continuar                                                       ║
                ║  [2] Sair da partida                                                 ║
                ║  [3] Salvar partida                                                  ║
                ╚══════════════════════════════════════════════════════════════════════╝
                """);
        while (!encerrarAplicacao) {
            int opcao = lerOpcao("Escolha uma opção > ");
            if (encerrarAplicacao) {
                return false;
            }

            switch (opcao) {
                case 1 -> { return true; }
                case 2 -> { return false; }
                case 3 -> {
                    servicosJogo.saveManual();
                    System.out.println("Save Concluído!");
                    //System.out.print(servicosJogo.acessarSave(save).toString());
                }
                default -> System.out.println("Opção inválida.");
            }
        }
        return false;
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
            ║     [1] - [5]      → realizar uma escolha                            ║
            ║     [6]            → sair                                            ║
            ║     [0]            → pausar                                          ║
            ║                                                                      ║
            ╠══════════════════════════════════════════════════════════════════════╣
            ║                    Pressione ENTER para voltar                       ║
            ╚══════════════════════════════════════════════════════════════════════╝
            
            """);

        aguardarEnter();
    }

    public void mostrarConquistas() throws IOException {
        limparTela();
        System.out.println("""
                ╔══════════════════════════════════════════════════════════════╗
                ║                     GALERIA DE CONQUISTAS                    ║
                ╚══════════════════════════════════════════════════════════════╝
                """);

        List<ConquistaDTO> conquistas = servicosJogo.getConquistasDesbloqueadas();
        if (conquistas.isEmpty()) {
            System.out.println("Nenhuma conquista desbloqueada ainda.");
        } else {
            for (ConquistaDTO conquista : conquistas) {
                System.out.printf("%n🏆 %s%n   %s%n", conquista.nome(), conquista.descricao());
            }
        }

        List<ConquistaDTO> finais = servicosJogo.getGaleriaFinais();
        long finaisAlcancados = finais.stream().filter(ConquistaDTO::desbloqueada).count();
        System.out.printf("%nFINAIS ALCANÇADOS: %d/%d%n", finaisAlcancados, finais.size());
        for (ConquistaDTO finalJogo : finais) {
            String estado = finalJogo.desbloqueada() ? "ALCANÇADO" : "NÃO ALCANÇADO";
            System.out.printf("%n[%s] %s%n   %s%n",
                    estado, finalJogo.nome(), finalJogo.descricao());
        }
        aguardarEnter();
    }

    private void mostrarNovasConquistas() {
        for (ConquistaDTO conquista : servicosJogo.consumirConquistasRecentes()) {
            System.out.printf("%n🏆 Conquista desbloqueada: %s — %s%n",
                    conquista.nome(), conquista.descricao());
        }
    }

    public void formatarSlot(int slot) throws Exception {
        EstadoJogoDTO save = servicosJogo.getMetaDadosSave(slot);

        System.out.printf("%n  ┌──────────────────── SLOT %02d ────────────────────┐%n", slot);

        if (save == null) {
            System.out.println("  │  [ VAZIO ]");
            System.out.println("  │  Nenhuma partida salva.");
        } else {
            System.out.println("  │  [ OCUPADO ]");
            System.out.printf("  │  Jogador  : %s%n", save.nomeJogador());
            System.out.printf("  │  Capítulo : %s%n", save.capitulo());
            System.out.printf("  │  Cena     : %s%n", save.nomeCena());
            System.out.printf("  │  Data     : %s%n", save.dataSalvamento());
        }

        System.out.println("  └─────────────────────────────────────────────────┘");
    }

    public void menuSaves() throws Exception {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("""
                
                ╔══════════════════════════════════════════════════════════════╗
                ║                           INÍCIO                             ║
                ╠══════════════════════════════════════════════════════════════╣
                ║  [1] Continuar partida                                       ║
                ║  [2] Carregar save                                           ║
                ║  [3] Novo jogo                                               ║
                ║  [4] Excluir save                                            ║
                ║  [0] Voltar                                                  ║
                ╠══════════════════════════════════════════════════════════════╣
                ║                      SAVES DISPONÍVEIS                       ║
                ╚══════════════════════════════════════════════════════════════╝
                """);

        for (int slot = 1; slot <= 3; slot++) {
            formatarSlot(slot);
        }
        while (!encerrarAplicacao) {

            int opcao = lerOpcao("\n> Opção: ");
            if (encerrarAplicacao) {
                return;
            }

            switch (opcao) {

                case 1 -> {
                    if (continuarPartida()) {
                        return;
                    }
                    aguardarEnter();
                }

                case 2 -> {
                    int save = lerSlot("Qual Save deseja carregar? \n> Save: ");
                    if (encerrarAplicacao) {
                        return;
                    }
                    if(!servicosJogo.temSave(save)) {
                        System.out.print("Nenhum jogo encontrado no save "+save);
                        aguardarEnter();
                        limparTela();
                    }else{
                        carregarSave(save);
                        return;
                    }
                }

                case 3 -> {
                    int save = lerSlot("Em qual Save deseja salvar seu novo jogo? \n> Save: ");
                    if (encerrarAplicacao) {
                        return;
                    }
                    if(!servicosJogo.temSave(save)){
                        novoJogo(save);
                        return;
                    }else{
                        System.out.print("Esse slot já possui um jogo salvo:\n");
                        formatarSlot(save);
                        int escolha = lerOpcao("""
                          Deseja excluir esse jogo e iniciar um novo?
                          [1] Sim                                                     
                          [0] Voltar  
                          >                                                                     
                        """);
                        if (encerrarAplicacao) {
                            return;
                        }


                        switch (escolha){
                            case 1 -> {
                                limparTela();
                                servicosJogo.Reescrita(save);
                                novoJogo(save);
                                return;
                            }
                            case 0 -> {
                                limparTela();
                            }
                        }
                    }
                }
                case 4 ->{
                    int save = lerSlot("Qual Save deseja excluir? \n> Save: ");
                    if (encerrarAplicacao) {
                        return;
                    }
                    if(servicosJogo.temSave(save)){
                        servicosJogo.Reescrita(save);
                        System.out.print("Save excluido com sucesso!:\n");
                        aguardarEnter();
                    }else {
                        System.out.print("Esse slot não possui nenhum jogo salvo:\n");
                        aguardarEnter();
                    }
                }
                case 0 -> {
                    return;
                }

                default -> {
                    System.out.println("\nOpção inválida.");
                }
            }
        }

    }

    public void jogando() throws Exception {
        boolean jogando = true;

        while (jogando && !encerrarAplicacao) {

            limparTela();

            if (servicosJogo.getCenaAtual() == null) {
                System.out.println("Cena não encontrada.");
                break;
            }

            System.out.print(
                    servicosJogo.cenaToString()
            );

            boolean executando = true;

            while (executando && !encerrarAplicacao) {
                int opcao = lerOpcao("Insira opção: ");
                if (encerrarAplicacao) {
                    return;
                }
                String resultado = servicosJogo.processarEscolha(opcao);
                mostrarNovasConquistas();

                if (resultado == null) {
                    System.out.println("Escolha inválida ou requisitos não atendidos.");
                    aguardarEnter();
                    continue;
                }

                if (resultado.equals("Sair")) {
                    jogando = false;
                    executando = false;
                    encerrarAplicacao();
                }
                else if (resultado.equals("Pausar")) {
                    if (pausar()) {
                        limparTela();
                        System.out.print(servicosJogo.cenaToString());
                    } else {
                        jogando = false;
                        executando = false;
                        if (!encerrarAplicacao) {
                            System.out.println("Partida encerrada. Voltando ao menu principal.");
                        }
                    }

                } else {
                    limparTela();
                    System.out.print(servicosJogo.textoTransicao(opcao));
                    aguardarEnter();
                    if (encerrarAplicacao) {
                        return;
                    }
                    servicosJogo.changeCena(resultado);
                    mostrarNovasConquistas();
                    executando = false;
                }
            }
        }

    }

    public void novoJogo(int save) throws Exception {
        System.out.println("Antes de entrar na Mansão Ashcroft, antes de Greyhaven, antes de Blackglass mudar tudo... você tinha um nome.\n" +
                "Qual é o nome que carrega sua historia?");

        String nome;
        try {
            nome = scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("\nEntrada encerrada. Fechando o jogo.");
            encerrarAplicacao = true;
            return;
        }
        if (nome == null || nome.isBlank()) {
            nome = "Aventureiro";
        }

        servicosJogo.iniciarNovaPartida(nome.trim(),save);
        jogando();
    }

    public void carregarSave(int save) throws Exception {
        limparTela();
        System.out.print("Entrando no save " +save);

        servicosJogo.carregarPartida(save);
        jogando();
    }

    public boolean continuarPartida() throws Exception {
        int save = servicosJogo.continuarPartida();

        if(save != -1){
            servicosJogo.carregarPartida(save);
            jogando();
            return true;
        }else{
            System.out.print("Crie um novo jogo antes de continuar partida!");
            return false;
        }
    }

    public void menuPreferencias() throws Exception {
        while (!encerrarAplicacao) {

            List<PreferenciaDTO> preferencias =
                    servicosJogo.getInfoPreferencias();

            System.out.println("""

                ════════════════════════════════════════
                       CONFIGURAÇÕES DO JOGO
                ════════════════════════════════════════
                """);

            for (int i = 0; i < preferencias.size(); i++) {

                PreferenciaDTO p = preferencias.get(i);

                String estado = p.ligado() ? "Ligado" : "Desligado";

                System.out.printf("[%d] %s [%s]%n",
                        i + 1,
                        p.titulo(),
                        estado);

                System.out.println("    -" + p.descricao());
                System.out.println();
            }

            System.out.println("────────────────────────────────────────");
            System.out.println("[0] Voltar");
            System.out.println("────────────────────────────────────────");
            int opcao = lerOpcao("Deseja alterar alguma opção? Qual? ");
            if (encerrarAplicacao) {
                return;
            }

            if (opcao == 0) {
                return;

            } else if (opcao >= 1 && opcao <= preferencias.size()) {
               servicosJogo.alterarPreferencia(opcao - 1);

            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void encerrarAplicacao() {
        if (!encerrarAplicacao) {
            encerrarAplicacao = true;
            Saida();
        }
    }

}
