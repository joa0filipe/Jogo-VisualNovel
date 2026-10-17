package Model;

import java.util.ArrayList;
import java.util.List;

public class Configuracoes {


    public static List<Preferencia> criarPreferenciasPadrao() {

        List<Preferencia> preferencias = new ArrayList<>();

        preferencias.add(new Preferencia("Autosave", "0","Salvar jogo automaticamente", true));

        preferencias.add(new Preferencia("Visualização de escolhas","1","Permite visualizar todas as escolhas que poderiam ser feitas", false));

        preferencias.add(new Preferencia("Visualização de escolhas","2","Permite visualizar todas as escolhas que poderiam ser feitas", false));
        return preferencias;
    }
}
