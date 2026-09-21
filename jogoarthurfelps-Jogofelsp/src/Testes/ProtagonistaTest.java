package Testes;

import Model.Item;
import Model.Protagonista;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProtagonistaTest {

    @Test
    public void constructorDefaultsAndGetters() {
        Protagonista p = new Protagonista("Alice");
        assertEquals("Alice", p.getNome());
        assertEquals(100, p.getSaude());
        assertEquals(150, p.getDinheiro());
        assertEquals(2, p.getAtributo("astucia"));
        assertEquals(2, p.getAtributo("charme"));
        assertEquals(2, p.getAtributo("furtividade"));
    }

    @Test
    public void alterarAtributoAndSaudeBounds() {
        Protagonista p = new Protagonista("Bob", 3, 3, 3);
        p.alterarAtributo("astucia", 2);
        assertEquals(5, p.getAtributo("astucia"));

        p.modificarSaude(10); // already 100, should stay 100
        assertEquals(100, p.getSaude());

        p.modificarSaude(-200);
        assertEquals(0, p.getSaude());
    }

    @Test
    public void dinheiroOperations() {
        Protagonista p = new Protagonista("Charlie", 1,1,1);
        assertEquals(0, p.getDinheiro());
        p.adicionarDinheiro(50);
        assertEquals(50, p.getDinheiro());
        p.retirarDinheiro(100);
        assertEquals(0, p.getDinheiro());
    }

    @Test
    public void inventarioAddUseAndPossui() {
        Protagonista p = new Protagonista("Dana");
        Item potion = new Item("Potion","Cura 20");
        assertFalse(p.possuiItem("Potion"));
        p.adicionarItem(potion);
        assertTrue(p.possuiItem("Potion"));
        p.usarItem("Potion");
        assertFalse(p.possuiItem("Potion"));
    }
}

