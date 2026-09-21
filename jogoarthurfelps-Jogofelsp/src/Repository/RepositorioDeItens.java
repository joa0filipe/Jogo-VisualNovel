package Repository;
import Model.Item;

import java.util.HashMap;

public class RepositorioDeItens {

    private HashMap<String, Item> itens;

    public RepositorioDeItens(){
        this.itens = new HashMap<>();
    }
    public void addItem(String id, Item item) {
        itens.put(id, item);
    }
    public Item getItem(String id) {
        return itens.get(id);
    }

}
