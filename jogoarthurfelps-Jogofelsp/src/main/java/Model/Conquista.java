package Model;

public class Conquista {
    private String nome;
    private String descricao;
    private String criterio;
    private boolean desbloqueada;

    public Conquista(String nome, String descricao, String criterio) {
        this.nome = nome;
        this.descricao = descricao;
        this.criterio = criterio;
        this.desbloqueada = false;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isDesbloqueada() {
        return desbloqueada;
    }

    public String getCriterio() {
        return criterio;
    }

    public void desbloquear() {
        this.desbloqueada = true;
    }
}
