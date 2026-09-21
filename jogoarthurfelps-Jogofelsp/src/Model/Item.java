package Model;

public class Item {
    private String nome;
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void usar(Protagonista protagonista) {
       if (protagonista == null) {
           return;
       }
       System.out.println("Item usado: " + nome + " - " + descricao);
    }
}
