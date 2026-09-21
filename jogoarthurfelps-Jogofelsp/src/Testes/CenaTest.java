package Testes;

import Model.Cena;
import Model.Escolhas;
import Model.Personagens;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CenaTest {

    @Test
    public void constructorAndGettersAndEscolhas() {
        List<Personagens> lista = new ArrayList<>();
        lista.add(new Personagens("NPC","Um npc"));
        Cena cena = new Cena("c1", lista, "Texto da cena", "NomeCena", 1);
        assertEquals("c1", cena.getId());
        assertEquals(1, cena.getCapitulo());
        assertEquals("NomeCena", cena.getNome_cena());
        assertEquals("Texto da cena", cena.getTexto());
        assertFalse(cena.isFinal());
        Escolhas e = new Escolhas("next","Escolha","Trans");
        cena.addEscolha(e);
        assertEquals(1, cena.getOpcoes().size());
        assertEquals(e, cena.getEscolha(0));
        assertNull(cena.getEscolha(5));

        cena.setFinal(true);
        assertTrue(cena.isFinal());
    }
}

