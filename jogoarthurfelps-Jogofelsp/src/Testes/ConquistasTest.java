import Controller.ServicosConquistas;
import DAO.implementacao.ConquistasJsonDao;
import DTOs.ConquistaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConquistasTest {
    @TempDir
    Path diretorioTemporario;

    @Test
    void criaCatalogoComSeisConquistasEQuatroFinais() throws Exception {
        ConquistasJsonDao dao = new ConquistasJsonDao(
                diretorioTemporario.resolve("conquistas.json"));
        ServicosConquistas servico = new ServicosConquistas(dao);

        assertEquals(6, dao.listarTodas().size());
        assertEquals(4, servico.getGaleriaFinais().size());
        assertTrue(servico.getConquistasDesbloqueadas().isEmpty());
    }

    @Test
    void desbloqueiaPorEventoEPreservaEstadoEmNovaInstanciaDoDao() throws Exception {
        Path arquivo = diretorioTemporario.resolve("conquistas.json");
        ServicosConquistas servico = new ServicosConquistas(new ConquistasJsonDao(arquivo));

        List<ConquistaDTO> liberadas = servico.registrarEvento("ESCOLHA_REALIZADA");

        assertEquals(1, liberadas.size());
        assertEquals("Primeiros passos", liberadas.getFirst().nome());
        assertEquals(1, servico.getConquistasDesbloqueadas().size());
        assertTrue(new ConquistasJsonDao(arquivo).estaDesbloqueada("primeira_escolha"));
        assertTrue(servico.registrarEvento("ESCOLHA_REALIZADA").isEmpty());
    }

    @Test
    void registraFinalEAtualizaGaleriaDeFinais() throws Exception {
        ServicosConquistas servico = new ServicosConquistas(new ConquistasJsonDao(
                diretorioTemporario.resolve("conquistas.json")));
        servico.registrarEvento("FINAL:C10_SEGREDO");

        List<ConquistaDTO> finais = servico.getGaleriaFinais();
        ConquistaDTO finalSecreto = finais.stream()
                .filter(finalJogo -> finalJogo.nome().equals("Quebrando o vidro"))
                .findFirst()
                .orElseThrow();

        assertTrue(finalSecreto.desbloqueada());
        assertEquals(1, finais.stream().filter(ConquistaDTO::desbloqueada).count());
        assertFalse(finais.stream()
                .filter(finalJogo -> finalJogo.nome().equals("O preço certo"))
                .findFirst()
                .orElseThrow()
                .desbloqueada());
    }
}
