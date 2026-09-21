package Testes;

import Model.Escolhas;
import Model.Item;
import Model.Protagonista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EscolhaTest {
    private Map<String, Integer> req;
    private Map<String, Integer> furtividade4;

    @BeforeEach
    public void Setup(){
        req = new HashMap<>();
        req.put("astucia", 4);

        furtividade4 = new HashMap<>();
        furtividade4.put("furtividade", 4);

    }


    @Test
    public void podeExecutarEscolhaRequerimentos() {
        Protagonista p = new Protagonista("Eve", 5,5,5);
        p.adicionarDinheiro(100);
        Item it = new Item("espada", "arma letal");

        Escolhas esc = new Escolhas("next","Fazer algo","Transição", it, req, 50, 0, 0, null, 0, null, null, furtividade4);
        assertTrue(esc.podeExecutarEscolha(p));

        p.retirarDinheiro(200);
        assertFalse(esc.podeExecutarEscolha(p));
    }

    @Test
    public void aplicarEfeitosAlteraProtagonista() {
        Protagonista p = new Protagonista("Frank",2,2,2);


        p.adicionarDinheiro(200);
        p.adicionarItem(new Item("Key","Chave"));

        Escolhas esc = new Escolhas("next","Texto","Tr", new Item("Key","Chave"), null, 50, -20, 30, "dante", 5, "Key", new Item("GoldCoin", "Descrição"), furtividade4);


        assertTrue(p.possuiItem("Key"));
        assertEquals(200, p.getDinheiro());
        assertEquals(0, p.getDanteLealdade());

        esc.aplicarEfeitos(p);


        assertEquals(180, p.getDinheiro()); // 200 - 50 + 30 = 180
        assertEquals(80, p.getSaude()); // 100 - 20 = 80
        assertEquals(5, p.getDanteLealdade());
        assertFalse(p.possuiItem("Key"));
        assertTrue(p.possuiItem("GoldCoin"));
        assertEquals(6, p.getFurtividade());





    }
}

