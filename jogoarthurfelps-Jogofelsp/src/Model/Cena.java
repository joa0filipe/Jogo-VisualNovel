package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cena {
    private String id;
    private List<Personagens> personagens;
    private int capitulo;
    private String nome_cena;
    private String texto;
    private List<Escolhas> opcoes;
    private boolean isFinal;//indica se a cena pode ser final ou não IDEIA;

    public Cena(String id, List<Personagens> personagens, String texto, String nomecena, int capitulo) {
        this.id = id;
        if (personagens != null) {
            this.personagens = personagens;
        } else {
            this.personagens = new ArrayList<>();
        }
        this.texto = texto;
        this.opcoes = new ArrayList<>();
        this.nome_cena = nomecena;
        this.capitulo = capitulo;
        this.isFinal = false;

    }

    public void addEscolha(Escolhas escolha) {
       if (escolha != null) {
            opcoes.add(escolha);
        }
    }


   public String getId() {
        return id;
    }

    public List<Personagens> getPersonagens() {
        return personagens;
    }
    public String getNome_cena() {
        return nome_cena;
    }
    public int getCapitulo () {
        return capitulo;
    }
    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean finalCena) {
        this.isFinal = finalCena;
    }

    public String getTexto() {
        return texto;
    }

    public List<Escolhas> getOpcoes() {
        return opcoes;
    }


    //Esse index vem do Controller , que recebe da view a entrada do usuario , ai retorna a escolha e o controller pega o next ID
    public Escolhas getEscolha(int index) {
        if (index >= 0 && index < opcoes.size()) {
            return opcoes.get(index);
        }
        return null;
    }



}
