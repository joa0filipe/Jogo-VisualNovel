package DAO.interfaces;

import Model.EstadoDeJogo;

import java.io.IOException;
import java.nio.file.Path;


public interface estadoDAO {



    EstadoDeJogo getEstado(int slot) throws IOException;

    void salvar(int slot, EstadoDeJogo state) throws IOException;

    void limpar(int slot) throws IOException;

    boolean temSave(int slot) throws IOException;

}
