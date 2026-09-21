package Repository;

import Model.Personagens;

import java.util.HashMap;

public class RepositorioDePersonagens {

    private HashMap<String, Personagens> personagens;

    public RepositorioDePersonagens() {
        personagens = new HashMap<>();
    }

    public void addPersonagem(String id, Personagens personagem) {
        personagens.put(id, personagem);
    }

    public Personagens getPersonagem(String id) {
        return personagens.get(id);
    }

    public HashMap<String, Personagens> getPersonagens() {
        return personagens;
    }
}