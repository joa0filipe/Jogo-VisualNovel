package DAO.interfaces;

import Model.Conquista;

import java.io.IOException;
import java.util.List;

public interface conquistasDAO {
    List<Conquista> listarTodas() throws IOException;

    boolean estaDesbloqueada(String id) throws IOException;

    void desbloquear(String id) throws IOException;
}
