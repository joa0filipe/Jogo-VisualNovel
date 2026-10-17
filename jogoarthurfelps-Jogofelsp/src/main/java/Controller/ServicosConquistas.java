package Controller;

import DAO.interfaces.conquistasDAO;
import DTOs.ConquistaDTO;
import Model.Conquista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicosConquistas {
    private final conquistasDAO dao;

    public ServicosConquistas(conquistasDAO dao) {
        this.dao = dao;
    }

    public List<ConquistaDTO> getConquistasDesbloqueadas() throws IOException {
        List<ConquistaDTO> desbloqueadas = new ArrayList<>();
        for (Conquista conquista : dao.listarTodas()) {
            if (dao.estaDesbloqueada(conquista.getId())) {
                desbloqueadas.add(toDTO(conquista, true));
            }
        }
        return List.copyOf(desbloqueadas);
    }

    public List<ConquistaDTO> getGaleriaFinais() throws IOException {
        List<ConquistaDTO> finais = new ArrayList<>();
        for (Conquista conquista : dao.listarTodas()) {
            if (conquista.getCriterio().startsWith("FINAL:")) {
                finais.add(toDTO(conquista, dao.estaDesbloqueada(conquista.getId())));
            }
        }
        return List.copyOf(finais);
    }

    public List<ConquistaDTO> registrarEvento(String evento) throws IOException {
        List<ConquistaDTO> novasConquistas = new ArrayList<>();
        for (Conquista conquista : dao.listarTodas()) {
            if (conquista.getCriterio().equals(evento)
                    && !dao.estaDesbloqueada(conquista.getId())) {
                dao.desbloquear(conquista.getId());
                novasConquistas.add(toDTO(conquista, true));
            }
        }
        return List.copyOf(novasConquistas);
    }

    private ConquistaDTO toDTO(Conquista conquista, boolean desbloqueada) {
        return new ConquistaDTO(conquista.getNome(), conquista.getDescricao(), desbloqueada);
    }
}
