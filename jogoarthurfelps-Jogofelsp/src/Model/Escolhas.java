package Model;

import java.util.Map;

public class Escolhas {
    private String idProximoCap;
    private String Texto;
    private Item itemRequerido;
    private Map<String, Integer> atributoRequerido;
    private String transicaoTexto;
    private int custoDinheiro;
    private int danoOucura;
    private int modificadorDinheiro;
    private String personagemAfinidade;
    private int difAfinidade;
    private Item itemConsumido;
    private Item itemGanho;
    private Map<String, Integer> atributosAlterados;



    public Escolhas(String idProximoCap, String texto, String transicaoTexto, Item itemRequerido, Map<String, Integer> atributoRequerido, int custoDinheiro, int danoOuCura, int modificadorDinheiro, String personagemAfinidade, int difAfinidade, Item itemConsumido, Item itemGanho, Map<String, Integer> atributosAlterados) {
        this.idProximoCap = idProximoCap;
        this.Texto = texto;
        this.itemRequerido = itemRequerido;
        this.atributoRequerido = atributoRequerido;
        this.transicaoTexto = transicaoTexto;
        this.custoDinheiro = custoDinheiro;
        this.danoOucura = danoOuCura;
        this.modificadorDinheiro = modificadorDinheiro;
        this.personagemAfinidade = personagemAfinidade;
        this.difAfinidade = difAfinidade;
        this.itemConsumido = itemConsumido;
        this.itemGanho = itemGanho;
        this.atributosAlterados = atributosAlterados;
    }

    public Escolhas(String idProximoCap, String texto, String transicaoTexto) {
        this.idProximoCap = idProximoCap;
        this.Texto = texto;
        this.transicaoTexto = transicaoTexto;
    }

    public String getIdProximoCap() {
        return idProximoCap;
    }


    public boolean podeExecutarEscolha(Protagonista protagonista) {
        if (protagonista == null) {
            return false;
        }

        if(this.itemRequerido != null && !protagonista.possuiItem(this.itemRequerido.getNome())){
            return false;
        }

        if (this.custoDinheiro > 0 && protagonista.getDinheiro() < this.custoDinheiro) {
            return false;
        }
        if (this.atributoRequerido != null && !this.atributoRequerido.isEmpty()) {
            for (Map.Entry<String, Integer> entry : this.atributoRequerido.entrySet()) {

                String atributo = entry.getKey();
                int valorNecessario = entry.getValue();

                if (protagonista.getAtributo(atributo) < valorNecessario) {
                    return false;
                }
            }
        }

        return true;
    }

    public void aplicarEfeitos(Protagonista protagonista) {
        if (protagonista == null) {
            return;
        }
        if (this.custoDinheiro > 0) {
            protagonista.retirarDinheiro(this.custoDinheiro);
        }
        if (danoOucura != 0) {
            protagonista.modificarSaude(danoOucura);
        }
        if (modificadorDinheiro != 0) {
            protagonista.modificarDinheiro(modificadorDinheiro);
        }
        if (personagemAfinidade != null && difAfinidade != 0) {
            protagonista.modificarAfinidade(personagemAfinidade, difAfinidade);
        }
        if (itemConsumido != null) {
            protagonista.retirarItem(itemConsumido);
        }
        if (itemGanho != null) {
            protagonista.adicionarItem(itemGanho);
        }
        if (this.atributosAlterados != null && !this.atributosAlterados.isEmpty()) {
            for (Map.Entry<String, Integer> entry : this.atributosAlterados.entrySet()) {

                String atributoGanho = entry.getKey();
                int valorGanho = entry.getValue();

                protagonista.alterarAtributo(atributoGanho, valorGanho);
            }
        }
    }

    public String getTexto() {
        if (Texto != null) {
            return Texto;
        }
        return "";
    }

    public Item getItemRequerido() {
        if (itemRequerido != null) {
            return itemRequerido;
        }
        return null;
    }

    public String getTransicaoTexto() {
        if (transicaoTexto != null) {
            return transicaoTexto;
        }
        return "";
    }

    public Map<String, Integer> getAtributoRequerido() {
        if (atributoRequerido != null) {
            return atributoRequerido;
        }
        return null;
    }

    public String getRequisitos() {
        StringBuilder sb = new StringBuilder();

        if (custoDinheiro > 0) {
            sb.append("Dinheiro: ").append(custoDinheiro).append(" | ");
        }

        if (itemRequerido != null) {
            sb.append("Item: ").append(itemRequerido).append(" | ");
        }

        if (atributoRequerido != null && !atributoRequerido.isEmpty()) {
            sb.append("Atributos: ").append(atributoRequerido);
        }

        return sb.toString();
    }


    @Override
    public String toString() {
        return Texto + '\'' +
                ", itemRequerido=" + itemRequerido +
                ", atributoRequerido=" + atributoRequerido +
                ", transicaoTexto='" + transicaoTexto + '\'' +
                '}';
    }
}
