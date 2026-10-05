package Controller;

import Loader.LoaderDeCenas;
import Model.Cena;
import Model.Protagonista;
import Model.Item;
import Model.EstadoDeJogo;
import Repository.JogoRepository;
import Repository.RepositorioDeCenas;
import Repository.RepositorioDeItens;
import Repository.RepositorioDePersonagens;

public class ServicosJogo {

    private RepositorioDeCenas repositorio;
    private Cena cenaAtual;
    private Protagonista prota;
    private RepositorioDeItens repositorioDeItens;
    private RepositorioDePersonagens repositorioDePersonagens;
    private EstadoDeJogo state;
    private JogoRepository staterepository;

    public ServicosJogo(RepositorioDeCenas repository, RepositorioDeItens repositorioDeItens, RepositorioDePersonagens repositorioDePersonagens, JogoRepository stateRepository) {
        this.repositorio = repository;
        this.repositorioDeItens = repositorioDeItens;
        this.cenaAtual = null;
        this.repositorioDePersonagens = repositorioDePersonagens;
        this.staterepository = stateRepository;
    }

    public void iniciarNovaPartida(String nomeJogador) {
        this.prota = new Protagonista(nomeJogador);
        LoaderDeCenas loader = new LoaderDeCenas(
                repositorio,
                repositorioDePersonagens,
                prota,
                repositorioDeItens
        );
        loader.createCena();

        this.cenaAtual = repositorio.getCena("C01_001");
        this.state = new EstadoDeJogo(cenaAtual.getId(), prota);
    }

    public Protagonista getProta() {
        return prota;
    }

    public Cena getCena(String id) {
        return repositorio.getCena(id);
    }

    public void setCenaAtual(String id) {
        this.cenaAtual = repositorio.getCena(id);
    }

    public Cena getCenaAtual() {
        return cenaAtual;
    }

    public String cenaToString() {
        if (cenaAtual == null) {
            return "Cena não encontrada.\n";
        }

        StringBuilder hudCena = new StringBuilder();
        hudCena.append("\n");
        hudCena.append("══════════════════════════════════════════════════════════════════════════════════\n");
        hudCena.append("Cena: ").append(cenaAtual.getNome_cena()).append("\n");
        hudCena.append("Capítulo: ").append(cenaAtual.getCapitulo()).append("\n");
        hudCena.append("══════════════════════════════════════════════════════════════════════════════════\n");
        hudCena.append(cenaAtual.getTexto()).append("\n\n");

        if (prota != null) {
            hudCena.append(" VIDA ")
                   .append(prota.getSaude())
                   .append(" | DINHEIRO R$ ")
                   .append(prota.getDinheiro())
                   .append(" | CHARME ")
                   .append(prota.getCharme())
                   .append(" | ASTÚCIA ")
                   .append(prota.getAstucia())
                   .append(" | FURTIVIDADE ")
                   .append(prota.getFurtividade()).append("\n");
                        hudCena.append("inventario  |  ");
                        for(Item i: prota.getInventario()){
                            if(i != null) {
                                hudCena.append(i.getNome()).append(" | ");
                            }
                        }
                        hudCena.append("\n");


        }

        hudCena.append("──────────────────────────────────────────────────────────────────────────────\n");

        for (int i = 0; i < cenaAtual.getOpcoes().size(); i++) {
            if (cenaAtual.getEscolha(i) != null) {
                if(cenaAtual.getEscolha(i).podeExecutarEscolha(prota)) {
                    hudCena.append("[").append(i + 1).append("] ").append(cenaAtual.getEscolha(i).getTexto()).append("\n");
                }
            }
        }
        hudCena.append("[6] Sair\n");
        hudCena.append("[0] Pausar jogo\n");
        hudCena.append("──────────────────────────────────────────────────────────────────────────────\n");
        hudCena.append("> Escolha uma opção: ");
        return hudCena.toString();
    }

    public StringBuilder textoTransicao(int index){
        StringBuilder texto = new StringBuilder();
        texto.append("""
                \n
                ════════════════════════════════════════════════════════════════════════\n""");
        texto.append(cenaAtual.getEscolha(index-1).getTransicaoTexto());
        texto.append("""
                \n
                ════════════════════════════════════════════════════════════════════════\n""");
        return texto;
    }

    public String processarEscolha(int index){
        if(index == 6){
            return "Sair";
        }
        if (index == 0) {
            return "Pausar";
        }
        if (index < 1 || index > 5) {
            return null;
        }
        if (cenaAtual != null){
            if(cenaAtual.getEscolha(index-1) != null) {

             if (cenaAtual.getEscolha(index - 1).podeExecutarEscolha(prota)) {
                cenaAtual.getEscolha(index -1).aplicarEfeitos(prota);
                 }
                else {
                    return null;
                }
        }else {
            return null;
        }

       return cenaAtual.getEscolha(index - 1).getIdProximoCap();

    }
        return null;
    }

    public void changeCena(String nextCena){
            if (nextCena == null || nextCena.isBlank()) {
                if (this.cenaAtual == null) {
                    this.cenaAtual = repositorio.getCena("C01_001");
                }
                return;
            }

            this.cenaAtual = repositorio.getCena(nextCena);
    }

    public void saveManual(){
        this.state.setCena(cenaAtual.getId());
        this.state.setProtagonista(prota);
        staterepository.salvarEstado(state);
    }



}
