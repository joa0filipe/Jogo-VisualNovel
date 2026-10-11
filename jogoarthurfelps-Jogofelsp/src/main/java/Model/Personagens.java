package Model;

public class Personagens {
    private String nome;
    private String descricao;

    public Personagens(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Personagens{"  +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +

                '}';
    }


}
