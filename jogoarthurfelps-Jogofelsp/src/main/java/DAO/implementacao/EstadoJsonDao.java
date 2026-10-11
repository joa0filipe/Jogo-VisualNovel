package DAO.implementacao;

import DAO.interfaces.estadoDAO;
import Model.EstadoDeJogo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class EstadoJsonDao implements estadoDAO {
    private Path arquivo;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Path getCaminho(int slot){
        if (slot < 1 || slot > 3) {
            return null;
        }else{
            switch(slot){
                case 1 -> {
                    return Path.of("jogoarthurfelps-Jogofelsp","saves","save1.json");
                }
                case 2 -> {
                    return Path.of("jogoarthurfelps-Jogofelsp","saves","save2.json");
                }
                case 3 -> {
                    return Path.of("jogoarthurfelps-Jogofelsp","saves","save3.json");
                }
            }
        }
        return null;
    }

    @Override
    public EstadoDeJogo getEstado(int slot) throws Exception {
        Path arquivo = getCaminho(slot);
        try (Reader reader = Files.newBufferedReader(arquivo)) {
            EstadoDeJogo state = gson.fromJson(reader, EstadoDeJogo.class);
            return state;
        }
    }

    @Override
    public void salvar(int slot, EstadoDeJogo state) throws Exception {
        Path arquivo = getCaminho(slot);

        if (arquivo == null) {
            throw new IllegalArgumentException("Slot inválido: " + slot);
        }

        try {
            Files.createDirectories(arquivo.getParent());

            try (Writer writer = Files.newBufferedWriter(arquivo)) {
                gson.toJson(state, writer);
            }

        } catch (IOException e) {
            throw new IOException("Erro ao salvar no slot " + slot, e);
        }

    }

    @Override
    public void limpar(int slot) throws IOException {
        Path arquivo = getCaminho(slot);
        if (arquivo == null) {
            throw new IllegalArgumentException("Slot inválido: " + slot);
        }

        try {
            Files.createDirectories(arquivo.getParent());

            try (Writer writer = Files.newBufferedWriter(arquivo)) {
                gson.toJson(null, writer);
            }

        } catch (IOException e) {
            throw new IOException("Erro ao limpar slot" + slot, e);
        }


    }

    @Override
    public boolean temSave(int slot) throws IOException {
        Path arquivo = getCaminho(slot);

        try (Reader reader = Files.newBufferedReader(arquivo)) {
            EstadoDeJogo state = gson.fromJson(reader, EstadoDeJogo.class);
            if(state == null) return false;
        }
        try {
            return arquivo != null
                    && Files.exists(arquivo)
                    && Files.size(arquivo) > 0;
        } catch (IOException e) {
            return false;
        }
    }


}



