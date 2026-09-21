package Repository;

import Model.Cena;

import java.util.HashMap;

public class RepositorioDeCenas {

    private HashMap<String, Cena> repositorioCenas;

    public RepositorioDeCenas() {
        repositorioCenas = new HashMap<>();
    }

    public void addCena(Cena cena){
        repositorioCenas.put(cena.getId(), cena);
    }
    public Cena getCena(String id){
        return repositorioCenas.get(id);
    }

    public HashMap<String, Cena> getRepositorioCenas() {
        return repositorioCenas;
    }


}
