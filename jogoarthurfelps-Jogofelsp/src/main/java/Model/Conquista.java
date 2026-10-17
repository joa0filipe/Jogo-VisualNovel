package Model;

public class Conquista {
    private String id;
    private String nome;
    private String descricao;
    private String criterio;
    private boolean desbloqueada;

    public Conquista(String id, String nome, String descricao, String criterio, boolean desbloqueada) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.criterio = criterio;
        this.desbloqueada = desbloqueada;
    }

    public String getId() {
        return id;
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
